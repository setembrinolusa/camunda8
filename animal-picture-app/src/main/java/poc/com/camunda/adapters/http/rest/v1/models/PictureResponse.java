package poc.com.camunda.adapters.http.rest.v1.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PictureResponse implements Serializable {

    private static final long serialVersionUID = 4331181472716298764L;

    @JsonProperty("animalType")
    private String animalType;

    @JsonProperty("name")
    private String name;
    
    @JsonProperty("url")
    private String url;

    @JsonProperty("type")
    private String type;

    @JsonProperty("path")
    private String path;

    @JsonProperty("size")
    private long size;

}
