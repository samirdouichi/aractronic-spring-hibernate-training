package com.aractron.spring.training.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aractron.spring.training.dao.StudentDao;
import com.aractron.spring.training.dao.mappers.StudentExtractor;
import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.domain.Student;

/**
 * The JDBC implementation of the StudentDao interface.
 * 
 * @author vanessa.ortiz
 */
@Repository
public class StudentDaoJDBCImpl implements StudentDao {

	/**
	 * Logger instance
	 */
	private static Log log = LogFactory.getLog((StudentDaoJDBCImpl.class));
	
	/**
	 * Spring's jdbcTemplate
	 */
	private JdbcTemplate jdbcTemplate;

	/**
	 * Constructor
	 * 
	 * @param dataSource
	 */
	@Autowired
	public StudentDaoJDBCImpl(DataSource dataSource) {
		this.setJdbcTemplate(new JdbcTemplate(dataSource));
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Student user) {
		String sql = "delete from student where student_id = ?";
		jdbcTemplate.update(sql, user.getId());

		log.info(user.getUserName() + " deleted");
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Student> findAll() {
		// First need to retrieve the Student objects with the Set of CourseSession objects 
		// that the Students are registered to. 
		String sql1 = "select s.student_id, s.username, s.password, s.firstname, s.lastname, s.email, s.phonenumber, s.classification,"
				+ " cs.course_session_id, cs.term, c.course_id, c.name, c.description, c.department, c.credithours,"
				+ " f.faculty_id, f.username as FACULTY_USERNAME, f.password AS FACULTY_PASSWORD, f.firstname AS FACULTY_FIRSTNAME, f.lastname AS FACULTY_LASTNAME, "
				+ " f.email AS FACULTY_EMAIL, f.phonenumber AS FACULTY_PHONENUMBER, f.department AS FACULTY_DEPARTMENT"
				+ " from student s "
				+ " left join coursesession_enrollment cse on (s.student_id = cse.student_id)"
				+ " left join coursesession cs on (cse.course_session_id = cs.course_session_id)"
				+ " left join course c on (cs.course_id = c.course_id)"
				+ " left join faculty f on (cs.faculty_id = f.faculty_id)";

		//log.info(sql1);
		List<Student> studentList1 = jdbcTemplate.query(sql1, new StudentExtractor());
		
		if (studentList1 != null && !studentList1.isEmpty()) {		
			// Now need to look up all of the Students that are registered to each of the CourseSession objects
			// that belong to the Student from the previous query.
			String sql2 = "select s.student_id, s.username, s.password, s.firstname, s.lastname, s.email, s.phonenumber, s.classification,"
				+ " cs.course_session_id, cs.term, c.course_id, c.name, c.description, c.department, c.credithours,"
				+ " f.faculty_id, f.username as FACULTY_USERNAME, f.password AS FACULTY_PASSWORD, f.firstname AS FACULTY_FIRSTNAME, "
				+ " f.lastname AS FACULTY_LASTNAME, f.email AS FACULTY_EMAIL, f.phonenumber AS FACULTY_PHONENUMBER, f.department AS FACULTY_DEPARTMENT"
				+ " from student s "
				+ " left join coursesession_enrollment cse on (s.student_id = cse.student_id)"
				+ " left join coursesession cs on (cse.course_session_id = cs.course_session_id)"
				+ " left join course c on (cs.course_id = c.course_id)"
				+ " left join faculty f on (cs.faculty_id = f.faculty_id)"
				+ " where cse.course_session_id = ?";	
			
			List<Student> studentList2;
			
			for (Student s : studentList1) {
				for (CourseSession cs : s.getRegisteredCourses()) {
					Object[] args2 = { cs.getId() };
					studentList2 = jdbcTemplate.query(sql2, args2,
							new StudentExtractor());

					if (studentList2 != null && !studentList2.isEmpty()) {
						cs.getStudents().addAll(studentList2);
					}
				}
			}
		}
		System.out.println("End of StudentDaoJDBCImpl- findAll method; size of thr studentList is: "+studentList1.size());
		return studentList1;
	}

	/**
	 * {@inheritDoc}
	 */
	public Student findById(Long id) {

		System.out.println("Entered StudentDaoJDBCImpl - findById method; value of id is: "+id);
		// First need to retrieve the Student object with the Set of CourseSession objects 
		// that the Student is registered to. 
		String sql1 = "select s.student_id, s.username, s.password, s.firstname, s.lastname, s.email, s.phonenumber, s.classification,"
				+ " cs.course_session_id, cs.term, c.course_id, c.name, c.description, c.department, c.credithours,"
				+ " f.faculty_id, f.username as FACULTY_USERNAME, f.password AS FACULTY_PASSWORD, f.firstname AS FACULTY_FIRSTNAME, "
				+ " f.lastname AS FACULTY_LASTNAME, f.email AS FACULTY_EMAIL, f.phonenumber AS FACULTY_PHONENUMBER, f.department AS FACULTY_DEPARTMENT"
				+ " from student s "
				+ " left join coursesession_enrollment cse on (s.student_id = cse.student_id)"
				+ " left join coursesession cs on (cse.course_session_id = cs.course_session_id)"
				+ " left join course c on (cs.course_id = c.course_id)"
				+ " left join faculty f on (cs.faculty_id = f.faculty_id)"
				+ " where s.student_id = ?";
		
		//log.info(sql1);
		Object[] args1 = { id };
		List<Student> singleStudent = jdbcTemplate.query(sql1, args1, new StudentExtractor());

		Student student = null;
		if (singleStudent != null && !singleStudent.isEmpty()) {		
			// Now need to look up all of the Students that are registered to each of the CourseSession objects
			// that belong to the Student from the previous query.
			String sql2 = "select s.student_id, s.username, s.password, s.firstname, s.lastname, s.email, s.phonenumber, s.classification,"
				+ " cs.course_session_id, cs.term, c.course_id, c.name, c.description, c.department, c.credithours,"
				+ " f.faculty_id, f.username as FACULTY_USERNAME, f.password AS FACULTY_PASSWORD, f.firstname AS FACULTY_FIRSTNAME, "
				+ " f.lastname AS FACULTY_LASTNAME, f.email AS FACULTY_EMAIL, f.phonenumber AS FACULTY_PHONENUMBER, f.department AS FACULTY_DEPARTMENT"
				+ " from student s "
				+ " left join coursesession_enrollment cse on (s.student_id = cse.student_id)"
				+ " left join coursesession cs on (cse.course_session_id = cs.course_session_id)"
				+ " left join course c on (cs.course_id = c.course_id)"
				+ " left join faculty f on (cs.faculty_id = f.faculty_id)"
				+ " where cse.course_session_id = ?";	
			
			List<Student> studentList;
			
			for(CourseSession cs : singleStudent.get(0).getRegisteredCourses()){
				Object[] args2 = {cs.getId()};
				studentList = jdbcTemplate.query(sql2, args2, new StudentExtractor());
				
				if(studentList != null && !studentList.isEmpty()){
					cs.getStudents().addAll(studentList);
					System.out.println("Printing the size of studentList: "+studentList.size());
				}
			}
			student = singleStudent.get(0);
			System.out.println("End of findById method; student is: "+student);
		}
		return student;
	}

	/**
	 * {@inheritDoc}
	 */
	public Student findByUsernameAndPassword(String username, String password) {
		// First need to retrieve the Student object with the Set of CourseSession objects 
		// that the Student is registered to. 
		System.out.println("Entered StudentDaoJDBCImpl - findByUsernameAndPassword method; "
					+"value of username: "+username+"; value of password: "+password);
		
		String sql = "select s.student_id, s.username, s.password, s.firstname, s.lastname, s.email, s.phonenumber, s.classification,"
				+ " cs.course_session_id, cs.term, c.course_id, c.name, c.description, c.department, c.credithours,"
				+ " f.faculty_id, f.username as FACULTY_USERNAME, f.password AS FACULTY_PASSWORD, f.firstname AS FACULTY_FIRSTNAME, f.lastname AS FACULTY_LASTNAME, "
				+ " f.email AS FACULTY_EMAIL, f.phonenumber AS FACULTY_PHONENUMBER, f.department AS FACULTY_DEPARTMENT"
				+ " from student s "
				+ " left join coursesession_enrollment cse on (s.student_id = cse.student_id)"
				+ " left join coursesession cs on (cse.course_session_id = cs.course_session_id)"
				+ " left join course c on (cs.course_id = c.course_id)"
				+ " left join faculty f on (cs.faculty_id = f.faculty_id)"
				+ " where s.username= ? and password = ?";
		
		//log.info(sql);
		Object[] args = { username, password };
		List<Student> singleStudent = jdbcTemplate.query(sql, args,	new StudentExtractor());


		Student student = null;
		if (singleStudent != null && !singleStudent.isEmpty()) {		
			// Now need to look up all of the Students that are registered to each of the CourseSession objects
			// that belong to the Student from the previous query.
			String sql2 = "select s.student_id, s.username, s.password, s.firstname, s.lastname, s.email, s.phonenumber, s.classification,"
				+ " cs.course_session_id, cs.term, c.course_id, c.name, c.description, c.department, c.credithours,"
				+ " f.faculty_id, f.username as FACULTY_USERNAME, f.password AS FACULTY_PASSWORD, f.firstname AS FACULTY_FIRSTNAME, "
				+ " f.lastname AS FACULTY_LASTNAME, f.email AS FACULTY_EMAIL, f.phonenumber AS FACULTY_PHONENUMBER, f.department AS FACULTY_DEPARTMENT"
				+ " from student s "
				+ " left join coursesession_enrollment cse on (s.student_id = cse.student_id)"
				+ " left join coursesession cs on (cse.course_session_id = cs.course_session_id)"
				+ " left join course c on (cs.course_id = c.course_id)"
				+ " left join faculty f on (cs.faculty_id = f.faculty_id)"
				+ " where cse.course_session_id = ?";	
			
			List<Student> studentList;
			
			for(CourseSession cs : singleStudent.get(0).getRegisteredCourses()){
				Object[] args2 = {cs.getId()};
				studentList = jdbcTemplate.query(sql2, args2, new StudentExtractor());
				
				if(studentList != null && !studentList.isEmpty()){
					cs.getStudents().addAll(studentList);
				}
			}
			student = singleStudent.get(0);
			System.out.println("End of findByUsernameAndPassword method; value of student: "+student);
		}
		return student;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Student> findRegisteredStudentsByCourseName(String courseName) {
		// First need to retrieve the Student objects with the Set of CourseSession objects 
		// that the Students are registered to. 
		String sql = "select s.student_id, s.username, s.password, s.firstname, s.lastname, s.email, s.phonenumber, s.classification,"
				+ " cs.course_session_id, cs.term, c.course_id, c.name, c.description, c.department, c.credithours,"
				+ " f.faculty_id, f.username as FACULTY_USERNAME, f.password AS FACULTY_PASSWORD, f.firstname AS FACULTY_FIRSTNAME, f.lastname AS FACULTY_LASTNAME, "
				+ " f.email AS FACULTY_EMAIL, f.phonenumber AS FACULTY_PHONENUMBER, f.department AS FACULTY_DEPARTMENT"
				+ " from student s "
				+ " left join coursesession_enrollment cse on (s.student_id = cse.student_id)"
				+ " left join coursesession cs on (cse.course_session_id = cs.course_session_id)"
				+ " left join course c on (cs.course_id = c.course_id)"
				+ " left join faculty f on (cs.faculty_id = f.faculty_id)"
				+ " where c.name = ?";
		
		log.info(sql);
		Object[] args = { courseName };
		List<Student> studentList1 = jdbcTemplate.query(sql, args, new StudentExtractor());

		if (studentList1 != null && !studentList1.isEmpty()) {		
			// Now need to look up all of the Students that are registered to each of the CourseSession objects
			// that belong to the Student from the previous query.
			String sql2 = "select s.student_id, s.username, s.password, s.firstname, s.lastname, s.email, s.phonenumber, s.classification,"
				+ " cs.course_session_id, cs.term, c.course_id, c.name, c.description, c.department, c.credithours,"
				+ " f.faculty_id, f.username as FACULTY_USERNAME, f.password AS FACULTY_PASSWORD, f.firstname AS FACULTY_FIRSTNAME, "
				+ " f.lastname AS FACULTY_LASTNAME, f.email AS FACULTY_EMAIL, f.phonenumber AS FACULTY_PHONENUMBER, f.department AS FACULTY_DEPARTMENT"
				+ " from student s "
				+ " left join coursesession_enrollment cse on (s.student_id = cse.student_id)"
				+ " left join coursesession cs on (cse.course_session_id = cs.course_session_id)"
				+ " left join course c on (cs.course_id = c.course_id)"
				+ " left join faculty f on (cs.faculty_id = f.faculty_id)"
				+ " where cse.course_session_id = ?";	
			
			List<Student> studentList2;
			
			for (Student s : studentList1) {
				for (CourseSession cs : s.getRegisteredCourses()) {
					Object[] args2 = { cs.getId() };
					studentList2 = jdbcTemplate.query(sql2, args2,
							new StudentExtractor());

					if (studentList2 != null && !studentList2.isEmpty()) {
						cs.getStudents().addAll(studentList2);
					}
				}
			}
		}	
		return studentList1;
	}

	/**
	 * {@inheritDoc}
	 */
	public void insert(Student student) {
		String sql = "insert into Student(userName, password, firstName, lastName, email, phoneNumber, classification) values (?,?,?,?,?,?,?)";
		Object[] args = { student.getUserName(), student.getPassword(),
				student.getFirstName(), student.getLastName(),
				student.getEmail(), student.getPhoneNumber(),
				student.getClassification() };
		log.info(sql);
		int insertCount = jdbcTemplate.update(sql, args);

		log.info(insertCount + " rows inserted into Student table");
	}

	/**
	 * {@inheritDoc}
	 */
	public void registerStudent(Student student, CourseSession courseSession) {
		int updateCount = 0;

		String sql = "insert into coursesession_enrollment(course_session_id, student_id) values (?,?)";
		Object[] args = { courseSession.getId(), student.getId() };
		log.info(sql);
		updateCount = jdbcTemplate.update(sql, args);

		log.info(updateCount + " student registered to courseSession");
	}

	/**
	 * {@inheritDoc}
	 */
	public void unregisterStudent(Student student, CourseSession courseSession) {
		int updateCount = 0;

		String sql = "delete from coursesession_enrollment where course_session_id=? and student_id=?";
		Object[] args = { courseSession.getId(), student.getId() };
		log.info(sql);
		updateCount = jdbcTemplate.update(sql, args);

		log.info(updateCount + " student unregistered from courseSession");
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Student student) {
		String sql = "update Student set userName=?, password=?, firstName=?, lastName=?, email=?, phoneNumber=?, classification=? where student_id=?";
		Object[] args = { student.getUserName(), student.getPassword(),
				student.getFirstName(), student.getLastName(),
				student.getEmail(), student.getPhoneNumber(),
				student.getClassification(), student.getId() };
		log.info(sql);
		int updateCount = jdbcTemplate.update(sql, args);

		log.info(updateCount + " rows updated in Student table");
	}
	

	/**
	 * @param jdbcTmplate
	 *            the jdbcTmplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * @return the jdbcTmplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
}
