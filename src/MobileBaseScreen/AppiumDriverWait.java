package MobileBaseScreen;


import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;


public class AppiumDriverWait extends WebDriverWait {
	
	public AppiumDriverWait(AppiumDriver<MobileElement>driver, long timeOutInSeconds) {
		super(driver, timeOutInSeconds);
	}
	
	protected void waitUntilPageLoaded() {
		until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"));
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}
		
	//check if widget is clickable
	protected void waitUntilElementisClickable(By locatorName) {
		until(ExpectedConditions.elementToBeClickable(locatorName));
	}
	//check if widget is present
	protected void waitUntilElementisPrensent(By locatorName) {
		until(ExpectedConditions.presenceOfElementLocated(locatorName));
	}
	
	protected MobileElement waitAndFindFor(By locator) {
		return (MobileElement) until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	protected boolean checkPageLoaded() {
		boolean completeLoad = false;
		
		return completeLoad;
	}

}
