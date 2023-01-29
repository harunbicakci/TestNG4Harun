package com.neotech.utils;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods extends BaseClass{

	// --> /** and enter
	
	/**
	 * This method first clears the text box and sends some text
	 * 
	 * @param element
	 * @param text
	 */
	public static void sendText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);	
	}
	
	/**
	 * This method checks if RadioButton or CheckBox isEnabled and then 
	 * clicks on the element that has the given value
	 * 
	 * @param elementList
	 * @param value
	 */
	public static void clickRadioOrCheckBox(List<WebElement> elementList, String value) {
		for (WebElement el : elementList) {
			String actualValue = el.getAttribute("value").trim();
			
			if (actualValue.equals(value) && el.isEnabled()) {
				el.click();
				break;
			}
		}
		
	}
	
	/**
	 * This method allows us to call Thread.sleep() for given seconds
	 * 
	 * @param seconds
	 */
	public static void wait(int seconds) {
		
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method checks whether a visible text is found in a drop-down and selects
	 * it.
	 * 
	 * @param element
	 * @param visibleText
	 */
	public static void selectDropDown(WebElement element, String visibleText) {
		try {
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	  * This method checks if a given index is valid for the WebElement and
	  * only then it selects an option by using the index
	  * 
	  * @param element
	  * @param index
	  */
	public static void selectDropDown(WebElement element, int index) {
		try {
			Select sel = new Select(element);
		
			int size = sel.getOptions().size();
		
			if (size > index) {
			sel.selectByIndex(index);
		}
		}catch ( Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will accept the alert
	 */
	public static void acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}catch(NoAlertPresentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will dismiss the alert
	 */
	public static void dismissAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		}catch(NoAlertPresentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will getText() from the alert text. If no alert is present
	 * the method will return "null"
	 */
	public static String getAlertText() {
		String alertText = null;
		try {
			Alert alert = driver.switchTo().alert();
			alertText = alert.getText();
		}catch(NoAlertPresentException e) {
			e.printStackTrace();
		}
	return alertText;
	}
	
	/**
	 * This method will sendKeys() the the alert
	 */
	public static void sendAlertText(String text) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(text);
		}catch(NoAlertPresentException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method switches to a frame using name or id 
	 * 
	 * @param nameOrId
	 */
	public static void switchToFrame(String nameOrId) {
		try {
			driver.switchTo().frame(nameOrId);
		}catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method switches to a frame using index
	 * 
	 * @param nameOrId
	 */
	public static void switchToFrame(int index) {
		try {
			driver.switchTo().frame(index);
		}catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method switches to a frame using WebElement
	 * 
	 * @param nameOrId
	 */
	public static void switchToFrame(WebElement element) {
		try {
			driver.switchTo().frame(element);
		}catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}
		
	/**
	 * This method switches to the child window
	 */
	public static void switchToChildWindow() {
		String mainWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		
		for (String handle : handles) {
			if(!mainWindow.equals(handle)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
	}
	
	/**
	 * This method creates a WebDriver object and returns it
	 * 
	 * @return wait
	 */
	public static WebDriverWait getWaitObject(){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		return wait;
	}
	
	/**
	 * This method will wait for element to be clickable
	 * 
	 * @param element
	 * @return
	 */
	public static WebElement waitForClickability(WebElement element) {
		return getWaitObject().until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	/**
	 * This method will wait for element to be visible
	 * 
	 * @param element
	 * @return
	 */
	public static WebElement waitForVisibility(WebElement element) {
		return getWaitObject().until(ExpectedConditions.visibilityOf(element));
		
	}
	
	/**
	 * This method waits for an element to be clickable and then clicks on it
	 * 
	 * @param element
	 */
	public static void click(WebElement element) {
		waitForClickability(element);
		element.click();
		
	}
	
	/**
	 * This method will cast the driver to a JavascriptExecutor and return it
	 * 
	 * @return
	 */
	public static JavascriptExecutor getJSObject() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js;
		// in one line
	//	return (JavascriptExecutor) driver;
	}
	
	/**
	 * This method will click on an element that is passed 
	 * using JavascriptExecutor
	 * 
	 * @param element
	 */
	public static void jsClick(WebElement element) {
		
		getJSObject().executeScript("arguments[0].click();", element);
	}
	
	/**
	 * This method will scroll the page until specific element is in view
	 * 
	 * @param element
	 */
	public static void scrollToElement (WebElement element) {
		
		getJSObject().executeScript("arguments[0].scrollIntoView(true)", element);
	}
	
	/**
	 * This method will scroll the page down based on the pixel parameter
	 * 
	 * @param pixel
	 */
	public static void scrollDown(int pixel) {
		
		getJSObject().executeAsyncScript("window.scrollBy(0,"+pixel+")");
	}
	
	/**
	 * This method will scroll the page up based on the pixel parameter
	 * 
	 * @param pixel
	 */
	public static void scrollUp(int pixel) {
		
		getJSObject().executeAsyncScript("window.scrollBy(0,-"+pixel+")");
	}
	
	
	/**
	 * This method will select a date from calendar
	 * 
	 * @param elements
	 * @param date
	 */
	public static void selectCalendarDate(List<WebElement> elements, String date) {
		
		for(WebElement day : elements) {
			if(day.isEnabled()) {
				if(day.getText().equals(date)) {
					day.click();
					break;
				}
			}else {
				System.out.println("This day is NOT enabled!");
				break;
			}
		}	
	}
	
}
