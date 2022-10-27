package com.project.biz.mypage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.biz.user.chatroom.chatDAO;
import com.project.biz.user.chatroom.chatVO;
import com.project.biz.user.chatroom.testJson;
import com.project.member.MemberVo;

@Controller
public class aiGallery {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping("/aigallery.do")
	public String getAiGallery(HttpSession session) {
		logger.info("get_aigallery_page");
		MemberVo user = (MemberVo) session.getAttribute("user");
		if(user != null) {
			return "aigallery";
		}else {
			return "redirect:login.do";
		}	
	}
	
	@ResponseBody
	@RequestMapping("/aigallery/json.do")
	public testJson getjson(HttpSession session, galleryDAO galleryDAO) {
		int chatRoom = (int) session.getAttribute("chatroom");
		MemberVo user = (MemberVo) session.getAttribute("user");
		List<chatVO> chatList = null;
		if (user != null) {
			System.out.println("aigallery_json");

		}
		
		testJson json = new testJson();
		json.setChatData(chatList);
		json.setUser(user);
		return json;
	}
	
	
	
}
