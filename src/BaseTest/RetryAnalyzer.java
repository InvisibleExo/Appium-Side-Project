package BaseTest;

import java.io.IOException;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import AppiumDriverSetUp_Lib.TLDriverFactory;


public class RetryAnalyzer extends BaseTest implements IRetryAnalyzer {
	int count = 0;
	private static int maxTry = 1;
	LogCaptureListener captureLogs;

	public boolean retry(ITestResult arg0) {
		if(count < maxTry) {
			count++;
			return true;
		} 
		else {
			initializeFailDir();
			try {
				captureLogs.captureTestFailureLogs(TLDriverFactory.getTLDriver(), arg0);
			} catch (IOException e) {
				e.printStackTrace();
			} 
			return false;
		}
	}
	
	public void initializeFailDir() {
		captureLogs = new LogCaptureListener();
	}
	

}
