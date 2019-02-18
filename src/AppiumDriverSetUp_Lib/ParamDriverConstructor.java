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
	
	private AppiumDriver<MobileElement> driver;
	
	private DesiredCapabilities caps;
	
	public AppOrBrowser determinePlatType = new AppOrBrowser();
	
	enum Devices{
		ANDROID, IOS;
	}
	
	public AppiumDriver<MobileElement> driverPreTestSetUp(String platform, String udid, String deviceName, 
			String URL ,String port) throws MalformedURLException{
		Devices d = null;
		caps = new DesiredCapabilities();
		
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
		
		
		//How to fix hang up errors? read ECONNRESET?
		if(platform.equalsIgnoreCase("Android")) {
			d = Devices.ANDROID;
			caps.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, Integer.parseInt(port));
			caps.setCapability("automationName", "UIAutomator2");
		}
		else if(platform.equalsIgnoreCase("IOS")) {
			d = Devices.IOS;
			caps.setCapability("wdaLocalPort", Integer.parseInt(port));
		}
		
		caps.setCapability("udid", udid.trim());
		
		caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 4000);
		caps.setCapability("noReset", false);
		
		caps = determinePlatType.determinePlat(caps);
		
		//caps.setCapability("appiumURL", URL);
		
		switch (d) {
			case ANDROID:
				driver = new AndroidDriver<MobileElement>(new URL(URL), caps);
				break;
			
			case IOS:
				driver = new IOSDriver<MobileElement>(new URL(URL), caps);
				break;
		}
		
		return driver;
		
	}
	
	
}
