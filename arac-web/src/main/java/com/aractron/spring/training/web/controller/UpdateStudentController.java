package com.aractron.spring.training.web.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.aractron.spring.training.domain.Student;
import com.aractron.spring.training.exception.UserManagementException;
import com.aractron.spring.training.service.UserFacade;
import com.aractron.spring.training.web.forms.BeanValidator;
import com.aractron.spring.training.web.forms.RegisterForm;

@Controller
@RequestMapping("/updateinfo.htm")
@SessionAttributes("registerForm")
/**
 * This class is used for Update Student Information.
 */
public class UpdateStudentController {
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private BeanValidator validator;

	/**
	 * Show update form.
	 * 
	 * @param model
	 *            the model
	 * @param session
	 *            the session
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showUpdateRegForm(ModelMap model, HttpSession session) {
		RegisterForm regForm = new RegisterForm();
		Student stu = (Student) session
				.getAttribute(LoginController.STUDENT_KEY);
		regForm.setFirstname(stu.getFirstName());
		regForm.setLastname(stu.getLastName());
		regForm.setUsername(stu.getUserName());
		regForm.setPassword(stu.getPassword());
		regForm.setEmail(stu.getEmail());
		regForm.setPhoneNo(stu.getPhoneNumber());
		regForm.setClassification(stu.getClassification());
		model.addAttribute(regForm);
		return new ModelAndView("updatemyinfo", "student", regForm);

	}

	/**
	 * Process the update on submit.
	 * 
	 * @param regForm
	 *            the reg form
	 * @param result
	 *            the result
	 * @param session
	 *            the session
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmitRegForm (
			@ModelAttribute("registerForm") RegisterForm regForm,
			BindingResult result, HttpSession session) {
		validator.validate(regForm, result);
		if (result.hasErrors()) {
			return new ModelAndView("updatemyinfo", "student", regForm);
		} else {
			try {
				updateStudent(session, regForm);
			} catch (UserManagementException e) {
				e.printStackTrace();
				result.reject("Fatal error: " + e);
				return new ModelAndView("updatemyinfo");
			}
			return new ModelAndView("successPage");
		}
	}

	/**
	 * Implementation of how the controller updates the Student
	 * 
	 * @param session
	 * @param regForm
	 * @throws UserManagementException
	 */
	private void updateStudent(HttpSession session, RegisterForm regForm)
			throws UserManagementException {
		Student student = (Student) session
				.getAttribute(LoginController.STUDENT_KEY);
		regForm.updateEntity(student);
		userFacade.saveUser(student);
		logger.info("Updated student " + student.getUserName());
		session.setAttribute(LoginController.STUDENT_KEY, student);
	}

	public UserFacade getUserFacade() {
		return userFacade;
	}

	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}

	public BeanValidator getValidator() {
		return validator;
	}

	public void setValidator(BeanValidator validator) {
		this.validator = validator;
	}
}
