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
public class CourseEntity implements Serializable {

	private static final long serialVersionUID = 5584345989376682627L;

	private Long id;

    private String name;

}
