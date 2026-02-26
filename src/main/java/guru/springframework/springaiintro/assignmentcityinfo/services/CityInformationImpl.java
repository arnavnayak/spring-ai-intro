package guru.springframework.springaiintro.assignmentcityinfo.services;

import guru.springframework.springaiintro.assignmentcityinfo.model.GetCityRequest;
import guru.springframework.springaiintro.assignmentcityinfo.model.GetCityResponse;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@Service
public class CityInformationImpl implements CityInformation{

    private final ChatModel chatModel;

    public CityInformationImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Value("classpath:templates/get-city-with-info.st")
    Resource cityPrompt;

    @Override
    public String getCityDetails(GetCityRequest cityRequest) {
        BeanOutputConverter<GetCityResponse> converter = new BeanOutputConverter<>(GetCityResponse.class);
        String format = converter.getFormat();

        PromptTemplate promptTemplate = new PromptTemplate(cityPrompt);
        Prompt prompt = promptTemplate.create(Map.of("city",cityRequest.cityName(),"format",format));
        ChatResponse chatResponse = chatModel.call(prompt);
        return chatResponse.getResult().getOutput().getText();
    }
}
