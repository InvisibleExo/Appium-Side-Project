package AppiumDriverSetUp_Lib;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class ParamDriverConstructor {
	
	public AppiumDriver<MobileElement> driver;
	
	public DesiredCapabilities caps;
	
	public AppOrBrowser determinePlatType = new AppOrBrowser();
	
	public AppiumDriver<MobileElement> driverPreTestSetUp(String platform, String udid, String deviceName, 
			String URL ,String port) throws MalformedURLException{
		
		caps = new DesiredCapabilities();
		
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
		
		if(platform.equalsIgnoreCase("Android")) {
			caps.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, port);
		}
		else if(platform.equalsIgnoreCase("IOS")) {
			caps.setCapability("wdaLocalPort", port);
		}
		
		caps.setCapability("udid", udid.trim());
		
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "4000");
		caps.setCapability("noReset", false);
		
		caps = determinePlatType.determinePlat(caps);
		
		caps.setCapability("appiumURL", URL);
		
		if(platform.equalsIgnoreCase("Android")) {
			driver = new AndroidDriver<MobileElement>(new URL(URL), caps);
		}
		else if(platform.equalsIgnoreCase("IOS")) {
			driver = new IOSDriver<MobileElement>(new URL(URL), caps);
		}
		
		return driver;
		
		
	}
	
	
}
