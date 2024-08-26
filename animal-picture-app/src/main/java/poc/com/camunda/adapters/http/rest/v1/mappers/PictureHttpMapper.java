package poc.com.camunda.adapters.http.rest.v1.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import poc.com.camunda.adapters.http.rest.v1.models.PictureRequest;
import poc.com.camunda.adapters.http.rest.v1.models.PictureResponse;
import poc.com.camunda.core.domain.entities.PictureEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PictureHttpMapper {

	public abstract PictureEntity responseToModel(PictureResponse obj);

	public abstract List<PictureEntity> responseToModelList(List<PictureResponse> list);

	public abstract PictureResponse modelToResponse(PictureEntity obj);

	public abstract List<PictureResponse> modelToResponseList(List<PictureEntity> list);
	
	public abstract PictureEntity requestToModel(PictureRequest obj);
	
}