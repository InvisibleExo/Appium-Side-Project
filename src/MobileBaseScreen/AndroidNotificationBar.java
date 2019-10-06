package MobileBaseScreen;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AndroidNotificationBar extends NotificationBar{
	
	private AndroidDriver<MobileElement> driver;
	private static String nativeContext = "NATIVE_APP";
	private static String defaultContext;
	
	private AppiumDriverWait wait;
	
	protected MobileElement notificationSection;
	
	private MobileElement notificationCommandsHeader;
	private List<MobileElement> notificationCommandList;
	
	private MobileElement notificationEventListHeader;
	private List<MobileElement> notificationEventList;
	
	AndroidNotificationBar(AppiumDriver<MobileElement> driver){
		this.driver = (AndroidDriver<MobileElement>)driver;
		defaultContext = driver.getContext();
		wait = new AppiumDriverWait(this.driver, 40);
	}
	
	void openNotifications() {
		driver.openNotifications();
		driver.context(nativeContext);
		notificationSection = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.android.systemui:id/quick_settings_container")));
		notificationEventListHeader = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.android.systemui:id/notification_stack_scroller")));
	}
	void listNotificationSystemCommands() {
		notificationCommandList = notificationCommandsHeader.findElements(By.className("android.widget.FrameLayout"));
		
	}
	
	public MobileElement getNotificationSection() {
		return notificationSection;
	}
	
	public MobileElement getNotificationScroller() {
		return notificationEventListHeader;
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
