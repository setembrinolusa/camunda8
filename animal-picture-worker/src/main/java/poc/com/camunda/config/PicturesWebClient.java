package poc.com.camunda.config;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
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

    public static final int BITE_SIZE = 4 * 1024;

    public List<Picture> getAllPicturess() {
        return webClient.get().uri("/pictures").retrieve().bodyToMono(PictureResponse.class)
                .map(PictureResponse::getPictures).block();
    }

    public MultipartFile getPicture(String animalType, String imageUrl) throws Exception {
        URL url = new URL(imageUrl);
        BufferedImage image = ImageIO.read(url);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        String fileName = animalType + new Date().getTime() + ".jpg";
        MultipartFile multipartFile = new MockMultipartFile(fileName, fileName, "image/jpg",
                byteArrayOutputStream.toByteArray());
        byteArrayOutputStream.close(); // Close once it is done saving
        return multipartFile;
    }

    public Mono<String> uploadAndSavePicture(String animalType, String uri, byte[] fileBytes) {

        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("file", fileBytes).header("Content-Disposition", "form-data; name=file; filename=" + uri);

        System.out.println(webClient.toString());

        Mono<String> result = webClient.post().uri("/pictures/upload")
                .body(BodyInserters.fromMultipartData(bodyBuilder.build())).retrieve().bodyToMono(String.class);
        return result;
    }

}