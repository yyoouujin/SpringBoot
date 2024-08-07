package com.yedam.app.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //Bean 등록, Web 과 관련된 부분
public class URLController {
	
	//메서드 위에 해당 컨트롤러를 호출하는 경로를 작성
	
	
	//GET
	//@RequestMapping(path="/test", method=RequestMethod.GET) - RequestMethod.GET 그냥 GET 아님!
	@GetMapping("/test") //권장
	@ResponseBody //아작스 전용 컨트롤러를 만들 떄 사용
	public String urlGetTest(String keyword) {
		return "Server Response : Get Method\n Select - " + keyword;
	//http://localhost:8099/test 로 확인
	}
	
	
	//POST
	//@RequestMapping(path="/test", method=RequestMethod.POST)
	@PostMapping("/test")
	@ResponseBody
	public String urlPostTest(String keyword) {
		return "Server Response : Post Method\n Select - " + keyword;
	//
	}
		
}
