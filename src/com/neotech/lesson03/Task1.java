package com.neotech.lesson03;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.neotech.utils.CommonMethods;
import com.neotech.utils.ConfigsReader;

public class Task1 extends CommonMethods {

//	TC 1: OrangeHRM Add Employee:
//		1. Open chrome browser
//		2. Go to "https://hrm.neotechacademy.com/"
//		3. Login to the application
//		4. Add 3 different Employees and Create Login Details by
//		providing:
//		○ First Name
//		○ Last Name
//		○ Username
//		○ Password
//		5. Verify Employee has been added successfully and take
//		screenshot (you must have 3 different screenshots)
//		6. Close the browser
//		Specify a group for this test case, add it into specific suite and
//		execute from the xml file.

	@Test(dataProvider = "fullData", groups = "random")
	public void addEmployee(String username, String lastname, String employeeID, String location) {

		sendText(driver.findElement(By.id("txtUsername")), ConfigsReader.getProperty("username"));
		sendText(driver.findElement(By.id("txtPassword")), ConfigsReader.getProperty("password"));
		click(driver.findElement(By.xpath("//button[@type='submit']")));
		click(driver.findElement(By.xpath("//span[text()='PIM']")));
		click(driver.findElement(By.xpath("//span[text()='Add Employee']")));
		waitForVisibility(driver.findElement(By.id("first-name-box")));
		sendText(driver.findElement(By.id("first-name-box")), username);
		sendText(driver.findElement(By.id("last-name-box")), lastname);
		sendText(driver.findElement(By.id("employeeId")), employeeID);
		selectDropDown(driver.findElement(By.id("location")), location);
		wait(1);
		click(driver.findElement(By.id("modal-save-button")));
		waitForVisibility(driver.findElement(By.id("pim.navbar.employeeName")));

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File("screenshots/HRM/employeeID-" + employeeID + ".png");
		try {
			FileUtils.copyFile(source, destination);
		} catch (Exception e) {
			System.out.println("Screenshot NOT taken!");
			e.printStackTrace();
		}
		System.out.println(username + " " + lastname + " --> screenshot successfully taken!");
		wait(1);
	}

	@DataProvider(name = "fullData")
	public Object[][] fullNameArray() {
		Object[][] data = { { "Erdal", "Erzincan", "3015", "France Regional HQ" },
				{ "Ahmet", "Aslan", "3016", "Canadian Regional HQ" },
				{ "Kemal", "Dinc", "3017", "German Regional HQ" } };
		return data;
	}

}
