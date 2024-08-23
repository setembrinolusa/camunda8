package poc.com.camunda.core.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

import poc.com.camunda.core.domain.entities.CourseEntity;

@Service
public interface CourseUseCase {

	public List<CourseEntity> findAll();

	public CourseEntity save(CourseEntity obj);

	public CourseEntity findById(Long id);
	
	public CourseEntity update(CourseEntity obj);
	
	public void delete(Long id);
	
	public List<CourseEntity> findAllWithoutStudents();

}
