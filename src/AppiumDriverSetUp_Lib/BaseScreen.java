package AppiumDriverSetUp_Lib;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;



public class BaseScreen {
	protected AppiumDriver<MobileElement> driver;
	protected WebDriverWait wait;
	private Swipe swipe;
	private DeviceFunction deviceCommands;

	
	public BaseScreen(AppiumDriver<MobileElement> driver){
		this.driver = driver;
		determineSwipe(this.driver);
		determineDeviceFunction(this.driver);
		wait = new WebDriverWait(driver, 15);
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
	
	public void swipeHorizontal (double startPercentage, double finalPercentage, int duration) {
		swipe.swipeHorizontal(startPercentage, finalPercentage, duration);
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
	
	
	
	
	
	
}
