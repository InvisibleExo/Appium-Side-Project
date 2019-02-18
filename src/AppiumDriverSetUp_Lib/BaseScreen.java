package AppiumDriverSetUp_Lib;

import org.openqa.selenium.support.PageFactory;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;



public class BaseScreen {
	protected AppiumDriver<MobileElement> driver;
	protected AppiumDriverWait wait;
	private Swipe swipe;
	private DeviceFunction deviceCommands;
	private NotificationBar notificationBar;

	
	public BaseScreen(AppiumDriver<MobileElement> driver){
		this.driver = driver;
		determineSwipe(this.driver);
		determineDeviceFunction(this.driver);
		determineDeviceNotifications(this.driver);
		wait = new AppiumDriverWait(this.driver, 30);
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
	}
	
	void determineSwipe(AppiumDriver<MobileElement> driver) {
		
		if (driver.getCapabilities().getCapability("platformName").equals("Android")) {
			swipe = new AndroidSwipe(driver);
		}
		else if (driver.getCapabilities().getCapability("platformName").equals("IOS")) {
			swipe = new IosSwipe(driver);
		}
		
	}
	
	void determineDeviceFunction(AppiumDriver<MobileElement> driver) {
		if (driver.getCapabilities().getCapability("platformName").equals("Android")) {
			deviceCommands = new AndroidDeviceFunction(driver);
		}
		else if (driver.getCapabilities().getCapability("platformName").equals("IOS")) {
			//Work on ios device funcitons
		}
	}
	
	void determineDeviceNotifications(AppiumDriver<MobileElement> driver) {
		if (driver.getCapabilities().getCapability("platformName").equals("Android")) {
			notificationBar = new AndroidNotificationBar(driver);
		}
		else if (driver.getCapabilities().getCapability("platformName").equals("IOS")) {
			//Work on ios device funcitons
		}
	}
	
	public void openNotifications() {
		notificationBar.openNotifications();
	}
	
	public void closeNotifications() {
		swipe.swipeThroughElementVertical(0.95, 0.1, 2000, notificationBar.getNotificationSection());
	}
	
	public void goBack() {
		deviceCommands.goBack();
	}
	
	public void goHome() {
		deviceCommands.goHome();
	}
	
	public void viewActiveAppList() {
		deviceCommands.viewActiveAppList();
	}
	
	public void resumeApp(String appName) {
		
		deviceCommands.resumeApp(appName);
	}

	public  void  swipeVertical  (double startPercentage, double finalPercentage, int duration) {
		swipe.swipeVertical(startPercentage, finalPercentage, duration);
	} 
	
	public void swipeThroughElementVertical (double startPercentage, double endPercentage, int duration, MobileElement element) {
		swipe.swipeThroughElementVertical(startPercentage, endPercentage, duration, element);
	}
	
	public void swipeHorizontal (double startPercentage, double finalPercentage, int duration) {
		swipe.swipeHorizontal(startPercentage, finalPercentage, duration);
	}
	
	public void swipeThroughElementHorizontal (double startPercentage, double endPercentage, int duration, MobileElement element) {
		swipe.swipeThroughElementHorizontal(startPercentage, endPercentage, duration, element);
	}
	
	
	enum diagonalDirection{
		nw, sw, ne, se;
	}
	public void swipeDiagonal (double startPercentageX, double startPercentageY, double finalPercentageX, 
		double finalPercentageY, int duration, diagonalDirection dir) {
		Swipe.diagonalDirection direct = null;
		switch (dir) {
		case nw:
			direct = Swipe.diagonalDirection.nw;
			break;
		case sw:
			direct = Swipe.diagonalDirection.sw;
			break;
		case ne:
			direct = Swipe.diagonalDirection.ne;
			break;
		case se:
			direct = Swipe.diagonalDirection.se;
			break;
		}
		swipe.swipeDiagonalDirection(startPercentageX, startPercentageY, finalPercentageX, finalPercentageY, duration, direct);
	}
	
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	public void get(String url) {
		driver.get(url);
		wait.waitUntilPageLoaded();
	}
	
	
	
	
	
	
}
