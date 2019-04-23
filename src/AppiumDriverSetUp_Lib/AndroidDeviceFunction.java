package AppiumDriverSetUp_Lib;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AndroidDeviceFunction extends DeviceFunction {

	private AndroidDriver<MobileElement> driver;
	private static String defaultContext;
	private AppiumDriverWait wait;
	
	AndroidDeviceFunction(AppiumDriver<MobileElement> driver){
		this.driver = (AndroidDriver<MobileElement>) driver;
		defaultContext = this.driver.getContext();
		wait = new AppiumDriverWait(this.driver, 30);
	}
	
	
	void goHome() {
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	void goBack() {
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		wait.waitUntilPageLoaded();
	}

	
	void viewActiveAppList() {
		driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}


	
	void resumeApp(String appName){
		
		viewActiveAppList();
		driver.context("NATIVE_APP");
		MobileElement app = (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId(appName)));
		app.click();
		driver.context(defaultContext);
		wait.waitUntilPageLoaded();
		
	}

}
