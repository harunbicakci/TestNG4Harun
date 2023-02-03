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

//	Open chrome browser
//	Go to "https://hrm.neotechacademy.com/"
//	Login to the application
//	Add 3 different Employees and Create Login Details by providing:
//	First Name
//	Last Name
//	Username
//	Password
//	Verify Employee has been added successfully and take screenshot (you must have 3 different screenshots)
//	Close the browser
//	Specify a group for this test case, add it into specific suite and execute from the xml file.

	@Test(dataProvider = "excelData", groups = { "smoke", "homework" })
	public void test(String firstName, String lastName, String username, String password) {

		// Login
		sendText(driver.findElement(By.id("txtUsername")), ConfigsReader.getProperty("username"));
		sendText(driver.findElement(By.id("txtPassword")), ConfigsReader.getProperty("password"));
		click(driver.findElement(By.xpath("//button[@type='submit']")));

		// Click on PIM menu
		driver.findElement(By.id("menu_pim_viewPimModule")).click();
		// Click on Add Employee
		driver.findElement(By.linkText("Add Employee")).click();

		wait(1);

		// Enter New Employee Data
		sendText(driver.findElement(By.id("first-name-box")), firstName);
		sendText(driver.findElement(By.id("last-name-box")), lastName);

		// Get empID for validation
		String empID = driver.findElement(By.id("employeeId")).getAttribute("value");

		// Select the correct location
		WebElement dropdown = driver.findElement(By.id("location"));
		Select sel = new Select(dropdown);
		sel.selectByVisibleText("France Regional HQ");
		// Extra HW, pass the location as a parameter

		// Selenium click didn't work, we used JavascriptExecutor
		jsClick(driver.findElement(By.id("hasLoginDetails")));
		wait(1);

		// Provide the username and password for the new Employee
		sendText(driver.findElement(By.id("username")), username);
		sendText(driver.findElement(By.id("password")), password);
		sendText(driver.findElement(By.id("confirmPassword")), password);
		wait(1);

		// Clicking on Save Button
		click(driver.findElement(By.id("modal-save-button")));

		waitForVisibility(driver.findElement(By.id("pimPersonalDetailsForm")));

		// Validation
		String actualID = driver.findElement(By.id("employeeId")).getAttribute("value");
		Assert.assertEquals(actualID, empID, "EmployeeId's do NOT match!");

		// Take Screenshot

		// 1st way
//		TakesScreenshot ts = (TakesScreenshot) driver;
//		File source = ts.getScreenshotAs(OutputType.FILE);
//		try {
//			FileUtils.copyFile(source, new File("screenshot/" + firstName + "_" + lastName + ".png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		// 2nd way
		takeScreenshot(firstName + "_" + lastName);
	}

	@DataProvider(name = "getData")
	public Object[][] createData() {
		Object[][] data = { { "Jeff", "Bezos", "Jeff20234", "Bezos@1234" },
				{ "Bill", "Gates", "Bill20234", "Gates@1234" }, 
				{ "Elon", "Musk", "Elon20234", "Musk@1234" } 
			};

		return data;
	}

	@DataProvider(name = "excelData")
	public Object[][] getExcelData() {
		return ExcelUtility.excelIntoArray(System.getProperty("user.dir") + "/testdata/Excel.xlsx", "Employee");
	}

}