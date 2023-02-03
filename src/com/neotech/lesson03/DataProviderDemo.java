package com.neotech.lesson03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.neotech.utils.CommonMethods;

public class DataProviderDemo extends CommonMethods{

	// we added @BeforeMethod and @AfterMethod on the setUp() and tearDown()
	// methods. We set alwaysRun = true
	@BeforeMethod
//	public void openAndNavigate() {
//		setUp();
//	}
//	
//	@AfterMethod
//	public void quitBrowser() {
//		tearDown();
//	}
//	
	@Test(dataProvider = "userData")
	public void loginFunctionality(String username, String password) {
		
		System.out.println(username + " " + password);
		
		WebElement usernameBox = driver.findElement(By.id("txtUsername"));
		sendText(usernameBox, username);
		WebElement passwordBox = driver.findElement(By.id("txtPassword"));
		sendText(passwordBox, password);
		wait(2);
		
		click(driver.findElement(By.xpath("//button[@type='submit']")));	
	}
	
	@DataProvider (name = "userData")
	public Object[][] getData() {
	
		// 1st test: Admin		Admin123 --> { 	{"Admin", "Admin123"} 
		// 2nd test: Burak		Admin456 --> 	{"Burak", "Admin456"}
		// 3rd test: Oguzhan	Admin789 --> 	{"Oguzhan", "Admin789"} }
		
		// the @Test method calling this dataProvider will know how many elements are
		// in the 2nd array and it will run depending on that
		
		Object[][] credentials = {
				{"Admin", "Admin123"},
				{"Burak", "Admin456"},
				{"Oguzhan", "Admin789"}
		};
		
		return credentials;
	}
	
	
	
	
	
	
	
	
}
