package BaseTest;

import java.io.IOException;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class RetryAnalyzer implements IRetryAnalyzer {
	int count = 0;
	private static int maxTry = 1;
	LogCaptureListener captureLogs;

	public boolean retry(ITestResult arg0) {
		if(count < maxTry) {
			count++;
			System.out.println("Fail count for case: " + arg0.getTestName());
			return true;
		} 
		else {
			initializeFailDir();
			try {
				System.out.println("collecting logs");
				captureLogs.captureTestFailureLogs(TLDriver.getTLDriver(), arg0);
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
