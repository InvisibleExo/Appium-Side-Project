package App_Lib;


import org.openqa.selenium.support.ui.ExpectedConditions;

import MobileBaseScreen.BaseScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class RedditSignInScreen extends BaseScreen{
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/background")
	public MobileElement background;
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/launch_logo")
	public MobileElement launchLogo;
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/welcome_message")
	public MobileElement signPromoterText;
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/login_button")
	public MobileElement loginEmailButton;
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/signup_button")
	public MobileElement signupButton;
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/skip_text")
	public MobileElement skipSignup;
	
	
	
	public RedditSignInScreen(AppiumDriver<MobileElement> driver) {
		super(driver);
		waitToLoad();
	}
	
	public void waitToLoad() {
		
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.reddit.frontpage:id/skip_text")));
		
	}
	
	public void skipSignUp() {
		skipSignup.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id("com.reddit.frontpage:id/home_screen")));
	}
	
	public void signUpOption() {
		signupButton.click();
	}
	
	public void logInOption() {
		loginEmailButton.click();
	}

}
