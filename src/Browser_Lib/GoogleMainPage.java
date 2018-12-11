package Browser_Lib;

import org.openqa.selenium.By;

import AppiumDriverSetUp_Lib.PageObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class GoogleMainPage extends PageObject {

	private MobileElement searchSection = driver.findElement(By.id("tsf"));
	
	public GoogleMainPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	
	}
	
	public void searchQuery(String query) {
		
		MobileElement searchText = searchSection.findElement(By.name("q"));
		MobileElement confirmSearch = searchSection.findElement(By.className("Tg7LZd")); 
		searchText.click();
		searchText.sendKeys(query);
		confirmSearch.click();
	}

}
