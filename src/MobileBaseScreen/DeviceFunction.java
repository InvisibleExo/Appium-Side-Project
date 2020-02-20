package MobileBaseScreen;


/**
 * DeviceFunction is an abstract class with methods to support device main functions such as go home button, go back, view active apps, and resume active app.
 * (Note: Android is currently the only supported sub class.) 
 * 
 * 
 * @author InvisibleExo
 */

abstract class DeviceFunction {

	abstract void goHome();
	
	abstract void goBack();
	
	abstract void viewActiveAppList();
	
	abstract void resumeApp(String appName);
	
	abstract boolean turnWiFiOn();
	
	abstract boolean turnWiFiOff();
	
	abstract boolean turnMobileDataOn();
	
	abstract boolean turnMobileDataOff();
	
	abstract boolean turnAirPlaneModeOn();
	
	abstract boolean turnAirPlaneModeOff();
	
}


