package com.yedam.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.aop.service.AaaService;

@SpringBootTest
public class AopTests {
	
	@Autowired
	AaaService aaaService;
	
	@Test
	void transcationalTest() {
		aaaService.insert();
	}
	
}



/*
1) 
[AaaServiceImpl 에 트랜잭션 설정 전]
에러 확인 (but, 테이블 확인하니 데이터는 들어감 : 오토커밋 상황이라 그럼)
bean에 대한 설정을 변경하게 되면 모든 service가 동작을 하지 않는다. (직접 변경을 해줘야한다)


2)
[AaaServiceImpl 에 트랜잭션 설정 후]
->
에러확인 (but, 테이블 확인하니 데이터가 들어가지않았음)
메소드 내부를 묶어서, 오토커밋이 실행되지않았다


*/
