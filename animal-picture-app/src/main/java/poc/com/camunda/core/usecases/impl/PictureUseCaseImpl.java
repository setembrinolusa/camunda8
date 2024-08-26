package poc.com.camunda.core.usecases.impl;

import java.io.IOException;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import poc.com.camunda.adapters.jpa.models.Picture;
import poc.com.camunda.adapters.jpa.repositories.PictureRepository;
import poc.com.camunda.core.domain.entities.PictureEntity;
import poc.com.camunda.core.usecases.PictureUseCase;
import poc.com.camunda.core.usecases.mappers.PictureUcMapper;

@Service
public class PictureUseCaseImpl implements PictureUseCase {

    @Autowired
    private PictureRepository repo;

    @Autowired
    private PictureUcMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(PictureUseCaseImpl.class);

    public Picture getFile(String id) {
        return repo.findById(id).get();
    }

    public Stream<Picture> getAllFiles() {
        return repo.findAll().stream();
    }

    public PictureEntity saveImage(String animalType, String path, MultipartFile file) {
        logger.info("Creating a Picture");

        PictureEntity picture = null;

        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            picture = new PictureEntity(null, animalType, fileName, file.getContentType(), path, file.getBytes());
            picture = mapper.repoToModel(repo.save(mapper.modelToRepo(picture)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return picture;
    }

}
