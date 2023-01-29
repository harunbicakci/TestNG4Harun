package com.neotech.lesson02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.neotech.utils.CommonMethods;
import com.neotech.utils.ConfigsReader;

public class SoftAssertDemo extends CommonMethods{

	@BeforeMethod
	public void  openAndNavitage() {
		setUp();
	}
	
	@AfterMethod
	public void  quitBrowser() {
		tearDown();
	}

	@Test
	public void logoAndLoginValidation() {
		
		WebElement logo =driver.findElement(By.xpath("//div[@class='orangehrm-logo']/img"));
		boolean logoDisplayed = logo.isDisplayed();
		
		// I am manually setting this variable to false, to fail the test
		logoDisplayed = false;
		
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(logoDisplayed, "Logo is NOT displayed!");
		
		// Logging in
		sendText(driver.findElement(By.id("txtUsername")), ConfigsReader.getProperty("username"));
		sendText(driver.findElement(By.id("txtPassword")), ConfigsReader.getProperty("password"));
		click(driver.findElement(By.xpath("//button[@type='submit']")));
		
		String expected = "Jacqualine";
		String actual = driver.findElement(By.id("account-name")).getText();
		
		soft.assertEquals(actual, expected, "Account name is nNOT correct");
		
		// it will collect all soft test assertions
		// and will decide whether the test passed of failed
		soft.assertAll();
		
		// if test case failed, execution will stop after assertAll();
		// this statement will NOT be executed
		System.out.println("After assertALL();");
		
		
	}
	
	
	
}
