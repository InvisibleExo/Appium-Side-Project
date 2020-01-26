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
	
	private MobileElement notificationSection;
	
	private MobileElement notificationEventListHeader;
	
	AndroidNotificationBar(AppiumDriver<MobileElement> driver){
		this.driver = (AndroidDriver<MobileElement>)driver;
		defaultContext = driver.getContext();
		wait = new AppiumDriverWait(this.driver, 40);
	}
	
	void openNotifications() {
		driver.openNotifications();
		driver.context(nativeContext);
		
		notificationSection = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.android.systemui:id/notification_container_parent")));
		notificationEventListHeader = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.android.systemui:id/notification_stack_scroller")));
	}
	
	protected MobileElement getNotificationScroller() {
		return notificationEventListHeader;
	}
	
	protected MobileElement getNotificationSection() {
		return notificationSection;
	}
	

	void clearNotifications() {
		try {
			driver.findElement(By.id("com.android.systemui:id/clear_all"));
		}
		catch(NoSuchElementException e) {
			System.out.println("There are no notifications to clear.");
		}
	}

	void seeEventList() {
		List<MobileElement> notificationEventList = notificationEventListHeader.findElements(By.id("com.android.systemui:id/expanded"));
		By listofTextNames = By.xpath("//android.widget.TextView[@resource-id='android:id/app_name_text' or @resource-id='android:id/title' or @resource-id='android:id/big_text' or @resource-id='android:id/header_text' or @resource-id='android:id/time' or @resource-id='com.android.systemui:id/notification_title' or @resource-id='com.android.systemui:id/notification_text']");
		int count = 1;
		for(MobileElement event:notificationEventList) {
			List<MobileElement> eventTextContent = event.findElements(listofTextNames);
			System.out.println("Event "+ count +":");
			for(MobileElement eventText : eventTextContent) {
				System.out.println(eventText.getText());
			}
			count++;
		}
		
	}

	void openNotificationEvent(String eventName) {
		List<MobileElement> notificationEventList = notificationEventListHeader.findElements(By.id("com.android.systemui:id/expanded"));
		By listofTextNames = By.xpath("//android.widget.TextView[@resource-id='android:id/app_name_text' or @resource-id='android:id/title' or @resource-id='android:id/big_text' or @resource-id='android:id/header_text' or @resource-id='android:id/time' or @resource-id='com.android.systemui:id/notification_title' or @resource-id='com.android.systemui:id/notification_text']");
		boolean found = false;
		OUTER:
		for(MobileElement event:notificationEventList) {
			List<MobileElement> eventTextContent = event.findElements(listofTextNames);
			for(MobileElement eventText : eventTextContent) {
				if(eventText.getText().contains(eventName)) {
					found = true;
					eventText.click();
					break OUTER;
				}
			}
		}
		if(!found) {
			System.out.println("The requested input: "+ eventName + " could not be found in the list of Notification");
		}
	}

	void revertToDefaultContext() {
		if(!driver.getContext().equals(defaultContext)) {
			driver.context(defaultContext);
		}
	}



}
