package AppiumDriverSetUp_Lib;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class PageObject {
	public AppiumDriver<MobileElement> driver;
	protected WebDriverWait wait;

	public PageObject(AppiumDriver<MobileElement> driver){
		this.driver = driver;
		wait = new WebDriverWait(driver, 15);
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
}
