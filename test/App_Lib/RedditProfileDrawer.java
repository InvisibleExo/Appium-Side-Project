package App_Lib;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import MobileBaseScreen.BaseScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class RedditProfileDrawer extends BaseScreen {
	
	@AndroidFindBy(id ="com.reddit.frontpage:id/drawer_nav")
	public MobileElement profileDrawerSection;
	
	@AndroidFindBy(id ="com.reddit.frontpage:id/drawer_nav_header")
	public MobileElement profileDrawerHeader;
	
	@AndroidFindBy(id ="com.reddit.frontpage:id/drawer_nav_items_container")
	public MobileElement profileLink;
	
	@AndroidFindBy(id ="com.reddit.frontpage:id/drawer_nav_settings")
	public MobileElement settingsOption;
	
	@AndroidFindBy(id ="com.reddit.frontpage:id/drawer_nav_night_theme")
	public MobileElement nightDayTheme;
	

	public RedditProfileDrawer(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	public String logInSignUp() {
		String responseMessage = "";
		if(profileLink.findElement(By.id("com.reddit.frontpage:id/drawer_nav_item_title"))
				.getAttribute("text").equals("Sign up / Log in")){
			responseMessage = "Going to Sign up/Log in prompt";
			profileLink.click();
			wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.reddit.frontpage:id/username")));
		} 
		else {
			responseMessage = "Profile already Signed in.";
		}
		return responseMessage;
	}
	
	public void goToSettings() {
		settingsOption.click();
	}
	
	public void switchNightDayTheme() {
		nightDayTheme.click();
	}
	
	public void closeProfileDrawer() {
		super.swipeThroughElementHorizontal(0.9, 0.05, 200, profileDrawerSection);
	}
	

}
