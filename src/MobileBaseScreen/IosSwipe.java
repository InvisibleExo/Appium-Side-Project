package MobileBaseScreen;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class IosSwipe extends Swipe {
	private AppiumDriver<MobileElement> driver;
	private static String defaultContext;
	
	/**
	 * Ios Swipe currently not supported for MobileBaseScreen package
	 * 
	 * @param driver
	 */
	
	IosSwipe(AppiumDriver<MobileElement> driver){
		this.driver = driver;
		defaultContext = this.driver.getContext();
	}

	@Override
	void swipeVertical(double startPercentage, double endPercentage, int duration) {
		// TODO Auto-generated method stub
	}


	@Override
	void swipeHorizontal(double startPercentage, double endPercentage, int duration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void swipeThroughElementVertical(double startPercentage, double endPercentage, int duration,
			MobileElement element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void swipeThroughElementHorizontal(double startPercentage, double endPercentage, int duration,
			MobileElement element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	MobileElement swipeUntilFound(By method, By secondMethod, int attempts, MobileElement targetElement, Direction dir) {
		// TODO Auto-generated method stub
		return targetElement;
	}

	@Override
	void swipeCustomDirection(double startPercentageX, double startPercentageY, double finalPercentageX,
			double finalPercentageY, int duration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	MobileElement swipeUntilFound(By method, int attempts, MobileElement targetElement, Direction dir) {
		return targetElement;
		// TODO Auto-generated method stub
		
	}

	@Override
	void swipeCustomDirectionThroughElement(double startPercentageX, double startPercentageY, double finalPercentageX,
			double finalPercentageY, int duration, MobileElement target) {
		// TODO Auto-generated method stub
		
	}
}
