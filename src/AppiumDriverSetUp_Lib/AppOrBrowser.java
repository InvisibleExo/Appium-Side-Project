package AppiumDriverSetUp_Lib;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.remote.MobileCapabilityType;

public class AppOrBrowser {
	
	//Enter App packages

	public DesiredCapabilities determinePlat(DesiredCapabilities caps) {
		String sysValue = "";
		if (System.getProperty("plat") != null) {
			sysValue = System.getProperty("plat");
		}
		
		System.out.println(sysValue);
		if(sysValue.equalsIgnoreCase("chrome")) {
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		}
		else {
			System.out.println("No browser or app preferred. /n Setting home screen as activity.");
			
			//Needs work to set up a softcode alternative
			caps.setCapability("appPackage", "com.android.launcher3");
			caps.setCapability("appActivity", "com.android.launcher3.Launcher");
		}
		
		return caps;
	}
}
