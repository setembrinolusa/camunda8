package poc.com.camunda;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.worker.JobWorker;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProvider;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProviderBuilder;

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
            
            final Map<String, Object> variables = new HashMap<String, Object>();
            variables.put("reference", "C8_12345");
            variables.put("amount", Double.valueOf(100.00));
            variables.put("cardNumber", "1234567812345678");
            variables.put("cardExpiry", "12/2023");
            variables.put("cardCVC", "999");
            
            client.newCreateInstanceCommand()
            .bpmnProcessId("paymentProcess")
            .latestVersion()
            .variables(variables)
            .send()
            .join();
            
            final JobWorker creditCardWorker =
                    client.newWorker()
                        .jobType("chargeCreditCard")
                        .handler(new CreditCardServiceHandler())
                        .timeout(Duration.ofSeconds(10).toMillis())
                        .open();
                Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
