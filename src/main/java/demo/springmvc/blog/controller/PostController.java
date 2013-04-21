package demo.springmvc.blog.controller;

import java.util.Date;

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

import demo.springmvc.blog.domain.Comment;
import demo.springmvc.blog.domain.Post;
import demo.springmvc.blog.domain.User;
import demo.springmvc.blog.service.CommentService;
import demo.springmvc.blog.service.PostService;

@Controller
@RequestMapping("/post")
public class PostController {
	private static final Logger logger = LoggerFactory
			.getLogger(PostController.class);

	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	@RequestMapping(method = RequestMethod.GET)
	public String getAllPost() {
		return "post";
	}

	@RequestMapping(value = "/{post_id}", method = RequestMethod.GET)
	public ModelAndView getPostOfUesrId(@PathVariable("post_id") int post_id,
			Model model, HttpServletRequest request) {
		model.addAttribute("postOfId", this.postService.getPostOfId(post_id));
		model.addAttribute("allCommentOfPost",
				this.commentService.getAllcommentOfPost(post_id));
		User loggedInUser = (User) request.getSession().getAttribute(
				"loggedInUser");
		if (loggedInUser == null) {
			model.addAttribute("loggedIn", false);
		}
		return new ModelAndView("post", "comment", new Comment());
	}

	@RequestMapping(value = "/{post_id}", method = RequestMethod.POST)
	public String postCommentOfPostId(@PathVariable("post_id") int post_id,
			@ModelAttribute("comment") Comment comment,
			BindingResult bindingResult, Model model, HttpServletRequest request) {
		User loggedInUser = (User) request.getSession().getAttribute(
				"loggedInUser");
		comment.setUser_id(loggedInUser.getId());
		comment.setPost_id(post_id);
		comment.setDate(new Date());
		this.commentService.createcomment(comment);
		return "redirect:/post/" + post_id;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView getPostCreationForm() {
		return new ModelAndView("new_post", "post", new Post());
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String createPost(@ModelAttribute("post") Post post,
			BindingResult bindingResult, Model model, HttpServletRequest request) {
		User loggedInUser = (User) request.getSession().getAttribute(
				"loggedInUser");
		logger.info("before conversion : " + post.getTitle());
		try {
			byte[] in = post.getTitle().getBytes();
			String out = new String(in, "UTF-8");
			logger.info("after conversion : " + out);
		} catch (Exception e) {
			logger.info("UTF-8 exception");
		}
		post.setUser_id(loggedInUser.getId());
		post.setDate(new Date());
		this.postService.createPost(post);
		return "redirect:/home";
	}
}
