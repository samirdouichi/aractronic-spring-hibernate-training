package com.aractron.spring.training.web.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
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
@RequestMapping("/register.htm")
@SessionAttributes("registerForm")
/**
 * The Class RegisterController.
 */
// Student exercise - consolidate the student mgmt controllers
public class StudentRegistrationController {
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private UserFacade userFacade;
	@Autowired
	private BeanValidator validator;

	/**
	 * Show register form.
	 * 
	 * @param model
	 *            the model
	 * @return the string
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String showRegisterForm(ModelMap model) {
		RegisterForm regForm = new RegisterForm();
		model.addAttribute(regForm);
		return "registration";
	}

	/**
	 * On submit.
	 * 
	 * @param regForm
	 *            the reg form
	 * @param result
	 *            the result
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(
			@ModelAttribute("registerForm") RegisterForm regForm,
			BindingResult result, HttpSession session) {
		String viewName = "registration";
		logger.info("Attempting to register user '" + regForm.getUsername()
				+ "'");
		validator.validate(regForm, result);
		if (!result.hasErrors()) {
			try {
				userFacade.saveUser(regForm.transform());
				Student student = (Student) userFacade.findUser(
						regForm.getUsername(), regForm.getPassword());
				session.setAttribute(LoginController.STUDENT_KEY, student);
			} catch (UserManagementException e) {
				e.printStackTrace();
				result.reject("Fatal error: " + e);
				// TODO - should go to system error page
			}
			viewName = "successPage";
		}

		return new ModelAndView(viewName);
	}

	public UserFacade getUserFacade() {
		return userFacade;
	}

	public void setUserFacade(UserFacade userFacade) {
		this.userFacade = userFacade;
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(BeanValidator validator) {
		this.validator = validator;
	}
}
