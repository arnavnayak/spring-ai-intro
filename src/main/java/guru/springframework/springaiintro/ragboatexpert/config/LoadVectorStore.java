package guru.springframework.springaiintro.ragboatexpert.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class LoadVectorStore implements CommandLineRunner {

    @Autowired
    VectorStore vectorStore;

    @Autowired
    RagBoatExpertVectorStoreProperties ragBoatExpertVectorStoreProperties;

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading vector store....");

        if(vectorStore.similaritySearch("Sportsman").isEmpty()){ //add ! in the if condition to store things in the vector store
            log.info("Loading documents into vector store");
            ragBoatExpertVectorStoreProperties.getDocumentsOfTowVehicleToLoad().forEach(document-> {
                log.info("loading document : "+document.getFilename());
                TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(document);
                List<Document> docs = tikaDocumentReader.get();
                TextSplitter textSplitter = new TokenTextSplitter();
                List<Document> splitDocs = textSplitter.apply(docs);
                vectorStore.add(splitDocs);
            });

        }
        log.info("Vector store loaded");
    }
}
