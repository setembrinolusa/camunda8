package poc.com.camunda.adapters.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import poc.com.camunda.adapters.jpa.models.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, String> {

}
