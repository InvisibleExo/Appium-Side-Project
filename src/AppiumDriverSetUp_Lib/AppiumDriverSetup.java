package AppiumDriverSetUp_Lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;


import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;



public class AppiumDriverSetup {
	
	enum Devices {
		ANDROID, IOS;
	}
	
	public int deviceCount = 0;
	
	public static int deviceNum = 0;
	
	private DesiredCapabilities driverCap;
	
	private List<Object> newDevice = new ArrayList<Object>();
	
	private static List<DesiredCapabilities> activeList = Collections.synchronizedList(new ArrayList<DesiredCapabilities>());
	
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
			driverCap = setCaps((Devices)newDevice.get(0), (String)newDevice.get(1));
			activeList.add(driverCap);
			newDevice.clear();
			
		}
		
		//Implement the IOS Process
	}
	
	private DesiredCapabilities setCaps(Devices d, String udid) throws MalformedURLException{
		
		DesiredCapabilities cap = new DesiredCapabilities();
		
		String deviceName = "device"+ ++deviceNum;
		deviceCount++;
		cap.setCapability("deviceName", deviceName);
		
		if(d == Devices.ANDROID) {
			
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			cap.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, activePorts.assignSystemPort());
			cap.setCapability("automationName", "UIAutomator2");
			
		}
		if(d == Devices.IOS) {
			
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
			cap.setCapability("wdaLocalPort", activePorts.assignWDAPort());
			
		}
		cap.setCapability("deviceId", deviceName);
		cap.setCapability("udid", udid.trim());
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "4000");
		cap.setCapability("noReset", false);
		
		cap = determinePlatType.determinePlat(cap);
		
		//Generate AppiumPorts Class and retrieve generated port number.
		activePorts.generateServer();
		cap.setCapability("appiumURL", activePorts.getAppiumURL());
		System.out.println("Argument to driver object : " + activePorts.getAppiumURL() + "\n");
		
		activePorts.increasePortNumCheck();
		
		return cap;
	}
	
	public List<DesiredCapabilities> getActiveList(){
		return activeList;
	}
	
	
	


}

