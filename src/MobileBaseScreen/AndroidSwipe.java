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



public class AndroidSwipe extends Swipe {
	
	private AppiumDriver<MobileElement> driver;
	private Dimension size;
	private Dimension elementSize;
	private static String nativeContext = "NATIVE_APP";
	private static String defaultContext;
	
	
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

	
	void swipeDiagonalDirection(double startPercentageX, double startPercentageY, double finalPercentageX, 
			double finalPercentageY, int duration, Direction dir){
		int startX;
		int startY;
		int endX;
		int endY;
		
		switch (dir) {
			case nw:
				startX = (int) startPercentageX * size.getHeight();
				startY = (int) startPercentageY * size.getWidth();
				endX = (int) finalPercentageX * size.getHeight();
				endY = (int) finalPercentageY * size.getWidth();
				
				new AndroidTouchAction(driver)
					.press(PointOption.point(endX, endY))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
					.moveTo(PointOption.point(startX, startY))
					.release()
					.perform();
				break;
				
			case sw:
				startX = (int) startPercentageX * size.getHeight();
				startY = (int) startPercentageY * size.getWidth();
				endX = (int) finalPercentageX * size.getHeight();
				endY = (int) finalPercentageY * size.getWidth();
				
				new AndroidTouchAction(driver)
					.press(PointOption.point(endX, startY))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
					.moveTo(PointOption.point(startX, endY))
					.release()
					.perform();
				break;
				
			case ne:
				startX = (int) startPercentageX * size.getHeight();
				startY = (int) startPercentageY * size.getWidth();
				endX = (int) finalPercentageX * size.getHeight();
				endY = (int) finalPercentageY * size.getWidth();
				
				new AndroidTouchAction(driver)
					.press(PointOption.point(startX, startY))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
					.moveTo(PointOption.point(endX, endY))
					.release()
					.perform();
				break;
				
			case se:
				startX = (int) startPercentageX * size.getHeight();
				startY = (int) startPercentageY * size.getWidth();
				endX = (int) finalPercentageX * size.getHeight();
				endY = (int) finalPercentageY * size.getWidth();
				
				new AndroidTouchAction(driver)
					.press(PointOption.point(startX, endY))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
					.moveTo(PointOption.point(endX, startY))
					.release()
					.perform();
				break;
		default:
			break;
		}
		
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

	void swipeUntilFound(By method, By secondMethod, int attempts, MobileElement targetElement) {
		int limitCount = 0;
		while (limitCount < attempts && (driver.findElements(method).isEmpty() || driver.findElements(secondMethod).isEmpty())) {
			swipeVertical(0.8, 0.25, 3000);
			limitCount++;
		}
		if(limitCount == attempts) {
			System.out.println("Unable to find Specified Element.");
		}else if (!driver.findElements(method).isEmpty()){
			targetElement = driver.findElement(method);
		} else if (!driver.findElements(secondMethod).isEmpty()) {
			targetElement = driver.findElement(secondMethod);
		}
	}

}
