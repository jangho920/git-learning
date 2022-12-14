package com.dasan.smartop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {
	final static Logger logger = LogManager.getLogger(MainController.class);
	
	@RequestMapping("/")
	public ModelAndView login(Model model, HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession(true);
		
		ModelAndView mv = new ModelAndView();
		if(session != null && session.getAttribute("sessionVO") != null) {
			mv.setViewName("main/main");
		}else {
			mv.setViewName("login");
		}
		
		return mv;
	}
	
	@RequestMapping("/main.do")
	public String main(Model model) throws Exception{
		//logger.debug("mainPage");
		return "main/main";
		//model.addAttribute(null, model);
	}
	
	@RequestMapping("/index.do")
	public String test(Model model) throws Exception{
		//logger.debug("mainPage");
		return "index";
		//model.addAttribute(null, model);
	}

}