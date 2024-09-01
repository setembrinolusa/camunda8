package poc.com.camunda;

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
public class Picture {

    private String id;

    private String animalType;
    
    private String name;

    private String type;

    private String path;

    private byte[] data;
    
    private String url;

    private long size;

}
