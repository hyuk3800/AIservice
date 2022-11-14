package com.project.biz.mypage;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.biz.user.chatroom.chatVO;
import com.project.biz.user.chatroom.testJson;
import com.project.member.MemberVo;

@Controller
public class myGallery {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/mygallery.do")
	public String getMyGallery(HttpSession session) {
		logger.info("get_mygallery_page");
		MemberVo user = (MemberVo) session.getAttribute("user");
		if(user != null) {		
			return "mygallery";
		}else {
			return "redirect:login.do";
		}

	}
	
	
	@ResponseBody
	@RequestMapping("/mygallery/json.do")
	public testJson getMyGalleryJson(HttpSession session, galleryDAO galleryDAO) {
		logger.info("GET_myGallery_json");
		MemberVo user = (MemberVo) session.getAttribute("user");
		String page = "";
		List<chatVO> chatList = null;
		if (user != null) {
			int chatroom = (int) session.getAttribute("chatroom");
			chatList = galleryDAO.searchChatterGallery(chatroom, page);
			for (chatVO vo : chatList) {
				System.out.println(vo);
			}
		}
		
		testJson json = new testJson();
		json.setChatData(chatList);
		json.setUser(user);
		return json;
	}
	
	
}
