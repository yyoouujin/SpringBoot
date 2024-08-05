package com.yedam.app.javabase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaBeanConfig {
	@Bean
	public Chef chef() {
		return new Chef();
	}
	
	@Bean
	public Restaurant restaurant(Chef chef) {
		return new Restaurant(chef);
	}

	
}

/*
Configuration ?
 : spring 에서 bean을 수동으로 등록하기 위함(내부와 관련된 설정을 들고있다)
 선언되어있는 메소드를 모두 실행하고, 해당 결과를 bean으로 등록
 
@Configuration - 스프링 컨테이너는 @Configuration이 붙어있는 클래스를 자동으로 빈으로 등록하게 된다
@Bean - 메소드를 실행한 결과를 Bean으로 등록하겠다
*/
