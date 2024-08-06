package com.yedam.app.emp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.emp.service.EmpVO;

public interface EmpMapper {
	//전체조회(리턴 타입이 여러개인 List VO)
	public List<EmpVO> selectEmpAllList();
	//단건조회(리턴 타입이 하나인 VO)
	public EmpVO selectEmpInfo(EmpVO empVO);	
	//등록(리턴 타입 정수)
	public int insertEmpInfo(EmpVO empVO);
	//수정(리턴 타입 정수)
	public int updateEmpInfo (@Param("id") int empId, @Param("emp") EmpVO empVO);
	//삭제(리턴 타입 정수)
	public int deleteEmpInfo(int empId);
	

}
