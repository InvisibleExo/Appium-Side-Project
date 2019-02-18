package AppiumDriverSetUp_Lib;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AndroidNotificationBar extends NotificationBar{
	
	private AndroidDriver<MobileElement> driver;
	private static String nativeContext = "NATIVE_APP";
	private static String defaultContext;
	
	protected MobileElement notificationSection;
	
	private MobileElement closeNotifications;
	
	private MobileElement notificationCommandsHeader;
	private List<MobileElement> notificationCommandList;
	
	private MobileElement notificationEventListHeader;
	private List<MobileElement> notificationEventList;
	
	AndroidNotificationBar(AppiumDriver<MobileElement> driver){
		this.driver = (AndroidDriver<MobileElement>)driver;
		defaultContext = driver.getContext();
		
	}
	
	void openNotifications() {
		driver.openNotifications();
		driver.context(nativeContext);
		notificationSection = driver.findElement(By.id("com.android.systemui:id/quick_settings_container"));
		notificationCommandsHeader = driver.findElement(By.id("com.android.systemui:id/tile_page"));
		listNotificationSystemCommands();
		notificationEventListHeader = driver.findElement(By.id("com.android.systemui:id/notification_stack_scroller"));
		closeNotifications = driver.findElementByAccessibilityId("Close quick settings.");
	}

	void listNotificationSystemCommands() {
		notificationCommandList = notificationCommandsHeader.findElements(By.className("android.widget.FrameLayout"));
		
	}
	
	public MobileElement getNotificationSection() {
		return notificationSection;
	}
	
	void selectSystemCommand(String systemName) {
		// TODO Auto-generated method stub
		
	}

	void clearNotifications() {
		try {
			driver.findElement(By.id("com.android.systemui:id/clear_all"));
		}
		catch(NoSuchElementException e) {
			System.out.println("There are no notifications to clear.");
		}
	}

	void listNotificationEvents() {
		notificationEventList = notificationEventListHeader.findElementsByAccessibilityId("com.android.systemui:id/expanded");
		
	}

	void openNotificationEvent(String eventName) {
		// TODO Auto-generated method stub
		
	}



}
