package guru.springframework.springaiintro.springaiaudio.services;

import guru.springframework.springaiintro.springaiaudio.model.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.stereotype.Service;

import static org.springframework.ai.openai.api.OpenAiAudioApi.TtsModel.TTS_1;

@Service
@Slf4j
@RequiredArgsConstructor
public class SpeechServiceImpl implements SpeechService{

    private final OpenAiAudioSpeechModel speechModel;

    @Override
    public byte[] getSpeechOutput(Question question) {

        OpenAiAudioSpeechOptions openAiAudioSpeechOptions = OpenAiAudioSpeechOptions.builder()
                .voice(OpenAiAudioApi.SpeechRequest.Voice.NOVA)
                .speed(1.0f)
                .responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
                .model(TTS_1.getValue())
                .build();
        SpeechPrompt speechPrompt = new SpeechPrompt(question.question(),openAiAudioSpeechOptions);

        SpeechResponse speechResponse = speechModel.call(speechPrompt);

        return speechResponse.getResult().getOutput();
    }
}
