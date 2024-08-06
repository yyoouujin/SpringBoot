package com.yedam.app.emp.service;

import java.util.Date;

import lombok.Data;


@Data
public class EmpVO {
	
	//employee_id => employeeId (mybatis.configuration.map-underscore-to-camel-case=true)
	private Integer employeeId; //Integer -> 공백 처리를 위함(null값이 넘어올 경우를 대비, 참조타입으로 기재)
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date hireDate;
	
	//초깃값 null -> test 조건문에 jobId != null을 쓰는 이유
	//!jobId.equals('') -> 공백이 들어올 수 있기 때문
	private String jobId; 
	
	private double salary;
	private double commissionPct;
	private int managerId;
	
	//초깃값 0 -> test 조건문에 >0 을 쓰는 이유
	private int departmentId;
	
	
	
}

/*
[@Data]
=> 
@Getter : getter
@Setter : setter 둘 중 하나만 필요 시 명시적으로 사용할 필요가 있다 (@Data 의 남용을 지양하자)
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
*/
