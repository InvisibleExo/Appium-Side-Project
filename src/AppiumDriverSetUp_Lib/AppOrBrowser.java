package AppiumDriverSetUp_Lib;

import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.remote.MobileCapabilityType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AppOrBrowser {
	
	public DesiredCapabilities determinePlat(DesiredCapabilities caps) {
		String sysValue = "";
		if (System.getProperty("plat") != null) {
			sysValue = System.getProperty("plat");
		}
		
		switch((String)caps.getCapability("platformName")) {
		
		case "Android":
			System.out.println(sysValue);
			if(sysValue.equalsIgnoreCase("chrome")) {
				caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
				System.setProperty("webdriver.chrome.driver", "./resources/chromedriver.exe");
			}
			else if (sysValue.equalsIgnoreCase("app")) {
				try {	
					JSONObject apkStartPoint = (JSONObject) new JSONParser().parse(new FileReader("./APKDir/APKStartPoint.json"));
					caps.setCapability("appPackage", (String)apkStartPoint.get("appPackage"));
					caps.setCapability("appActivity", (String)apkStartPoint.get("appActivity"));
					caps.setCapability("appWaitActivity", (String)apkStartPoint.get("appActivity"));
					caps.setCapability("waitAppDuration", 25000);
				} 
				catch (IOException | ParseException e) {
					e.printStackTrace();
				}
			}
			else {
				System.out.println("No browser or app preferred. /n Setting home screen as activity.");
				caps.setCapability("appPackage", "com.android.launcher3");
				caps.setCapability("appActivity", "com.android.launcher3.Launcher");
			}
			
		caps.setCapability("autoGrantPermissions", true);
		break;
		
		//case ios section 
		
		}
		
		return caps;
	}
}
