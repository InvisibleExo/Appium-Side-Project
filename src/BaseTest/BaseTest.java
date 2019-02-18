package BaseTest;

import static org.testng.Assert.assertTrue;


import java.net.MalformedURLException;



import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import AppiumDriverSetUp_Lib.*;
import Browser_Lib.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

@Listeners({ScreenshotListener.class, TestMethodListener.class})
public class BaseTest {
	
	protected AppiumDriver<MobileElement> driver;
	protected WebDriverWait wait;
	
	public BaseScreen pageObject = null;
	
	public ParamDriverConstructor driverSetup = new ParamDriverConstructor();
	
	public static String defaultContext;
	
	
	@BeforeClass(alwaysRun=true)
	@Parameters({"platform", "udid", "deviceName", "URL", "port"})
	public void driverSetUp(String platform, String udid, String deviceName, 
			String URL ,String port) throws MalformedURLException {
		System.out.println("before test");
		driver = driverSetup.driverPreTestSetUp(platform, udid, deviceName, URL, port);
		System.out.println("Testing driver: " + driver.getCapabilities().getCapability(MobileCapabilityType.DEVICE_NAME));
		TLDriverFactory.setTLDriver(driver);
		wait = new WebDriverWait(TLDriverFactory.getTLDriver(), 60);
		System.out.println(TLDriverFactory.getTLDriver().getContextHandles());
		defaultContext = TLDriverFactory.getTLDriver().getContext();
		
	}
	
	@BeforeTest(alwaysRun=true)
	public void prepSetUp() {
		TLDriverFactory.getTLDriver().context("NATIVE_APP");
		
		TLDriverFactory.getTLDriver().manage().logs().get("server");
		if((TLDriverFactory.getTLDriver().getPlatformName()+"").toLowerCase().contains("android")){
			TLDriverFactory.getTLDriver().manage().logs().get("logcat");
		}
		
		TLDriverFactory.getTLDriver().context(defaultContext);
	}
	
	@Test
	public void testActivation() {
		pageObject = new BaseScreen(TLDriverFactory.getTLDriver());
		pageObject.get("https://www.google.com/");
		System.out.println(pageObject.getCurrentUrl());
		
		assertTrue(pageObject.getCurrentUrl().equals("https://www.google.com/"));
		
		GoogleMainPage googleMainPage = new GoogleMainPage(TLDriverFactory.getTLDriver());
		
		googleMainPage.searchQuery("star wars");
		
		GoogleSearchResultsPage resultsPage = new GoogleSearchResultsPage(TLDriverFactory.getTLDriver());
		
		resultsPage.swipeVertical(0.7, 0.25, 6000);
		
	}
	
	@Test
	public void deviceCommands() {
		
		pageObject = new BaseScreen(TLDriverFactory.getTLDriver());
		pageObject.get("https://www.google.com");
		
		GoogleMainPage mainPage = new GoogleMainPage(TLDriverFactory.getTLDriver());
		
		mainPage.goBack();
		
		mainPage.goHome();
		//open
		mainPage.viewActiveAppList();
		//close
		mainPage.viewActiveAppList();
			
		mainPage.resumeApp("Chrome");
		
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
