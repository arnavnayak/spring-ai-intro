package guru.springframework.springaiintro.springaibasic.services;

import guru.springframework.springaiintro.spingaibasic.services.OpenAIServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpenAIServiceImplTest {

    @Autowired
    OpenAIServiceImpl openAIService;

    @Test
    void getAnswerTest() {
        String response = openAIService.getAnswer("What is life?");
        System.out.println(response);
        assertNotNull(response);
    }
}