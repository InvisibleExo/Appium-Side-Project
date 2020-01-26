package App_Lib;

import java.util.List;

import org.openqa.selenium.By;

import MobileBaseScreen.BaseScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class RedditSearchInput extends BaseScreen{
	
	@AndroidFindBy(id="com.reddit.frontpage:id/search_results")
	public MobileElement searchResultSection;
	
	@AndroidFindBy(id="com.reddit.frontpage:id/toolbar")
	public MobileElement searchBarSection;
	
	@AndroidFindBy(id="com.reddit.frontpage:id/search_icon")
	public MobileElement searchIcon;

	@AndroidFindBy(id="com.reddit.frontpage:id/tokens_search_container")
	public MobileElement searchContent;
	
	@AndroidFindBy(id="com.reddit.frontpage:id/search_clear_icon")
	public MobileElement clearSearchInput;
	
	
	public RedditSearchInput(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	public void selectByTopSuggestedResult(String input) {
		searchContent.click();
		searchContent.sendKeys(input);
		List<MobileElement> topSuggested = searchResultSection.findElements(By.id("com.reddit.frontpage:id/community_name"));
		topSuggested.get(0).click();
	}
	
	public void lookForMoreResults(String input) {
		searchContent.click();
		searchContent.sendKeys(input);
		searchResultSection.findElement(By.id("com.reddit.frontpage:id/community_name")).click();
	}
	
	public void leaveSearchResult() {
		searchBarSection.findElement(By.className("	android.widget.ImageButton")).click();
	}
	

}
