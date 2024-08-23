package poc.com.camunda.adapters.http.rest.v1.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import poc.com.camunda.adapters.http.rest.v1.models.RegistrationRequest;
import poc.com.camunda.adapters.http.rest.v1.models.RegistrationResponse;
import poc.com.camunda.core.domain.entities.RegistrationEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class RegistrationHttpMapper {

	public abstract RegistrationEntity responseToModel(RegistrationResponse obj);

	public abstract List<RegistrationEntity> responseToModelList(List<RegistrationResponse> list);

	public abstract RegistrationResponse modelToResponse(RegistrationEntity obj);

	public abstract List<RegistrationResponse> modelToResponseList(List<RegistrationEntity> list);
	
	public abstract RegistrationEntity requestTomodel(RegistrationRequest obj);
	
}