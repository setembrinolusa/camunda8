package poc.com.camunda;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.api.worker.JobHandler;

public class PictureServiceHandler implements JobHandler {

    @Autowired
    PictureService pictureService;

    @Override
    public void handle(JobClient client, ActivatedJob job) throws Exception {

        final Map<String, Object> inputVariables = job.getVariablesAsMap();
        final String animalType = (String) inputVariables.get("animalType");

        String path = "";
        if (animalType.equalsIgnoreCase("dog")) {
            path = "https://place.dog/300/200";
        } else if (animalType.equalsIgnoreCase("bear")) {
            path = "https://placebear.com/200/300";
        } else {
            path = "https://api.thecatapi.com/v1/images/search";
        }

        final String confirmation = pictureService.uploadAndSavePicture(animalType, path);

        final Map<String, Object> outputVariables = new HashMap<String, Object>();
        outputVariables.put("confirmation", confirmation);

        client.newCompleteCommand(job.getKey()).variables(outputVariables).send().join();
    }
}