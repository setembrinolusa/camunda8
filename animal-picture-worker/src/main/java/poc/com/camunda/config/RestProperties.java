package poc.com.camunda.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(value = "spring.backend")
public class RestProperties {

    private String host;

}