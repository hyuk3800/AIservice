package com.project.biz.user.login;


import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;

//import com.project.biz.user.userDAO;
import com.project.biz.user.userVO;


@Controller
public class loginUser {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginVeiw() {
		logger.info("GET_loginpage");

		return "login";
	}
	
	@ResponseBody
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String loginGo(@RequestBody HashMap<String, Object> map, HttpSession session) {
		logger.info("POST_loginpage");
		System.out.println(map);
		String ID = (String) map.get("userid");
		String password = (String) map.get("pwd");
		userVO user = null;
		if (ID != null || password != null) {
			user = new userVO().setID(ID).setPassword(password);
			System.out.println(user.toString());
		}

		if(user != null) {
			session.setAttribute("user", user);
			System.out.println("로그인");
			return "home";
		}
		else {
			System.out.println("실패");
			return "login";			
		}
	}
//	@ResponseBody
//	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
//	public String loginGo(@RequestBody HashMap<String, Object> map, HttpSession session) {
//		logger.info("POST_loginpage");
//		System.out.println(map);
////		userVO user = (userVO) session.getAttribute("user");
//		
//		if(user != null) {
//			session.setAttribute("user", user);
//			return "home";
//		}
//		else {
//			return "login";			
//		}
//	}
}
