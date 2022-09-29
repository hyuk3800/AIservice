package com.project.biz.user.login;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.biz.user.userDAO;
import com.project.biz.user.userVO;

@RestController
@Controller
public class loginUser {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginVeiw() {
		logger.info("GET_loginpage");

		return "login";
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String loginGo(userVO vo, userDAO userDao, HttpSession session) {
		logger.info("POST_loginpage");
		userVO user = userDao.getUser(vo);
		
		if(user != null) {
			session.setAttribute("user", user);
			return "home";
		}
		else {
			return "login";			
		}
	}
}
