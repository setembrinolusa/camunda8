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
public class StudentResponse implements Serializable {

	private static final long serialVersionUID = -7614990327890009781L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("name")
    private String name;

}
