package guru.springframework.springaiintro.spingaibasic.controllers;

import guru.springframework.springaiintro.spingaibasic.model.Answer;
import guru.springframework.springaiintro.spingaibasic.model.GetCapitalRequest;
import guru.springframework.springaiintro.spingaibasic.model.GetCapitalResponse;
import guru.springframework.springaiintro.spingaibasic.model.Question;
import guru.springframework.springaiintro.spingaibasic.services.OpenAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    private final OpenAIService openAIService;


    public QuestionController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/capital")
    public GetCapitalResponse getCapital(@RequestBody GetCapitalRequest getCapitalRequest){
        return this.openAIService.getCapital(getCapitalRequest);
    }

    @PostMapping("/capitalWithInfo")
    public Answer getCapitalWithFormattedInfo(@RequestBody GetCapitalRequest getCapitalRequest){
        return this.openAIService.getCapitalWithFormattedInfo(getCapitalRequest);
    }

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question){
        return openAIService.getAnswer(question);
    }
}
