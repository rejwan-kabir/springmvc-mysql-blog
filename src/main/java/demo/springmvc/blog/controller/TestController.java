package demo.springmvc.blog.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

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

import demo.springmvc.blog.domain.Test;
import demo.springmvc.blog.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {
	private static final Logger logger = LoggerFactory
			.getLogger(TestController.class);

	@Autowired
	private TestService testService;

	@RequestMapping(value = "/{user_name}", method = RequestMethod.GET)
	public String testGet(@PathVariable(value = "user_name") String username,
			Model model) {
		try {
			logger.info(URLDecoder.decode(
					new String(username.getBytes("ISO-8859-1")), "UTF-8"));
			model.addAttribute("name", URLDecoder.decode(
					new String(username.getBytes("ISO-8859-1")), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("object", new Test());
		return "test";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String testCase(Model model) {
		model.addAttribute("object", new Test());
		return "test";
	}

	/*
	 * @RequestMapping(method = RequestMethod.POST) public String
	 * testPost(@RequestParam("one") String one,
	 * 
	 * @RequestParam("two") String two, Model model) { model.addAttribute("one",
	 * one); model.addAttribute("two", two); return "test"; }
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String testPost(@ModelAttribute("object") Test test,
			BindingResult bindingResult, Model model) {
		/*
		try {
			// TODO : works for now . A very clumsy way . Need to do better.
			String one = URLEncoder.encode(test.getOne(), "UTF-8");
			String two = URLEncoder.encode(test.getTwo(), "UTF-8");
			logger.info("one = " + one + "\ttwo : " + two);
			test.setOne(one);
			test.setTwo(two);
			logger.info("one = " + test.getOne() + "\ttwo : " + test.getTwo());
			this.testService.createTest(test);
			List<Test> testList = this.testService.getTest();
			model.addAttribute("one",
					URLDecoder.decode(testList.get(0).getOne(), "UTF-8"));
			model.addAttribute("two",
					URLDecoder.decode(testList.get(0).getTwo(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		*/
		
		model.addAttribute("one", test.getOne());
		model.addAttribute("two", test.getTwo());
		logger.info("one = " + test.getOne() + "\ttwo : " + test.getTwo());
		// TODO : still showing ????? . need to make it work !
		this.testService.createTest(test);
		List<Test> testList = this.testService.getTest();
		model.addAttribute("one",testList.get(0).getOne());
		model.addAttribute("two",testList.get(0).getTwo());		
		return "test";
	}
}
