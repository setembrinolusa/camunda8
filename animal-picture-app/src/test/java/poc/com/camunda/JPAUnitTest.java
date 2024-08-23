package poc.com.camunda;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import poc.com.camunda.adapters.jpa.models.Course;
import poc.com.camunda.adapters.jpa.models.Registration;
import poc.com.camunda.adapters.jpa.models.Student;
import poc.com.camunda.adapters.jpa.repositories.CourseRepository;
import poc.com.camunda.adapters.jpa.repositories.RegistrationRepository;
import poc.com.camunda.adapters.jpa.repositories.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JPAUnitTest {

	@Autowired
	RegistrationRepository registrationRepo;

	@Autowired
	CourseRepository courseRepo;

	@Autowired
	StudentRepository studentRepo;

	@Test
	public void should_find_no_registration_if_repository_is_empty() {
		List<Registration> regs = registrationRepo.findAll();
		assertTrue(regs.isEmpty());
	}

	@Test
	public void should_delete_all_registration() {
		registrationRepo.deleteAll();
		assertTrue(registrationRepo.findAll().isEmpty());
	}

	@Test
	public void should_find_all_courses() {
		List<Course> regs = courseRepo.findAll();
		assertFalse(regs.isEmpty());
	}

	@Test
	public void should_find_all_students() {
		List<Student> regs = studentRepo.findAll();
		assertFalse(regs.isEmpty());
	}

	@Test
	public void should_find_course_by_id() {
		Course c = new Course();
		c = courseRepo.save(c);
		Course foundCourse = courseRepo.findById(c.getId()).get();
		assertTrue(foundCourse.getId().equals(c.getId()));
	}

	@Test
	public void should_find_student_by_id() {
		Student s = new Student();
		s = studentRepo.save(s);
		Student foundCourse = studentRepo.findById(s.getId()).get();
		assertTrue(foundCourse.getId().equals(s.getId()));
	}
}
