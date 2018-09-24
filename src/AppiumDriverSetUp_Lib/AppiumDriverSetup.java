package AppiumDriverSetUp_Lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;



public class AppiumDriverSetup {
	
	enum Devices {
		ANDROID, IOS;
	}
	
	public static int deviceNum = 0;
	
	public AppiumDriver<MobileElement> driver;
	
	private List<Object> newDevice = new ArrayList<Object>();
	
	private static List<AppiumDriver<MobileElement>> activeList = Collections.synchronizedList(new ArrayList<AppiumDriver<MobileElement>>());
	
	AppOrBrowser determinePlatType = new AppOrBrowser();
	
	ActiveAppiumPorts activePorts = new ActiveAppiumPorts();
	
	public void makeList () throws IOException {
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec("adb devices");
		
		BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line ="";
		
		while ((line = output.readLine()) != null) {
			if(StringUtils.isEmpty(line) || line.startsWith("List of devices attached")) {
				continue;
			}
			System.out.println(line);
			String [] serialNum = line.replace(" ", "").split("device");
			newDevice.add(Devices.ANDROID);
			newDevice.add(serialNum[0]);
			driver = setCaps((Devices)newDevice.get(0), (String)newDevice.get(1));
			activeList.add(driver);
			newDevice.clear();
		}
		
		//Implement the IOS Process
	}
	
	private AppiumDriver<MobileElement> setCaps(Devices d, String udid) throws MalformedURLException{
		AppiumDriver<MobileElement> driver = null;
		DesiredCapabilities cap = new DesiredCapabilities();
		
		String deviceName = "device"+ ++deviceNum;
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		if(d == Devices.ANDROID) {
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		}
		if(d == Devices.IOS) {
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
		}
		cap.setCapability("deviceId", udid);
		cap.setCapability("udid", udid.trim());
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "4000");
		cap.setCapability("noReset", false);
		
		cap = determinePlatType.determinePlat(cap);
		
		//Generate AppiumPorts Class and retrieve generated port number.
		activePorts.generateServer();
		System.out.println("Argument to driver object : " + activePorts.getAppiumURL() + "\n");
		switch (d) {
			case ANDROID: 
				driver = new AndroidDriver<MobileElement>(new URL(activePorts.getAppiumURL()), cap);
				break;
			case IOS:
				driver = new IOSDriver<MobileElement>(new URL(activePorts.getAppiumURL()), cap);
				break;
		}
		activePorts.increasePortNum();
		
		return driver;
	}
	
	public List<AppiumDriver<MobileElement>> getActiveList(){
		return activeList;
	}
	
	public void tearDown() {
		driver.quit();
	}

}

