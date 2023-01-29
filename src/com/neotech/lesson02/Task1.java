package com.neotech.lesson02;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.neotech.utils.CommonMethods;

public class Task1 extends CommonMethods{

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
	
	@Test (enabled = true, priority = 3)
	public void firstMethod() {
		System.out.println("This is the firstMethod");
	}
	
	@Test (enabled = false)
	public void firstMethod1() {
		System.out.println("This is the firstMethod1");
	}
	
	@Test (enabled = true, priority = 2)
	public void secondMethod() {
		System.out.println("This is the secondMethod");
	}
	
	@Test (enabled = true, priority = 1)
	public void thirdMethod() {
		System.out.println("This is the thirdMethod");
	}
	
	@Test (enabled = true, priority = 0)
	public void fourthMethod() {
		System.out.println("This is the fourthMethod");
	}
	

}
