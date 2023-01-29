package com.neotech.lesson01;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.neotech.utils.CommonMethods;
import com.neotech.utils.ConfigsReader;

public class Homework1 extends CommonMethods{

//	Homework 1: HRMS Application Negative Login:
//	    1. Open chrome browser
//	    2. Go to "https://hrm.neotechacademy.com/"
//	    3. Enter valid username
//	    4. Leave password field empty
//	    5. Verify error message with text "Password cannot be empty" is displayed.
	
	
	
	@BeforeMethod
	public void openAndNavigate() {
		setUp();
	}
	
	@AfterMethod
	public void quitBrowser() {
		tearDown();
	}
	
	@Test
	public void emptyPasswordTest() throws Exception {
		sendText(driver.findElement(By.id("txtUsername")), ConfigsReader.getProperty("username"));
		sendText(driver.findElement(By.id("txtPassword")), "");
		click(driver.findElement(By.xpath("//button[@type='submit']")));
		wait(1);
		
		String expectedError = "Password cannot be empty";
		String actualError = driver.findElement(By.xpath("//span[@id='txtPassword-error']")).getText();
		
		if(actualError.equals(expectedError)) {
			System.out.println("Error message verificaton test PASSED!");
		}else {
			System.out.println("Error message verificaton test FAILED!");
			throw new Exception();
		}
	}
	
}
