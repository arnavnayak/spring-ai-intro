package guru.springframework.springaiintro.ragboatexpert.controller;

import guru.springframework.springaiintro.ragboatexpert.model.Answer;
import guru.springframework.springaiintro.ragboatexpert.model.Question;
import guru.springframework.springaiintro.ragboatexpert.services.RagBoatExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RagBoatExpertController {

    private final RagBoatExpertService ragBoatExpertService;

    @PostMapping("/boat")
    public Answer getAnswerFromDocument(@RequestBody Question question){
       return ragBoatExpertService.getAnswer(question);
    }
}
