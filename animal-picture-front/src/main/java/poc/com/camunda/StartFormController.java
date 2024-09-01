package poc.com.camunda;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.camunda.zeebe.client.ZeebeClient;

@RestController
@RequestMapping("/")
public class StartFormController {

    @Autowired
    private ZeebeClient zeebe;

    private static final Logger LOG = LoggerFactory.getLogger(StartFormController.class);

    @PostMapping("/start")
    public void startProcessInstance(@RequestBody Map<String, Object> variables) {

        LOG.info("Starting process `" + ProcessConstants.BPMN_PROCESS_ID + "` with variables: " + variables);

        zeebe.newCreateInstanceCommand().bpmnProcessId(ProcessConstants.BPMN_PROCESS_ID).latestVersion()
                .variables(variables).send();
    }
}
