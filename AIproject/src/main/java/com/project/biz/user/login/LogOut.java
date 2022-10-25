package com.project.biz.user.login;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogOut {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		// TODO Auto-generated method stub
		logger.info("Logout.do");
		
		session.invalidate();
		
		return "redirect:login.do";
	}
}
