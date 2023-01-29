package com.neotech.lesson02;

import org.testng.annotations.Test;

public class DependsDemo {

	@Test
	public void firstTest() {
		System.out.println("First Test Method");
	}
	
	@Test
	public void secondTest() {
		System.out.println("Second Test Method");
	}
	
	@Test(dependsOnMethods = "secondTest")
	public void thirdTest() {
		System.out.println("Third Test Method");
	}
	
	@Test(dependsOnMethods = "thirdTest")
	public void fourthTest() {
		System.out.println("Fourth Test Method");
	}
	
	
}
