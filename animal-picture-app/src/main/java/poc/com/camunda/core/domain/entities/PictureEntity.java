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
public class PictureEntity implements Serializable {

    private static final long serialVersionUID = 5584345989376682627L;

    private String id;

    private String name;

    private String type;

    private byte[] data;

}
