package BaseTest;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import AppiumDriverSetUp_Lib.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;


public class BaseTest {
	
	protected AppiumDriver<MobileElement> driver;
	protected WebDriverWait wait;
	
	//Change to default mobile Layout classes for anything on the screen
	public PageObject pageObject = null;
	
	public AppiumDriverSetup createDrivers = new AppiumDriverSetup();
	
	
	@BeforeClass(alwaysRun=true)
	public void setupDrivers() throws IOException {
		createDrivers.makeList();
		for(AppiumDriver<MobileElement> driver : createDrivers.getActiveList()){
		
			this.driver = driver;
			TLDriverFactory.setTLDriver(driver);
			System.out.println("Calling for driver: " + driver.getCapabilities());
			
			wait = new WebDriverWait(TLDriverFactory.getTLDriver(), 10);
			testActivation();
		}
	}
	
	@Test
	public void testActivation() {
		assertTrue(driver != null);
		
		pageObject = new PageObject(TLDriverFactory.getTLDriver());
		pageObject.driver.get("https://www.google.com");
		
	}
	
	
	
	
}
