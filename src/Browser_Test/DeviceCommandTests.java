package Browser_Test;

import org.testng.annotations.Test;

import BaseTest.BaseTest;
import BaseTest.TLDriverFactory;
import Browser_Lib.GoogleMainPage;
import MobileBaseScreen.BaseScreen;

public class DeviceCommandTests extends BaseTest {
	
		@Test
		public void deviceCommands() {
		
		pageObject = new BaseScreen(TLDriverFactory.getTLDriver());
		pageObject.get("https://www.google.com");
		
		GoogleMainPage mainPage = new GoogleMainPage(TLDriverFactory.getTLDriver());
		
		mainPage.goBack();
		
		mainPage.goHome();
		//open
		mainPage.viewActiveAppList();
		//close
		mainPage.viewActiveAppList();
			
		mainPage.resumeApp("Chrome");
		
	}
		
		@Test
		public void notificationCommands() {
			pageObject = new BaseScreen(TLDriverFactory.getTLDriver());
			pageObject.get("http://www.google.com");
			
			GoogleMainPage mainPage = new GoogleMainPage(TLDriverFactory.getTLDriver());
			
			mainPage.openNotifications();
			
			mainPage.closeNotifications();
			
		}

}
