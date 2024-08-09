package com.yedam.app.aop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.aop.mapper.AaaMapper;
import com.yedam.app.aop.service.AaaService;

@Service
public class AaaServiceImpl implements AaaService {
	private AaaMapper aaaMapper;
	
	@Autowired
	public AaaServiceImpl(AaaMapper aaaMapper) {
		this.aaaMapper = aaaMapper;
	}
	
	@Transactional
	@Override
	public void insert() {
		aaaMapper.aaaInsert("101");
		aaaMapper.aaaInsert("a101");
		
	}

}


/*
@Transactional을 클래스 단위 혹은 메서드 단위에 선언해주면 된다(@Service 밑에서만 동작)
클래스에 선언하게 되면, 해당 클래스에 속하는 메서드에 공통적으로 적용된다.
메서드에 선언하게 되면, 해당 메서드에만 적용된다.

*/
