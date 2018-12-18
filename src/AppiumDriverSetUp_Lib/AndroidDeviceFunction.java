package AppiumDriverSetUp_Lib;



import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AndroidDeviceFunction extends DeviceFunction {

	private AndroidDriver<MobileElement> driver;
	private static String defaultContext;
	
	
	AndroidDeviceFunction(AppiumDriver<MobileElement> driver){
		this.driver = (AndroidDriver<MobileElement>) driver;
		defaultContext = this.driver.getContext();
	}
	
	
	void goHome() {
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
	}

	void goBack() {
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}

	
	void viewActiveAppList() {
		driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
	}


	
	void resumeApp(String appName){
		
		driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
		
		try {Thread.sleep(8000);} catch (InterruptedException e) {e.printStackTrace();}
		driver.context("NATIVE_APP");
		driver.findElementByAccessibilityId(appName).click();
		try {Thread.sleep(4000);} catch (InterruptedException e) {e.printStackTrace();}
		driver.context(defaultContext);
		
	}

}
