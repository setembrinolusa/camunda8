package poc.com.camunda;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;

@SpringBootApplication
@EnableZeebeClient
public class Application {

    @Autowired
    private ZeebeClient zeebe;

    @Autowired
    PictureService pictureService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @JobWorker(type = "savePicture", autoComplete = false)
    public void savePicture(final ActivatedJob job) {
        try {

            final Map<String, Object> inputVariables = job.getVariablesAsMap();
            final String animalType = (String) inputVariables.get("animalType");

            final String confirmation = pictureService.sendPictureDetails(animalType);

            final Map<String, Object> outputVariables = new HashMap<String, Object>();
            outputVariables.put("confirmation", confirmation);

            zeebe.newCompleteCommand(job.getKey()).variables(outputVariables).send().join();
            // Thread.sleep(10000);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
