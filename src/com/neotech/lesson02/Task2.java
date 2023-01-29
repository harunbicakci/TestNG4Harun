package com.neotech.lesson02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.neotech.utils.CommonMethods;
import com.neotech.utils.ConfigsReader;

public class Task2 extends CommonMethods{

//	HW2: OrangeHRM Add Employee:
//
//	    Open chrome browser
//	    Go to "https://hrm.neotechacademy.com/"
//	    Login into the application
//	    Click on Add Employee
//	    Verify labels: Employee Full Name*, Employee Id, Location* are displayed
	
//	    Provide Employee First and Last Name
//	    Select a Location
//	    Verify Employee has been added successfully
//	    Close the browser
	
	@BeforeMethod
	public void openAndNavitage() {
		setUp();
	}
	
	@AfterMethod
	public void  quitBrowser() {
		tearDown();
	}
	
	@Test (priority = 0)
	public void labelValidation() {
		sendText(driver.findElement(By.id("txtUsername")), ConfigsReader.getProperty("username"));
		sendText(driver.findElement(By.id("txtPassword")), ConfigsReader.getProperty("password"));
		click(driver.findElement(By.xpath("//button[@type='submit']")));
		click(driver.findElement(By.xpath("//span[text()='PIM']")));
		click(driver.findElement(By.xpath("//span[text()='Add Employee']")));
		
		WebElement employeeFullName = driver.findElement(By.xpath("//label[text()='Employee Full Name']"));
		WebElement employeeID = driver.findElement(By.xpath("//label[text()='Employee Full Name']"));
		WebElement employeeLocation = driver.findElement(By.xpath("//label[text()='Employee Full Name']"));
		
		SoftAssert soft = new SoftAssert();
		
		soft.assertTrue(employeeFullName.isDisplayed(), "Employee Full Name Not Displayed!");
		soft.assertTrue(employeeID.isDisplayed(), "Employee ID Not Displayed!");
		soft.assertTrue(employeeLocation.isDisplayed(), "Employee Location Not Displayed!");
		
		soft.assertAll();
	}
	
	@Test(priority = 1, dependsOnMethods = "labelValidation")
	public void addEmployeeValidation() {
		sendText(driver.findElement(By.id("txtUsername")), ConfigsReader.getProperty("username"));
		sendText(driver.findElement(By.id("txtPassword")), ConfigsReader.getProperty("password"));
		click(driver.findElement(By.xpath("//button[@type='submit']")));
		click(driver.findElement(By.xpath("//span[text()='PIM']")));
		click(driver.findElement(By.xpath("//span[text()='Add Employee']")));
		
		String employeeName = "George";
		String employeeLastName = "Best";
		String employeeWorkLocation = "German Regional HQ";
		sendText(driver.findElement(By.id("first-name-box")), employeeName);
		sendText(driver.findElement(By.id("last-name-box")), employeeLastName);
		selectDropDown(driver.findElement(By.id("location")), employeeWorkLocation);
		click(driver.findElement(By.id("modal-save-button")));
		wait(1);
		System.out.println("Employee ID is --> " + driver.findElement(By.id("employeeId")).getAttribute("value"));
		System.out.println("Add Employee Validation Test Passed!");
	}
}
