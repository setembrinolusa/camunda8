package poc.com.camunda;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.worker.JobWorker;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProvider;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProviderBuilder;

@SpringBootApplication
public class Application {

    @Value("${zeebe.client.address}")
    private static String zeebeClientAddress;

    @Value("${zeebe.client.id}")
    private static String zeebeClientId;

    @Value("${zeebe.client.secret}")
    private static String zeebeClientSecret;

    @Value("${zeebe.authorization.server.url}")
    private static String zeebeAuthorizationServerUrl;

    @Value("${zeebe.client.token.audiente}")
    private static String zeebeClientTokenAudiente;

    public static void main(String[] args) {

        final OAuthCredentialsProvider credentialsProvider = new OAuthCredentialsProviderBuilder()
                .authorizationServerUrl(zeebeAuthorizationServerUrl).audience(zeebeClientTokenAudiente)
                .clientId(zeebeClientId).clientSecret(zeebeClientSecret).build();

        try (final ZeebeClient client = ZeebeClient.newClientBuilder().gatewayAddress(zeebeClientAddress)
                .credentialsProvider(credentialsProvider).build()) {

            final JobWorker pictureWorker = client.newWorker().jobType("savePicture")
                    .handler(new PictureServiceHandler()).timeout(Duration.ofSeconds(10).toMillis()).open();
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
