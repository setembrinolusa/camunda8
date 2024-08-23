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

import poc.com.camunda.adapters.http.rest.v1.mappers.StudentHttpMapper;
import poc.com.camunda.adapters.http.rest.v1.models.StudentRequest;
import poc.com.camunda.adapters.http.rest.v1.models.StudentResponse;
import poc.com.camunda.core.exceptions.AppException;
import poc.com.camunda.core.usecases.StudentUseCase;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private StudentUseCase useCase;

	@Autowired
	private StudentHttpMapper mapper;

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@GetMapping
	public ResponseEntity<?> findAll() {
		logger.info("Get all Students");
		List<StudentResponse> response = mapper.modelToResponseList(useCase.findAll());
		if (response == null) {
			throw new AppException("Error");
		} else {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody StudentRequest obj) {
		StudentResponse response = mapper.modelToResponse(useCase.save(mapper.requestTomodel(obj)));

		if (response == null) {
			throw new AppException("Error creating Student");
		} else {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}
	}

	@GetMapping("/{id}")
	public StudentResponse findById(@PathVariable Long id) {
		return mapper.modelToResponse(useCase.findById(id));
	}

	@PatchMapping
	public ResponseEntity<?> update(@RequestBody StudentRequest obj) {
		StudentResponse response = mapper.modelToResponse(useCase.update(mapper.requestTomodel(obj)));

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
			throw new AppException("Error deleting Student");
		}
	}

	@GetMapping("/no-courses")
	public ResponseEntity<?> findAllWithoutCourses() {
		logger.info("Get all Students without Courses");
		List<StudentResponse> response = mapper.modelToResponseList(useCase.findAllWithoutCourses());
		if (response == null) {
			throw new AppException("Error");
		} else {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
}
