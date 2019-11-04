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
	 
	AppiumDriver<MobileElement> driver;
	
	JavascriptExecutor js;

	public AppiumDriverWait(AppiumDriver<MobileElement>driver, long timeOutInSeconds) {
		super(driver, timeOutInSeconds);
		this.driver = driver;
		js = (JavascriptExecutor)driver;
		
		
	}
	
	public void waitUntilPageLoaded() {
		until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"));
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	
	}
		
	//check if widget is clickable
	public void waitUntilElementisClickable(By locatorName) {
		until(ExpectedConditions.elementToBeClickable(locatorName));
	}
	//check if widget is present
	public void waitUntilElementisPrensent(By locatorName) {
		until(ExpectedConditions.presenceOfElementLocated(locatorName));
	}
	
	
	public boolean checkPageLoaded() {
		boolean completeLoad = false;
		
		return completeLoad;
		
	}
	
	
	//Add method to check if element goes stale and reinitialze? 
	
	
	
	
	
	

}
