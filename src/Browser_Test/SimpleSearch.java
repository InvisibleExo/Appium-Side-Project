package Browser_Test;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import AppiumDriverSetUp_Lib.*;
import AppiumDriverSetUp_Lib.BaseScreen.Direction;
import BaseTest.*;
import Browser_Lib.*;
import io.appium.java_client.MobileElement;

public class SimpleSearch extends BaseTest {
	
	@Test
	public void testActivation() {
		pageObject = new BaseScreen(TLDriverFactory.getTLDriver());
		pageObject.get("https://www.google.com/");
		System.out.println(pageObject.getCurrentUrl());
		
		assertTrue(pageObject.getCurrentUrl().equals("https://www.google.com/"));
		
		GoogleMainPage googleMainPage = new GoogleMainPage(TLDriverFactory.getTLDriver());
		
		googleMainPage.searchQuery("star wars");
		
		GoogleSearchResultsPage resultsPage = new GoogleSearchResultsPage(TLDriverFactory.getTLDriver());
		
		resultsPage.swipeVertical(0.7, 0.25, 6000);
		
		MobileElement searchResult = null;
		
		searchResult = resultsPage.swipeUntilFound(By.name("q"), 3, searchResult, Direction.up);
		
		assertTrue(searchResult != null);
	}

}
