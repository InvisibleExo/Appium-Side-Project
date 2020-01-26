package App_Lib;

import MobileBaseScreen.BaseScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;


public class RedditMainActivityHeader extends BaseScreen {
	
	@AndroidFindBy(id="com.reddit.frontpage:id/toolbar")
	public MobileElement headerSection;
	
	@AndroidFindBy(id ="com.reddit.frontpage:id/nav_icon")
	public MobileElement userIcon;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.reddit.frontpage:id/search_view' or @resource-id='com.reddit.frontpage:id/toolbar_title']")
	public MobileElement navTitle;
	
	public RedditMainActivityHeader(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	public void openProfileDrawer() {
		userIcon.click();
	}
	
	public String useSearchView(String input) {
		String responseMessage = "";
		if(navTitle.getAttribute("resource-id").equals("com.reddit.frontpage:id/search_view")) {
			responseMessage = "Initiating search for: " + input;
			navTitle.click();
			navTitle.sendKeys(input);
		}
		else {
			responseMessage = "Search isn't enabled.";
		}
		
		return responseMessage;
	}
	

}
