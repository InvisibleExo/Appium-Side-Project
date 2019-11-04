package App_Lib;

import java.util.List;

import org.openqa.selenium.By;

import MobileBaseScreen.BaseScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class RedditSettings extends BaseScreen {
	
	@AndroidFindBy(id="android:id/content")
	public MobileElement settingsSection;
	
	@AndroidFindBy(accessibility="Navigate up")
	public MobileElement leaveSettingsOption;
	
	@AndroidFindBy(id="com.reddit.frontpage:id/container")
	public MobileElement settingSubCategories;
	
	
	
	public RedditSettings(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	public void signInWithAccount() {
		List<MobileElement> subCategories = settingSubCategories.findElements(By.className("android.view.ViewGroup"));
		for(MobileElement target:subCategories) {
			if(target.findElement(By.id("android:id/title")).getAttribute("text").equals("Add account")) {
				target.findElement(By.id("android:id/title")).click();
				break;
			}
		}
	}
	
	public void signOutAccount() {
		List<MobileElement> subCategories = settingSubCategories.findElements(By.className("android.view.ViewGroup"));
		for(MobileElement target:subCategories) {
			if(target.findElement(By.id("android:id/title")).getAttribute("text").equals("Accounts")) {
				target.findElement(By.id("android:id/title")).click();
				driver.findElement(By.id("com.reddit.frontpage:id/account_remove")).click();
				driver.findElement(By.id("com.reddit.frontpage:id/confirm_remove_account_logout")).click();
				break;
			}
		}
	}
	

}
