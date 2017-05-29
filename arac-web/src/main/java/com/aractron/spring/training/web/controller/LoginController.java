package com.aractron.spring.training.web.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.domain.Student;
import com.aractron.spring.training.exception.CourseManagementException;
import com.aractron.spring.training.service.CourseManagementFacade;
import com.aractron.spring.training.service.UserFacade;
import com.aractron.spring.training.web.forms.LoginForm;

@Controller
/**
 * The Class LoginController.
 */
public class LoginController {
	private final Log logger = LogFactory.getLog(getClass());
	public static final int MAX_ENROLLMENT = 10;
	public static final String STUDENT_KEY = "session.key.student";
	@Autowired
	private UserFacade studentService;
	@Autowired
	private CourseManagementFacade courseManagementService;

	/**
	 * Clear the student and return to the login page.
	 * 
	 * @return view name
	 */
	@RequestMapping("/logout")
	public ModelAndView logout() {
		return new ModelAndView("index", "loginForm", new LoginForm());
	}

	/**
	 * Show the form on a GET
	 * 
	 * @param model
	 * @return view name
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginForm(ModelMap model) {
		LoginForm loginform = new LoginForm();
		model.addAttribute("loginForm", loginform);
		return "index";
	}

	/**
	 * Process the form on a POST
	 * 
	 * @param loginForm
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView onSubmit(
			@ModelAttribute("loginForm") LoginForm loginForm,
			HttpSession session) {
		logger.info(loginForm.getUsername() + " attempting log in");
		Student student = (Student) this.studentService.findUser(
				loginForm.getUsername(), loginForm.getPassword());
		if (student == null) {
			return new ModelAndView("loginerror");
		} else {
			session.setAttribute(STUDENT_KEY, student);
			Map<String, Object> models = new HashMap<String, Object>();
			models.put("student", student);
			models.put("courseList", getAvailableCourses(student));
			return new ModelAndView("myportal", models);
		}
	}

	@RequestMapping("/home")
	public ModelAndView home(HttpSession session) {
		ModelAndView mav = new ModelAndView("index", "loginForm",
				new LoginForm());
		Student student = (Student) session.getAttribute(STUDENT_KEY);
		if (student != null) {
			Map<String, Object> models = new HashMap<String, Object>();
			models.put("student", student);
			models.put("courseList", getAvailableCourses(student));
			mav = new ModelAndView("myportal", models);
		}
		return mav;
	}

	/**
	 * Find the CourseSessions that the student may enroll in.
	 * 
	 * @param student
	 * @return empty set by default
	 */
	private Collection<CourseSession> getAvailableCourses(Student student) {
		Collection<CourseSession> courseList = new TreeSet<CourseSession>();

		Collection<Course> courses = courseManagementService.findAllCourses();
		for (Course course : courses) {
			try {
				// Only display courses the student is eligible to enroll in
				if (course.getLevel().compareTo(student.getClassification()) <= 0) {
					Collection<CourseSession> sessions = courseManagementService
							.getSessions(course);
					for (CourseSession session : sessions) {
						if (session.getStudents().size() < MAX_ENROLLMENT
								&& !student.getRegisteredCourses().contains(
										session)) {
							courseList.add(session);
						}
					}
				}
			} catch (CourseManagementException e) {
				logger.error("Unable to locate sessions for course " + course);
				e.printStackTrace();
			}
		}
		return courseList;
	}

	public UserFacade getStudentService() {
		return studentService;
	}

	public void setStudentService(UserFacade studentService) {
		this.studentService = studentService;
	}

	public CourseManagementFacade getCourseManagementService() {
		return courseManagementService;
	}

	public void setCourseManagementService(
			CourseManagementFacade courseManagementService) {
		this.courseManagementService = courseManagementService;
	}
}
