package com.project.biz.user.chatroom;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.biz.user.userVO;
import com.project.member.MemberVo;

@Controller
public class ChatRoom {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String getChathome(HttpSession session) {
		logger.info("GET_ChatRoomPage");
		if(session.getAttribute("user") != null) {
			MemberVo user = (MemberVo) session.getAttribute("user");
			System.out.println(user.toString());
		}
		
		return "home";
	}
	
	
	
	
	
}
