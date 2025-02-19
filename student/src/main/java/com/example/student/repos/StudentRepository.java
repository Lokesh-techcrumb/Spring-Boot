package com.example.student.repos;

import com.example.student.entities.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;

public interface StudentRepository extends CrudRepository<Student, Long> {

    @Query("from Student")
    List<Student> findAllStudents(Pageable pageable);

    @Query("select st.firstName, st.lastName from Student st")
    List<Object[]> findAllStudentsPartialData();

    @Query("from Student where firstName=:firstName")
    List<Student> findAllStudentsByFirstName(@Param("firstName") String firstName);

    @Query("from Student where score>:min and score<:max")
    List<Student> findAllStudentsByScore(@Param("min") int min, @Param("max") int max);

    @Modifying
    @Query("delete from Student where firstName=:firstName")
    void deleteStudentByFirstName(@Param("firstName") String firstName);
}
