package App_Lib;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import MobileBaseScreen.BaseScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class RedditLogInPrompt extends BaseScreen {
	
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/loginsignup_pager")
	public MobileElement logInPage;
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/auth_container")
	public MobileElement logInSection;
	
	public MobileElement logInImage =logInSection.findElement(By.className("android.widget.ImageView"));
	
	public MobileElement logIntitle = logInSection.findElement(By.className("android.widget.TextView"));

	@AndroidFindBy(id = "com.reddit.frontpage:id/username")
	public MobileElement username;
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/password")
	public MobileElement password;
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/text_input_password_toggle")
	public MobileElement showPassword;
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/register_prompt")
	public MobileElement signUp;
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/forgot_password")
	public MobileElement forgotPassword;
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/confirm")
	public MobileElement logIn;
	
	@AndroidFindBy(accessibility = "Navigate up")
	public MobileElement backButton;
	
	public RedditLogInPrompt(AppiumDriver<MobileElement> driver) {
		super(driver);	
		
	}
	
	public void provideAccountCrendtialtoLogIn(String userName, String pW) {
		enterUsername(userName);
		enterPW(pW);
		logIn();
	}
	
	
	public void enterUsername(String userName) {
		username.click();
		username.sendKeys(userName);
	}
	
	public void enterPW(String pW) {
		password.click();
		password.sendKeys(pW);
	}
	
	public void forgotPassword() {
		forgotPassword.click();
	}
	
	public void showOrHidePassword() {
		showPassword.click();
	}
	
	public void goToSignUp() {
		signUp.click();
	}
	
	public void logIn() {
		logIn.click();
		wait.until(ExpectedConditions.elementToBeClickable(MobileBy.id("com.reddit.frontpage:id/nav_icon")));
	}
	
	public void goBackPreviousPage() {
		backButton.click();
	}
	
	//When app is tested in developer version
	public void findLogInImage() throws IOException {
		logInImage = driver.findElementByImage(getSpeicifiedImageB64("snoo_logo_large.png"));
	}

}
