package com.yedam.app.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //Bean 등록, Web 과 관련된 부분
public class URLController {
	
	@RequestMapping(path="/test", method=RequestMethod.GET)
	@ResponseBody
	public String urlGetTest(String keyword) {
		return "Server Response : Get Method\n Select - " + keyword;
		
		//http://localhost:8099/test 로 확인
	}
}
