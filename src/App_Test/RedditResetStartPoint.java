package App_Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;


public class RedditResetStartPoint {
	
	
	public void resetStartPointSignInOptions(AppiumDriver<MobileElement> driver) {
		System.out.println("Resetting StartPoint " + driver.getCapabilities().getCapability("platformName")+"");
		switch(driver.getCapabilities().getCapability("platformName")+"") {
		
		case "android":
			System.out.println("Currently on: "+ ((AndroidDriver<MobileElement>)driver).currentActivity());
			((AndroidDriver<MobileElement>) driver).startActivity(new Activity("com.reddit.frontPage", "com.reddit.frontPage.StartActivity"));
			break;
		}
		//placeholder for ios ver.
		
	}
	

}
