package App_Test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import App_Lib.RedditMainActivityFooter;
import BaseTest.*;

public class RedditOfflineTest extends BaseTest{
	
	RedditResetStartPoint resetStartPoint = new RedditResetStartPoint();
	
	@BeforeMethod(alwaysRun=true)
	public void setStartPoint() {
		resetStartPoint.resetStartPointMainActivityPage(TLDriver.getTLDriver());
	}
	
	@Test
	public void testWifi() {
		RedditMainActivityFooter footer = new RedditMainActivityFooter(TLDriver.getTLDriver());
		System.out.println("Checking what response we get from setting wifi on:");
		assert(footer.turnOffWifi() == true);
		assert(footer.turnOffWifi() == false);
		assert(footer.turnOnWifi() == true);
		assert(footer.turnOnWifi() == false);
		
	}
	
	@Test
	public void testMobileData() {
		RedditMainActivityFooter footer = new RedditMainActivityFooter(TLDriver.getTLDriver());
		
		System.out.println("Checking what response we get from setting Mobile data on:");
		assert(footer.turnOffMobileData() == true);
		assert(footer.turnOffMobileData() == false);
		assert(footer.turnOnMobileData() == true);
		assert(footer.turnOnMobileData() == false);
	}
	
	@Test
	public void testAirPlaneMode() {
		RedditMainActivityFooter footer = new RedditMainActivityFooter(TLDriver.getTLDriver());
		
		System.out.println("Checking what response we get from setting Mobile data on:");
		assert(footer.turnOnAirPlaneMode() == true);
		assert(footer.turnOnAirPlaneMode() == false);
		assert(footer.turnOffAirPlaneMode() == true);
		assert(footer.turnOffAirPlaneMode() == false);
		
		
	}
}
