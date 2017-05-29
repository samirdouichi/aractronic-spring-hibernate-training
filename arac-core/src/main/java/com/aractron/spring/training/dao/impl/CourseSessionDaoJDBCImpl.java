package com.aractron.spring.training.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.aractron.spring.training.dao.CourseSessionDao;
import com.aractron.spring.training.dao.mappers.CourseSessionExtractor;
import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.domain.Student;

/**
 * The JDBC implementation of the CourseSessionDao interface.
 * 
 * @author vanessa.ortiz
 */
@Repository
public class CourseSessionDaoJDBCImpl implements CourseSessionDao {
	
	/**
	 * Logger instance
	 */
	private static Log log = LogFactory.getLog((CourseSessionDaoJDBCImpl.class));
	
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
	public CourseSessionDaoJDBCImpl(DataSource dataSource) {
		this.setJdbcTemplate(new JdbcTemplate(dataSource));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * If not results found, this method will return an empty list. This method
	 * returns all of the course session objects in the table, even if they have
	 * no students enrolled.
	 */
	public List<CourseSession> findAllCourseSessions(){
		
		// This first query will return a CourseSession object with a Set of Students which will have their
		// CourseSession Set initialized to a empty Set. This returns levels 1 & 2 of data.
		String sql1 = "select cs.COURSE_SESSION_ID, cs.TERM, "
				+ " c.COURSE_ID, c.NAME, c.DESCRIPTION, c.DEPARTMENT, c.CREDITHOURS, c.LEVEL,"
				+ " f.FACULTY_ID, f.USERNAME as FACULTY_USERNAME, f.PASSWORD AS FACULTY_PASSWORD, f.FIRSTNAME AS FACULTY_FIRSTNAME,"
				+ " f.LASTNAME AS FACULTY_LASTNAME, f.EMAIL AS FACULTY_EMAIL, f.PHONENUMBER AS FACULTY_PHONENUMBER, f.DEPARTMENT AS FACULTY_DEPARTMENT,"
				+ " s.STUDENT_ID, s.USERNAME, s.PASSWORD, s.FIRSTNAME, s.LASTNAME, s.EMAIL, s.PHONENUMBER, s.CLASSIFICATION"
				+ " from coursesession cs"
				+ " left join COURSE c on (cs.course_id = c.course_id)"
				+ " left join faculty f on (cs.faculty_id = f.faculty_id)"
				+ " left join coursesession_enrollment cse on (cs.COURSE_SESSION_ID = cse.COURSE_SESSION_ID)"
				+ " left join student s on (cse.student_id = s.student_id)";
		System.out.println(sql1);

		List<CourseSession> courseSessionList1 = jdbcTemplate.query(sql1, new CourseSessionExtractor());
		
		if(courseSessionList1 == null) {
			return new ArrayList<CourseSession>();
		}
			
		if (courseSessionList1 != null && !courseSessionList1.isEmpty()){
			
			// This next query will return all of the CourseSession objects for each Student in the CourseSession
			// object that was previously retrieved.
			String sql2 = "select cs.COURSE_SESSION_ID, cs.TERM, "
				+ " c.COURSE_ID, c.NAME, c.DESCRIPTION, c.DEPARTMENT, c.CREDITHOURS, c.LEVEL,"
				+ " f.FACULTY_ID, f.USERNAME as FACULTY_USERNAME, f.PASSWORD AS FACULTY_PASSWORD, f.FIRSTNAME AS FACULTY_FIRSTNAME,"
				+ " f.LASTNAME AS FACULTY_LASTNAME, f.EMAIL AS FACULTY_EMAIL, f.PHONENUMBER AS FACULTY_PHONENUMBER, f.DEPARTMENT AS FACULTY_DEPARTMENT,"
				+ " s.STUDENT_ID, s.USERNAME, s.PASSWORD, s.FIRSTNAME, s.LASTNAME, s.EMAIL, s.PHONENUMBER, s.CLASSIFICATION"
				+ " from coursesession cs"
				+ " left join course c on (cs.course_id = c.course_id)"
				+ " left join faculty f on (cs.faculty_id = f.faculty_id)"
				+ " left join coursesession_enrollment cse on (cs.COURSE_SESSION_ID = cse.COURSE_SESSION_ID)"
				+ " left join student s on (cse.student_id = s.student_id)"
				+ " where s.username=?";
			
			List<CourseSession> courseSessionList2;
			
			// This next query will return all of the CourseSession objects for each Student in 
			// each of the CourseSession object from the returned array.
			for (CourseSession cs : courseSessionList1) {
				for (Student stu : cs.getStudents()) {
					Object[] args2 = {stu.getUserName()};
					courseSessionList2 = jdbcTemplate.query(sql2, args2, new CourseSessionExtractor());

					if (courseSessionList2 != null && !courseSessionList2.isEmpty()) {
						stu.getRegisteredCourses().addAll(courseSessionList2);
					}
				}
			}
		}
		return courseSessionList1;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CourseSession> findAllCourseSessions(Course course) {
		
		String sql1 = "select cs.COURSE_SESSION_ID, cs.TERM, "
				+ " c.COURSE_ID, c.NAME, c.DESCRIPTION, c.DEPARTMENT, c.CREDITHOURS, c.LEVEL,"
				+ " f.FACULTY_ID, f.USERNAME as FACULTY_USERNAME, f.PASSWORD AS FACULTY_PASSWORD, f.FIRSTNAME AS FACULTY_FIRSTNAME,"
				+ " f.LASTNAME AS FACULTY_LASTNAME, f.EMAIL AS FACULTY_EMAIL, f.PHONENUMBER AS FACULTY_PHONENUMBER, f.DEPARTMENT AS FACULTY_DEPARTMENT,"
				+ " s.STUDENT_ID, s.USERNAME, s.PASSWORD, s.FIRSTNAME, s.LASTNAME, s.EMAIL, s.PHONENUMBER, s.CLASSIFICATION"
				+ " from coursesession cs"
				+ " left join COURSE c on (cs.course_id = c.course_id)"
				+ " left join faculty f on (cs.faculty_id = f.faculty_id)"
				+ " left join coursesession_enrollment cse on (cs.COURSE_SESSION_ID = cse.COURSE_SESSION_ID)"
				+ " left join student s on (cse.student_id = s.student_id)"
				+ " where cs.COURSE_ID=?";

		Object[] args1 = { course.getId() };
		List<CourseSession> courseSessionList1 = jdbcTemplate.query(sql1, args1,	new CourseSessionExtractor());
		
		if(courseSessionList1 == null) {
			return new ArrayList<CourseSession>();
		}
		
		if (courseSessionList1 != null && !courseSessionList1.isEmpty()){
			
			// This next query will return all of the CourseSession objects for each Student in the CourseSession
			// object that was previously retrieved.
			String sql2 = "select cs.COURSE_SESSION_ID, cs.TERM, "
				+ " c.COURSE_ID, c.NAME, c.DESCRIPTION, c.DEPARTMENT, c.CREDITHOURS, c.LEVEL,"
				+ " f.FACULTY_ID, f.USERNAME as FACULTY_USERNAME, f.PASSWORD AS FACULTY_PASSWORD, f.FIRSTNAME AS FACULTY_FIRSTNAME,"
				+ " f.LASTNAME AS FACULTY_LASTNAME, f.EMAIL AS FACULTY_EMAIL, f.PHONENUMBER AS FACULTY_PHONENUMBER, f.DEPARTMENT AS FACULTY_DEPARTMENT,"
				+ " s.STUDENT_ID, s.USERNAME, s.PASSWORD, s.FIRSTNAME, s.LASTNAME, s.EMAIL, s.PHONENUMBER, s.CLASSIFICATION"
				+ " from coursesession cs"
				+ " left join course c on (cs.course_id = c.course_id)"
				+ " left join faculty f on (cs.faculty_id = f.faculty_id)"
				+ " left join coursesession_enrollment cse on (cs.COURSE_SESSION_ID = cse.COURSE_SESSION_ID)"
				+ " left join student s on (cse.student_id = s.student_id)"
				+ " where s.username=?";
			
			List<CourseSession> courseSessionList2;
			
			// This next query will return all of the CourseSession objects for each Student in 
			// each of the CourseSession object from the returned array.
			for (CourseSession cs : courseSessionList1) {
				for (Student stu : cs.getStudents()) {
					Object[] args2 = {stu.getUserName()};
					courseSessionList2 = jdbcTemplate.query(sql2, args2, new CourseSessionExtractor());

					if (courseSessionList2 != null && !courseSessionList2.isEmpty()) {
						stu.getRegisteredCourses().addAll(courseSessionList2);
					}
				}
			}
		}
		return courseSessionList1;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * If not results found, this method will return an empty list. This method
	 * returns a course session object, even if it has no students enrolled. 
	 */
	public CourseSession findCourseSessionById(Long id) {
		String sql = "select cs.COURSE_SESSION_ID, cs.TERM, "
				+ " c.COURSE_ID, c.NAME, c.DESCRIPTION, c.DEPARTMENT, c.CREDITHOURS, c.LEVEL,"
				+ " f.FACULTY_ID, f.USERNAME as FACULTY_USERNAME, f.PASSWORD AS FACULTY_PASSWORD, f.FIRSTNAME AS FACULTY_FIRSTNAME,"
				+ " f.LASTNAME AS FACULTY_LASTNAME, f.EMAIL AS FACULTY_EMAIL, f.PHONENUMBER AS FACULTY_PHONENUMBER, f.DEPARTMENT AS FACULTY_DEPARTMENT,"
				+ " s.STUDENT_ID, s.USERNAME, s.PASSWORD, s.FIRSTNAME, s.LASTNAME, s.EMAIL, s.PHONENUMBER, s.CLASSIFICATION"
				+ " from coursesession cs"
				+ " left join COURSE c on (cs.course_id = c.course_id)"
				+ " left join faculty f on (cs.faculty_id = f.faculty_id)"
				+ " left join coursesession_enrollment cse on (cs.COURSE_SESSION_ID = cse.COURSE_SESSION_ID)"
				+ " left join student s on (cse.student_id = s.student_id)"
				+ " where cs.COURSE_SESSION_ID=?";

		Object[] args = { id };

		List<CourseSession> courseSessionList = jdbcTemplate.query(sql, args, new CourseSessionExtractor());

		CourseSession courseSession = null;
		if (courseSessionList != null && !courseSessionList.isEmpty()) {
			courseSession = courseSessionList.get(0);
		}

		return courseSession;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * If not results found, this method will return an empty list. This method
	 * returns a course session object, even if it has no students enrolled.
	 */
	public CourseSession findCourseSessionBySession(String courseName, String term) {

		// This first query will return a CourseSession object with a Set of Students which will have their
		// CourseSession Set initialized to a empty Set. This returns levels 1 & 2 of data.
		String sql1 = "select cs.COURSE_SESSION_ID, cs.TERM, "
				+ " c.COURSE_ID, c.NAME, c.DESCRIPTION, c.DEPARTMENT, c.CREDITHOURS, c.LEVEL,"
				+ " f.FACULTY_ID, f.USERNAME as FACULTY_USERNAME, f.PASSWORD AS FACULTY_PASSWORD, f.FIRSTNAME AS FACULTY_FIRSTNAME,"
				+ " f.LASTNAME AS FACULTY_LASTNAME, f.EMAIL AS FACULTY_EMAIL, f.PHONENUMBER AS FACULTY_PHONENUMBER, f.DEPARTMENT AS FACULTY_DEPARTMENT,"
				+ " s.STUDENT_ID, s.USERNAME, s.PASSWORD, s.FIRSTNAME, s.LASTNAME, s.EMAIL, s.PHONENUMBER, s.CLASSIFICATION"
				+ " from coursesession cs"
				+ " left join course c on (cs.course_id = c.course_id)"
				+ " left join faculty f on (cs.faculty_id = f.faculty_id)"
				+ " left join coursesession_enrollment cse on (cs.COURSE_SESSION_ID = cse.COURSE_SESSION_ID)"
				+ " left join student s on (cse.student_id = s.student_id)"
				+ " where c.name=? and cs.term=?";

		Object[] args1 = { courseName, term };
		log.info(sql1);
		List<CourseSession> courseSessionList1 = jdbcTemplate.query(sql1, args1, new CourseSessionExtractor());

		CourseSession courseSession = null;
		
		if (courseSessionList1 != null && !courseSessionList1.isEmpty() 
				&& (courseSessionList1.get(0).getStudents() != null && !courseSessionList1.get(0).getStudents().isEmpty())) {
			
			// This next query will return all of the CourseSession objects for each Student in 
			// the CourseSession object that was previously retrieved.
			String sql2 = "select cs.COURSE_SESSION_ID, cs.TERM, "
				+ " c.COURSE_ID, c.NAME, c.DESCRIPTION, c.DEPARTMENT, c.CREDITHOURS, c.LEVEL,"
				+ " f.FACULTY_ID, f.USERNAME as FACULTY_USERNAME, f.PASSWORD AS FACULTY_PASSWORD, f.FIRSTNAME AS FACULTY_FIRSTNAME,"
				+ " f.LASTNAME AS FACULTY_LASTNAME, f.EMAIL AS FACULTY_EMAIL, f.PHONENUMBER AS FACULTY_PHONENUMBER, f.DEPARTMENT AS FACULTY_DEPARTMENT,"
				+ " s.STUDENT_ID, s.USERNAME, s.PASSWORD, s.FIRSTNAME, s.LASTNAME, s.EMAIL, s.PHONENUMBER, s.CLASSIFICATION"
				+ " from coursesession cs"
				+ " left join course c on (cs.course_id = c.course_id)"
				+ " left join faculty f on (cs.faculty_id = f.faculty_id)"
				+ " left join coursesession_enrollment cse on (cs.COURSE_SESSION_ID = cse.COURSE_SESSION_ID)"
				+ " left join student s on (cse.student_id = s.student_id)"
				+ " where s.username=?";
			
			List<CourseSession> courseSessionList2;
			
			for(Student stu : courseSessionList1.get(0).getStudents()){
				Object[] args2 = {stu.getUserName()};
				courseSessionList2 = jdbcTemplate.query(sql2, args2, new CourseSessionExtractor());
				
				if(courseSessionList2 != null && !courseSessionList2.isEmpty()){
					stu.getRegisteredCourses().addAll(courseSessionList2);
				}
			}
			courseSession = courseSessionList1.get(0);
		}

		return courseSession;
	}

	/**
	 * {@inheritDoc}
	 */
	public void insert(CourseSession courseSession) {
		String sql = "insert into coursesession(course_id, faculty_id, term) values (?,?,?)";

		int insertCount = jdbcTemplate.update(sql, courseSession.getCourse()
				.getId(), (courseSession.getFaculty() == null ? null
				: courseSession.getFaculty().getId()), courseSession.getTerm());

		log.info(insertCount + " rows inserted into CourseSession table");
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(CourseSession courseSession) {
		String sql = "update coursesession set course_id=?, faculty_id=?, term=? where course_session_id=?";

		int updateCount = jdbcTemplate.update(sql, courseSession.getCourse()
				.getId(), (courseSession.getFaculty() == null ? null
				: courseSession.getFaculty().getId()), courseSession.getTerm(),
				courseSession.getId());

		log.info(updateCount + " rows updated in the CourseSession table");
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void delete(CourseSession courseSession) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	/**
	 * @param jdbcTemplate
	 *            the jdbcTemplate to set
	 */
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
