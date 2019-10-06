package BaseTest;


import java.net.MalformedURLException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import MobileBaseScreen.BaseScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

@Listeners({LogCaptureListener.class, TestMethodListener.class})
public class BaseTest {
	
	protected AppiumDriver<MobileElement> driver;
	protected WebDriverWait wait;
	
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
		TLDriverFactory.setTLDriver(driver);
		wait = new WebDriverWait(TLDriverFactory.getTLDriver(), 60);
		defaultContext = TLDriverFactory.getTLDriver().getContext();
	}
	
	@BeforeMethod(alwaysRun=true)
	public void prepSetUp() {
		System.out.println("Set to Default before test:");
		TLDriverFactory.getTLDriver().context("NATIVE_APP");
		System.out.println("Current SessionID for: " + TLDriverFactory.getTLDriver().getCapabilities().getCapability("udid") +  ": " +TLDriverFactory.getTLDriver().getSessionId().toString());
		TLDriverFactory.getTLDriver().manage().logs().getAvailableLogTypes();
		TLDriverFactory.getTLDriver().manage().logs().get("server");
		if((TLDriverFactory.getTLDriver().getPlatformName()+"").toLowerCase().contains("android")){
			TLDriverFactory.getTLDriver().manage().logs().get("logcat");
		}
		
		TLDriverFactory.getTLDriver().context(defaultContext);
	}
	
	
	@AfterTest(alwaysRun=true)
	public void closingTime() {
		TLDriverFactory.getTLDriver().quit();
	}
	
	
	
	
}
