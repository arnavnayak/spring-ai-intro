package guru.springframework.springaiintro.springairag.services;

import guru.springframework.springaiintro.springairag.model.Answer;
import guru.springframework.springaiintro.springairag.model.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class RagServiceImpl implements RagService{

    private final ChatModel chatModel;
    private final SimpleVectorStore simpleVectorStore;

    @Value("classpath:/templates/rag-prompt-template-metadata.st")
    private Resource getRagPrompt;

    @Override
    public Answer getAnswer(Question question) {
        List<Document> documents = simpleVectorStore.similaritySearch(SearchRequest.builder()
                .query(question.question())
                .topK(5)
                .build());
        List<String> contentList = documents.stream().map(Document::getFormattedContent).toList();

        contentList.forEach(System.out::println);

        PromptTemplate promptTemplate = new PromptTemplate(getRagPrompt);
        Prompt prompt = promptTemplate
                .create(Map.of("input",question,"documents",String.join("\n",contentList)));
        ChatResponse chatResponse = chatModel.call(prompt);
        return new Answer(chatResponse.getResult().getOutput().getText());
    }
}
