package com.yedam.app.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ThymeleafController {

	private EmpService empService;
	
	@GetMapping("thymeleaf")
	public String thymeleafTest(Model model) {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(100);
		
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("empInfo", findVO); //"empInfo"는 화면단에서 사용할 변수가 된다(key)
		return "test";
	}
	
	//http://localhost:8099/thymeleaf
	
}


/*
model.addAttribute
-> res, req에 일시적으로 값을 담아놓고 페이지까지 전달하는 역할
(사용자에겐 전달하지않는다, ajax에서는 사용할 일이 없다 페이지를 통해 열지않기때문)

Model은 스프링이 지원하는 기능으로써, key와 value로 이루어져있는 HashMap이다.
(=> key 를 화면단에서 사용할 변수로 사용함)
Model의 .addAttribute()를 통해 view에 전달할 데이터를 저장할 수 있다.
Servlet의 request.setAttribute()와 비슷한 역할을 한다.

*/
