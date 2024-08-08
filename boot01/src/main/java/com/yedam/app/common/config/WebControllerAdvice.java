package com.yedam.app.common.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class WebControllerAdvice {
	
	@ModelAttribute("contextPath")
	public String contextPath(final HttpServletRequest request) {
		return request.getContextPath();
	}
	
}


/*
컨트롤러가 동작 시 보조적인 내용을 지원
컨트롤러에서 발생하는 예외처리를 담당하는 bean = advice
모든 컨트롤러가 사용하는 변수를 등록할 때에도 사용하는 방식

@ModelAttribute ?
 : 사용자가 요청시 전달하는 값을 오브젝트 형태로 매핑해주는 어노테이션


*/
