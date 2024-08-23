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
public class StudentEntity implements Serializable {

	private static final long serialVersionUID = 7899988800446207352L;

	private Long id;

    private String name;

}
