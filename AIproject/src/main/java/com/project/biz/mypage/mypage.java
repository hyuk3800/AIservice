package com.project.biz.mypage;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.biz.user.userDAO;
import com.project.biz.user.chatroom.chatVO;
import com.project.biz.user.chatroom.testJson;
import com.project.member.MemberVo;

@Controller
public class mypage {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/mypage.do")
	public String getMypage(HttpSession session) {
		logger.info("GET_Mypage");
		
		MemberVo user = (MemberVo) session.getAttribute("user");
		if(user != null) {
			return "mypage";
		}
		else {
			return "redirect:login.do";
		}
	}
	
	
	@ResponseBody
	@RequestMapping("/mypage/json.do")
	public testJson getjson(HttpSession session, galleryDAO galleryDAO) {
		logger.info("GET_gallery");
		MemberVo user = (MemberVo) session.getAttribute("user");
		List<chatVO> chatList = null;
		if (user != null) {
			int chatroom = (int) session.getAttribute("chatroom");
			chatList = galleryDAO.searchChatterGallery(chatroom);
			for (chatVO vo : chatList) {
				System.out.println(vo);
			}
			
			if(chatList.size() > 6) {
				chatList = chatList.subList(0, 6);
			}
		}
		
		testJson json = new testJson();
		json.setChatData(chatList);
		json.setUser(user);
		return json;

	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/mypage/changename.do", method = RequestMethod.POST)
	public int newName(@RequestBody HashMap<String, Object> map, 
					HttpSession session,
					userDAO userDao) {
		logger.info("POST_ChangeName");

		
		String newName = (String) map.get("newname");
		MemberVo vo = (MemberVo) session.getAttribute("user");
		vo.setNickname(newName);
		int row = userDao.updateUserName(vo);
		System.out.println(row + "행에 수정");
		
		return row;
	}
	
	
	
}
