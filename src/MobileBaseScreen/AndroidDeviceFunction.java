package MobileBaseScreen;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

/**
 * AndroidDeviceFunction is a sub class for DeviceFunction class. With functions, go home, go back, view active apps, and resume apps, contains implict and explict waits 
 * to avoid errors with mobile apps and browsers.
 * 
 * @author InvisibleExo
 */

public class AndroidDeviceFunction extends DeviceFunction {

	private AndroidDriver<MobileElement> driver;
	private static String defaultContext;
	private AppiumDriverWait wait;
	
	/**
	 * AndroidDeviceFunction is a sub class for DeviceFunction class. With functions, go home, go back, view active apps, and resume apps, contains implict and explict waits 
	 * to avoid errors with mobile apps and browsers.
	 * 
	 * @param driver
	 */
	
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
		if(driver.getCapabilities().getCapability("browserName") != null) {
			wait.waitUntilPageLoaded();
		}
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
		if(driver.getCapabilities().getCapability("browserName") != null) {
			wait.waitUntilPageLoaded();
		}
		
	}


	boolean turnWiFiOn() {
		return AndroidToggleSettings.turnWiFiOn(driver);
	}


	boolean turnWiFiOff() {
		return AndroidToggleSettings.turnWifiOff(driver);
	}


	boolean turnMobileDataOn() {
		return AndroidToggleSettings.turnMobileDataOn(driver);
	}


	boolean turnMobileDataOff() {
		return AndroidToggleSettings.turnMobileDataOff(driver);
	}


	boolean turnAirPlaneModeOn() {
		return AndroidToggleSettings.turnAirPlaneModeOn(driver);
	}


	boolean turnAirPlaneModeOff() {
		return AndroidToggleSettings.turnAirPlaneModeOff(driver);
	}
	
	

}
