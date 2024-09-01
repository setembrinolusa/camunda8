package poc.com.camunda.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import poc.com.camunda.Picture;
import poc.com.camunda.PictureResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class PicturesWebClient {

    @Autowired
    private final WebClient webClient;

    public List<Picture> getAllPicturess() {
        return webClient.get().uri("/pictures").retrieve().bodyToMono(PictureResponse.class)
                .map(PictureResponse::getPictures).block();
    }

    public Mono<Picture> sendPictureDetails(String animalType, String path) {

        Picture picture = new Picture("", animalType, "", "", path, null, "", 0L);

        Mono<Picture> pictureMono = webClient.post().uri("/pictures/find-and-save")
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(picture), Picture.class).retrieve().bodyToMono(Picture.class);

        return pictureMono;

    }

}