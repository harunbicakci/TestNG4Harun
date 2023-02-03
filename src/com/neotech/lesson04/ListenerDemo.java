package com.neotech.lesson04;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.neotech.utils.Listener.class)
public class ListenerDemo {

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before Method");
	}
	
	@Test
	public void test1() {
		System.out.println("This is test1");

		// I am passing this test
		Assert.assertTrue(true);
	}
	
	@Test
	public void test2() {
		System.out.println("This is test2");
		
		// I am failing this test
		Assert.assertTrue(false);
	}
	
	
}
