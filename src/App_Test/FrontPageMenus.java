package App_Test;

import AppiumDriverSetUp_Lib.TLDriverFactory;
import BaseTest.*;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import App_Lib.*;

public class FrontPageMenus extends BaseTest {

	@Test
	public void checkOnEmailPage() {
	GlassDoorSignInScreen emailSignIn = new GlassDoorSignInScreen(TLDriverFactory.getTLDriver());
	
	assertTrue(emailSignIn.defaultEmailButton != null);
	
	}
}
