package com.yedam.app.anotation;

import org.springframework.stereotype.Component;

//빈으로 등록하겠다는 선언
@Component
public class Chef {
	public void cooking() {
		// TODO Auto-generated method stub
		System.out.println("Spring annotation 방식");
	}

}

/*
등록하고자 하는 class위에 annotation 이 붙는다 (빈으로 등록하겠다는 선언)

*/
