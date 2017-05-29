package com.aractron.spring.training.web.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.domain.Student;
import com.aractron.spring.training.exception.CourseRegistrationException;
import com.aractron.spring.training.service.CourseRegistrationFacade;
import com.aractron.spring.training.service.UserFacade;

/**
 * A single controller class can be responsible for multiple request mappings.
 * 
 */
@Controller
public class CourseRegistrationController {
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private CourseRegistrationFacade courseRegistrationService;
	@Autowired
	private UserFacade userFacade;

	/**
	 * Perform un-registration.
	 * 
	 * @param courseSessionId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/courseunreg.htm")
	public ModelAndView unregisterCourse(@RequestParam Long courseSessionId,
			HttpSession session) {
		Student student = (Student) session
				.getAttribute(LoginController.STUDENT_KEY);
		logger.info("Unregistering " + student.getUserName()
				+ " from courseSession " + courseSessionId);
		CourseSession courseSession = null;
		try {
			courseSession = courseRegistrationService.unregisterStudent(
					student, courseSessionId);
			student = (Student) userFacade.findUser(student.getUserName(),
					student.getPassword());
			session.setAttribute(LoginController.STUDENT_KEY, student);
		} catch (CourseRegistrationException e) {
			e.printStackTrace();
			// TODO - handle/report error
		}
		return new ModelAndView("successfulUnRegistration", "courseSession",
				courseSession);
	}

	@RequestMapping(value = "/coursereg.htm")
	public ModelAndView registerCourse(@RequestParam Long courseSessionId,
			HttpSession session) {
		Student student = (Student) session
				.getAttribute(LoginController.STUDENT_KEY);
		logger.info("Registering " + student.getUserName()
				+ " to courseSession " + courseSessionId);
		CourseSession courseSession = null;
		try {
			courseSession = courseRegistrationService.registerStudent(student,
					courseSessionId);
			session.setAttribute(LoginController.STUDENT_KEY, student);
		} catch (CourseRegistrationException e) {
			e.printStackTrace();
			// TODO - handle/report error
		}
		return new ModelAndView("successfulRegistration", "courseSession",
				courseSession);
	}
}
