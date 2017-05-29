package com.aractron.spring.training.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aractron.spring.training.dao.FacultyDao;
import com.aractron.spring.training.dao.mappers.FacultyRowMapper;
import com.aractron.spring.training.domain.Faculty;

/**
 * The JDBC implementation of the FacultyDao interface.
 * 
 * @author vanessa.ortiz
 */
@Repository
public class FacultyDaoJDBCImpl implements FacultyDao {

	/**
	 * Logger instance
	 */
	private static Log log = LogFactory.getLog((FacultyDaoJDBCImpl.class));
	
	/**
	 * Spring's JdbcTemplate
	 */
	private JdbcTemplate jdbcTemplate;

	/**
	 * Constructor
	 * 
	 * @param dataSource
	 */
	@Autowired
	public FacultyDaoJDBCImpl(DataSource dataSource) {
		this.setJdbcTemplate(new JdbcTemplate(dataSource));
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Faculty faculty) {
		String sql = "delete from faculty where username=?";
		Object[] args = {faculty.getUserName()};

		int updateCount = jdbcTemplate.update(sql, args);

		log.info(updateCount + " rows removed from the Faculty table");

	}

	/**
	 * {@inheritDoc}
	 */
	public List<Faculty> findAll() {
		String sql = "select * from faculty";
		List<Faculty> facultyList = jdbcTemplate.query(sql,	new FacultyRowMapper());

		return facultyList;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Faculty> findAllFacultyByDepartment(String departmentName) {
		String sql = "select * from faculty where department=?";
		Object[] args = { departmentName };
		List<Faculty> facultyList = jdbcTemplate.query(sql, args,
				new FacultyRowMapper());

		return facultyList;
	}

	/**
	 * {@inheritDoc}
	 */
	public Faculty findById(Long id) {
		String sql = "select * from faculty where faculty_id=?";
		Object[] args = { id };
		List<Faculty> facultyList = jdbcTemplate.query(sql, args, new FacultyRowMapper());

		if(facultyList != null && !facultyList.isEmpty()){
			return facultyList.get(0);
		}
		else{
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Faculty findByUsernameAndPassword(String username, String password) {
		String sql = "select * from faculty where username=? and password=?";
		Object[] args = { username, password };
		
		List<Faculty> facultyList = jdbcTemplate.query(sql, args, new FacultyRowMapper());

		if(facultyList != null && !facultyList.isEmpty()){
			return facultyList.get(0);
		}
		else{
			return null;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void insert(Faculty faculty) {
		String sql = "insert into faculty (userName, password, firstName, lastName, email, phoneNumber, department) values (?,?,?,?,?,?,?)";
		Object[] args = { faculty.getUserName(), faculty.getPassword(),
				faculty.getFirstName(), faculty.getLastName(),
				faculty.getEmail(), faculty.getPhoneNumber(),
				faculty.getDepartment() };

		int insertCount = jdbcTemplate.update(sql, args);

		log.info(insertCount + " rows inserted into Faculty table");
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Faculty faculty) {
		String sql = "update faculty set userName=?, password=?, firstName=?, lastName=?, email=?, phoneNumber=?, department=? where faculty_id = ?";
		Object[] args = { faculty.getUserName(), faculty.getPassword(),
				faculty.getFirstName(), faculty.getLastName(),
				faculty.getEmail(), faculty.getPhoneNumber(),
				faculty.getDepartment(), faculty.getId() };

		int updateCount = jdbcTemplate.update(sql, args);

		log.info(updateCount + " rows updated in Faculty table");
	}

	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}	

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
}
