package com.aractron.spring.training.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.domain.Faculty;
import com.aractron.spring.training.domain.Student;
import com.aractron.spring.training.domain.StudentClassification;

/**
 * Implementation of a ResultSetExtractorused to map & extract data from the Student
 * table to the Student object. These Mappers are used for JDBCDao implementations only
 * 
 * @author vanessa.ortiz
 */
public class StudentExtractor implements ResultSetExtractor<List<Student>> {

	/**
	 * {@inheritDoc}
	 */
	public List<Student> extractData(ResultSet rs) throws SQLException,
			DataAccessException {

		List<Student> studentList = new ArrayList<Student>();
		Map<Long, Student> map = new HashMap<Long, Student>();
		CourseSession courseSession = null;
		Student student = null;
		Course course = null;
		Faculty faculty = null;

		while (rs.next()) {
			Long studentId = rs.getLong("STUDENT_ID");
			student = map.get(studentId);

			// Create the Student object first
			if (student == null) {
				student = new Student();
				student.setId(studentId);
				student.setUserName(rs.getString("USERNAME"));
				student.setPassword(rs.getString("PASSWORD"));
				student.setFirstName(rs.getString("FIRSTNAME"));
				student.setLastName(rs.getString("LASTNAME"));
				student.setEmail(rs.getString("EMAIL"));
				student.setPhoneNumber(rs.getString("PHONENUMBER"));
				student.setClassification(StudentClassification.valueOf(
						StudentClassification.class,
						rs.getString("CLASSIFICATION")));
				student.setRegisteredCourses(new HashSet<CourseSession>());

				map.put(studentId, student);
			}

			// Create the list CourseSession objects by creating the Course object first
			course = new Course();
			course.setId(rs.getLong("COURSE_ID"));
			course.setName(rs.getString("NAME"));
			course.setDescription(rs.getString("DESCRIPTION"));
			course.setDepartment(rs.getString("DEPARTMENT"));
			double hours = 0.0;
			String tmp = rs.getString("CREDITHOURS");
			if (tmp != null) {
				hours = new Double(tmp);
			}
			course.setCreditHours(hours);

			// Next create the Faculty associated with the CourseSession
			faculty = new Faculty();
			faculty.setId(rs.getLong("FACULTY_ID"));
			faculty.setUserName(rs.getString("FACULTY_USERNAME"));
			faculty.setPassword(rs.getString("FACULTY_PASSWORD"));
			faculty.setFirstName(rs.getString("FACULTY_FIRSTNAME"));
			faculty.setLastName(rs.getString("FACULTY_LASTNAME"));
			faculty.setEmail(rs.getString("FACULTY_EMAIL"));
			faculty.setPhoneNumber(rs.getString("FACULTY_PHONENUMBER"));
			faculty.setDepartment(rs.getString("FACULTY_DEPARTMENT"));

			// Create the CourseSesson object itself
			courseSession = new CourseSession();
			courseSession.setId(rs.getLong("COURSE_SESSION_ID"));
			courseSession.setTerm(rs.getString("TERM"));
			courseSession.setCourse(course);
			courseSession.setFaculty(faculty);
			courseSession.setStudents(new HashSet<Student>());

			// Set the CourseSession in the Student object
			if (!courseSession.getId().equals(new Long(0))) {
				student.getRegisteredCourses().add(courseSession);
			}
		}

		if (!map.isEmpty()) {
			studentList.addAll(map.values());
		}

		return studentList;
	}
}
