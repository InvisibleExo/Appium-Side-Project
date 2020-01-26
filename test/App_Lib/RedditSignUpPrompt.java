package App_Lib;

import org.openqa.selenium.By;

import MobileBaseScreen.BaseScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class RedditSignUpPrompt extends BaseScreen {
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/loginsignup_pager")
	public MobileElement signUpPage;
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/auth_container")
	public MobileElement signUpSection;
	
	public MobileElement signUpImage = signUpSection.findElement(By.className("android.widget.ImageView"));
	
	public MobileElement signUpTitle = signUpSection.findElement(By.className("android.widget.TextView"));
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/email")
	public MobileElement email;
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/username")
	public MobileElement username;
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/password")
	public MobileElement password;
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/text_input_password_toggle")
	public MobileElement showPassword;
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/login_prompt")
	public MobileElement logInOption;
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/terms")
	public MobileElement terms;
	
	@AndroidFindBy(id = "com.reddit.frontpage:id/confirm")
	public MobileElement createAccount;
	
	@AndroidFindBy(accessibility = "Navigate up")
	public MobileElement backButton;

	public RedditSignUpPrompt(AppiumDriver<MobileElement> driver) {
		super(driver);
		
	}
	
	public void createAccountCredntial(String emailInput, String userName, String pW) {
		enterEmail(emailInput);
		enterUsername(userName);
		enterPW(pW);
		createAccount();
	}
	
	public void enterEmail(String emailInput) {
		email.click();
		email.sendKeys("emailInput");
	}
	
	public void enterUsername(String userName) {
		username.click();
		username.sendKeys(userName);
	}
	
	public void enterPW(String pW) {
		password.click();
		password.sendKeys(pW);
	}
	
	public void showOrHidePassword() {
		showPassword.click();
	}
	
	public void createAccount() {
		createAccount.click();
	}
	
	public void logInInstead() {
		logInOption.click();
	}
	
	public void seeTerms() {
		terms.click();
	}
	
	public void goBackPreviousPage() {
		backButton.click();
	}

}
