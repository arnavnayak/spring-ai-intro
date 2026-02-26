package guru.springframework.springaiintro.spingaibasic.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.springaiintro.spingaibasic.model.Answer;
import guru.springframework.springaiintro.spingaibasic.model.GetCapitalRequest;
import guru.springframework.springaiintro.spingaibasic.model.GetCapitalResponse;
import guru.springframework.springaiintro.spingaibasic.model.Question;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    @Autowired
    ObjectMapper objectMapper;

    private final ChatModel chatModel;

    public OpenAIServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public Answer getAnswer(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        ChatResponse chatResponse = chatModel.call(prompt);
        return new Answer(chatResponse.getResult().getOutput().getText());
    }

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:templates/get-capital-with-info.st")
    private Resource getGetCapitalPromptWithFormatterInfo;

    @Override
    public GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest) {
        //instead of doing the below use the @Value to get template from resources
        //PromptTemplate promptTemplate = new PromptTemplate("What is the capital of " + getCapitalRequest.stateOrCountry() + "?");

        BeanOutputConverter<GetCapitalResponse> converter = new BeanOutputConverter<>(GetCapitalResponse.class);
        String format = converter.getFormat();
        //to see whether the format is json format or not
        //System.out.println("format: \n"+ format);

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry",getCapitalRequest.stateOrCountry(),"format",format));
        ChatResponse chatResponse = chatModel.call(prompt);

        //to see how does the json after
        //System.out.println(chatResponse.getResult().getOutput().getText());

        return converter.convert(Objects.requireNonNull(chatResponse.getResult().getOutput().getText()));
    }

    @Override
    public Answer getCapitalWithFormattedInfo(GetCapitalRequest getCapitalRequest) {
        PromptTemplate promptTemplate = new PromptTemplate(getGetCapitalPromptWithFormatterInfo);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry",getCapitalRequest.stateOrCountry()));
        ChatResponse chatResponse = chatModel.call(prompt);
        return new Answer(chatResponse.getResult().getOutput().getText());
    }

    @Override
    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        ChatResponse chatResponse = chatModel.call(prompt);
        return chatResponse.getResult().getOutput().getText();
    }
}
