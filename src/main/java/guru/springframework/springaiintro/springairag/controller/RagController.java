package guru.springframework.springaiintro.springairag.controller;

import guru.springframework.springaiintro.springairag.model.Answer;
import guru.springframework.springaiintro.springairag.model.Question;
import guru.springframework.springaiintro.springairag.services.RagService;
import guru.springframework.springaiintro.springairag.services.RagServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RagController {

    private final RagService ragService;

    @PostMapping("/document")
    public Answer getAnswerFromDocument(@RequestBody Question question){
       return ragService.getAnswer(question);
    }
}
