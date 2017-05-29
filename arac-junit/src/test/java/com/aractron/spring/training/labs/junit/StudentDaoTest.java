package com.aractron.spring.training.labs.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.aractron.spring.training.dao.StudentDao;
import com.aractron.spring.training.domain.Student;

@RunWith(SpringJUnit4ClassRunner.class)
// Discuss the parameters for ContextConfiguration, particularly 'locations'
@ContextConfiguration
public class StudentDaoTest {
	@Autowired
	private StudentDao studentDao;

	@Test
	public void testFindAllStudents() {
		List<Student> students = studentDao.findAll();
		assertNotNull(students);
		assertTrue(students.size() > 0);
	}

	@Test
	public void testFindStudentById() {
		Student student = null;
		// participants to write test to find student by id '1'
		assertNotNull(student);
	}

	// instructor to walkthrough the below method
	@Test
	@Transactional
	public void testUpdateStudent() {
		Student student = studentDao.findById(1l);
		System.out.println("Student info before updating; username: "
				+ student.getUserName() + "; email: " + student.getEmail());
		student.setUserName("John");
		student.setEmail("test@aractron.com");
		studentDao.update(student);
		Student anotherStudent = studentDao.findByUsernameAndPassword(
				student.getUserName(), student.getPassword());
		System.out.println("Student info after updating; username: "
				+ anotherStudent.getUserName() + "; email: "
				+ anotherStudent.getEmail());
		assertNotNull(anotherStudent);
		assertTrue(anotherStudent.getId() > 0);
	}

}
