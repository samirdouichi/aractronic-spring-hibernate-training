package com.aractron.spring.training.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.aractron.spring.training.domain.Faculty;

/**
 * Implementation of a RowMapper class used to map & extract data from the Faculty
 * table to the Faculty object. These Mappers are used for JDBCDao implementations only.
 * 
 * @author vanessa.ortiz
 */
public class FacultyRowMapper implements ParameterizedRowMapper<Faculty> {
	
	/**
	 * {@inheritDoc}
	 */
	public Faculty mapRow(ResultSet rs, int rowNum) throws SQLException {

		Faculty faculty = new Faculty();
		faculty.setId(rs.getLong("FACULTY_ID"));
		faculty.setUserName(rs.getString("USERNAME"));
		faculty.setPassword(rs.getString("PASSWORD"));
		faculty.setFirstName(rs.getString("FIRSTNAME"));
		faculty.setLastName(rs.getString("LASTNAME"));
		faculty.setEmail(rs.getString("EMAIL"));
		faculty.setPhoneNumber(rs.getString("PHONENUMBER"));
		faculty.setDepartment(rs.getString("DEPARTMENT"));

		return faculty;
	}
}
