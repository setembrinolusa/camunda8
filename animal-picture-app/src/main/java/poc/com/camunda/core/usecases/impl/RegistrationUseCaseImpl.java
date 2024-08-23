package poc.com.camunda.core.usecases.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poc.com.camunda.adapters.jpa.repositories.RegistrationRepository;
import poc.com.camunda.core.domain.entities.RegistrationEntity;
import poc.com.camunda.core.exceptions.BadRequestException;
import poc.com.camunda.core.exceptions.ResourceNotFoundException;
import poc.com.camunda.core.usecases.RegistrationUseCase;
import poc.com.camunda.core.usecases.mappers.RegistrationUcMapper;

@Service
public class RegistrationUseCaseImpl implements RegistrationUseCase {
	
	@Autowired
    private RegistrationRepository repo;
	
	@Autowired
	private RegistrationUcMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(RegistrationUseCaseImpl.class);

    public List<RegistrationEntity> findAll() {
    	logger.info("Get all Registrations");
    	return mapper.repoToModelList(repo.findAll());
    }
    
    public List<RegistrationEntity> findAllByCourseId(Long id) {
    	logger.info("Get all Registrations By Course");
    	return mapper.repoToModelList(repo.findAllByCourseId(id));
    }
    
    public List<RegistrationEntity> findAllByStudentId(Long id) {
    	logger.info("Get all RegistrationsBy Student");
    	return mapper.repoToModelList(repo.findAllByStudentId(id));
    }

    public RegistrationEntity save(RegistrationEntity obj, Long courseId, Long studentId) {
    	logger.info("Creating a Registration");
    	
    	//- Already registered
    	if (repo.findByCourseIdAndStudentId(courseId, studentId) != null) {
    		throw new BadRequestException("This Student is Already Registered in this Course");
    	}
    	//- A course can have 50 students maximum
    	if (repo.countByCourseId(courseId) >= 50) {
    		throw new BadRequestException("A course can have 50 students maximum");
    	}
    	//- A student can register to 5 course maximum
    	if (repo.countByStudentId(studentId) >= 5) {
    		throw new BadRequestException("A student can register to 5 course maximum");
    	}
    	return mapper.repoToModel(repo.save(mapper.modelToRepo(obj)));
    }

    public RegistrationEntity findById(Long id) {
    	logger.info("Get Registration By Id" + id);
    	return mapper.repoToModel(repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("REgistration", "id", id)));
    }
	
	public RegistrationEntity update(RegistrationEntity obj) {
		logger.info("Creating a Registration");
		return mapper.repoToModel(repo.save(mapper.modelToRepo(obj)));
	}
	
	public void delete(Long id) {
		logger.info("Creating a Registration");
		repo.deleteById(id);
	}
}