package Browser_Lib;

import org.openqa.selenium.support.FindBy;

import AppiumDriverSetUp_Lib.BaseScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class GoogleMainPage extends BaseScreen {

	@FindBy(id = "tsf")
	private MobileElement searchSection;
	
	@FindBy(name = "q")
	MobileElement searchText;
	
	@FindBy(className = "Tg7LZd")
	MobileElement confirmSearch;
	
	public GoogleMainPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	
	}
	
	public void searchQuery(String query) {
		 
		searchText.click();
		searchText.sendKeys(query);
		confirmSearch.click();
		wait.waitUntilPageLoaded();
	}

}
