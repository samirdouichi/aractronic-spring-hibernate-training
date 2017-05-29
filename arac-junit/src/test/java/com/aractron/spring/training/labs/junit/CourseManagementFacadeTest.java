package com.aractron.spring.training.labs.junit;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.ExpectedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aractron.spring.training.dao.CourseSessionDao;
import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.exception.CourseManagementException;
import com.aractron.spring.training.service.CourseSessionFactory;
import com.aractron.spring.training.service.impl.CourseManagementFacadeDaoImpl;

/**
 * Test class for validating the behavior of specific CourseManagementFacade
 * implementations
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CourseManagementFacadeTest {

	/**
	 * Verify that CourseManagementFacadeDaoImpl returns a new CourseSession
	 * with the following properties:
	 * <ol>
	 * <li>Contains the Course provided
	 * <li>Contains the term provided
	 * <li>has an ID that is not 0
	 * </ol>
	 * 
	 * @throws CourseManagementException
	 */
	@Test
	public void testDaoImplAddSession() throws CourseManagementException {
		Course course = new Course();
		course.setName("test");
		CourseManagementFacadeDaoImpl cmDaoImpl = setupMocksForDaoImplAddSession(
				course, "SPRING");
		CourseSession session = cmDaoImpl.addSession(course, "SPRING");

		assertNotNull(session);
		assertEquals(course, session.getCourse());
		assertFalse(new Long(0).equals(session.getId()));
	}

	/**
	 * Verify that CourseManagementFacadeDaoImpl throws
	 * CourseManagementException when provided with an invalid term.
	 * 
	 * @throws CourseManagementException
	 */
	@Test
	@ExpectedException(CourseManagementException.class)
	public void testDaoImplAddSessionWithInvalidTerm()
			throws CourseManagementException {
		Course course = new Course();
		course.setName("test");
		CourseManagementFacadeDaoImpl cmDaoImpl = setupMocksForDaoImplAddSession(
				course, null);
		cmDaoImpl.addSession(course, "INVALID TERM");
	}

	/**
	 * Sets up the mock collaborators for the addSession() test cases
	 * 
	 * 
	 * @param course
	 *            Course instance for positive test case
	 * @param term
	 *            session term for positive test case
	 * @return a configured instance of CourseManagementFacadeDaoImpl
	 */
	private CourseManagementFacadeDaoImpl setupMocksForDaoImplAddSession(
			Course course, String term) {
		CourseSession expectedSession = new CourseSession();
		expectedSession.setTerm(term);
		expectedSession.setCourse(course);
		expectedSession.setId(1l);

		// Use EasyMock to mock the behavior of collaborators.
		// CourseManagementFacadeDaoImpl.testAddSession() calls:
		// CourseSessionFactory.createSession()
		CourseSessionFactory mockCSFactory = createMock(CourseSessionFactory.class);
		CourseSession newSession = new CourseSession();
		newSession.setCourse(course);
		expect(mockCSFactory.createSession(course)).andReturn(newSession);

		// CourseSession.insert()
		CourseSessionDao mockCourseSessionDao = createMock(CourseSessionDao.class);
		mockCourseSessionDao.insert((CourseSession) anyObject());
		// CourseSession.findCourseSessionBySession()
		expect(
				mockCourseSessionDao.findCourseSessionBySession(
						(String) anyObject(), (String) anyObject())).andReturn(
				expectedSession);

		// put the mocks into playback mode
		replay(mockCSFactory);
		replay(mockCourseSessionDao);

		CourseManagementFacadeDaoImpl mgr = new CourseManagementFacadeDaoImpl();
		mgr.setCourseSessionFactory(mockCSFactory);
		mgr.setCourseSessionDao(mockCourseSessionDao);
		return mgr;
	}

}
