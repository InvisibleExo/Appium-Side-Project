package AppiumDriverSetUp_Lib;

import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;

abstract class Swipe {
	
	abstract void swipeVertical(double startPercentage, double endPercentage, int duration);
	
	abstract void swipeThroughElementVertical(double startPercentage, double endPercentage, int duration, MobileElement element);
	
	abstract void swipeHorizontal(double startPercentage, double endPercentage, int duration);
	
	abstract void swipeThroughElementHorizontal(double startPercentage, double endPercentage, int duration, MobileElement element);
	
	enum Direction{
		nw, sw, ne, se, up, down;
	}
	abstract void swipeDiagonalDirection(double startPercentageX, double startPercentageY, double finalPercentageX, 
			double finalPercentageY, int duration, Direction dir);
	
	abstract MobileElement swipeUntilFound(By method, int attempts, MobileElement targetElement, Direction dir);
	
	abstract void swipeUntilFound(By method, By secondMethod, int attempts, MobileElement targetElement);
}
