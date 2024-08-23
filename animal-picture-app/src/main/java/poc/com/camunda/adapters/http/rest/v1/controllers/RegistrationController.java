package poc.com.camunda.adapters.http.rest.v1.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import poc.com.camunda.adapters.http.rest.v1.mappers.RegistrationHttpMapper;
import poc.com.camunda.adapters.http.rest.v1.models.RegistrationRequest;
import poc.com.camunda.adapters.http.rest.v1.models.RegistrationResponse;
import poc.com.camunda.core.exceptions.AppException;
import poc.com.camunda.core.usecases.RegistrationUseCase;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

	@Autowired
	private RegistrationUseCase useCase;

	@Autowired
	private RegistrationHttpMapper mapper;

	private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@GetMapping
	public ResponseEntity<?> findAll() {
		logger.info("Get all Registrations");
		List<RegistrationResponse> response = mapper.modelToResponseList(useCase.findAll());
		if (response == null) {
			throw new AppException("Error");
		} else {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
	
	@GetMapping("/course/{id}")
	public ResponseEntity<?> findAllByCourseId(@PathVariable Long id) {
		logger.info("Get all Registrations By Course");
		List<RegistrationResponse> response =  mapper.modelToResponseList(useCase.findAllByCourseId(id));
		if (response == null) {
			throw new AppException("Error");
		} else {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<?> findAllByStudentId(@PathVariable Long id) {
		logger.info("Get all Registrations By Student");
		List<RegistrationResponse> response = mapper.modelToResponseList(useCase.findAllByStudentId(id));
		if (response == null) {
			throw new AppException("Error");
		} else {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping("/course/{courseId}/student/{studentId}")
	public ResponseEntity<?> save(@RequestBody RegistrationRequest obj, @PathVariable Long courseId, @PathVariable Long studentId) {
		RegistrationResponse response = mapper.modelToResponse(useCase.save(mapper.requestTomodel(obj), courseId, studentId));

		if (response == null) {
			throw new AppException("Error creating Registration");
		} else {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}
	}

	@GetMapping("/{id}")
	public RegistrationResponse findById(@PathVariable Long id) {
		return mapper.modelToResponse(useCase.findById(id));
	}
	
	@PatchMapping
	public ResponseEntity<?> update(@RequestBody RegistrationRequest obj) {
		RegistrationResponse response = mapper.modelToResponse(useCase.update(mapper.requestTomodel(obj)));

		if (response == null) {
			throw new AppException("Error updating Registration");
		} else {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		try {
			useCase.delete(id);
			return new ResponseEntity<>("Delete Success...", HttpStatus.OK);
		} catch (Exception e) {
			throw new AppException("Error deleting Registraton");
		}
	}

}