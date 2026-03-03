package guru.springframework.springaiintro.ragboatexpert.services;

import guru.springframework.springaiintro.ragboatexpert.model.Answer;
import guru.springframework.springaiintro.ragboatexpert.model.Question;

public interface RagBoatExpertService {

    public Answer getAnswer(Question question);
}
