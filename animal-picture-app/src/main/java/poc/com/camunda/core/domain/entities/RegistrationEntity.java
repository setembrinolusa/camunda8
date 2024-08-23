package poc.com.camunda.core.domain.entities;

import java.io.Serializable;

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
public class RegistrationEntity implements Serializable {

	private static final long serialVersionUID = -5206939025796018561L;

	private Long id;

    private CourseEntity course;

    private StudentEntity student;

}
