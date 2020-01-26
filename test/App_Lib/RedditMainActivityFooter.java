package App_Lib;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import MobileBaseScreen.BaseScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class RedditMainActivityFooter extends BaseScreen {
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/bottom_nav")
	public MobileElement footerSection;
	
	public List<MobileElement> footerOptions;
	

	public RedditMainActivityFooter(AppiumDriver<MobileElement> driver) {
		super(driver);
		makeFooterList();
	}
	
	public void makeFooterList() {
		switch (driver.getCapabilities().getCapability("platformName")+"") {
			case"Android":
				wait.until(ExpectedConditions.elementToBeClickable(MobileBy.className("android.widget.ImageView")));
				footerOptions = footerSection.findElements(By.className("android.widget.ImageView"));
			break;
			// ios case placement
		}
	}
	
	public void selectAllFooterTabs() {
		int index = 0;
		for(MobileElement tab: footerOptions) {
			System.out.println("Index " + index);
			tab.click();
			System.out.println(tab.getLocation());
			index++;
		}
	}
	
	public void goToHomePopularTab() {
		footerOptions.get(0).click();
	}
	
	public void goToCommunitiesTab() {
		footerOptions.get(1);
	}
	
	public void openPostOptions() {
		footerOptions.get(2).click();
	}
	
	public void goToCommentSection() {
		footerOptions.get(3).click();
	}
	
	public void goToEmails() {
		footerOptions.get(4).click();;
	}

}
