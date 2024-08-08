package com.yedam.app.test.web;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpVO;

//@CrossOrigin(origins = "*") //cors
@Controller
public class ParamController {
	
	
		//HandlerAdapter - Controller 호출 전 단계!
	
	
	//1) QueryString(질의문자열) : key=value&key=value
	//Content-Type: Application/x-www-form-urlencode (디폴트)
	//Method : 상관없음
	
	
	// QueryString => 커멘드 객체(어노테이션 X) : 객체
	//GET은 경로에 붙는다(querystring타입), POST 는 보이지 않음!
	@RequestMapping(path="comobj",
					method= {RequestMethod.GET, RequestMethod.POST})
	
	@ResponseBody
	public String commandObject(EmpVO empVO) {
		String result = "";
		result += "Path : /combj \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
		
	/*
	 http://localhost:8099/comobj 으로 확인
	 post 방식 시 EmpVO의 필드 값으로 파라미터의 키를 맞춰줘야한다!
	 (매개변수에 정의된 VO내의 필드값 = key)
	*/
		
	}
	
	// QueryString => @RequestParam : 기본타입, 단일값
	@RequestMapping(path="reqparm",
					method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String requestParam
		(@RequestParam Integer employeeId, //필수
						String lastName, //생략가능
		@RequestParam(name="message", //name 이 key가된다!(변수 사용하는거 아님!)
					  defaultValue="No message", //기본값 -> 강제로 값을 넣어준다
					  required = true) String msg) { //파라미터 필수 여부, true(필수) false(필수 X)
		String result = "";
		result += "path : /reqparm \n";
		result += "\t employee_id : " + employeeId;
		result += "\t last_name : " + lastName;
		result += "\t message : " + msg;
		return result;
		
		
		/*
		 http://localhost:8099/reqparm 으로 확인
		 
		 GET 방식 오류 확인 
		 => 
		 "status": 400, (경로는 맞지만 서버가 요청하는 데이터가 없을 때 뜸 / @PathVariable에선 404 오류 확인) 
    	 "error": "Bad Request",
		 "message": "Required parameter 'employeeId' is not present.",
		*/
		
	}
	
	
	//2) @PathVariable : 경로에 값을 포함하는 방식, 단일 값
	//Method 는 상관없음
	//Content-type 도 상관없음
	@RequestMapping("path/{id}") //path/Hong, path/1234
	@ResponseBody
	public String pathVariable(@PathVariable String id) {
		String result = "";
		result += "path : /path/{id} \n";
		result += "\t id : " + id;
		return result;
		
		/*
		 http://localhost:8099/path/Hong 으로 확인
		 
		 @PathVariable 가 누락되고(값이 누락되고) path 만 쓰게 되면 404에러 발생(경로가 틀어진 에러 다른건 400)
		 => 오류 확인
		 "status": 404,
    	"error": "Not Found",
    
		 
		*/
		
	}
	
	
	//3) @RequestBody : JSON포맷, 배열 or 객체
	//Method : POST, PUT
	//Content-type : Application/json
	//requestBody => json 의 형태로 데이터를 줘야한다~ 라는 선언
	
		//객체 형태
	@PostMapping("resbody")
	@ResponseBody
	public String requestBody(@RequestBody EmpVO empVO) {
		String result = "path : /resbody \n";
		result += "\t employee_id : " + empVO.getEmployeeId();
		result += "\t last_name : " + empVO.getLastName();
		return result;
		
		/*
		http://localhost:8099/resbody 으로 확인
		=>
		객체 형태의 JSON
		{
			"employeeId":1004,
			"lastName":"yujin"
		}
		
		*/

	}
	
		//list 형태
	@PostMapping("resbodyList")
	@ResponseBody
	public String requestBodyList(@RequestBody List<EmpVO> list) {
		
		String result = "path : /resbodyList \n";
		
		for (EmpVO empVO : list) {
			result += "\t employee_id : " + empVO.getEmployeeId();
			result += "\t last_name : " + empVO.getLastName();
			result += "\n";
		}
		
		return result;
		
		/*
		http://localhost:8099/resbodyList 으로 확인
		=>
		배열 형태의 JSON
		[{
			"employeeId":1004,
			"lastName":"yujin"
		}, ...]
		* 하나만 보낼때도 대괄호가 필요!
		
		*/
		
	}
	
	
	
	
	
	//4) @DateTimeFormat
	
	
	/*
	http://localhost:8099/comobj 로 (querystring 포맷)
	(Form 데이터) 
	hireDate : 2024-08-07
	=> 전송 시 오류 확인 
	"code": "typeMismatch"
	hireDate : 2024/08/07
	=> 전송 시 정상작동
	
	
	http://localhost:8099/resbody 로 (JSON포맷)
	(JSON 데이터) 
	{"hireDate" : "2024-08-07"} 
	=> 전송 시 정상작동 
	{"hireDate" : "2024/08/07"}
	=> 전송 시 에러 확인
	
	
	VO의 hireDate 객체에
	@DateTimeFormat(pattern = "yyyy-MM-dd") 추가
	후 오류 확인
	=> 하나의 포맷만 사용 가능!

	*/
	
	
	
	
	
	
	
	
	
	
	

}
