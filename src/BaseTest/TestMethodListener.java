package BaseTest;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

public class TestMethodListener implements IInvokedMethodListener {

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		ITestNGMethod m = method.getTestMethod();
		if(null == m.getRetryAnalyzer()) {
			m.setRetryAnalyzer(new RetryAnalyzer());
		}
	}

	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		
	}

}
