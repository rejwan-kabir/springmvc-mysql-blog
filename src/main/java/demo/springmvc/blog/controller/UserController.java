package demo.springmvc.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import demo.springmvc.blog.domain.User;
import demo.springmvc.blog.service.PostService;
import demo.springmvc.blog.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String getAllUser() {
		return "user";
	}

	@RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
	public String getUser(@PathVariable("user_id") int user_id, Model model, HttpServletRequest request) {
		User loggedInUser = (User)request.getSession().getAttribute("loggedInUser");
		if( loggedInUser != null && loggedInUser.getId() == user_id ){
			return "redirect:/home";
		}
		model.addAttribute("allPostFromUser",this.postService.getAllPost(user_id));
		return "user";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView getUserCreationForm() {
		return new ModelAndView("signup", "user", new User());
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createUser(@ModelAttribute("user") User user,
			BindingResult bindingResult, HttpServletRequest request) {
		logger.info("User : " + user.getFullname());
		this.userService.createUser(user);
		request.getSession().setAttribute("loggedInUser", user);
		return "redirect:/home";
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String viewAllUser(Model data) {
		List<User> allUser = this.userService.getAllUser();
		data.addAttribute("allUser", allUser);
		return "user";
	}
}
