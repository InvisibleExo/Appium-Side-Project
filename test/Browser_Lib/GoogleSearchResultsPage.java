package Browser_Lib;

import org.openqa.selenium.support.FindBy;

import MobileBaseScreen.BaseScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class GoogleSearchResultsPage extends BaseScreen {
	
	
	@FindBy(name = "q")
	public MobileElement searchInput;

	public GoogleSearchResultsPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	
	
}
