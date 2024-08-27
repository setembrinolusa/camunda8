package poc.com.camunda;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProvider;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProviderBuilder;

@RestController
@RequestMapping("/")
public class StartFormRestController {

    @Value("${zeebe.client.address}")
    private String zeebeClientAddress;

    @Value("${zeebe.client.id}")
    private String zeebeClientId;

    @Value("${zeebe.client.secret}")
    private String zeebeClientSecret;

    @Value("${zeebe.authorization.server.url}")
    private String zeebeAuthorizationServerUrl;

    @Value("${zeebe.client.token.audiente}")
    private String zeebeClientTokenAudiente;

    private static final Logger LOG = LoggerFactory.getLogger(StartFormRestController.class);

    @PostMapping("/start")
    public void startProcessInstance(@RequestBody Map<String, Object> variables) {

        LOG.info("Starting process `" + ProcessConstants.BPMN_PROCESS_ID + "` with variables: " + variables);

        final OAuthCredentialsProvider credentialsProvider = new OAuthCredentialsProviderBuilder()
                .authorizationServerUrl(zeebeAuthorizationServerUrl).audience(zeebeClientTokenAudiente)
                .clientId(zeebeClientId).clientSecret(zeebeClientSecret).build();

        try (final ZeebeClient client = ZeebeClient.newClientBuilder().gatewayAddress(zeebeClientAddress)
                .credentialsProvider(credentialsProvider).build()) {
            client.newCreateInstanceCommand().bpmnProcessId(ProcessConstants.BPMN_PROCESS_ID).latestVersion()
                    .variables(variables).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
