package com.neotech.lesson03;

import org.testng.annotations.Test;

public class Homework1 {

//	HW1: Executing tests using priority and enable attributes
//
//	Create a class TaskOne that has 5 test methods named:
//	firstMethod
//	firstMethod1
//	secondMethod
//	thirdMethod
//	fourthMethod
//	
//	And each of them printing the method name
//	Run and observe results
//
//	Then, add the needed attributes to print the following result:
//	fourthMethod
//	thirdMethod
//	secondMethod
//	firstMethod
	
	@Test (priority = 3)
	public void firstMethod() throws Exception{
		System.out.println("firstMethod");
		throw new Exception();
	}
	
	@Test (enabled = false, groups="smoke")
	public void firstMethod1() {
		System.out.println("firstMethod1");
	}
	
	@Test (priority = 2)
	public void secondMethod() {
		System.out.println("secondMethod");
	}
	
	@Test (priority = 1,groups="smoke")
	public void thirdMethod() {
		System.out.println("thirdMethod");
	}
	
	@Test (priority = 0)
	public void fourthMethod() {
		System.out.println("fourthMethod");
	}
	
	
	
	
	
}
