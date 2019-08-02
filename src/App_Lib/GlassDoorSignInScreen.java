package App_Lib;

import org.openqa.selenium.support.FindBy;

import AppiumDriverSetUp_Lib.BaseScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class GlassDoorSignInScreen extends BaseScreen{
	
	@FindBy(id = "com.glassdoor.app:id/emailButton")
	public MobileElement defaultEmailButton;
	
	public GlassDoorSignInScreen(AppiumDriver<MobileElement> driver) {
		super(driver);
	}

}
