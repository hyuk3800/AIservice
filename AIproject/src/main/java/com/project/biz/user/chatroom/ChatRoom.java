package com.project.biz.user.chatroom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChatRoom {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getChathome() {
		logger.info("GET_ChatRoomPage");
		
		
		
		return "home";
	}
	
	
	
	
	
}
