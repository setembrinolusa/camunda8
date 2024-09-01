package poc.com.camunda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
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

    @Autowired
    private final PicturesWebClient webClient;

    public String uploadAndSavePicture(String animalType, String path) throws Exception {

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

        MultipartFile multipartFile = webClient.getPicture(animalType, path);

        System.out.println("------------------");
        System.out.println("chamando app animalType=" + animalType);
        System.out.println("------------------");
        webClient.uploadAndSavePicture(animalType, path, multipartFile.getBytes());

        final String confirmation = String.valueOf(System.currentTimeMillis());
        System.out.println("Successful Transaction: " + confirmation);
        return confirmation;
    }
}