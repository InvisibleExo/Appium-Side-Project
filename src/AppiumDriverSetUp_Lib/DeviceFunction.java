package AppiumDriverSetUp_Lib;

abstract class DeviceFunction {

	abstract void goHome();
	
	abstract void goBack();
	
	abstract void viewActiveAppList();
	
	abstract void resumeApp(String appName);
}
