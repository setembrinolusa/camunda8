package poc.com.camunda.core.usecases.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import poc.com.camunda.adapters.jpa.models.Registration;
import poc.com.camunda.core.domain.entities.RegistrationEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class RegistrationUcMapper {

	
	public abstract List<RegistrationEntity> repoToModelList(List<Registration> list);
	
	public abstract Registration modelToRepo(RegistrationEntity obj);
	
	public abstract RegistrationEntity repoToModel(Registration obj);

}
