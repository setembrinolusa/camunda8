package poc.com.camunda;

import java.time.Duration;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.worker.JobWorker;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProvider;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProviderBuilder;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        
        final OAuthCredentialsProvider credentialsProvider =
                new OAuthCredentialsProviderBuilder()
                    .authorizationServerUrl(System.getenv("ZEEBE_AUTHORIZATION_SERVER_URL"))
                    .audience(System.getenv("ZEEBE_TOKEN_AUDIENCE"))
                    .clientId(System.getenv("ZEEBE_CLIENT_ID"))
                    .clientSecret(System.getenv("ZEEBE_CLIENT_SECRET"))
                    .build();
     
        try (final ZeebeClient client =
                ZeebeClient.newClientBuilder()
                    .gatewayAddress(System.getenv("ZEEBE_ADDRESS"))
                    .credentialsProvider(credentialsProvider)
                    .build()) {
            
//            final Map<String, Object> variables = new HashMap<String, Object>();
//            variables.put("reference", "C8_12345");
//            variables.put("animalType", "CAT");
//            variables.put("name", "cat.jpg");
//            variables.put("path", "pictures/");
//            
//            client.newCreateInstanceCommand()
//            .bpmnProcessId("pictures-process")
//            .latestVersion()
//            .variables(variables)
//            .send()
//            .join();
            
            final JobWorker creditCardWorker =
                    client.newWorker()
                        .jobType("savePicture")
                        .handler(new PictureServiceHandler())
                        .timeout(Duration.ofSeconds(10).toMillis())
                        .open();
                Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
