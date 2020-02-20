package MobileBaseScreen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
* BaseScreen is a class to simulate the basic default functionality of mobile smart phone devices.
* This class includes AppiumDriver class, a Wait class to support fluent and implicit waits, Swipe class, DeviceFunction class(for Go Back button, Home button,
* and view Active tab,), Notification class(Open notification bar), and PageFactory class, (using AppiumFieldDecorator class for initElement() method parameter,) to support element initialization.
* 
* @Declaration Constructor parameters for new instance: BaseScreen(AppiumDriver<MobileElement> driver)
* 
* @Note BaseScreen currently supports Android initialization.
* 
* @author InvisibleExo

*/
public class BaseScreen {
	protected AppiumDriver<MobileElement> driver;
	protected AppiumDriverWait wait;
	private Swipe swipe;
	private DeviceFunction deviceCommands;
	private NotificationBar notificationBar;

/**
* BaseScreen is a class to simulate the basic default functionality of mobile smart phone devices.
* This class includes AppiumDriver class, a Wait class to support fluent and implicit waits, Swipe class, DeviceFunction class(for Go Back button, Home button,
* and view Active tab,), Notification class(Open notification bar), and PageFactory class, (using AppiumFieldDecorator class for initElement() method parameter,) to support element initialization.
 * @param driver
 */
	public BaseScreen(AppiumDriver<MobileElement> driver){
		this.driver = driver;
		determineSwipe(this.driver);
		determineDeviceFunction(this.driver);
		determineDeviceNotifications(this.driver);
		wait = new AppiumDriverWait(this.driver, 30);
		PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
	}
/**	
 * This method is called once as part of the BaseScreen initialization. Based on the driver's platformName capability will determine if Swipe class is an
 * instance of AndroidSwipe or IosSwipe.
 * 
 * @param AppiumDriver<MobileElement> driver
*/	
	void determineSwipe(AppiumDriver<MobileElement> driver) {
		switch(driver.getCapabilities().getCapability("platformName")+"") {
			case "Android":
				swipe = new AndroidSwipe(driver);
				break;
			case "IOS":
				swipe = new IosSwipe(driver);
				break;
		}
	}
/**
 *	This method is called once as part of the BaseScreen initialization. Based on the driver's platformName capability will determine if DeviceFunction class is 
 * an instance of AndroidDeviceFunction or IosDeviceFucntion.
 *
 * @param AppiumDriver<MobileElement> driver
 */
	void determineDeviceFunction(AppiumDriver<MobileElement> driver) {
		switch (driver.getCapabilities().getCapability("platformName")+""){
			case "Android":
				deviceCommands = new AndroidDeviceFunction(driver);
				break;
			case "IOS":
				//Work on ios device funcitons
				break;
		}
	}
/**
 * This method is called once as part of the BaseScreen initialization. Based on the driver's platformName capability will determine if NotificationBar class is 
 * an instance of AndroidNotificationBar or IosNotificationBar.
 * 	
 * @param AppiumDriver<MobileElement> driver
 */
	void determineDeviceNotifications(AppiumDriver<MobileElement> driver) {
		switch (driver.getCapabilities().getCapability("platformName")+""){
			case "Android":
				notificationBar = new AndroidNotificationBar(driver);
				break;	
			case "IOS":
				//Work on ios device funcitons
				break;
		}
	}
/**
 *  Sends a command to the NotificatonBar instance to open Notifications on device.	
 */
	public void openNotifications() {
		notificationBar.openNotifications();
	}
/**
 * Sends a command to perform an upward swipe within the expanded notificationBar to close.	
 */
	public void closeNotifications() {
		swipe.swipeThroughElementVertical(0.90, 0.05, 200, notificationBar.getNotificationScroller());
		notificationBar.revertToDefaultContext();
	}
	
	public void readNotificationEvents() {
		notificationBar.seeEventList();
	}
	
	public void openNotificationEvent(String target) {
		notificationBar.openNotificationEvent(target);
	}
/**
 * Sends a command to the DeviceFunction instance to perform the device's go back command.	
 */
	public void goBack() {
		deviceCommands.goBack();
	}
/**
 * Sends a command to the DeviceFunction instance to take the device's home screen.
 */
	public void goHome() {
		deviceCommands.goHome();
	}
	
