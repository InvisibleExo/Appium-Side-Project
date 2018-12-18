package BaseTest;

import static org.testng.Assert.assertTrue;


import java.net.MalformedURLException;



import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import AppiumDriverSetUp_Lib.*;
import Browser_Lib.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;


public class BaseTest {
	
	protected AppiumDriver<MobileElement> driver;
	protected WebDriverWait wait;
	
	//Change to default mobile Layout classes for anything on the screen
	public BaseScreen pageObject = null;
	
	public ParamDriverConstructor driverSetup = new ParamDriverConstructor();
	
	
	@BeforeClass(alwaysRun=true)
	@Parameters({"platform", "udid", "deviceName", "URL", "port"})
	public void driverSetUp(String platform, String udid, String deviceName, 
			String URL ,String port) throws MalformedURLException {
		System.out.println("before test");
		driver = driverSetup.driverPreTestSetUp(platform, udid, deviceName, URL, port);
		System.out.println("Testing driver: " + driver.getCapabilities().getCapability(MobileCapabilityType.DEVICE_NAME));
		TLDriverFactory.setTLDriver(driver);
		wait = new WebDriverWait(TLDriverFactory.getTLDriver(), 20);
		
		
	}
	
	
	@Test
	public void testActivation() {
		assertTrue(driver != null);
		
		TLDriverFactory.getTLDriver().get("https://www.google.com");
		pageObject = new BaseScreen(TLDriverFactory.getTLDriver());
		System.out.println(TLDriverFactory.getTLDriver().getCurrentUrl());
		
		assertTrue(pageObject.getCurrentUrl().equals("https://www.google.com/"));
		
		try {generalWait(6000);} catch (InterruptedException e) {e.printStackTrace();}
		
		GoogleMainPage googleMainPage = new GoogleMainPage(TLDriverFactory.getTLDriver());
		
		googleMainPage.searchQuery("star wars");
		
		try {generalWait(10000);} catch (InterruptedException e) {e.printStackTrace();}
		
		GoogleSearchResultsPage resultsPage = new GoogleSearchResultsPage(TLDriverFactory.getTLDriver());
		
		resultsPage.swipeVertical(0.7, 0.25, 6000);
		
		try {generalWait(4000);} catch (InterruptedException e) {e.printStackTrace();}
		
	}
	
	@Test
	public void deviceCommands() {
		TLDriverFactory.getTLDriver().get("https://www.google.com");
		
		try {generalWait(6000);} catch (InterruptedException e) {e.printStackTrace();}
		
		GoogleMainPage mainPage = new GoogleMainPage(TLDriverFactory.getTLDriver());
		
		mainPage.goBack();
		
		try {generalWait(4000);} catch (InterruptedException e) {e.printStackTrace();}
		
		mainPage.goHome();
		
		try {generalWait(2000);} catch (InterruptedException e) {e.printStackTrace();}
		//open
		mainPage.viewActiveAppList();
		//close
		mainPage.viewActiveAppList();
		
		try {generalWait(2000);} catch (InterruptedException e) {e.printStackTrace();}
		
		mainPage.resumeApp("Chrome");
		
		try {generalWait(6000);} catch (InterruptedException e) {e.printStackTrace();}
		
	}
	
	public void generalWait(int time) throws InterruptedException {
		Thread.sleep(time);
	}
	
	@AfterClass(alwaysRun=true)
	public void closingTime() {
		TLDriverFactory.getTLDriver().quit();
		TLDriverFactory.getThread().remove();
	}
	
	
	
	
}
