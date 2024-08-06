package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest //테스트 환경에서 IoC컨테이너를 생성
class Boot01ApplicationTests {

	@Autowired //필드주입 (해당 bean을 조금 더 편하게 불러내기 위함, 보통 test 환경에서 많이 사용, private 를 무시하기 때문에 사용하지 말 것)
	EmpMapper empMapper;
	
	//전체조회
	//@Test
	void empList() {
		List<EmpVO> list = empMapper.selectEmpAllList();
		assertTrue(!list.isEmpty()); 
		//isEmpty : 내부값이 비어있는지 check (비어있으면 true)
		//assertTrue : 특정 조건이 true인지(비어있지 말라고 !)
	}
	
	//단건조회
	//@Test
	void empInfo() {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(100);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		assertEquals("King",findVO.getLastName());
		
	}
	
	//등록
	//@Test
	void empInsert() {
		EmpVO empVO = new EmpVO();
		empVO.setLastName("Park");
		empVO.setEmail("park@com");
		empVO.setJobId("IT_PROG");
		//empVO.setSalary(1000);
		
		int result = empMapper.insertEmpInfo(empVO);
		System.out.println(empVO.getEmployeeId());
		
		assertEquals(1, result);
		
	}
	
	//삭제
	//@Test
	public void empDelete() {
		int result = empMapper.deleteEmpInfo(891);
		assertEquals(1, result);
	}
	
	//수정
	@Test
	public void empUpdate() {
		
		//1) 대상 원래 데이터 조회 : 단건조회
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(890);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		System.err.println(findVO); //빨간색으로 표시
		//2) 사용자가 수정하는 내용 입력
		findVO.setLastName("Updating");
		
		//3) Update
		int result = empMapper.updateEmpInfo(findVO.getEmployeeId(), findVO);
		
		assertEquals(1, result);
		
	}
	

}

/*
jUnit ?
 : 컨테이너를 생성하지 못한다(Java꺼임) 
 단위테스트(unit Test)
 @SpringBootTest -> spring 이 가지는 container 를 생성해주는 역할 (환경은 조성해줄것)
 단위 테스트 후 console 창을 보는 이유 (쿼리문 확인도 같이 하라고)
 
 Autowired ?
  : 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입
  해당 bean을 조금 더 편하게 불러내기 위함, 보통 test 환경에서 많이 사용, private 를 무시하기 때문에 사용하지 말 것
*/
