package BaseTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.google.common.io.Files;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LogCaptureListener extends TestListenerAdapter {
	File failDir;
	File testMethodFail;
	
	File stackCapture;
	
	File serverCapture;
	File logCatCapture;
	
	File bugPic;
	
	File pageSourceLayout;
	
	Calendar calendar;
	private String methodName;
	SimpleDateFormat formatter;
	
	private static SimpleDateFormat suiteFormatter;
	private static String suiteFormatterDateStamp;
	
	
	SessionId sessionID;
	
	public LogCaptureListener() {
		calendar = Calendar.getInstance();
		declareSuiteFolderTime();
		failDir = new File("./FailLogs/FailLogs_"+LogCaptureListener.suiteFormatterDateStamp);
		failDir.mkdir();
	}
	
	public void declareSuiteFolderTime() {
		if(LogCaptureListener.suiteFormatterDateStamp == null) {
			LogCaptureListener.suiteFormatter = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss");
			LogCaptureListener.suiteFormatterDateStamp = LogCaptureListener.suiteFormatter.format(calendar.getTime());
		}
	}
	
	public void captureTestFailureLogs(AppiumDriver<MobileElement> driver, ITestResult result) throws IOException {
		testMethodFail = testMethodFolderLogs(driver, result);
		stackCapture = testMethodFailureStackTrace(driver, result);
		driver.context("NATIVE_APP");
		sessionID = driver.getSessionId();
		serverCapture = testMethodFailureServerLogs(driver, result);
		logCatCapture = testMethodFailureLogCatLogs(driver, result);
		driver.context(BaseTest.defaultContext);
		bugPic = testMethodFailScreenshot(driver, result);
		if(System.getProperty("getlayout") != null && System.getProperty("getlayout").equalsIgnoreCase("yes")) {
			pageSourceLayout = testMethodSourceLayout(driver, result);
		}
	}
	
	public File testMethodFolderLogs(AppiumDriver<MobileElement> driver, ITestResult result) {
		methodName = result.getMethod().getMethodName();
		calendar = Calendar.getInstance();
		formatter = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss");
		
		testMethodFail = new File(failDir + "/method_" + methodName + "_" + formatter.format(calendar.getTime()));
		testMethodFail.mkdir();
		return testMethodFail;
	}
	
	public File testMethodFailureStackTrace(AppiumDriver<MobileElement> driver, ITestResult result) throws IOException {
		methodName = result.getMethod().getMethodName();
		calendar = Calendar.getInstance();
		formatter = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss");
		
		stackCapture = new File(testMethodFail + "/" + methodName + "_" + formatter.format(calendar.getTime()) + ".txt");
		
		stackCapture.createNewFile();
		
		Throwable th = result.getThrowable();
		PrintStream printCapture;
		printCapture = new PrintStream(stackCapture);
		th.printStackTrace(printCapture);
		printCapture.close();
		
		return stackCapture;
	}

	public File testMethodFailureServerLogs(AppiumDriver<MobileElement> driver, ITestResult result) throws IOException {
		methodName = result.getMethod().getMethodName();
		calendar = Calendar.getInstance();
		formatter = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss");
		
		serverCapture = new File(testMethodFail + "/" + methodName + "_ServerLogs_" + formatter.format(calendar.getTime()) + ".txt");
		
		serverCapture.createNewFile();
		
		PrintStream printCapture;
		printCapture = new PrintStream(new FileOutputStream(serverCapture));
		driver.manage().logs().getAvailableLogTypes();
		LogEntries logEntries = driver.manage().logs().get("server");
		for(LogEntry entry : logEntries.getAll()) {
			
			printCapture.println(entry.getMessage());
		}
		
		printCapture.close();
		return serverCapture;
	}
	
	public File testMethodFailureLogCatLogs(AppiumDriver<MobileElement> driver, ITestResult result) throws IOException {
		methodName = result.getMethod().getMethodName();
		calendar = Calendar.getInstance();
		formatter = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss");
		
		logCatCapture = new File(testMethodFail + "/" + methodName + "_LogCat_Logs_" + formatter.format(calendar.getTime()) + ".txt");
		
		logCatCapture.createNewFile();
		
		PrintStream printCapture;
		printCapture = new PrintStream(new FileOutputStream(logCatCapture));
		driver.manage().logs().getAvailableLogTypes();
		LogEntries logEntries = driver.manage().logs().get("logcat");
		for(LogEntry entry : logEntries.getAll()) {
			
			printCapture.println(entry.getMessage());
		}
		
		printCapture.close();
		return logCatCapture;
	}
	
	
	
	public File testMethodFailScreenshot(AppiumDriver<MobileElement> driver, ITestResult result) throws IOException, ScreenshotException{
		methodName = result.getMethod().getMethodName();
		calendar = Calendar.getInstance();
		formatter = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss");
		
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		
		bugPic = screenshot.getScreenshotAs(OutputType.FILE);
		
		Files.copy(bugPic, new File(testMethodFail + "/" + methodName + "_screenshot_" + formatter.format(calendar.getTime()) + ".png"));
		
		return bugPic;
	}
	
	public File testMethodSourceLayout(AppiumDriver<MobileElement> driver, ITestResult result) throws IOException {
		methodName = result.getMethod().getMethodName();
		calendar = Calendar.getInstance();
		formatter = new SimpleDateFormat("MM_dd_yyyy_hh_mm_ss");
		
		pageSourceLayout = new File(testMethodFail + "/" + methodName + "_sourceLayout_" + formatter.format(calendar.getTime()) + ".txt");
		
		pageSourceLayout.createNewFile();
		
		PrintStream printCapture;
		printCapture = new PrintStream(new FileOutputStream(pageSourceLayout));
		printCapture.println(driver.getPageSource());
		printCapture.close();
		
		return pageSourceLayout;
	}

}
