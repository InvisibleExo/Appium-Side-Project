package AppiumDriverSetUp_Lib;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.remote.MobileCapabilityType;

public class AppOrBrowser {
	
	//Enter App packages

	public DesiredCapabilities determinePlat(DesiredCapabilities caps) {
		
		String sysValue = System.getProperty("plat");
		System.out.println(sysValue);
		if(sysValue.equalsIgnoreCase("chrome")) {
			caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
			System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		}
		
		return caps;
	}
}
