package poc.com.camunda.adapters.jpa.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PICTURE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Picture implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String animalType;

    private String name;

    private String type;

    private String path;

    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] data;

    private static final long serialVersionUID = 5584345989376682627L;

}
