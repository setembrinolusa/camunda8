package poc.com.camunda.adapters.http.rest.v1.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import poc.com.camunda.adapters.jpa.models.Course;
import poc.com.camunda.adapters.jpa.models.Student;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequest implements Serializable {

	private static final long serialVersionUID = 6599204680379166090L;

	@JsonProperty("course")
	private Course course;

	@JsonProperty("student")
    private Student student;

}
