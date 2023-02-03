package com.neotech.lesson04;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.neotech.utils.CommonMethods;
import com.neotech.utils.ConfigsReader;
import com.neotech.utils.ExcelUtility;

public class Homework1 extends CommonMethods{

//	TC 1: OrangeHRM Add Employee:
//	1. Open chrome browser
//	2. Go to "https://hrm.neotechacademy.com/"
//	3. Login to the application
//	4. Add 3 different Employees and Create Login Details by
//	providing:
//	○ First Name
//	○ Last Name
//	○ Username
//	○ Password
//	5. Verify Employee has been added successfully and take
//	screenshot (you must have 3 different screenshots)
//	6. Close the browser
//	Specify a group for this test case, add it into specific suite and
//	execute from the xml file.

	@Test (dataProvider = "createData")
	public void test(String firstName, String lastName, String username, String password) {

		// login
		sendText(driver.findElement(By.id("txtUsername")), ConfigsReader.getProperty("username"));
		sendText(driver.findElement(By.id("txtPassword")), ConfigsReader.getProperty("password"));
		click(driver.findElement(By.xpath("//button[@type='submit']")));
		
		// click on PIM meni
		click(driver.findElement(By.xpath("//span[text()='PIM']")));
		// click on Add Employee
		click(driver.findElement(By.xpath("//span[text()='Add Employee']")));
		
		wait(1);
		
		// enter new employee data
		sendText(driver.findElement(By.id("first-name-box")), firstName);
		sendText(driver.findElement(By.id("last-name-box")), lastName);
		
		wait(1);
		
		// enter empID for validation
		String empID = driver.findElement(By.id("employeeId")).getAttribute("value");
		
		// select the correct location
		WebElement dropdown = driver.findElement(By.id("location"));
		Select sel = new Select(dropdown);
		sel.selectByVisibleText("France Regional HQ");
		// extra hw, pass the location as a parameter
		
		// selenium click didnt work, jsClick worked
		jsClick(driver.findElement(By.id("hasLoginDetails")));
		wait(1);
		
		// provide the username and password for the new employee
		sendText(driver.findElement(By.id("username")), username);
		sendText(driver.findElement(By.id("password")), password);
		sendText(driver.findElement(By.id("confirmPassword")), password);
		wait(1);

		// clicking on save button
		click(driver.findElement(By.id("modal-save=button")));
		
		waitForVisibility(driver.findElement(By.id("pimPersonalDetailsForm")));
		
		// validation
		String actualID = driver.findElement(By.id("employeeId")).getAttribute("value");
		Assert.assertEquals(actualID, empID, "EmployeeId's do NOT match!");
		
		// 1st way take screenshot
//		TakesScreenshot ts = (TakesScreenshot) driver;
//		File source = ts.getScreenshotAs(OutputType.FILE);
//		try {
//			FileUtils.copyFile(source, new File("screenshot/" + firstName + "_" + lastName + ".png"));
//		} catch (IOException e) {
//			System.out.println("Screenshot NOT taken");			
//			e.printStackTrace();
//		}
		
		// 2nd way take screenshot --> will save to parameter name
		takeScreenshot(firstName + "_" + lastName);
		
		
		
	}

	@DataProvider
	public Object[][] createData() {
		Object[][] data = { { "Jeff", "Bezos", "Jeff2023", "Bezos@1234" },
				{ "Bill", "Gates", "Bill2023", "Gates@1234" }, 
				{ "Elon", "Musk", "Elon2023", "Musk@1234" } 
			};

		return data;
	}
	
	
	@DataProvider (name = "excelData")
	public Object[][] getExcelData(){
		return ExcelUtility.excelIntoArray(System.getProperty("user.dir") + "/testdata/Excel.xlsx", getAlertText())
	}
	
	
	

}
