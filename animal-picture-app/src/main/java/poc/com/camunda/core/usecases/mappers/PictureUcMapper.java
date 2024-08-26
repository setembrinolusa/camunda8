package poc.com.camunda.core.usecases.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import poc.com.camunda.adapters.jpa.models.Picture;
import poc.com.camunda.core.domain.entities.PictureEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PictureUcMapper {

	
	public abstract List<PictureEntity> repoToModelList(List<Picture> list);
	
	public abstract Picture modelToRepo(PictureEntity obj);
	
	public abstract PictureEntity repoToModel(Picture obj);

}
