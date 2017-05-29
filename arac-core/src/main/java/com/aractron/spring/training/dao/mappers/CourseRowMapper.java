package com.aractron.spring.training.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.StudentClassification;

/**
 * Implementation of a ParameterizedRowMapper class used to map & extract data from the 
 * Course table to the Course object. These Mappers are used for JDBCDao implementations only.
 * 
 * @author vanessa.ortiz
 */
public class CourseRowMapper implements ParameterizedRowMapper<Course> {
	
	/**
	 * {@inheritDoc}
	 */
	public Course mapRow(ResultSet rs, int rowNum) throws SQLException {

		Course course = new Course();
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
		course.setLevel(StudentClassification.valueOf(rs.getString("LEVEL")));

		return course;
	}
}