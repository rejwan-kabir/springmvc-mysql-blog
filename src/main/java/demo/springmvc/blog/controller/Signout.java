package demo.springmvc.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/signout")
public class Signout {
	@RequestMapping( method = RequestMethod.GET)
	public String signOut( HttpServletRequest request ){
		request.getSession().setAttribute("loggedInUser", null);
		return "redirect:/";
	}
}
