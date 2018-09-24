package AppiumDriverSetUp_Lib;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class TLDriverFactory {
	
	private static ThreadLocal<AppiumDriver<MobileElement>> tlDriver = new ThreadLocal<AppiumDriver<MobileElement>>();
	
	

	public synchronized static void setTLDriver(AppiumDriver<MobileElement> driver) {
		tlDriver.set(driver);
	}
	
	public synchronized static AppiumDriver<MobileElement> getTLDriver() {
		return tlDriver.get();
	}
	
	public synchronized static ThreadLocal<AppiumDriver<MobileElement>> getThread() {
		return tlDriver;
	}
}
