package App_Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;


public class RedditResetStartPoint {
	
	
	//Unable to use DeepLink or other back-end techniques since this app is a prod app
	//Can only resort to StartActivity for Android (public iOS apps might be more difficult for moving around start points)
	//Dev apps with ownership would be preferable to use DeepLink/Back-End Techniques
	
	public void resetStartPointSignInOptions(AppiumDriver<MobileElement> driver) {
		System.out.println("Currently on : " + currentActivityStatus(driver));
		switch(driver.getCapabilities().getCapability("platformName")+"") {
		
		case "Android":
			System.out.println("Resetting StartPoint");
			((AndroidDriver<MobileElement>) driver).startActivity(new Activity("com.reddit.frontpage", "com.reddit.frontpage.IntroductionActivity"));
			break;
		}
		//placeholder for ios ver.
		
	}
	
	
	public void resetStartPointMainActivityPage(AppiumDriver<MobileElement> driver) {
		System.out.println("Currently on : " + currentActivityStatus(driver));
		switch(driver.getCapabilities().getCapability("platformName")+"") {
		
		case "Android":
			System.out.println("Resetting StartPoint");
			((AndroidDriver<MobileElement>) driver).startActivity(new Activity("com.reddit.frontpage", "com.reddit.frontpage.MainActivity"));
			break;
		}
		//placeholder for ios ver.
	}
	
	public void resetStartPointLoginPrompt(AppiumDriver<MobileElement> driver) {
		System.out.println("Currently on : " + currentActivityStatus(driver));
		switch(driver.getCapabilities().getCapability("platformName")+"") {
		
		case "Android":
			System.out.println("Resetting StartPoint");
			((AndroidDriver<MobileElement>) driver).startActivity(new Activity("com.reddit.frontpage", "com.reddit.frontpage.redditauth_private.ui.AuthActivity"));
			break;
		}
		//placeholder for ios ver.
	}
	
	public void resetStartPointSettings(AppiumDriver<MobileElement> driver) {
		System.out.println("Currently on : " + currentActivityStatus(driver));
		switch(driver.getCapabilities().getCapability("platformName")+"") {
		
		case "Android":
			System.out.println("Resetting StartPoint");
			((AndroidDriver<MobileElement>) driver).startActivity(new Activity("com.reddit.frontpage", "com.reddit.frontpage.ui.preferences.PreferencesActivity"));
			break;
		}
		//placeholder for ios ver.
	}
	
	public String currentActivityStatus(AppiumDriver<MobileElement> driver) {
		String currentActivity = "Error in platform provided";
		switch(driver.getCapabilities().getCapability("platformName")+"") {
		
		case "Android":
			currentActivity = ((AndroidDriver<MobileElement>)driver).currentActivity();
			break;
		}
		return currentActivity;
	}

}
