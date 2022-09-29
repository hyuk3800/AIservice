package com.project.biz.testjson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.biz.user.userVO;

//import com.project.biz.user.userVO;

@RestController
public class testJson {
	@GetMapping("/testjackson")
	public List<userVO> helloVo() {
		ArrayList<userVO> vos = new ArrayList<userVO>();
		for (int i = 0; i < 6; i++) {
			String num = Integer.toString(i);
			userVO vo = new userVO().setID("test"+num).setUserName("testname");
			vos.add(vo);
		}
		return vos;
	}
}