	public boolean turnOnWifi() {
		return deviceCommands.turnWiFiOn();
	}
	
	public boolean turnOffWifi() {
		return deviceCommands.turnWiFiOff();
	}
	
	public boolean turnOnMobileData() {
		return deviceCommands.turnMobileDataOn();
	}
	
	public boolean turnOffMobileData() {
		return deviceCommands.turnMobileDataOff();
	}
	
	public boolean turnOnAirPlaneMode() {
		return deviceCommands.turnAirPlaneModeOn();
	}
	
	public boolean turnOffAirPlaneMode() {
		return deviceCommands.turnAirPlaneModeOff();
	}
	
/**
 * Sends a command to view the DeviceFunction instance to view any active apps from the device's home screen.	
 */
	public void viewActiveAppList() {
		deviceCommands.viewActiveAppList();
	}
/**
 * Calls the device to view all active apps, search, and select the app based on the String input entered to resume on the app.	
 * @param appName
 */
	public void resumeApp(String appName) {
		deviceCommands.resumeApp(appName);
	}
/**
 * Sends  start point and end point Coordinates, and duration instructions to Swipe instance to perform a vertical swipe along the device's screen. 
 * The vertical swipe is perform within the middle of the screen.
 * 
 * @Note Shorter duration value means a faster swipe is performed.
 * @param startPercentage
 * @param finalPercentage
 * @param duration
 */
	public  void  swipeVertical  (double startPercentage, double finalPercentage, int duration) {
		swipe.swipeVertical(startPercentage, finalPercentage, duration);
	} 
/**
 * Sends  start point and end point Coordinates, and duration instructions to Swipe instance to perform a vertical swipe within the boundaries of the element provided in the parameters. 
 * The vertical swipe is perform within the middle of the element provided in the method's parameters.
 * 	
 * @param startPercentage
 * @param endPercentage
 * @param duration
 * @param element
 */
	public void swipeThroughElementVertical (double startPercentage, double endPercentage, int duration, MobileElement element) {
		swipe.swipeThroughElementVertical(startPercentage, endPercentage, duration, element);
	}
/**
 * Sends  start point and end point Coordinates, and duration instructions to Swipe instance to perform a horizontal swipe along the device's screen. 
 * The horizontal swipe is perform within the middle of the screen.
 * 	
 * @param startPercentage
 * @param finalPercentage
 * @param duration
 */
	public void swipeHorizontal (double startPercentage, double finalPercentage, int duration) {
		swipe.swipeHorizontal(startPercentage, finalPercentage, duration);
	}
/**
 * Sends start point and end point Coordinates, and duration instructions to Swipe instance to perform a horizontal swipe within the boundaries of the element provided. 
 * The horizontal swipe is perform within the middle of the element provided in the method's parameters.
 * 	
 * @param startPercentage
 * @param endPercentage
 * @param duration
 * @param element
 */
	public void swipeThroughElementHorizontal (double startPercentage, double endPercentage, int duration, MobileElement element) {
		swipe.swipeThroughElementHorizontal(startPercentage, endPercentage, duration, element);
	}
/**
 * Direction contain a list of enum variables to help the more complex swipe methods determine which direction the swipe will move towards. 
 * List of enum variables are: (up and down). 
 * 
 * When selecting a Direction enum when required: (Example: Set the direction of the swipe to upward -> Direction.up) 
 * 
 * @author InvisibleExo
 *
 */
	public enum Direction{
		up, down;
	}
/**
 * Sends a start and end points for both X and Y Axis. The first two parameters for this method at the start points, while third and forth parameters are the end points.
 * ex: swipeCustomDirection(startPoint X, startPoint Y, endPoint X, endPoint Y, time duration). 
 * To help determine where to set start and end points, the (0,0) point on the screen is the top left corner, while the bottom right corner is the max length and height point exp:(720, 1280).
 * 
 * @param startPercentageX
 * @param startPercentageY
 * @param finalPercentageX
 * @param finalPercentageY
 * @param duration
 */
	public void swipeCustomDirection (double startPercentageX, double startPercentageY, double finalPercentageX, 
		double finalPercentageY, int duration) {
		swipe.swipeCustomDirection(startPercentageX, startPercentageY, finalPercentageX, finalPercentageY, duration);
	}
	
/**
 * Similar to swipeCustomeDirection method, except range of the swipe within the selected target element pass into the method instead of the screen.
 * 
 * @param startPercentageX
 * @param startPercentageY
 * @param finalPercentageX
 * @param finalPercentageY
 * @param duration
 * @param targetElement
 */
	public void swipeCustomDirectionThroughElement(double startPercentageX, double startPercentageY, double finalPercentageX, 
			double finalPercentageY, int duration, MobileElement targetElement) {
		swipe.swipeCustomDirectionThroughElement(startPercentageX, startPercentageY, finalPercentageX, finalPercentageY, duration, targetElement);
	}

/**
 * This method will continue to swipe either up or down(based on Direction enum provided,) until the desired MobileElement is found or the number of attempts provided for the method has hit its max.
 * 
 * @param method
 * @param attempts
 * @param targetElement
 * @param dir
 * @return MobileElement
 */
	public MobileElement swipeUntilFound(By method, int attempts, MobileElement targetElement, Direction dir) {
		Swipe.Direction direct= null;
		switch (dir) {
		case up:
			direct = Swipe.Direction.up;
			break;
		case down: 
			direct = Swipe.Direction.down;
			break;
		default:
			break;
		}
		return targetElement = swipe.swipeUntilFound(method, attempts, targetElement, direct);
	}
	
