package AppiumDriverSetUp_Lib;


abstract class Swipe {
	
	abstract void swipeVertical(double startPercentage, double endPercentage, int duration);
	
	abstract void swipeHorizontal(double startPercentage, double endPercentage, int duration);
	
	enum diagonalDirection{
		nw, sw, ne, se;
	}
	abstract void swipeDiagonalDirection(double startPercentageX, double startPercentageY, double finalPercentageX, 
			double finalPercentageY, int duration, diagonalDirection dir);

}
