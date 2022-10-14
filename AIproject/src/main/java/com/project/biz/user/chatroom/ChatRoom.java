package com.project.biz.user.chatroom;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	
	@ResponseBody
	@RequestMapping(value = "/chat/json.do", method = RequestMethod.GET)
	public testJson testGetJson(HttpSession session) {
		testJson json = new testJson();
		json.setWeb("home");
		if(session.getAttribute("user") != null) {
			MemberVo user = (MemberVo) session.getAttribute("user");
			System.out.println(user.toString());
			json.setUser(user);
		}
		return json;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/chat/chatting.do", method = RequestMethod.POST)
	public String chattingPost(@RequestBody HashMap<String, Object> map, HttpSession session) {
		logger.info("POST_Chatting");
		System.out.println(map);
		String userChat = (String) map.get("chat");
		System.out.println(userChat);
		MemberVo user = (MemberVo) session.getAttribute("user");
		if(user != null) {
			return "hi im Ai user";		
		}
		else {
			return "hi im Ai null";
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/chat/uploadFile.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String publ(MultipartFile uploadFile) throws Exception {
		logger.info("POST_File");
		// TODO Auto-generated method stub
		System.out.println(uploadFile);
		// File file1 = (File) param.get("");
		String fileName = null;
		
		fileName = UUID.randomUUID().toString();

		return fileName;
		
		
	}
	
	
}
