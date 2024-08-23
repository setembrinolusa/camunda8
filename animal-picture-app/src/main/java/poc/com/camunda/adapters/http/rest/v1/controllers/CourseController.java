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

import poc.com.camunda.adapters.http.rest.v1.mappers.CourseHttpMapper;
import poc.com.camunda.adapters.http.rest.v1.models.CourseRequest;
import poc.com.camunda.adapters.http.rest.v1.models.CourseResponse;
import poc.com.camunda.core.exceptions.AppException;
import poc.com.camunda.core.usecases.CourseUseCase;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	@Autowired
	private CourseUseCase useCase;

	@Autowired
	private CourseHttpMapper mapper;

	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	@GetMapping
	public ResponseEntity<?> findAll() {
		logger.info("Get all Courses");
		List<CourseResponse> response = mapper.modelToResponseList(useCase.findAll());
		if (response == null) {
			throw new AppException("Error");
		} else {
			return new ResponseEntity<>(response, HttpStatus.OK);	
		}
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody CourseRequest obj) {
		CourseResponse response = mapper.modelToResponse(useCase.save(mapper.requestTomodel(obj)));

		if (response == null) {
			throw new AppException("Error creating Course");
		} else {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}
	}

	@GetMapping("/{id}")
	public CourseResponse findById(@PathVariable Long id) {
		return mapper.modelToResponse(useCase.findById(id));
	}
	
	@PatchMapping
	public ResponseEntity<?> update(@RequestBody CourseRequest obj) {
		CourseResponse response = mapper.modelToResponse(useCase.update(mapper.requestTomodel(obj)));

		if (response == null) {
			throw new AppException("Error updating Course");
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
			throw new AppException("Error deleting Course");
		}
	}
	
	@GetMapping("/no-students")
	public ResponseEntity<?> findAllWithoutStudents() {
		logger.info("Get all Courses without Students");
		List<CourseResponse> response =  mapper.modelToResponseList(useCase.findAllWithoutStudents());
		if (response == null) {
			throw new AppException("Error");
		} else {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

}
