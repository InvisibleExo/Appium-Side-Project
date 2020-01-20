package MobileBaseScreen;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

/**
 * AndroidSwipe is a subclass of Swipe. This class provides the functions for all swipe commands supported by Android.
 * 
 * 
 * @author InvisibleExo
 *
 */

public class AndroidSwipe extends Swipe {
	
	private AppiumDriver<MobileElement> driver;
	private Dimension size;
	private Dimension elementSize;
	private static String nativeContext = "NATIVE_APP";
	private static String defaultContext;
	
	/**
	 * AndroidSwipe is a subclass of Swipe. This class provides the functions for all swipe commands supported by Android. 
	 * Swipes can be perform device swipes or swipes through selected elements.
	 * 
	 * @Note Swipe methods can be performed in non-native context. (Earlier versions of Appium may require swipes to be performed in Native context)
	 * 
	 * @param driver
	 */
	
	
	AndroidSwipe(AppiumDriver<MobileElement> driver){
		this.driver = driver;
		defaultContext = this.driver.getContext();
		this.driver.context(nativeContext);
		size = this.driver.manage().window().getSize();
		this.driver.context(defaultContext);
	}
	
	void swipeVertical(double startPercentage, double finalPercentage, int duration) {
		int width = (int) (size.width/2);
		int startPoint = (int) (size.getHeight() * startPercentage);
		int endPoint = (int) (size.getHeight() * finalPercentage);
		new AndroidTouchAction(driver)
			.press(PointOption.point(width, startPoint))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
			.moveTo(PointOption.point(width, endPoint))
			.release()
			.perform();
	}

	
	void swipeHorizontal(double startPercentage, double finalPercentage, int duration) {
		int height = (int) (size.height/2);
		int startPoint = (int) (size.getWidth() * startPercentage);
		int endPoint = (int) (size.getWidth() * finalPercentage);
		new AndroidTouchAction(driver)
			.press(PointOption.point(startPoint, height))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
			.moveTo(PointOption.point(endPoint, height))
			.release()
			.perform();
	}

	
	void swipeCustomDirection(double startPercentageX, double startPercentageY, double finalPercentageX, 
			double finalPercentageY, int duration){
		int startX = (int) startPercentageX * size.getHeight();
		int startY = (int) startPercentageY * size.getWidth();
		int endX = (int) finalPercentageX * size.getHeight();
		int endY = (int) finalPercentageY * size.getWidth();
			new AndroidTouchAction(driver)
				.press(PointOption.point(startX, startY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
				.moveTo(PointOption.point(endX, endY))
				.release()
				.perform();
	}
	
	void swipeCustomDirectionThroughElement(double startPercentageX, double startPercentageY, double finalPercentageX,
			double finalPercentageY, int duration, MobileElement target) {
		elementSize = target.getSize();
		int startX = (int) startPercentageX * elementSize.getHeight();
		int startY = (int) startPercentageY * elementSize.getWidth();
		int endX = (int) finalPercentageX * elementSize.getHeight();
		int endY = (int) finalPercentageY * elementSize.getWidth();
			
		new AndroidTouchAction(driver)
			.press(PointOption.point(startX, startY))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
			.moveTo(PointOption.point(endX, endY))
			.release()
			.perform();
		
	}

	void swipeThroughElementVertical(double startPercentage, double endPercentage, int duration,
			MobileElement element) {
		elementSize = element.getSize();
		int width = (int) (elementSize.width/2);
		int startPoint = (int) (elementSize.getHeight() * startPercentage);
		int endPoint = (int) (elementSize.getHeight() * endPercentage);
		new AndroidTouchAction(driver)
			.press(PointOption.point(width, startPoint))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
			.moveTo(PointOption.point(width, endPoint))
			.release()
			.perform();
	}

	void swipeThroughElementHorizontal(double startPercentage, double endPercentage, int duration,
			MobileElement element) {
		elementSize = element.getSize();
		int height = (int) (elementSize.height/2);
		int startPoint = (int) (elementSize.getWidth() * startPercentage);
		int endPoint = (int) (elementSize.getWidth() * endPercentage);
		new AndroidTouchAction(driver)
			.press(PointOption.point(startPoint, height))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
			.moveTo(PointOption.point(endPoint, height))
			.release()
			.perform();
	}
	
	MobileElement swipeUntilFound(By method, int attempts, MobileElement targetElement, Direction dir) {
		int limitCount = 0;
		double startScrollPoint = 0;
		double endScrollPoint = 0;
		switch (dir) {
			case up:
				startScrollPoint = 0.35;
				endScrollPoint = 0.8;
				break;
			case down:
				startScrollPoint = 0.8;
				endScrollPoint = 0.35;
				break;
			default:
				System.out.println("This method only supports directions up and down");
				break;
		}
		
		List<MobileElement> searchAfterSwipe = driver.findElements(method);
		while (searchAfterSwipe.isEmpty() && limitCount < attempts ) {
			swipeVertical(startScrollPoint, endScrollPoint, 3000);
			searchAfterSwipe = driver.findElements(method);
			limitCount++;
		}
		if(limitCount == attempts) {
			System.out.println("Unable to find Specified Element.");
		}else {
			targetElement = driver.findElement(method);
			System.out.println("Element " + method.toString() + " was found.");
		}
		return targetElement;
	}

	MobileElement swipeUntilFound(By method, By secondMethod, int attempts, MobileElement targetElement, Direction dir) {
		int limitCount = 0;
		double startScrollPoint = 0;
		double endScrollPoint = 0;
		switch (dir) {
		case up:
			startScrollPoint = 0.35;
			endScrollPoint = 0.8;
			break;
		case down:
			startScrollPoint = 0.8;
			endScrollPoint = 0.35;
			break;
		default:
			System.out.println("This method only supports directions up and down");
			break;
		}	
		List<MobileElement> searchAfterSwipe = driver.findElements(method);
		while (limitCount < attempts && searchAfterSwipe.isEmpty()) {
			swipeVertical(startScrollPoint, endScrollPoint, 3000);
			searchAfterSwipe = driver.findElements(method);
			searchAfterSwipe = driver.findElements(secondMethod);
			limitCount++;
		}
		if(limitCount == attempts) {
			System.out.println("Unable to find Specified Element.");
			
		}else if (!driver.findElements(method).isEmpty()){
			targetElement = driver.findElement(method);
			
		} else if (!driver.findElements(secondMethod).isEmpty()) {
			targetElement = driver.findElement(secondMethod);
			
		}
		return targetElement;
	}

	

}
