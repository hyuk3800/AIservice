package com.project.biz.user.login;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.biz.user.userDAO;
import com.project.member.MemberVo;

@Controller
public class singUp {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ResponseBody
	@RequestMapping(value = "/addUser.do", method = RequestMethod.POST)
	public String singUpGo(@RequestBody HashMap<String, Object> map, userDAO useDao) {
		logger.info("POST_singUp");
		
//		System.out.println(map);
		String ID = (String) map.get("userid");
		String userName = (String) map.get("username");
		String password = (String) map.get("pwd");
		
		MemberVo vo = new MemberVo();
		vo.setID(ID);
		vo.setNickname(userName);
		vo.setPwd(password);
		System.out.println(vo.toString());
		
		int row = useDao.InsertMember(vo);
		
		if (row == 0) {
			System.out.println("실패 : false");
			return "false";
		}
		else {
			System.out.println("성공 : True");
			return "true";
		} 
	}
	
	@ResponseBody
	@RequestMapping(value = "/searchID.do", method = RequestMethod.POST)
	public String searchID(@RequestBody HashMap<String, Object> map, userDAO useDao) {
		logger.info("POST_searchID");
		String ID = (String) map.get("userid");
		MemberVo vo = new MemberVo();
		vo.setID(ID);
		
		int row = useDao.SearchMemberID(vo);
		
		if(row == 1) {
			System.out.println("중복 : overlap");
			return "overlap";
		}else {
			return null;			
		}
	}

}
