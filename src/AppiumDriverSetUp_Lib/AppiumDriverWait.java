package AppiumDriverSetUp_Lib;

import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
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
	
	//Add methods to check if widget/webpage is fully loaded.
	public void waitUntilPageLoaded() {
		Wait<WebDriver> wait = new WebDriverWait(driver, 30);
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"));
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	
	}
		
	
	
	public boolean checkPageLoaded() {
		boolean completeLoad = false;
		
		return completeLoad;
		
	}
	
	
	//Add method to check if element goes stale and reinitialze 
	
	
	
	//Should I replace this class with FluentAppiumWait? Should I mimic AppiumDriverWait to extend from FluentAppiumWait?
	
	
	
	

}
