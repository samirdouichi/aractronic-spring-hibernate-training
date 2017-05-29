package com.aractron.spring.training.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.domain.Faculty;
import com.aractron.spring.training.exception.CourseManagementException;
import com.aractron.spring.training.service.CourseManagementFacade;

/**
 * Trivial implementation of CourseManagementFacade for testing purposes.
 */
public class CourseManagementFacadeStubImpl implements CourseManagementFacade {

	private Map<Course, List<CourseSession>> sessions = new HashMap<Course, List<CourseSession>>();

	public CourseSession registerFaculty(Faculty faculty, CourseSession session)
			throws CourseManagementException {
		session.setFaculty(faculty);
		return session;
	}

	public CourseSession unregisterFaculty(Faculty faculty,
			CourseSession session) throws CourseManagementException {
		session.setFaculty(null);
		return session;
	}

	public CourseSession addSession(Course course, String term)
			throws CourseManagementException {
		CourseSession session = new CourseSession();
		session.setCourse(course);
		session.setTerm(term);

		if (!sessions.containsKey(course)) {
			List<CourseSession> sessionList = new ArrayList<CourseSession>();
			sessions.put(course, sessionList);
		}
		sessions.get(course).add(session);

		return session;
	}

	public void removeSession(CourseSession session)
			throws CourseManagementException {
		List<CourseSession> sessionList = sessions.get(session.getCourse());
		if (sessionList == null || sessionList.isEmpty()) {
			throw new CourseManagementException("There are no sessions of "
					+ session.getCourse().getName());
		} else {
			sessionList.remove(session);
		}
	}

	public List<CourseSession> getSessions(Course course)
			throws CourseManagementException {
		return sessions.get(course);
	}

	public void addNewCourseIntoCatalog(Course course) {
		// TODO Auto-generated method stub
		
	}

	public void removeCourseFromCatalog(String courseName) {
		// TODO Auto-generated method stub
		
	}

	public Course findCourseByName(String courseName) {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateCourseInfo(Course course) {
		// TODO Auto-generated method stub
		
	}

	public Collection<Course> findAllCourses() {
		// TODO Auto-generated method stub
		return null;
	}

}
