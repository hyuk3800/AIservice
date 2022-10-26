package com.project.biz.mypage;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.biz.user.chatroom.chatVO;
import com.project.member.MemberVo;

@Controller
public class mypage {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/mypage.do")
	public String getMypage(HttpSession session) {
		logger.info("GET_Mypage");
		
		MemberVo user = (MemberVo) session.getAttribute("user");
//		if(user != null) {
//			return "mypage";
//		}
//		else {
//			
//			return "redirect:login.do";
//		}
		return "mypage";
	}
	
	
	@ResponseBody
	@RequestMapping("/mypage/json.do")
	public List<chatVO> name(HttpSession session, galleryDAO galleryDAO) {
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
				return chatList.subList(0, 6);
			}else {
				return chatList;
			}
			
		}
		return chatList;

	}
	
	
	
}
