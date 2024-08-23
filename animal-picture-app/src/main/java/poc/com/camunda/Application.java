package poc.com.camunda;

import java.util.TimeZone;
import java.util.stream.LongStream;

import javax.annotation.PostConstruct;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import poc.com.camunda.adapters.jpa.models.Course;
import poc.com.camunda.adapters.jpa.models.Registration;
import poc.com.camunda.adapters.jpa.models.Student;
import poc.com.camunda.adapters.jpa.repositories.CourseRepository;
import poc.com.camunda.adapters.jpa.repositories.RegistrationRepository;
import poc.com.camunda.adapters.jpa.repositories.StudentRepository;

@SpringBootApplication
public class Application {

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner initCourse(CourseRepository repository) {
		return args -> {
			repository.deleteAll();
			LongStream.range(1, 5).mapToObj(i -> {
				Course c = Course.builder().id(i).name("Course " + i).build();
				return c;
			}).map(v -> repository.save(v)).forEach(System.out::println);
		};
	}

	@Bean
	CommandLineRunner initStudent(StudentRepository repository) {
		return args -> {
			repository.deleteAll();
			LongStream.range(1, 10).mapToObj(i -> {
				Student s = Student.builder().id(i).name("Student " + i).build();
				return s;
			}).map(v -> repository.save(v)).forEach(System.out::println);
		};
	}

	@Bean
	CommandLineRunner initRegistration(RegistrationRepository repository) {
		return args -> {
			repository.deleteAll();
			LongStream.range(1, 3).mapToObj(i -> {
				Registration r = Registration.builder().id(i)
						.course(Course.builder().id(i + 1).name("Course " + i + 1).build())
						.student(Student.builder().id(i + 2).name("Student " + i + 2).build()).build();
				return r;
			}).map(v -> repository.save(v)).forEach(System.out::println);
		};
	}

}
