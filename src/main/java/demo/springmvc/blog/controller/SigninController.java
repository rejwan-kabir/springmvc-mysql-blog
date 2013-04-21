package demo.springmvc.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import demo.springmvc.blog.domain.User;
import demo.springmvc.blog.service.UserService;

@Controller
@RequestMapping("/signin")
public class SigninController {
	@Autowired
	private UserService userService;

	private static final Logger logger = LoggerFactory
			.getLogger(SigninController.class);

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getSigninForm() {
		return new ModelAndView("signin", "loginUser", new User());
	}

	@RequestMapping(method = RequestMethod.POST)
	public String signinUser(@ModelAttribute("loginUser") User user,
			BindingResult bindingResult, Model model, HttpServletRequest request) {
		User loggedInUser = this.userService.checkUser(user);
		if (loggedInUser != null) {
			logger.info(loggedInUser.getUsername() + "\t"
					+ loggedInUser.getPassword());
			request.getSession().setAttribute("loggedInUser", loggedInUser);
			return "redirect:/home";
		}
		model.addAttribute("error", true);
		return "signin";
	}
}
