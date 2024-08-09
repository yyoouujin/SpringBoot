package com.yedam.app.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.yedam.app.emp.service.EmpVO;

@Aspect //AOP 의 설정
@Component //Spring이 해당 빈을 서칭할 수 있도록 선언
public class CommonAspect {
	
	//포인트컷 : 조인포인트 중에서 Advice(횡단관심=부가기능) 이 적용될 메소드 필터
	//within : 검색조건(지정한 경로 아래 존재하는 모든 비즈니스 메소드를 총칭)
	@Pointcut("within(com.yedam.app.emp.service.impl.*)")
	//포인트컷이 적용되는 메서드는 실행블럭이 비어있다(자기 위에 적용된 메소드들의 포인트컷을 불러오는역할)
	public void empPointCut() {}
	
	
	//Weaving : 포인트 컷 + 타이밍 + Advice(횡단관심)
	//비즈니스 메서드가 동작하기 전 사전작업을 사용하는 시점(Method 가 호출되기 전에 실행되는 Advice 기능 수행)
	@Before("empPointCut()")
	//매개변수의 joinpoint = 비즈니스 메소드
	public void beforeAdvice(JoinPoint joinPoint) {
		
		/*
		getSignature()
		 : 클라이언트가 호출한 메소드의 시그니처 정보(리턴타입, 이름, 매개변수)가
		 저장된 Signature 객체 리턴
		*/
		String sinagerStr = joinPoint.getSignature().toString();
		
		/*
		getArgs()
		 : 클라이언트가 메소드를 호출할 때 넘겨준 인자 목록을 Object 배열 로 리턴
		*/
		Object[] args = joinPoint.getArgs();
		
		System.err.println("###### 실행 : " + sinagerStr);
		for(Object arg : args) {
			System.err.println("매개변수");
			if(arg instanceof Integer) {
				System.err.println((Integer)arg);
			} else if(arg instanceof EmpVO) {
				System.err.println((EmpVO)arg);
			}
		}
		
	}
	
	
	//메소드 호출 이전, 이후, 예외발생 등 모든 시점에서 동작
	//메소드 실행을 delay 시킬 수 있다 (직접적으로)
	//매개변수로 joinpoint를 전달받는다
	@Around("empPointCut()")
	public Object executeTime(ProceedingJoinPoint joinPoint) throws Throwable  {
		String signaterStr = joinPoint.getSignature().toString();
		System.out.println("=== 시작 : " + signaterStr);
		
		//공통기능
		System.out.println("=== 핵심 기능 전 실행 : " + System.currentTimeMillis());
		
		try {
			//비즈니스 메소드를 실행(명시적으로 실행시킴, @Around 에서만 사용가능)
			Object obj = joinPoint.proceed();
			return obj;
		} finally {
			//공통기능
			System.out.println("=== 핵심 기능 후 실행 : " + System.currentTimeMillis());
			System.out.println("=== 끝 : " + signaterStr);
		}
		
		
	}
	


}



















/*
[실습]

서로 다른 클래스들이 한꺼번에 돌면서 출력을 해주고있다
(AOP 가 하는 역할)

1.
Aspect ?
 : 하나의 컴포넌트가 된다, 동작 부분에 있어선 일반 Bean과 사실상 같다
 Aspect는 JoinPoint와 Advice의 결합한 객체를 의미합니다. 
 다시말해서, 횡단 관심사 로직과 로직이 수행될 시점을 결합하여 모듈화한 객체입니다.

2.
PointCut ?
 : joinpoint 비즈니스의 메서드를 총칭
 횡단 로직(Advice)이 수행될 대상을 의미합니다.
 
3.
@Pointcut 이 어노테이션을 사용하면
-> 핵심 메서드의 경로를 공통 메서드에 일일이 쳐넣을 필요가 없습니다

4.
@Around("${pattern}"}
 : 지정된 패턴에 해당하는 메소드의 실행되기 전, 실행된 후 모두에서 동작한다.(before 보다 먼저 돈다)
 이 어노테이션이 붙은 메소드의 반환 값은 Object여야 한다.(지정된 패턴에 해당하는 메소드의 실행 결과를 반환해야 하므로)
 
 => 주로 시간체크 시 많이 사용
 
 



*/


/*
[이론]

관심분리 ?
 : 서비스를 분리 (횡단 / 핵심)
 
횡단관심(=부가기능)
 : 핵심 기능을 보조하기 위해 제공되는 기능 ( ex: 로그 추적 기능, 트랜잭션 기능 )
 
핵심관심
 : 횡단이 분리된 후, 해당 객체가 제공하는 고유의 기능

AOP ?
 : 횡단관심(=부가기능) 을 별도의 클래스로 분리하여 관리하기 위함 (독립된 형태의 객체로 만든다)
 부가 기능을 핵심 기능에서 분리해 한 곳으로 관리하도록 하고, 
 이 부가 기능을 어디에 적용할지 선택하는 기능을 합한 하나의 모듈
 서비스어노테이션이 붙은 컨트롤러의 메소드만 동작


[AOP 용어]

어드바이스 
 : 부가기능
 특정 조인 포인트에서 Aspect에 의해 취해지는 조치
 
 
Aspect ?
 : AOP의 설정을 담당
 어드바이스 + 포인트컷을 모듈화 한 것
 
 
조인포인트 ?
 : 서비스어노테이션이 붙은 클래스들의 모든 메소드
 특정 대상(메소드)에서 횡단 로직(Advice)이 수행될 시점
 

포인트컷 ?
 : 조인 포인트 중에서 어드바이스(부가 기능)를 어디에 적용할 지, 
 적용하지 않을 지 위치를 판단하는 필터링하는 기능 ( 주로 AspectJ 표현식을 사용해서 지정 )
 횡단 로직(해당 객체가 제공하는 고유의 기능)이 수행될 대상


위빙 ? 
 : Advice를 주기능에 적용하는 행위



*/
