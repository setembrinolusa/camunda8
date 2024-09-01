package poc.com.camunda.core.usecases.impl;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import poc.com.camunda.adapters.jpa.models.Picture;
import poc.com.camunda.adapters.jpa.repositories.PictureRepository;
import poc.com.camunda.core.domain.entities.PictureEntity;
import poc.com.camunda.core.exceptions.ResourceNotFoundException;
import poc.com.camunda.core.usecases.PictureUseCase;
import poc.com.camunda.core.usecases.mappers.PictureUcMapper;
import poc.com.camunda.core.usecases.utils.ImageUtils;

@Service
public class PictureUseCaseImpl implements PictureUseCase {

    @Autowired
    private PictureRepository repo;

    @Autowired
    private PictureUcMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(PictureUseCaseImpl.class);

    public byte[] getFile(String id) {
        Optional<Picture> picture = Optional
                .of(repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Picture", "id", id)));

        return picture.map(image -> {
            try {
                return ImageUtils.decompressImage(image.getData());
            } catch (DataFormatException | IOException exception) {
                throw new ContextedRuntimeException("Error downloading an image", exception).addContextValue("Image ID",
                        image.getId());
            }
        }).orElse(null);
    }

    public Stream<Picture> getAllFiles() {
        return repo.findAll().stream();
    }

    public PictureEntity saveImage(MultipartFile file) {
        logger.info("Creating a Picture");

        PictureEntity picture = null;

        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            picture = new PictureEntity(null, fileName, file.getContentType(),
                    ImageUtils.compressImage(file.getBytes()));
            picture = mapper.repoToModel(repo.save(mapper.modelToRepo(picture)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return picture;
    }

}
