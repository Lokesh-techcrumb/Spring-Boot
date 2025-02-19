package com.example.student;

import com.example.student.entities.Student;
import com.example.student.repos.StudentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Objects;

@SpringBootTest
class StudentApplicationTests {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void testStudentCreate(){
		Student student = new Student();
		student.setFirstName("John");
		student.setLastName("Doe");
		student.setScore(88);

		Student student2 = new Student();
		student2.setFirstName("Lokesh");
		student2.setLastName("Sharma");
		student2.setScore(90);

		studentRepository.save(student);
		studentRepository.save(student2);
	}

	@Test
	public void testStudentFindAll() {
		System.out.println(studentRepository.findAllStudents(PageRequest.of(0, 5)));
	}

	@Test
	public void testStudentFindAllPartialData() {
		List<Object[]> partial = studentRepository.findAllStudentsPartialData();
		for(Object[] objects : partial) {
			System.out.println(objects[0]);
			System.out.println(objects[1]);
		}
	}

	@Test
	public void estStudentFindAllByFirstName(){
		List<Student> students = studentRepository.findAllStudentsByFirstName("John");
		for(Student student : students) {
			System.out.println(student.getFirstName());
			System.out.println(student.getLastName());
		}
	}

	@Test
	public void testStudentFindAllByScore(){
		System.out.println(studentRepository.findAllStudentsByScore(80,91));
	}

	@Test
	@Transactional
	@Rollback(false)
	public void testDeleteStudentByFirstName(){
		studentRepository.deleteStudentByFirstName("John");
	}
}
