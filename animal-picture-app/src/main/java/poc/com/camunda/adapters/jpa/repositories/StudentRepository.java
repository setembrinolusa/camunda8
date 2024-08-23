package poc.com.camunda.adapters.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import poc.com.camunda.adapters.jpa.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("SELECT st FROM Student st LEFT OUTER JOIN Registration reg ON st.id = reg.student.id WHERE reg.id IS NULL")
    List<Student> findAllWithoutCourses();
}
