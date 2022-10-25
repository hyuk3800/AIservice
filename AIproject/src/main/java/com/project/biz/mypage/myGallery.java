package com.project.biz.mypage;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.member.MemberVo;

@Controller
public class myGallery {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/mygallery.do")
	public String getMyGallery(HttpSession session) {
		
		MemberVo user = (MemberVo) session.getAttribute("user");
//		if(user != null) {
//			
//			
//			
//			return "mygallery";
//		}else {
//			return "redirect:login.do";
//		}
			return "mygallery";
	}
	
	
	
}
