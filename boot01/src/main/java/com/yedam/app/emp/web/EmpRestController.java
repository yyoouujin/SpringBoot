package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@RestController //@Controller + 모든 메소드에 @ResponseBody를 적용
				//@ResponseBody : ajax를 쓰겠다는 선언
public class EmpRestController {
	
	private EmpService empService;
	
	@Autowired
	EmpRestController(EmpService empService){
		this.empService = empService;
	}
	
	
	//전체조회 : GET => emps
	@GetMapping("emps")
	public List<EmpVO> empList() {
		return empService.empList();
		
	/*
	http://localhost:8099/yedam/emps
	=>
	JSON 데이터만출력되는 것 확인
	*/
		
	}
	//단건조회 : GET => emps/100 (조회하고자 하는 대상의 정보를 경로에 붙인다)
	@GetMapping("emps/{eid}")
	public EmpVO empInfo(@PathVariable(name = "eid") Integer employeeId) {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(employeeId);
		return empService.empInfo(empVO);
	}
	
	//등록 : POST => emps
	@PostMapping("emps") //@RequestBody : JSON
	public int empInsert(@RequestBody EmpVO empVO) {
		return empService.empInsert(empVO);
		
	/*
	[부메랑 post 실습]
	http://localhost:8099/yedam/emps
	=>
	출력되는 숫자 데이터가 primarykey (895 출력 확인)
	
	{
    "lastName": "Queen",
    "email": "Ujin@naver",
    "hireDate": "2024-08-09",
    "jobId": "IT_PROG"
	}
	*/
		
	}
	
	//수정 : PUT => emps/100
	@PutMapping("emps/{employeeId}")
	public Map<String, Object> empUpdate(
										//경로를 통해서 수정할 Target(대상)
										@PathVariable Integer employeeId,
										//수정할 정보는 JSON 포맷으로
										@RequestBody EmpVO empVO) 
	{	
		empVO.setEmployeeId(employeeId); //두개의 정보를 합쳐준다
		return empService.empUpdate(empVO);
		
		
	/*
	[부메랑 put 실습]
	http://localhost:8099/yedam/emps/895
	->
	현재 쿼리문 때문에 "jobId"가 필수로 들어가야함
	
	{
    "lastName": "UJin",
    "email": "yyoouujin@gmail.com",
    "hireDate": "2024-08-09",
    "jobId": "IT_PROG"
	}
	*/
		
	}
	
	//삭제 : DELETE => emps/100 (특정 대상을 삭제해야 하는 경우 경로에 값이 붙어야한다)
	@DeleteMapping("emps/{employeeId}")
	public Map<String, Object> empDelete(@PathVariable Integer employeeId){
		return empService.empDelete(employeeId);
	
	/*
	[부메랑 delete 실습]
	http://localhost:8099/yedam/emps/895
	->
	body가 필요없지만, method 가 중요
	
	*/
	
	}
	
}













/*

 REST의 목적
 : 이해하기 쉽고 사용하기 쉬운 REST API를 만드는 것(기존의 방식을 탈피하자!)
 자원(URI) + 행위(HTTP METHOD) + 표현(Representations)
 기본 통신방식은 ajax라서 데이터만 넘어오는 형태가 된다 (이렇게 가져온 데이터를 처리하게되겠지)
 전달하는 방식, 통신하는 방식이 다르다 
 Ajax 의 빈도가 높아지고, JSON 이 등장하게 되면서 클라이언트가 굉장히 다양해졌다,
 이를 대응하기 위해 탄생하게 된 REST 형태 
 	
 	자원(URI)
 	 : 자원을 구별하는 ID는 ‘/groups/:group_id’와 같은 HTTP URI 다.
 	
 	행위(HTTP METHOD)
 	 : HTTP 프로토콜의 method (get, post, put, delete)
 	
 	표현(Representations)
 	 : 자원은 JSON, XML 등 여러 형태로 표현될 수 있다
 
 
 (참고 : https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html)
 
 

 [ REST 방식의 서버 와 기존 방식과의 차이 ]
 ->
 1. 페이지를 생성하지 않는다(요청하는 부분을 처리하는 서버다)
 2. 메서드가 늘어난다 (+ put, delete) / CRUD 방식
 3. cors 는 필수! (rest 와 매우 연관성이 깊다)
 
 
 1)
 페이지를 요구하지 않기 때문에 등록과 수정의 경우 하나면 충분!
 
 2)
 [단건조회]
 쿼리스트링의 ? 뒤는 데이터로 인식
 즉, pathVariable 을 이용해서 경로에서 값을 가져와야한다!
 	
 	+) @PathVariable 의 기타속성
 	 : url 에 붙여주는 변수명과 내부 필드명이 조금 다를경우 별도의 이름을 설정해줄 수 있다 (name 속성)
 	 클라이언트가 사용하는 이름과 내부의 변수명이 다를 경우 사용
 	 
 	 (참고 : https://dingdingmin-back-end-developer.tistory.com/entry/Springboot-MVC-%ED%8C%8C%ED%97%A4%EC%B9%98%EA%B8%B04-PathVarialbe)


3)
[등록]
post : body 를 요구하기 때문에 사용 post 메소드를 사용
rest 방식은 ajax를 기반으로 하고, 반드시 json 형태를 요구한다
그렇기 때문에 반드시 @RequestBody 가 필요! (@RequestBody 의 뜻 : JSON 으로 데이터를 받는다)


4)
[수정]
엄연히 말하면 post 와 다른 메소드라, 같은 경로를 사용해도 무방하다
하지만, 가장 많이 사용하는 방식으론 경로에 수정할 대상을 불러온다 -> "emps/{employeeId}"

@PathVariable 를 사용해서 경로를 통해 수정할 대상을 가져온다(primaryKey)
@RequestBody 를 사용해서 body 에 json 타입의 정보를 가져온다 
empVO.setEmployeeId(employeeId) -> 두개의 정보를 합쳐서 
return 보내준다


5)
[삭제]
@PathVariable 를 사용해 삭제할 대상을 끄집어낸다



*/