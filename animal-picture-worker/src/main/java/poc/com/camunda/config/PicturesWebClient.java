package poc.com.camunda.config;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import poc.com.camunda.Picture;
import poc.com.camunda.PictureResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class PicturesWebClient {

    private final WebClient webClient;

    public List<Picture> getAllPicturess() {
        return webClient.get().uri("/pictures").retrieve().bodyToMono(PictureResponse.class)
                .map(PictureResponse::getPictures).block();
    }

    public byte[] getPicture(String uri) {
        return WebClient.create(uri).get().accept(MediaType.IMAGE_JPEG).retrieve().bodyToMono(byte[].class).block();
    }

    public Mono<String> uploadAndSavePicture(String animalType, String uri, byte[] fileBytes) {

        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("file", fileBytes).header("Content-Disposition", "form-data; name=file; filename=" + uri);

        return webClient.post().uri("/pictures/upload").body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .retrieve().bodyToMono(String.class);
    }

}