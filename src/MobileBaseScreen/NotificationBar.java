package MobileBaseScreen;

import io.appium.java_client.MobileElement;

abstract class NotificationBar {
	
	abstract void openNotifications();
	
	abstract void clearNotifications();
	
	abstract void seeEventList();
	
	abstract void openNotificationEvent(String eventName);

	abstract MobileElement getNotificationScroller();
	
	abstract MobileElement getNotificationSection();
	
	abstract void revertToDefaultContext();
}
