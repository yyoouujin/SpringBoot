package com.yedam.app.anotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Restaurant {
	
	private Chef chef;
	@Autowired //생성자 인젝션(쓰고자 하는 대상 위에 Autowired)
	Restaurant(Chef chef){
		this.chef = chef;
		System.out.println("생성자 인젝션");
	}


	
	Restaurant() {
		System.out.println("세터 인젝션");
	}
	@Autowired //세터 인젝션(쓰고자 하는 대상 위에 Autowired)
	public void setChef(Chef chef) {
		this.chef = chef;
	}

	
	
	public void run() {
		chef.cooking();
	}

}

