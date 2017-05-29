package com.aractron.spring.training.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aractron.spring.training.dao.CourseDao;
import com.aractron.spring.training.dao.mappers.CourseRowMapper;
import com.aractron.spring.training.domain.Course;

/**
 * The JDBC implementation of the CourseDao interface.
 * 
 * @author vanessa.ortiz
 */
@Repository
public class CourseDaoJDBCImpl implements CourseDao {

	/**
	 * Logger instance
	 */
	private static Log log = LogFactory.getLog((CourseDaoJDBCImpl.class));

	/**
	 * Spring's JdbcTemplate
	 */
	private JdbcTemplate jdbcTemplate;

	/**
	 * Empty constructor.
	 */
	public CourseDaoJDBCImpl() {

	}

	/**
	 * Constructor.
	 * 
	 * @param dataSource
	 */
	@Autowired
	public CourseDaoJDBCImpl(DataSource dataSource) {
		this.setJdbcTemplate(new JdbcTemplate(dataSource));
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Course course) {
		String sql = "delete from Course where name=?";
		int updateCount = jdbcTemplate.update(sql, course.getId());

		log.info(updateCount + " rows delete from the Course table");
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Course> findAllCourses() {
		String sql = "select * from Course";
		List<Course> localList = jdbcTemplate.query(sql, new CourseRowMapper());

		return localList;
	}

	/**
	 * {@inheritDoc}
	 */
	public Course findCourseById(Long id) {
		String sql = "select * from Course where course_id = ?";
		Object[] args = { id };
		List<Course> courseList = jdbcTemplate.query(sql, args,
				new CourseRowMapper());

		if (courseList != null && !courseList.isEmpty()) {
			return courseList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Course findCourseByName(String courseName) {
		String sql = "select * from Course where name = ?";
		Object[] args = { courseName };
		List<Course> courseList = jdbcTemplate.query(sql, args,
				new CourseRowMapper());

		if (courseList != null && !courseList.isEmpty()) {
			return courseList.get(0);
		} else {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Course> findCoursesByDepartment(String departmentName) {
		String sql = "select * from Course where department = ?";
		Object[] args = { departmentName };
		List<Course> localList = jdbcTemplate.query(sql, args,
				new CourseRowMapper());
		return localList;
	}

	/**
	 * {@inheritDoc}
	 */
	public void insert(Course course) {
		String sql = "insert into Course(name, description, department, creditHours, level) values (?,?,?,?,?)";
		Object[] args = { course.getName(), course.getDescription(),
				course.getDepartment(), course.getCreditHours(),
				course.getLevel() };
		jdbcTemplate.update(sql, args);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Course course) {
		String sql = "update Course set name=?, description=?, department=?, creditHours=?, level=? where course_id=?";
		int updateCount = jdbcTemplate.update(sql, course.getName(),
				course.getDescription(), course.getDepartment(),
				course.getCreditHours(), course.getLevel().toString(),
				course.getId());
		log.info(updateCount + " rows updated in Course table");
	}

	/**
	 * Getter for jdbcTemplate.
	 * 
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	/**
	 * Setter for jdbcTemplate.
	 * 
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
