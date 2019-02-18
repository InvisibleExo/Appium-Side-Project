package BaseTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.google.common.io.Files;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class ScreenshotListener extends TestListenerAdapter {
	File failDir;
	File testMethodFail;
	
	File stackCapture;
	PrintStream printCapture;
	
	File serverCapture;
	File logCatCapture;
	
	File bugPic;
	
	Calendar calendar;
	private String methodName;
	SimpleDateFormat formatter;
	
	public ScreenshotListener() {
		calendar = Calendar.getInstance();
		formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		failDir = new File("./FailLogs/FailLogs_"+formatter.format(calendar.getTime()));
		failDir.mkdir();
	}
	
	public void captureTestFailureLogs(AppiumDriver<MobileElement> driver, ITestResult result) throws IOException {
		testMethodFail = testMethodFolderLogs(driver, result);
		stackCapture = testMethodFailureStackTrace(driver, result);
		driver.context("NATIVE_APP");
		serverCapture = testMethodFailureServerLogs(driver, result);
		logCatCapture = testMethodFailureLogCatLogs(driver, result);
		bugPic = testMethodFailScreenshot(driver, result);
		driver.context(BaseTest.defaultContext);
	}
	
	public File testMethodFolderLogs(AppiumDriver<MobileElement> driver, ITestResult result) {
		methodName = result.getMethod().getMethodName();
		calendar = Calendar.getInstance();
		formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		testMethodFail = new File(failDir + "/method_" + methodName + "_" + formatter.format(calendar.getTime()));
		testMethodFail.mkdir();
		return testMethodFail;
	}
	
	public File testMethodFailureStackTrace(AppiumDriver<MobileElement> driver, ITestResult result) throws IOException {
		methodName = result.getMethod().getMethodName();
		calendar = Calendar.getInstance();
		formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		stackCapture = new File(testMethodFail + "/" + methodName + "_" + formatter.format(calendar.getTime()) + ".txt");
		
		stackCapture.createNewFile();
		
		Throwable th = result.getThrowable();
		
		printCapture = new PrintStream(stackCapture);
		th.printStackTrace(printCapture);
		printCapture.flush();
		
		return stackCapture;
	}
	//Work on method to capture Appium/Http server logs
	public File testMethodFailureServerLogs(AppiumDriver<MobileElement> driver, ITestResult result) throws IOException {
		methodName = result.getMethod().getMethodName();
		calendar = Calendar.getInstance();
		formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		serverCapture = new File(testMethodFail + "/" + methodName + "_ServerLogs_" + formatter.format(calendar.getTime()) + ".txt");
		
		serverCapture.createNewFile();
		
		
		printCapture = new PrintStream(new FileOutputStream(serverCapture));
		Set<String> logtypes = driver.manage().logs().getAvailableLogTypes();
		System.out.println(logtypes);
		LogEntries logEntries = driver.manage().logs().get("server");
		for(LogEntry entry : logEntries.getAll()) {
			
			printCapture.println(entry.getMessage());
		}
		
		printCapture.flush();
		
		return serverCapture;
	}
	
	public File testMethodFailureLogCatLogs(AppiumDriver<MobileElement> driver, ITestResult result) throws IOException {
		methodName = result.getMethod().getMethodName();
		calendar = Calendar.getInstance();
		formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		logCatCapture = new File(testMethodFail + "/" + methodName + "_LogCat_Logs_" + formatter.format(calendar.getTime()) + ".txt");
		
		logCatCapture.createNewFile();
		
		
		printCapture = new PrintStream(new FileOutputStream(logCatCapture));
		Set<String> logtypes = driver.manage().logs().getAvailableLogTypes();
		System.out.println(logtypes);
		LogEntries logEntries = driver.manage().logs().get("logcat");
		for(LogEntry entry : logEntries.getAll()) {
			
			printCapture.println(entry.getMessage());
		}
		
		printCapture.flush();
		
		return logCatCapture;
	}
	
	
	
	public File testMethodFailScreenshot(AppiumDriver<MobileElement> driver, ITestResult result) throws IOException {
		methodName = result.getMethod().getMethodName();
		calendar = Calendar.getInstance();
		formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		
		bugPic = screenshot.getScreenshotAs(OutputType.FILE);
		
		Files.copy(bugPic, new File(testMethodFail + "/" + methodName + "_screenshot_" + formatter.format(calendar.getTime()) + ".png"));
		
		return bugPic;
	}

}
