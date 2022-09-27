package com.project.biz.testjson;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.project.biz.user.userVO;

@RestController
public class testJson {
//	@GetMapping("/login.do")
	public String helloVo(@RequestParam("ID") String ID) {
		ID = "test";
		return ID;
	}
}
