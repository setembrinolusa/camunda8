package poc.com.camunda.core.usecases.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import poc.com.camunda.adapters.jpa.models.Course;
import poc.com.camunda.core.domain.entities.CourseEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CourseUcMapper {

	
	public abstract List<CourseEntity> repoToModelList(List<Course> list);
	
	public abstract Course modelToRepo(CourseEntity obj);
	
	public abstract CourseEntity repoToModel(Course obj);

}
