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
 * Implementation of a ResultSetExtractorused to map & extract data from the CourseSession
 * table to the CourseSession object. These Mappers are used for JDBCDao implementations only
 * 
 * @author vanessa.ortiz
 */
public class CourseSessionExtractor implements ResultSetExtractor<List<CourseSession>> {

	/**
	 * {@inheritDoc}
	 */
	public List<CourseSession> extractData(ResultSet rs) throws SQLException,
			DataAccessException {

		List<CourseSession> courseSessionList = new ArrayList<CourseSession>();
		Map<String, CourseSession> map = new HashMap<String, CourseSession>();
		CourseSession courseSession = null;
		Course course = null;
		Faculty faculty = null;
		Student student = null;

		while (rs.next()) {
			String unique = rs.getString("TERM") + rs.getLong("COURSE_ID");
			courseSession = map.get(unique);

			if (courseSession == null) {
				// Create the CourseSession object first
				courseSession = new CourseSession();
				courseSession.setId(rs.getLong("COURSE_SESSION_ID"));
				courseSession.setTerm(rs.getString("TERM"));
				courseSession.setStudents(new HashSet<Student>());

				// Create the Course object 
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
				course.setLevel(StudentClassification.valueOf(rs
						.getString("LEVEL")));

				// Set the course in the CourseSession object
				courseSession.setCourse(course);

				// Create the Faculty object
				faculty = new Faculty();
				faculty.setId(rs.getLong("FACULTY_ID"));
				faculty.setUserName(rs.getString("FACULTY_USERNAME"));
				faculty.setPassword(rs.getString("FACULTY_PASSWORD"));
				faculty.setFirstName(rs.getString("FACULTY_FIRSTNAME"));
				faculty.setLastName(rs.getString("FACULTY_LASTNAME"));
				faculty.setEmail(rs.getString("FACULTY_EMAIL"));
				faculty.setPhoneNumber(rs.getString("FACULTY_PHONENUMBER"));
				faculty.setDepartment(rs.getString("FACULTY_DEPARTMENT"));

				// Set the faculty in the CourseSession object
				courseSession.setFaculty(faculty);

				map.put(unique, courseSession);
			}

			// Retrieve all of the Student objects that are associated to this 
			// particular CourseSession object
			Long stu_id = rs.getLong("STUDENT_ID");
			if (stu_id > 0) {
				student = new Student();
				student.setId(stu_id);
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
				// student.getRegisteredCourses().add(courseSession);

				// Set the Student in the CourseSession object
				courseSession.getStudents().add(student);
			}
		}

		if (!map.isEmpty()) {
			courseSessionList.addAll(map.values());
		}

		return courseSessionList;
	}
}