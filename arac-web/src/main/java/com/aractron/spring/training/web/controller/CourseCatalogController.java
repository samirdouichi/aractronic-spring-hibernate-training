package com.aractron.spring.training.web.controller;

import java.util.Collection;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.exception.CourseManagementException;
import com.aractron.spring.training.service.CourseManagementFacade;

@Controller
@RequestMapping("/catalog.htm")
/**
 * This class is used for course catalog.
 */
public class CourseCatalogController {
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private CourseManagementFacade courseManagementService;

	/**
	 * Show catalog page.
	 * 
	 * @param model
	 *            the model
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showCatalogPage(ModelMap model) {
		Collection<Course> courses = courseManagementService.findAllCourses();
		Collection<CourseSession> catalog = new TreeSet<CourseSession>();
		for (Course course : courses) {
			try {
				catalog.addAll(courseManagementService.getSessions(course));
			} catch (CourseManagementException e) {
				logger.error("Unable to locate sessions for course " + course);
				e.printStackTrace();
			}
		}
		return new ModelAndView("courseCatalog", "courseList", catalog);
	}

	public CourseManagementFacade getCourseManagementService() {
		return courseManagementService;
	}

	public void setCourseManagementService(
			CourseManagementFacade courseManagementService) {
		this.courseManagementService = courseManagementService;
	}
}
