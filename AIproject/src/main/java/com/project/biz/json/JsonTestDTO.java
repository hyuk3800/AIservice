package com.project.biz.json;


import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.biz.user.userVO;

@RestController
public class JsonTestDTO {
	
	@GetMapping("/loginUser.do")
	public userVO printJson(userVO vo, HttpSession session) {
		userVO user = (userVO) session.getAttribute("user");
		return vo;
	}
	
	
	@PostMapping("/loginUser.do")
	public String postLoginJson(@RequestBody userVO vo) {
		System.out.println("ddd");
		System.out.println(vo.getID());
		return "home";
	}
}
