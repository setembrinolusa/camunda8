package poc.com.camunda.adapters.http.rest.v1.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import poc.com.camunda.adapters.http.rest.v1.models.StudentRequest;
import poc.com.camunda.adapters.http.rest.v1.models.StudentResponse;
import poc.com.camunda.core.domain.entities.StudentEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class StudentHttpMapper {

	public abstract StudentEntity responseToModel(StudentResponse obj);

	public abstract List<StudentEntity> responseToModelList(List<StudentResponse> list);

	public abstract StudentResponse modelToResponse(StudentEntity obj);

	public abstract List<StudentResponse> modelToResponseList(List<StudentEntity> list);
	
	public abstract StudentEntity requestTomodel(StudentRequest obj);
	
}