package com.yedam.app.javabase;

public class Restaurant {
	
	private Chef chef;
	
	/*
	 생성자 인젝션
	  : 클래스의 생성자를 통해서 의존성을 주입해주는 방식
	  constructor-arg -> 해당 클래스가 가진 생성자로 매핑되어있다
	  (매핑되어있는 코드들끼리 연결이 안되면 에러)
	  
	  xml 파일 내 생성자 인젝션 코드
	  <constructor-arg ref="chef" />
	 */
	Restaurant(Chef chef){
		this.chef = chef;
		System.out.println("생성자 인젝션");
	}

	
	/*
	세터 인젝션(기본베이스)
	 : 클래스의 수정자를 통해서 의존성을 주입해주는 방식(반드시 기본 생성자가 있어야한다)
	 property -> setter 로 매핑되어있다
	 (매핑되어있는 코드들끼리 연결이 안되면 에러) 
	 생성자가 명시되어있지 않으면 자바는 가장 기본형태의 생성자를 호출하도록 되어있다
	 
	  xml 파일 내 세터 인젝션 코드
	 <property name="chef" ref="chef" />
	 */
	Restaurant() {
		System.out.println("세터 인젝션");
	}
	public void setChef(Chef chef) {
		this.chef = chef;
	}

	
	public void run() {
		chef.cooking();
	}

}


/*
DI ?
 : 외부에서 두 객체 간의 관계를 결정해주는 디자인 패턴
 클래스 레벨에서는 의존성이 고정되지 않도록 하고, 런타임 시 관계를 동적으로 주입 (xml 폴더 내 기재)
 두 객체 간의 관계(의존성)를 맺어주는 것을 의존성 주입이라고 하며 생성자 주입, 필드 주입, 수정자 주입 등 
 
 
 => 결론!
 세터 인젝션 : 주입받는 객체가 변경될 가능성이 있는 경우에 사용
 생성자 인젝션 : 생성자의 호출 시점에 1회 호출 되는 것이 보장 (주입받은 객체가 변하지 않거나, 반드시 객체의 주입이 필요한 경우에 강제하기 위해 사용)
 
 
*/
