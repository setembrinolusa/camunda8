package poc.com.camunda;

import java.io.Serializable;
import java.util.List;

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
    private List<Picture> pictures;

}
