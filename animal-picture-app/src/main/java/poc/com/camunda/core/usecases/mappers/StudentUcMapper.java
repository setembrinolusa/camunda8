package poc.com.camunda.core.usecases.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import poc.com.camunda.adapters.jpa.models.Student;
import poc.com.camunda.core.domain.entities.StudentEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class StudentUcMapper {

	
	public abstract List<StudentEntity> repoToModelList(List<Student> list);
	
	public abstract Student modelToRepo(StudentEntity obj);
	
	public abstract StudentEntity repoToModel(Student obj);

}
