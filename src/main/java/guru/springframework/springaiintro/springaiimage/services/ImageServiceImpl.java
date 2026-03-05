package guru.springframework.springaiintro.springaiimage.services;

import guru.springframework.springaiintro.springaiimage.model.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.content.Media;
import org.springframework.ai.image.ImageMessage;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{

    private final OpenAiImageModel openAiImageModel;

    private final ChatModel chatModel;


    @Override
    public byte[] getImage(Question question) {

        OpenAiImageOptions imageOptions = OpenAiImageOptions.builder()
                .height(1024)
                .width(1024)
                .responseFormat("b64_json")
                .model("dall-e-3")
                .quality("hd")
                .style("natural")
                .build();

        String message = new PromptTemplate(question.question()).createMessage().getText();

        ImagePrompt imagePrompt = new ImagePrompt(message,imageOptions);

        ImageResponse imageResponse = openAiImageModel.call(imagePrompt);

        return Base64.getDecoder().decode(imageResponse.getResult().getOutput().getB64Json());
    }

    @Override
    public String getImageDescription(MultipartFile file) {
        OpenAiChatOptions openAiChatOptions = OpenAiChatOptions.builder()
                .model(OpenAiApi.ChatModel.GPT_4_O.getValue())
                .build();
        var userMessage = UserMessage.builder()
                .text("Explain what do you see in this picture?")
                .media(new Media(MimeTypeUtils.IMAGE_JPEG,file.getResource()))
                .build();

        ChatResponse chatResponse = chatModel.call(new Prompt(List.of(userMessage),openAiChatOptions));
        return chatResponse.getResult().getOutput().getText();
    }
}
