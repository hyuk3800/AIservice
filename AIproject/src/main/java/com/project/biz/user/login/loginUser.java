package com.project.biz.user.login;


import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;

// import com.project.member.MemberVo;

import com.project.biz.user.userDAO;
//import com.project.biz.user.userDAO;
import com.project.biz.user.userVO;
import com.project.member.MemberVo;



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
	public String loginGo(@RequestBody HashMap<String, Object> map, userDAO userDao, HttpSession session, Model model) {
		logger.info("POST_loginpage");
		System.out.println(map);
		String ID = (String) map.get("userid");
		String password = (String) map.get("pwd");

		MemberVo vo = new MemberVo();
		vo.setID(ID);
		vo.setPwd(password);

		MemberVo user = userDao.selectMember(vo);
		if(user != null) {
			session.setAttribute("user", user);
			System.out.println("로그인");
			return "true";
		}
		else {
			System.out.println("실패");
			return "false";			
		}
	}

}
