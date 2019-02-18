package AppiumDriverSetUp_Lib;

import io.appium.java_client.MobileElement;

abstract class NotificationBar {
	
	abstract void openNotifications();
	
	abstract void listNotificationSystemCommands();
	
	abstract void selectSystemCommand(String systemName);
	
	abstract void clearNotifications();
	
	abstract void listNotificationEvents();
	
	abstract void openNotificationEvent(String eventName);

	abstract MobileElement getNotificationSection();
}
