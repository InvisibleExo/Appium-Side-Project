package BaseTest;

import static org.testng.Assert.assertTrue;


import java.net.MalformedURLException;



import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import AppiumDriverSetUp_Lib.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;


public class BaseTest {
	
	protected AppiumDriver<MobileElement> driver;
	protected WebDriverWait wait;
	
	//Change to default mobile Layout classes for anything on the screen
	public PageObject pageObject = null;
	
	public ParamDriverConstructor driverSetup = new ParamDriverConstructor();
	
	
	/*@BeforeSuite(alwaysRun=true)
	public void suiteSetUp() throws IOException, InterruptedException {
		System.out.println("Before Suite");
		createDrivers.makeList();
		System.out.println("Drivers List size:" + createDrivers.getActiveList().size());
		//System.out.println("Drivers List size:" + AppiumDriverSetup.getActiveList().size());
		makeFile.setupDriverXMLFile(createDrivers.getActiveList());
		//createXMLFile();
		
		
		try {
			makeFile.createDriverFile();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
			System.out.println("file not created");
		} 
	}  */
	
	
	
	@BeforeTest(alwaysRun=true)
	@Parameters({"platform", "udid", "deviceName", "URL", "port"})
	public void driverSetUp(String platform, String udid, String deviceName, 
			String URL ,String port) throws MalformedURLException {
		System.out.println("before test");
		driver = driverSetup.driverPreTestSetUp(platform, udid, deviceName, URL, port);
		System.out.println("Testing driver: " + driver.getCapabilities().getCapability("deviceName"));
		TLDriverFactory.setTLDriver(driver);
		wait = new WebDriverWait(TLDriverFactory.getTLDriver(), 15);
	}
	
	@Test
	public void testActivation() {
		assertTrue(driver != null);
		
		pageObject = new PageObject(TLDriverFactory.getTLDriver());
		pageObject.driver.get("https://www.google.com");
		System.out.println(pageObject.driver.getCurrentUrl());
		assertTrue(pageObject.driver.getCurrentUrl().equals("https://www.google.com/"));
		
	}
	
	
	
	
	
}
