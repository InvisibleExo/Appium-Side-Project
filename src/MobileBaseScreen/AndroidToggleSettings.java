package MobileBaseScreen;

import java.util.HashMap;
import java.util.Map;

import org.testng.collections.Lists;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AndroidToggleSettings {
	
	static Map<String, Object> args = createMap();
	
	private static Map<String, Object> createMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("command", "");
		return map;
	}
	
	static boolean turnWifiOff(AppiumDriver<MobileElement> driver) {
		boolean turningOn;
		Object status;
		
		args.put("args", Lists.newArrayList("settings get global wifi_on"));
		status =  driver.executeScript("mobile: shell", args);
		System.out.println("Status: "+ status.toString().trim());
		if(status.toString().trim().contains("1") || status.toString().trim().contains("3")) {
			System.out.println("Turning WiFi off");
			((AndroidDriver<MobileElement>) driver).toggleWifi();
			turningOn = true;
		} else {
			System.out.println("WiFi is already off");
			turningOn = false;
		}
		return turningOn;
	}
	
	static boolean turnWiFiOn(AppiumDriver<MobileElement> driver) {
		boolean turningOn;
		Object status;
		
		args.put("args", Lists.newArrayList("settings get global wifi_on"));
		status =  driver.executeScript("mobile: shell", args);
		System.out.println("Status: "+ status.toString().trim());
		if(status.toString().trim().contains("0")) {
			System.out.println("Turning WiFi on");
			((AndroidDriver<MobileElement>) driver).toggleWifi();
			turningOn = true;
		} else {
			System.out.println("WiFi is already on");
			turningOn = false;
		}
		return turningOn;
	}
	
	
	static boolean turnMobileDataOn(AppiumDriver<MobileElement> driver) {
		boolean turningOn;
		Object status;
		
		args.put("args", Lists.newArrayList("settings get global mobile_data"));
		status = driver.executeScript("mobile: shell", args);
		System.out.println("Status: "+ status.toString().trim());
		if(status.toString().trim().contains("0")){
			System.out.println("Turning on Mobile Data");
			args.put("args", Lists.newArrayList("svc data enable"));
			driver.executeScript("mobile: shell", args);
			turningOn = true;
		} else {
			System.out.println("Mobile Data is already on");
			turningOn = false;
		}
		return turningOn; 
	}
	
	static boolean turnMobileDataOff(AppiumDriver<MobileElement> driver) {
		boolean turningOff;
		Object status;
		
		args.put("args", Lists.newArrayList("settings get global mobile_data"));
		status = driver.executeScript("mobile: shell", args);
		System.out.println("Status: "+ status.toString().trim());
		if(status.toString().trim().contains("1")){
			System.out.println("Turning off Mobile Data");
			args.put("args", Lists.newArrayList("svc data disable"));
			driver.executeScript("mobile: shell", args);
			turningOff = true;
		} else {
			System.out.println("Mobile Data is already off");
			turningOff = false;
		}
		return turningOff; 
	}
	
	//(Doesn't support physical devices)
	static boolean turnAirPlaneModeOn (AppiumDriver<MobileElement> driver) {
		boolean turningOn;
		Object status;
		
		args.put("args", Lists.newArrayList("settings get global airplane_mode_on"));
		status = driver.executeScript("mobile: shell", args);
		if(status.toString().trim().contains("0")){
			System.out.println("Turning on AirPlane Mode");
			((AndroidDriver<MobileElement>) driver).toggleAirplaneMode();
			turningOn = true;
		} else {
			System.out.println("AirPlane Mode is already On");
			turningOn = false;
		}
		return turningOn;
	}
	
	static boolean turnAirPlaneModeOff (AppiumDriver<MobileElement> driver) {
		boolean turningOff;
		Object status;
		
		args.put("args", Lists.newArrayList("settings get global airplane_mode_on"));
		status = driver.executeScript("mobile: shell", args);
		if(status.toString().trim().contains("1")){
			System.out.println("Turning off AirPlane Mode");
			((AndroidDriver<MobileElement>) driver).toggleAirplaneMode();
			turningOff = true;
		} else {
			System.out.println("AirPlane Mode is already Off");
			turningOff = false;
		}
		return turningOff;
	}

}
