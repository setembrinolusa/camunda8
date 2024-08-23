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
public class CourseResponse implements Serializable {

	private static final long serialVersionUID = 4331181472716298764L;

	@JsonProperty("ïd")
	private Long id;
	
	@JsonProperty("name")
    private String name;

}
