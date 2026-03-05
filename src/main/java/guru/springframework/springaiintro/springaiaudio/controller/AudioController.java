package guru.springframework.springaiintro.springaiaudio.controller;

import guru.springframework.springaiintro.springaiaudio.services.SpeechService;
import guru.springframework.springaiintro.springaiaudio.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;

@RestController
@RequiredArgsConstructor
public class AudioController {

    private final SpeechService speechService;

    @PostMapping(value = "/speech" , produces = "audio/mpeg")
    public byte[] getSpeechOutput(@RequestBody Question question){
        return speechService.getSpeechOutput(question);
    }
}
