package poc.com.camunda;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PictureService {

    @Autowired
    private final WebClient webClient;

    public List<Picture> getAllPicturess() {
        return webClient.get().uri("/pictures").retrieve().bodyToMono(PictureResponse.class)
                .map(PictureResponse::getPictures).block();
    }

    public String sendPictureDetails(String animalType) throws Exception {

        Picture picture = new Picture("", animalType, "", "", findPath(animalType), "", 0L);

        PictureResponse responseEntity = webClient.post().uri("/pictures/find-and-save")
                .contentType(MediaType.APPLICATION_JSON).bodyValue(picture).retrieve().bodyToMono(PictureResponse.class)
                .block();

        final String confirmation = String.valueOf(System.currentTimeMillis());
        return confirmation;
    }

    public String findPath(String animalType) throws Exception {

        String path = "";
        if (animalType.equalsIgnoreCase("dog")) {
            path = "https://place.dog/300/200";
        } else if (animalType.equalsIgnoreCase("bear")) {
            path = "https://placebear.com/200/300";
        } else if (animalType.equalsIgnoreCase("cat")) {
            path = "https://api.thecatapi.com/v1/images/search";
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode;
            rootNode = mapper.readTree(WebClient.builder().baseUrl(path).build().get().exchange()
                    .block()
                    .bodyToMono(String.class)
                    .block());
            path = rootNode.findValue("url").asText();
        }
        return path;
    }

}