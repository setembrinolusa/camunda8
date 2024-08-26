package poc.com.camunda;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import poc.com.camunda.config.PicturesWebClient;

@Service
@RequiredArgsConstructor
public class PictureService {

    private final PicturesWebClient webClient;

    public String uploadAndSavePicture(String animalType, String path) {

        System.out.println("Animal Type: " + animalType);
        System.out.println("Path: " + path);

        if (animalType.equalsIgnoreCase("cat")) {

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode;

            try {
                rootNode = mapper.readTree(WebClient.builder().baseUrl(path).build().get().retrieve().toString());

                path = rootNode.findValue("url").asText();

            } catch (JsonMappingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        var pictureBytes = webClient.getPicture(path);

        webClient.uploadAndSavePicture(animalType, path, pictureBytes);

        final String confirmation = String.valueOf(System.currentTimeMillis());
        System.out.println("Successful Transaction: " + confirmation);
        return confirmation;
    }
}