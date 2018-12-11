package AppiumDriverSetUp_Lib;

import java.time.Duration;

import org.openqa.selenium.Dimension;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class AndroidSwipe extends Swipe {
	
	private AppiumDriver<MobileElement> driver;
	private Dimension size;
	private static String defaultContext;
	
	AndroidSwipe(AppiumDriver<MobileElement> driver){
		this.driver = driver;
		defaultContext = this.driver.getContext();
	}
	
	//Make swipe methods aligned to side-scrolls and drop-down menus base on MobileElement location/WebElement

	void swipeVertical(double startPercentage, double finalPercentage, int duration) {
		driver.context("NATIVE_APP");
		size = driver.manage().window().getSize();
		int width = (int) (size.width/2);
		int startPoint = (int) (size.getHeight() * startPercentage);
		int endPoint = (int) (size.getHeight() * finalPercentage);
		new AndroidTouchAction(driver)
			.press(PointOption.point(width, startPoint))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
			.moveTo(PointOption.point(width, endPoint))
			.release()
			.perform();
		driver.context(defaultContext);
		
	}

	
	void swipeHorizontal(double startPercentage, double finalPercentage, int duration) {
		driver.context("NATIVE_APP");
		size = driver.manage().window().getSize();
		int height = (int) (size.height/2);
		int startPoint = (int) (size.getWidth() * startPercentage);
		int endPoint = (int) (size.getWidth() * finalPercentage);
		new AndroidTouchAction(driver)
			.press(PointOption.point(startPoint, height))
			.waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
			.moveTo(PointOption.point(endPoint, height))
			.release()
			.perform();
		driver.context(defaultContext);
	}

	
	void swipeDiagonalDirection(double startPercentageX, double startPercentageY, double finalPercentageX, 
			double finalPercentageY, int duration, diagonalDirection dir) {
		size = driver.manage().window().getSize();
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
		}
		
	}


}
