package guru.springframework.springaiintro.spingaibasic.services;

import guru.springframework.springaiintro.spingaibasic.model.Answer;
import guru.springframework.springaiintro.spingaibasic.model.GetCapitalRequest;
import guru.springframework.springaiintro.spingaibasic.model.GetCapitalResponse;
import guru.springframework.springaiintro.spingaibasic.model.Question;

public interface OpenAIService {
    String getAnswer(String question);

    Answer getAnswer(Question question);

    GetCapitalResponse getCapital(GetCapitalRequest getCapitalRequest);

    Answer getCapitalWithFormattedInfo(GetCapitalRequest getCapitalRequest);
}


