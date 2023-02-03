package com.neotech.lesson03;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.neotech.utils.CommonMethods;

public class AddEmployeeWithDataProvider extends CommonMethods{

	// because we have said @BeforeMethod and @AfterMethod on BaseClass
	// we do not need any @BeforeMethod and @AfterMEthod anymore
	
	@Test
	public void addEmployee(String firstName, String lastName) {
		
		System.out.println(firstName + " " + lastName);
		// login
		
		
		
		
		// navigate to PIM menu
		
		//click on Add Employee
		
		
		// send employee data: firstName, lastName, location
		
		
		
		// save employee
		
	}
	
	@DataProvider(name = "")
	public Object[][] create2DArray(){
		
		// { {"name1", "lastName1}, {"name2", "lastName2"}, ... }
		
		Object[][] data = { {"Murad", "Bayramov"}, {"Emine", "Karakaya"}, {"Vugar", "Alisultanov"} };
		
		return data;
	}
	
	
}
