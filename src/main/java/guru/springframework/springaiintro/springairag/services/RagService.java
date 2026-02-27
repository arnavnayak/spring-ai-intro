package guru.springframework.springaiintro.springairag.services;

import guru.springframework.springaiintro.springairag.model.Answer;
import guru.springframework.springaiintro.springairag.model.Question;

public interface RagService {

    public Answer getAnswer(Question question);
}
