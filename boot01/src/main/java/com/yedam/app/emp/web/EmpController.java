package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller //route : 사용자의 요청(endpoint)와 그에 대한 처리
			//URL + method => Service => View (컨트롤러 내 작성)
			//서비스 + 화면 + 사용자 셋을 연결해준다 = 컨트롤러의 역할
public class EmpController {
	
	//해당 컨트롤러에서 제공하는 서비스
	private EmpService empService;
	
	@Autowired
	public EmpController(EmpService empService) {
		this.empService = empService;
	}
	
	//GET : 조회, 빈페이지
	//POST : 데이터조작(등록, 수정, 삭제)
	
	
	//전체조회 : GET
	@GetMapping("empList") // -> http://localhost:8099/empList
	public String empList(Model model) { //Model = Request + Response
		//1) 해당 기능 수행 => Service
		List<EmpVO> list = empService.empList();
		//2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("emps", list); // request의 setAttribute개념
		//3) 데이터를 출력할 페이지 생성
		return "emp/list"; //문자열 맨 앞엔 슬래쉬 금지! (templates 앞에 /가 있어서)
		//classpath:/templates/	emp/list	.html
		//prefix				return		subfix
		//src/main/resources/templates/emp/list.html
	}
	
	//단건조회 : GET => QueryString(실제로 동작하는 서비스가 요구하는 타입에 따라 달라진다 : 커맨드 or 단일)
	@GetMapping("empInfo") // -> http://localhost:8099/empInfo
	public String empInfo(EmpVO empVO, Model model) { //한건을 가르킬 조건을 담아야한다
		//1) 해당 기능 수행 => Service
		EmpVO findVO = empService.empInfo(empVO);
		//2) 클라이언트에 전달할 데이터 담기
		model.addAttribute("emp", findVO);
		//3) 데이터를 출력할 페이지 생성
		return "emp/info";
		//return "redirect:empList";  //->새로운 경로를 호출 302번
		
		//prefix				return		subfix
		//classpath:/templates/	emp/info	.html
		//src/main/resources/templates/emp/info.html
	}
	
	//등록 - 페이지(사용자의 입력을 받는 페이지) : GET
	@GetMapping("empInsert")
	public String empInsertForm() {
		return "emp/insert";
	}
	
	
	//등록 - 처리(입력값을 처리) : POST => form 태그를 통한 submit(QueryString -> 커맨드 객체)
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		int eid = empService.empInsert(empVO);
		String url = null;
		if(eid > -1) {
			//정상적으로 등록된 경우
			url = "redirect:empInfo?employeeId="+eid;
		} else {
			//등록되지 않은 경우
			url = "redirect:empList";
		}
		return url;
	}
		/*
		form 태그를 통한 submit(QueryString -> 커맨드 객체)
		form 태그는 JSON 타입으로 받을 수 없다
		*/
	
	//수정 - 페이지 : Get <=> 단건조회 (한 사람을 특정지어, 그 데이터를 수정하는것)
	@GetMapping("empUpdate")
	public String empUpdateForm(EmpVO empVO, Model model) {
		EmpVO findVO = empService.empInfo(empVO);
		model.addAttribute("emp", findVO);
		return "emp/update";
		
	}
	
	
	
	//수정 - 처리 : AJAX => QueryString
	//@PostMapping("empUpdate")
	@ResponseBody //AJAX를 의미
	public Map<String, Object> empupdateAJAXQueryString(EmpVO empVO) {
		return empService.empUpdate(empVO);
	}
	
	//수정 - 처리 : AJAX => JSON (매개변수에 @RequestBody를 사용)
	@PostMapping("empUpdate")
	@ResponseBody //AJAX를 의미
	public Map<String, Object> empupdateAJAXJSON(@RequestBody EmpVO empVO) {
		return empService.empUpdate(empVO); //node : res.send ... / {"retCode":"Good"}
	}
	
	//수정은 redirect를 잘 하지 X (수정은 보통 연속으로 일어나니까)
	
	

	//삭제 - 처리 (페이지를 요구하는 경우가 없다, 단건 -> 이어짐) : GET 
	//넘기는 데이터가 별로 없어서 post 보다는 단일값을 넘기는 get 방식을 많이 사용(가능한 아작스 사용을 지양하자, redirect 하자)
	@GetMapping("empDelete")
	public String empDelete(Integer employeeId) {
		empService.empDelete(employeeId);
		return "redirect:empList";
	}
	
	
}


/*
 한 컨트롤러 내 서비스는 여러개일 수 있으나, 해당 서비스 만큼 bean을 주입해줘야한다! (Autowired)
	 
Model ?
 : Model은 스프링이 지원하는 기능으로써, key와 value로 이루어져있는 HashMap이다
 Model의 .addAttribute()를 통해 view에 전달할 데이터를 저장할 수 있다.
 request.setAttribute()와 비슷한 역할을 한다. (response를 대신한다)
 (참고 : https://m42-orion.tistory.com/119)


단건조회 시 
=> 
EmpVO empVO = new EmpVO();
empVO.setEmployeeId(employeeId);
		

뷰 리졸버(View Resolver) ?
 : 우리가 작성해야 할 화면이 어디에 위치해야 하는지 알려주는 역할
 실행할 뷰를 찾는 일을 한다.
 페이지 컨트롤러가 리턴한 뷰 이름에 해당하는 뷰 콤포넌트를 찾는 역할.

 
*/
