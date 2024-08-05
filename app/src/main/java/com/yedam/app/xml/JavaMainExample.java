package com.yedam.app.xml;

public class JavaMainExample {

	public static void main(String[] args) {
		Chef chef = new Chef();
		Restaurant res = new Restaurant(chef);
		res.run();
		
		
	}
}
