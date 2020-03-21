package BaseTest;

import java.net.MalformedURLException;

import org.testng.annotations.*;

import MobileBaseScreen.BaseScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;


public class BaseTest {
	
	protected AppiumDriver<MobileElement> driver;
	
	
	public BaseScreen pageObject = null;
	
	public ParamDriverConstructor driverSetup = new ParamDriverConstructor();
	
	public static String defaultContext;
	
	
	@BeforeTest(alwaysRun=true)
	@Parameters({"platform", "udid", "deviceName", "URL", "port"})
	public void driverSetUp(String platform, String udid, String deviceName, 
			String URL ,String port) throws MalformedURLException {
		System.out.println("Setting up Driver:");
		driver = driverSetup.driverPreTestSetUp(platform, udid, deviceName, URL, port);
		System.out.println("Testing driver: " + driver.getCapabilities().getCapability(MobileCapabilityType.DEVICE_NAME));
		TLDriver.setTLDriver(driver);
		defaultContext = TLDriver.getTLDriver().getContext();
	}
	
	@BeforeMethod(alwaysRun=true)
	public void prepSetUp() {
		System.out.println("Context currently: " + TLDriver.getTLDriver().getContext());
		System.out.println("Set to Default before test:");
		TLDriver.getTLDriver().context("NATIVE_APP");
		System.out.println("Current SessionID for: " + TLDriver.getTLDriver().getCapabilities().getCapability("udid") +  ": " +TLDriver.getTLDriver().getSessionId().toString());
		TLDriver.getTLDriver().manage().logs().getAvailableLogTypes();
		TLDriver.getTLDriver().manage().logs().get("server");
		if((TLDriver.getTLDriver().getPlatformName()+"").toLowerCase().contains("Android")){
			TLDriver.getTLDriver().manage().logs().get("logcat");
		}
		
		TLDriver.getTLDriver().context(defaultContext);
	}
	
	
	@AfterTest(alwaysRun=true)
	public void closingTime() {
		TLDriver.getTLDriver().quit();
	}
	
	
	
	
}
