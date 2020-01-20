package MobileBaseScreen;

import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;

/**
 * Swipe is an abstract class for MobileBaseScreen package and must be initialized 
 * as AndroidSwipe or IosSwipe. (Note: IosSwipe class isn't currently support for MobileBaseScreen package) 
 * 
 * @author InvisibleExo
 *
 */

abstract class Swipe {
	
	
	abstract void swipeVertical(double startPercentage, double endPercentage, int duration);
	
	abstract void swipeThroughElementVertical(double startPercentage, double endPercentage, int duration, MobileElement element);
	
	abstract void swipeHorizontal(double startPercentage, double endPercentage, int duration);
	
	abstract void swipeThroughElementHorizontal(double startPercentage, double endPercentage, int duration, MobileElement element);
	
	enum Direction{
		up, down;
	}
	abstract void swipeCustomDirection(double startPercentageX, double startPercentageY, double finalPercentageX, 
			double finalPercentageY, int duration);
	
	abstract void swipeCustomDirectionThroughElement(double startPercentageX, double startPercentageY, double finalPercentageX, 
			double finalPercentageY, int duration, MobileElement target);
	
	abstract MobileElement swipeUntilFound(By method, int attempts, MobileElement targetElement, Direction dir);
	
	abstract MobileElement swipeUntilFound(By method, By secondMethod, int attempts, MobileElement targetElement, Direction dir);
}
