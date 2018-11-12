package AppiumDriverSetUp_Lib;

import java.io.IOException;


public class ActiveAppiumPorts {
	
	
	private int portNum = 5000;
	private int serverIPChanger = 1;
	
	private int systemPortNumCounter = 0;
	
	//Android
	private int systemPort = 8200;
	//IOS
	private int wdaPort = 8100;
	
	private int wdaPortNumCounter = 0;
	
	private String serverIp = "127.0.0." + serverIPChanger;
	
	private static boolean serverMade = false;
	
	
	public String getAppiumURL() {
		String serverURL = "http://" + serverIp + ":" + portNum + "/wd/hub";
		return serverURL;
	}
	
	public void increasePortNumCheck() {
		if(!(systemPortNumCounter < 2) || !(wdaPortNumCounter < 2)){
			portNum++;
			System.out.println("Next Port will be: " + portNum);
			if(systemPortNumCounter >= 2) {
				systemPortNumCounter = 0;
			}
			if(wdaPortNumCounter >= 2) {
				wdaPortNumCounter = 0;
			}
		}
	}
	
	public int getSystemPort() {
		return systemPort;
	}
	
	public void increaseSystemPort() {
		systemPort++;
	}
	
	public int assignSystemPort() {
		int assignSystemPort  = getSystemPort();
		increaseSystemPort();
		systemPortNumCounter++;
		return assignSystemPort;
	}
	
	public int getWDAPort() {
		return wdaPort;
	}
	
	public void increaseWDAPort() {
		wdaPort++;
	}
	
	public int assignWDAPort() {
		int assignIOSPort = getWDAPort();
		increaseWDAPort();
		wdaPortNumCounter++;
		return assignIOSPort;
	}
	
	public String getPort() {
		return ""+portNum;
	}
	
	public void generateServer() {
		if (serverMade == false) {
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
			serverMade = true;
		}
		else {
			serverMade = false;
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
