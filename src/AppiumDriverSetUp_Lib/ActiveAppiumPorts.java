package AppiumDriverSetUp_Lib;

import java.io.IOException;

public class ActiveAppiumPorts {
	
	private int portNum = 5000;
	
	private String appiumPort = "500" + portNum;
	private String serverIp = "127.0.0.1";
	
	
	public String getAppiumURL() {
		String serverURL = "http://" + serverIp + ":" + portNum + "/wd/hub";
		System.out.println(serverURL);
		return serverURL;
	}
	
	public void increasePortNum() {
		portNum++;
		System.out.println("Next Port will be: " + portNum);
	}
	
	public String getPort() {
		return ""+portNum;
	}
	
	public void generateServer() {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a " + serverIp + " -p " + portNum + 
					"--session-override -dc \" {\"\"noReset\"\": \"\"false\"\"}\"\"");
			Thread.sleep(20000);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException r) {
			r.printStackTrace();
		}
		
	}
	
	public void stopServer() {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("taskkill /F /IM node.exe");
			runtime.exec("taskkill /F /IM cmd.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
