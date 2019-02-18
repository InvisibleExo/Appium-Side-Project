package AppiumDriverSetUp_Lib;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class IosSwipe extends Swipe {
	private AppiumDriver<MobileElement> driver;
	private static String defaultContext;
	
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
	void swipeDiagonalDirection(double startPercentageX, double startPercentageY, double finalPercentageX,
			double finalPercentageY, int duration, diagonalDirection dir) {
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

	
	void swipeUntilFound(By method, int attempts, MobileElement targetElement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void swipeUntilFound(By method, By secondMethod, int attempts, MobileElement targetElement) {
		// TODO Auto-generated method stub
		
	}
}
