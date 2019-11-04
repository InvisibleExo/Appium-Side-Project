package App_Test;

import static org.testng.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import App_Lib.RedditLogInPrompt;
import App_Lib.RedditMainActivityHeader;
import App_Lib.RedditProfileDrawer;
import App_Lib.RedditSettings;
import App_Lib.RedditSignUpPrompt;
import BaseTest.*;

public class RedditLogInAndSignUpTest extends BaseTest{
	RedditResetStartPoint resetStartPoint = new RedditResetStartPoint();
	
	@BeforeClass
	public void checkIfLoggedIn() {
		resetStartPoint.resetStartPointMainActivityPage(TLDriver.getTLDriver());
		
		RedditMainActivityHeader header = new RedditMainActivityHeader(TLDriver.getTLDriver());
		
		header.userIcon.click();
		
		assertEquals(TLDriver.getTLDriver().findElement(By.id("com.reddit.frontpage:id/drawer_nav_item_title")).getAttribute("text"),"Sign up / Log in");
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public void setStartPoint(){
		resetStartPoint.resetStartPointLoginPrompt(TLDriver.getTLDriver());
	}
	
	@Test
	public void logInPromptUnitTest() {
		
		RedditLogInPrompt logInPage = new RedditLogInPrompt(TLDriver.getTLDriver());
		
		assert(logInPage.username.getAttribute("text").equals("Username"));
		assert(logInPage.password.getAttribute("text").equals("Password"));
		assert(logInPage.signUp.getAttribute("text").equals("SIGN UP"));
		assert(logInPage.forgotPassword.getAttribute("text").equals("FORGOT PASSWORD"));
		assert(logInPage.logIn.getAttribute("text").equals("LOG IN"));
		assert(logInPage.logIntitle.getAttribute("text").equals("Log in to Reddit"));
		assert(logInPage.logInImage.isDisplayed());
		
		logInPage.swipeHorizontal(0.9, 0.1, 200);
		
		RedditSignUpPrompt signUpPage = new RedditSignUpPrompt(TLDriver.getTLDriver());
		
		assert(signUpPage.username.getAttribute("text").equals("Username"));
		assert(signUpPage.password.getAttribute("text").equals("Password"));
		assert(signUpPage.email.getAttribute("text").equals("Email"));
		assert(signUpPage.logInOption.getAttribute("text").equals("LOG IN INSTEAD"));
		assert(signUpPage.createAccount.getAttribute("text").equals("CREATE ACCOUNT"));
		assert(signUpPage.signUpTitle.getAttribute("text").equals("Create an account"));
		assert(signUpPage.signUpImage.isDisplayed());
		
		signUpPage.swipeHorizontal(0.05, 0.9, 200);
		
		logInPage.goBackPreviousPage();	
	}
	
	@Test
	public void logInandLogOut() {
		
		resetStartPoint.resetStartPointMainActivityPage(TLDriver.getTLDriver());
		
		RedditMainActivityHeader header = new RedditMainActivityHeader(TLDriver.getTLDriver());
		
		header.openProfileDrawer();
		
		RedditProfileDrawer profileDrawer = new RedditProfileDrawer(TLDriver.getTLDriver());
		
		profileDrawer.logInSignUp();
		
		RedditLogInPrompt logIn = new RedditLogInPrompt(TLDriver.getTLDriver());
		
		JSONObject accInfo;
		
		try {
			accInfo =  (JSONObject) new JSONParser().parse(new FileReader("./src/App_Test_Account.json"));
			 
			logIn.enterUsername((String)accInfo.get("userName"));
			
			logIn.goBack();
			
			logIn.showPassword.click();
			
			JSONArray pWArr = (JSONArray) accInfo.get("pW");
			String enter = "";
			for(int i = 0; i < pWArr.size(); i++) {
				enter += pWArr.get(i);
			}
			logIn.password.sendKeys(enter);
			logIn.checkIfElementisPresent(By.id("com.reddit.frontpage:id/confirm"));
			logIn.logIn();
			
			header.userIcon.click();
			
			assert(profileDrawer.profileDrawerSection.findElement(By.id("com.reddit.frontpage:id/nav_user_name")).getAttribute("text").contains((String)accInfo.get("userName")));
			
			profileDrawer.goToSettings();
			
			RedditSettings settings = new RedditSettings(TLDriver.getTLDriver());
			
			settings.signOutAccount();
			
			header.checkIfElementisPresent(By.id("com.reddit.frontpage:id/nav_icon"));
			
			header.openProfileDrawer();
			
			assertEquals(profileDrawer.profileLink.findElement(By.id("com.reddit.frontpage:id/drawer_nav_item_title")).getAttribute("text"), "Sign up / Log in");
		} catch (ParseException | IOException e) {
		
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void switchSignInPromptsThroughButtons() {
		RedditLogInPrompt logIn = new RedditLogInPrompt(TLDriver.getTLDriver());
		
		assert(logIn.signUp.getAttribute("text").equals("SIGN UP"));
		
		logIn.goToSignUp();
		
		RedditSignUpPrompt signUp = new RedditSignUpPrompt(TLDriver.getTLDriver());
		
		assert(signUp.logInOption.getAttribute("text").equals("LOG IN INSTEAD"));
		
		signUp.logInInstead();
		
		assert(logIn.signUp.getAttribute("text").equals("SIGN UP"));
		
	}
	

}
