package poc.com.camunda.core.usecases;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import poc.com.camunda.adapters.jpa.models.Picture;
import poc.com.camunda.core.domain.entities.PictureEntity;

@Service
public interface PictureUseCase {

    public byte[] getFile(String id);

    public Stream<Picture> getAllFiles();

    public PictureEntity saveImage(String animalType, String path, MultipartFile file);

}
