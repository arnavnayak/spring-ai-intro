package guru.springframework.springaiintro.springaiimage.services;

import guru.springframework.springaiintro.springaiimage.model.Question;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    public byte[] getImage(Question question);

    public String getImageDescription(MultipartFile file);
}
