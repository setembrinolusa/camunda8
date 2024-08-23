package poc.com.camunda.adapters.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import poc.com.camunda.adapters.jpa.models.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
   

    @Query("SELECT COUNT(r.id) from Registration r where r.student.id = :studentId")
    short countByStudentId(@Param("studentId") Long studentId);

    @Query("SELECT COUNT(r.id) from Registration r where r.course.id = :courseId")
    short countByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT r FROM Registration r where r.course.id in :id")
    List<Registration> findAllByCourseId(@Param("id") Long id);
    
    @Query("SELECT r FROM Registration r where r.student.id = :id")
    List<Registration> findAllByStudentId(@Param("id") Long id);
    
    @Query("SELECT r FROM Registration r where r.course.id = :courseId and r.student.id = :studentId")
    Registration findByCourseIdAndStudentId(@Param("courseId") Long courseId, @Param("studentId") Long studentId);
}