	/**
	 *  This method will continue to swipe either up or down(based on Direction enum provided,) until the desired MobileElement is found or the number of attempts provided for the method has hit its max.
	 * This method provides the option with a second By method to help search for desired Mobile Element.
	 * 
	 * @param method
	 * @param secondMethod
	 * @param attempts
	 * @param targetElement
	 * @param dir
	 * @return MobileElement
	 */
	
	public MobileElement swipeUntilFound(By method, By secondMethod, int attempts, MobileElement targetElement, Direction dir) {
		Swipe.Direction direct= null;
		switch (dir) {
		case up:
			direct = Swipe.Direction.up;
			break;
		case down: 
			direct = Swipe.Direction.down;
			break;
		default:
			break;
		}
		return targetElement = swipe.swipeUntilFound(method, secondMethod, attempts, targetElement, direct);
	}
	
	
/**
 * Will continue checking for MobileElement by locator method until element 
 * is now click-able.
 * 	
 * @param locatorName
 */
	public void checkIfElementisClickable(By locatorName) {
		wait.waitUntilElementisClickable(locatorName);
	}
/**
 * Will continue checking for MobileElement by locator method until element 
 * becomes present.
 * 
 * @param locatorName
 */
	public void checkIfElementisPresent(By locatorName) {
		wait.waitUntilElementisPrensent(locatorName);
	}
	
/**
 * Returns a string of the device's current URL. This feature to be used when testing web-browser based applications.
 * 
 * @return String of Current URL
 */
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
/**
 * Sends a command to web-browser to go to the provided http web address.
 * The driver will wait to perform any further actions until the requested URL 
 * has complete its loading in the browser.
 * 	
 * @param url
 */
	public void get(String url) {
		driver.get(url);
		wait.waitUntilPageLoaded();
	}
	
/** 
 * This method is to help create and provide a B64 Image of string for the parameters of 
 * finElementByImage(b64Template). You must know the name of the image you have placed inside the ImageCheck directory 
 * in order to retrieve the that image's b64 encoded String.
 * 
 * @param imageNameRequest
 * @return String of Base64 Encoded scheme of image
 * @throws IOException
 */
	public String getSpeicifiedImageB64(String imageNameRequest) throws IOException {
		File refImgFile = new File("./ImageCheck/"+imageNameRequest);
		return Base64.getEncoder().encodeToString(Files.readAllBytes(refImgFile.toPath()));
	}
}
