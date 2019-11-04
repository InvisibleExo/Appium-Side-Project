package App_Test;

import BaseTest.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import App_Lib.*;

public class RedditIntroductionTest extends BaseTest {
	
	RedditResetStartPoint resetStartPoint = new RedditResetStartPoint();
	
	@BeforeMethod(alwaysRun=true)
	public void setStartPoint() {
		resetStartPoint.resetStartPointSignInOptions(TLDriver.getTLDriver());
		assert(resetStartPoint.currentActivityStatus(TLDriver.getTLDriver()).equalsIgnoreCase(".IntroductionActivity"));
	}

	@Test
	public void checkOnEmailPage_UnitTest() {
		RedditSignInScreen emailSignIn = new RedditSignInScreen(TLDriver.getTLDriver());
	
		assert(emailSignIn.loginEmailButton.getAttribute("resource-id").equals("com.reddit.frontpage:id/login_button"));
		assert(emailSignIn.signupButton.getAttribute("resource-id").equals("com.reddit.frontpage:id/signup_button"));
		assert(emailSignIn.skipSignup.getAttribute("resource-id").equals("com.reddit.frontpage:id/skip_text"));
		assert(emailSignIn.signPromoterText.getAttribute("resource-id").equals("com.reddit.frontpage:id/welcome_message"));
	
		
		assert(emailSignIn.loginEmailButton.getAttribute("text").equals("LOG IN"));
		assert(emailSignIn.signupButton.getAttribute("text").equals("SIGN UP"));
		assert(emailSignIn.skipSignup.getAttribute("text").equals("SKIP FOR NOW"));
		assert(emailSignIn.signPromoterText.getAttribute("text").equals("Share, upvote, and discuss the best of the internet"));
		
		
		
		assertTrue(emailSignIn.loginEmailButton.isDisplayed());
		assertTrue(emailSignIn.signupButton.isDisplayed());
		assertTrue(emailSignIn.skipSignup.isDisplayed());
		assertTrue(emailSignIn.signPromoterText.isDisplayed());
		assertTrue(emailSignIn.background.isDisplayed());
		assertTrue(emailSignIn.launchLogo.isDisplayed());
	}
	
	@Test
	public void selectSkipTest() {
		RedditSignInScreen emailSignIn = new RedditSignInScreen(TLDriver.getTLDriver());
		
		emailSignIn.skipSignUp();
		
		assert(driver.findElement(By.id("com.reddit.frontpage:id/home_screen")).isEnabled());
	}
	
	
}
