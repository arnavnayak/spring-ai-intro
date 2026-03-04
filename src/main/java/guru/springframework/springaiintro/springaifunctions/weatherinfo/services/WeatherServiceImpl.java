package guru.springframework.springaiintro.springaifunctions.weatherinfo.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.springaiintro.springaifunctions.weatherinfo.functions.WeatherServiceFunction;
import guru.springframework.springaiintro.springaifunctions.weatherinfo.model.Answer;
import guru.springframework.springaiintro.springaifunctions.weatherinfo.model.Question;
import guru.springframework.springaiintro.springaifunctions.weatherinfo.model.WeatherRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherServiceImpl implements WeatherService{

    @Value("${sfg.aiapp.apininjakey}")
    private String apiNinjaKey;

    @Value("classpath:/templates/weather-info-system-message.st")
    private Resource weatherSystemMessage;

    @Value("classpath:/templates/location-info-system-message.st")
    private Resource locationSystemMessage;

    private final OpenAiChatModel openAiChatModel;

    @Override
    public Answer getWeatherInfo(Question question) {

        //pre-process the query to get latitude and longitude of the place
        try {
            String locationCoordinates = getLatitudeAndLongitudeInfo(question);
            log.info("location coordinates received from openAI : {}",locationCoordinates);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(locationCoordinates);
            String latitude = jsonNode.get("latitude").asText();
            String longitude = jsonNode.get("longitude").asText();
        }catch (Exception e){
            log.error("failed to parse json result of coordinates error : {}", String.valueOf(e));
        }

        var promptOptions = OpenAiChatOptions.builder()
                .toolCallbacks(List.of(FunctionToolCallback.builder("CurrentWeather", new WeatherServiceFunction(apiNinjaKey))
                        .description("Get the current weather for a location")
                        .inputType(WeatherRequest.class)
                        .build()))
                .build();

        Message userMessage = new PromptTemplate(question.question()).createMessage();

        Message systemMessage = new SystemPromptTemplate(weatherSystemMessage).createMessage();

        var response = openAiChatModel.call(new Prompt(List.of(userMessage,systemMessage),promptOptions));

        return new Answer(response.getResult().getOutput().getText());
    }

    private String getLatitudeAndLongitudeInfo(Question question) {
        Message userMessage = new PromptTemplate(question.question()).createMessage();

        Message systemMessage = new SystemPromptTemplate(locationSystemMessage).createMessage();

        var response = openAiChatModel.call(new Prompt(List.of(userMessage,systemMessage)));

        return response.getResult().getOutput().getText();
    }
}
