package com.yedam.app.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainExample {
	public static void main(String[] args) {
		ApplicationContext ctx
		= new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		TV tv = (TV)ctx.getBean("tv");
		tv.turnOn();
		
		//id속성이 없는 경우 class 정보만 가지고 처리가능!
		TV subTv = (TV)ctx.getBean(TV.class); //.class -> 클래스 정보 그 자체를 넘긴다(해당 클래스를 통채로 넘김)
		subTv.turnOn();
		
		
		//class와 같은 참조타입은 == 비교 시 같은 인스턴스인지를 물어본다!
		//singletone 패턴
		if(tv == subTv) {
			System.out.println("같은 빈입니다");
		} else {
			System.out.println("다른 빈입니다");
		}
	}

}
/*
spring이 생성하는 코드
applicationContext (인터페이스)
classpath (단축키로 등록) : sec/main/resources 로 연결해준다!

ApplicationContext ?
 : 빈 객체의 생성, 초기화, 보관, 제거 등을 관리하며 스프링 컨테이너

GenericXmlApplicationContext ? 
 : 작성된 스프링 설정 파일을 읽어와 로딩이 시키고, 객체를 생성하며, 초기화하는 역할

getBean ?
 : 메소드를 이용하여 스프링 컨테이너에 생성된 객체에 접근 가능
=> <bean id="tv" class="com.yedam.app.spring.TV"></bean>

Spring Bean ?
 : 스프링 컨테이너에서 생성되어 관리되는 핵심 객체를 스프링 빈
 기본적으로 싱글톤 스코프(Scope)로 생성
 싱글톤 스코프는 프로그램에서 해당 빈의 인스턴스를 오직 하나만 생성해서 재사용하는 것

*/
