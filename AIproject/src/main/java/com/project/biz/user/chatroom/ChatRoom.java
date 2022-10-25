package com.project.biz.user.chatroom;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.biz.user.userVO;
import com.project.member.MemberVo;

@Controller
public class ChatRoom {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FileService fileservice;
	
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String getChathome(HttpSession session) {
		logger.info("GET_ChatRoomPage");
		MemberVo user = (MemberVo) session.getAttribute("user");
		if(user != null) {
			System.out.println(user.toString());
		}

		return "home";			

	}
	
	@ResponseBody
	@RequestMapping(value = "/chat/json.do", method = RequestMethod.GET)
	public testJson testGetJson(HttpSession session, chatDAO chatDAO) {
		testJson json = new testJson();
		json.setWeb("home");
		if(session.getAttribute("user") != null) {
			int chatRoom = chatDAO.searchChatRoom((MemberVo) session.getAttribute("user"));
			System.out.println(chatRoom);
			session.setAttribute("chatroom", chatRoom);
			json.setChatRoom(chatRoom);
			MemberVo user = (MemberVo) session.getAttribute("user");
			System.out.println(user.toString());
			json.setUser(user);
			
			List<chatVO> chatList = chatDAO.searchAllChat(json);
			json.setChatData(chatList);
		}
		
		
		return json;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/chat/chatting.do", method = RequestMethod.POST)
	public String chattingPost(@RequestBody HashMap<String, Object> map, HttpSession session, chatDAO chatDAO) {
		logger.info("POST_Chatting");
		System.out.println(map);
		String userChat = (String) map.get("chat");
		System.out.println(userChat);
		MemberVo user = (MemberVo) session.getAttribute("user");
		if(user != null) {
			int chatRoom = (int) session.getAttribute("chatroom");
			chatVO vo = new chatVO()
							.setChatter(user.getID())
							.setChatroomnum(chatRoom)
							.setChatData(userChat)
							.setType(0);
			int row = chatDAO.insertUserChat(vo);
			System.out.println(row + " 행에 추가됨");
			
			String ai = "hi im Ai " + user.getID();
			
			chatVO aiVo = new chatVO()
							.setChatData(ai)
							.setChatroomnum(chatRoom)
							.setType(0);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			int airow = chatDAO.insertChatterChat(aiVo);
			
			
			return ai;		
		}
		else {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			return "hi im Ai null";
		}
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/chat/uploadFile.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String publ(@RequestParam("files") MultipartFile uploadFile, HttpSession session, HttpServletRequest request, chatDAO chatDAO) throws Exception {
		logger.info("POST_File");
		String fileName = fileservice.fileUpload(uploadFile, session);
		
		System.out.println(fileName);
		
		MemberVo user = (MemberVo) session.getAttribute("user");
		if(user != null) {
			System.out.println("userTrue");
			int chatroom = (int) session.getAttribute("chatroom");
			String userID = user.getID();
			
			chatVO vo = new chatVO()
							.setChatter(userID)
							.setChatData(fileName)
							.setChatroomnum(chatroom)
							.setType(1);
			int row = chatDAO.insertUserChat(vo);
			System.out.println(row+" 행에 추가");
		}
		
		return fileName;
	}
	
	
}
