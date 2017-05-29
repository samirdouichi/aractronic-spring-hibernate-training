package com.aractron.spring.training.labs.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aractron.spring.training.dao.CourseDao;
import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.StudentClassification;

/**
 * The JDBC implementation of the CourseDao interface. This is the example
 * class that Students will modify and work on.
 * 
 * @author vanessa.ortiz
 */
@Repository
public class CourseDaoJDBCImplExample implements CourseDao {

	/**
	 * Logger instance
	 */
	private static Log log = LogFactory.getLog((CourseDaoJDBCImplExample.class));
	
	// TODO: CS5_LAB2_0 - Uncomment the jdbcTemplate variable below.
	// TODO: CS5_LAB2_1.1.1 - Add the setter and getter methods for jdbcTemplate at the bottom of the class.
//	private JdbcTemplate jdbcTemplate;

	
	/**
	 * Constructor.
	 */
	public CourseDaoJDBCImplExample() {
		// TODO: CS5_LAB2_1.1.2 - Correctly implement the class's constructor by passing in the DataSource
		// and setting it.
		
		// TODO: CS_LAB2_1.1.3 - Add the correct annotation to autowire the DataSource 
		// dependency into this class.
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Course course) {
		// TODO: CS5_LAB2_1.4 - Change the implementation of this method by deleting all of the unnecessary 
		// boilerplate code and replacing it with code using the jdbcTemplate. 
		// Hint: check out FacultyDaoJDBCImpl.java in the core project.

		String sql = "delete from Course where name=?";
		int updateCount = 0;

		PreparedStatement stmt = null;
		Connection conn = null;

		try {
			// Load the database driver
			Class.forName("org.hsqldb.jdbcDriver");

			// Get a connection to the database
			conn = DriverManager.getConnection("jdbc:hsqldb:mem:university");

			// Get a statement from the connection
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, course.getName());

			// Execute query
			updateCount = stmt.executeUpdate();

			// Clean up
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle other errors
			e.printStackTrace();

		} finally {
			// finally block used to close resources
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
		}

		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}

		log.info(updateCount + " rows deleted from the Course table");
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Course> findAllCourses() {
		// Intentional example of how JDBC access worked before JdbcTemplate.
		
		String sql = "select * from Course";
		List<Course> courseList = new ArrayList<Course>();

		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs;

		try {
			// Load the database driver
			Class.forName("org.hsqldb.jdbcDriver");

			// Get a connection to the database
			conn = DriverManager.getConnection("jdbc:hsqldb:mem:university") ;

			// Get a statement from the connection
			stmt = conn.prepareStatement(sql) ;

			// Execute query
			rs = stmt.executeQuery();

			// Extract data from the result set
			while(rs.next()){
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
				courseList.add(course);
			}

			// Clean up
			rs.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch (Exception e){
			// Handle other errors
			e.printStackTrace();

		}
		finally {
			//finally block used to close resources
			try{
				if(stmt!=null){
					stmt.close();
				}
			}
			catch(SQLException se2){
				se2.printStackTrace();
			}
		}

		try{
			if(conn!=null){
				conn.close();
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		return courseList;
	}

	/**
	 * {@inheritDoc}
	 */
	public Course findCourseById(Long id) {
		// Intentional example of how JDBC access worked before JdbcTemplate.
		
		String sql = "select * from Course where course_id = ?";
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs;
		Course course = null;

		try {
			// Load the database driver
			Class.forName("org.hsqldb.jdbcDriver");

			// Get a connection to the database
			conn = DriverManager.getConnection("jdbc:hsqldb:mem:university") ;

			// Get a statement from the connection
			stmt = conn.prepareStatement(sql);
			stmt.setLong (1, id);

			// Execute query
			rs = stmt.executeQuery();

			// Extract data from the result set
			while(rs.next()){
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
				course.setLevel(StudentClassification.valueOf(rs.getString("LEVEL")));
			}

			// Clean up
			rs.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch (Exception e){
			// Handle other errors
			e.printStackTrace();

		}
		finally {
			//finally block used to close resources
			try{
				if(stmt!=null){
					stmt.close();
				}
			}
			catch(SQLException se2){
				se2.printStackTrace();
			}
		}

		try{
			if(conn!=null){
				conn.close();
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		return course;
	}

	/**
	 * {@inheritDoc}
	 */
	public Course findCourseByName(String courseName) {
		// Intentional example of how JDBC access worked before JdbcTemplate.
		
		String sql = "select * from Course where name = ?";
		Course course = null;
		
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs;

		try {
			// Load the database driver
			Class.forName("org.hsqldb.jdbcDriver");

			// Get a connection to the database
			conn = DriverManager.getConnection("jdbc:hsqldb:mem:university") ;

			// Get a statement from the connection
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, courseName);

			// Execute query
			rs = stmt.executeQuery();

			// Extract data from the result set
			while(rs.next()){
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
				course.setLevel(StudentClassification.valueOf(rs.getString("LEVEL")));
			}

			// Clean up
			rs.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch (Exception e){
			// Handle other errors
			e.printStackTrace();

		}
		finally {
			//finally block used to close resources
			try{
				if(stmt!=null){
					stmt.close();
				}
			}
			catch(SQLException se2){
				se2.printStackTrace();
			}
		}

		try{
			if(conn!=null){
				conn.close();
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		return course;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Course> findCoursesByDepartment(String departmentName) {
		// Intentional example of how JDBC access worked before JdbcTemplate.
		
		List<Course> courseList = new ArrayList<Course>();
		String sql = "select * from Course where department=?";
		
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs;

		try {
			// Load the database driver
			Class.forName("org.hsqldb.jdbcDriver");

			// Get a connection to the database
			conn = DriverManager.getConnection("jdbc:hsqldb:mem:university") ;

			// Get a statement from the connection
			stmt = conn.prepareStatement(sql);
			stmt.setString (1, departmentName);

			// Execute query
			rs = stmt.executeQuery();

			// Extract data from the result set
			while(rs.next()){
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
				courseList.add(course);
			}

			// Clean up
			rs.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch (Exception e){
			// Handle other errors
			e.printStackTrace();

		}
		finally {
			//finally block used to close resources
			try{
				if(stmt!=null){
					stmt.close();
				}
			}
			catch(SQLException se2){
				se2.printStackTrace();
			}
		}

		try{
			if(conn!=null){
				conn.close();
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		
		return courseList;
	}

	/**
	 * {@inheritDoc}
	 */
	public void insert(Course course) {
		// Intentional example of how JDBC access worked before JdbcTemplate.
		
		int insertCount = 0;
		String sql = "insert into Course(name, description, department, creditHours, level) values (?,?,?,?,?)";
		
		PreparedStatement stmt = null;
		Connection conn = null;

		try {
			// Load the database driver
			Class.forName("org.hsqldb.jdbcDriver");

			// Get a connection to the database
			conn = DriverManager.getConnection("jdbc:hsqldb:mem:university") ;

			// Get a statement from the connection
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, course.getName());
			stmt.setString(2, course.getDescription());
			stmt.setString(3, course.getDepartment());
			stmt.setDouble(4, course.getCreditHours());
			stmt.setString(5, course.getLevel().toString());

			// Execute query
			insertCount = stmt.executeUpdate();

			// Clean up
			stmt.close();
			conn.close();
		} 
		catch (SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch (Exception e){
			// Handle other errors
			e.printStackTrace();

		}
		finally {
			//finally block used to close resources
			try{
				if(stmt!=null){
					stmt.close();
				}
			}
			catch(SQLException se2){
				se2.printStackTrace();
			}
		}

		try{
			if(conn!=null){
				conn.close();
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		
		log.info(insertCount + " rows inserted into Course table");
	}


	/**
	 * {@inheritDoc}
	 */
	public void update(Course course) {
		// Intentional example of how JDBC access worked before JdbcTemplate.
		
		int updateCount = 0;
		String sql = "update Course set name=?, description=?, department=?, creditHours=?, level=? where course_id=?";
				
		PreparedStatement stmt = null;
		Connection conn = null;

		try {
			// Load the database driver
			Class.forName("org.hsqldb.jdbcDriver");

			// Get a connection to the database
			conn = DriverManager.getConnection("jdbc:hsqldb:mem:university") ;

			// Get a statement from the connection
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, course.getName());
			stmt.setString(2, course.getDescription());
			stmt.setString(3, course.getDepartment());
			stmt.setDouble(4, course.getCreditHours());
			stmt.setString(5, course.getLevel().toString());
			stmt.setLong(6, course.getId());
			
			// Execute query
			updateCount = stmt.executeUpdate();

			// Clean up
			stmt.close();
			conn.close();
		} 
		catch (SQLException se) {
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch (Exception e){
			// Handle other errors
			e.printStackTrace();

		}
		finally {
			//finally block used to close resources
			try{
				if(stmt!=null){
					stmt.close();
				}
			}
			catch(SQLException se2){
				se2.printStackTrace();
			}
		}

		try{
			if(conn!=null){
				conn.close();
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		
		log.info(updateCount + " rows updated in Course table");
	}
	
}
