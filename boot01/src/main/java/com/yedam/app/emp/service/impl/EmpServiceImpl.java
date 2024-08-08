package com.yedam.app.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.AllArgsConstructor;

@Service
//@AllArgsConstructor
public class EmpServiceImpl implements EmpService {

	private EmpMapper empMapper;
	
	@Autowired
	EmpServiceImpl(EmpMapper empMapper) {
		this.empMapper = empMapper;
	}
	
	@Override
	public List<EmpVO> empList() {
		return empMapper.selectEmpAllList();
	}

	@Override
	public EmpVO empInfo(EmpVO empVO) {
		return empMapper.selectEmpInfo(empVO);
	}
	
	@Override
	public int empInsert(EmpVO empVO) {
		int result = empMapper.insertEmpInfo(empVO);
		return result == 1 ? empVO.getEmployeeId() : -1;
	}
	
	@Override
	public Map<String, Object> empUpdate(EmpVO empVO) {
		Map<String, Object> map = new HashMap<>();
		
		boolean isSuccessed = false;
		int result = empMapper.updateEmpInfo(empVO.getEmployeeId(), empVO);
		
		if (result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", empVO);
		
		return map;
	}
	
	
	@Override
	public Map<String, Object> empDelete(int empId) {
		Map<String, Object> map = new HashMap<>();
		
		int result = empMapper.deleteEmpInfo(empId);
		
		if (result == 1) {
			map.put("employeeId", empId);
		}
		//{}
		//{ "employeeId":104 }
		return map;
	}
	
	
	/*
	등록, 수정 , 삭제는 선택!
	기존의 List 는 두가지 정보를 한번에 담을 수 없으나, map 은 여러가지 타입을 한번에 담을 수 있다
	=> map의 장점
	
	
	Map
	Map 은 Key 와 Value 를 가진 집합이며, 중복을 허용하지 않는다.
	즉, 한개의 Key에 한개의 Value 가 매칭된다.
	Java.util 패키지에 여러 집합들을 사용하기 위한 여러 Interface 와 class 들이 정의되어 있다.
	Map은 키(Key)와 값(Value)으로 이루어진 데이터의 집합이다.
	순서는 유지되지 않으며, 키는 중복을 허락하지 않는다.
	Map의 클래스로는 TreeMap, HashTable, HashMap등이 있다.

	
	HashMap
	HashMap 은 Map Interface 를 Implements 한 클래스로서 중복을 허용하지 않는다.
	Map 의 특징인 Key 와 Value 의 쌍으로 이루어지며, key 또는 value 값으로써 null 을 허용한다. 

	
	
	*/
	
	
}
