package guru.springframework.springaiintro.springaiaudio.services;


import guru.springframework.springaiintro.springaiaudio.model.Question;

public interface SpeechService {

    public byte[] getSpeechOutput(Question question);
}
