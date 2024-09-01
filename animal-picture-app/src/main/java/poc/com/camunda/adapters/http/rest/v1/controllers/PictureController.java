package poc.com.camunda.adapters.http.rest.v1.controllers;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import poc.com.camunda.adapters.http.rest.v1.models.PictureResponse;
import poc.com.camunda.adapters.http.rest.v1.models.ResponseMessage;
import poc.com.camunda.core.usecases.PictureUseCase;

@RestController
@RequestMapping("/api")
public class PictureController {

    @Autowired
    private PictureUseCase useCase;

    private static final Logger logger = LoggerFactory.getLogger(PictureController.class);

    @GetMapping("/pictures")
    public ResponseEntity<List<PictureResponse>> getListFiles() {
        List<PictureResponse> pictures = useCase.getAllFiles().map(picture -> {
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/pictures/")
                    .path(picture.getId()).toUriString();

            return new PictureResponse(picture.getId(), picture.getAnimalType(), picture.getName(), fileDownloadUri,
                    picture.getType(), picture.getData().length);

        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(pictures);
    }

    @GetMapping("/pictures/{id}")
    public ResponseEntity<?> getFile(@PathVariable String id) {

        byte[] imageData = useCase.getFile(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(IMAGE_PNG_VALUE)).body(imageData);
    }

    @PostMapping("/pictures/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("animalType") String animalType,
            @RequestParam("path") String path, @RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            System.out.println("------------------");
            System.out.println("Chamando saving picture animalType=" + animalType);
            System.out.println("------------------");
            useCase.saveImage(animalType, path, file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
