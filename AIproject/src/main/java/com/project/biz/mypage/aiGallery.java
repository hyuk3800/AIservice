package com.project.biz.mypage;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.biz.user.chatroom.chatDAO;
import com.project.member.MemberVo;

@Controller
public class aiGallery {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping("/aigallery.do")
	public String getAiGallery(HttpSession session) {
		
		MemberVo user = (MemberVo) session.getAttribute("user");
		
		if(user != null) {
			
			
			
			return "aigallery";
		}else {
			return "redirect:login.do";
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/aigallery/json.do")
	public void getjson(HttpSession session, galleryDAO galleryDAO) {
		int chatRoom = (int) session.getAttribute("chatroom");
	
	
	
	}
	
	
	
}
