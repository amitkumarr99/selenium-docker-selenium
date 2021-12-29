package Utils;

import java.io.IOException;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

@Listeners(TestListeners.class)
public class TestListeners implements ITestListener {
	static Logger log = Logger.getLogger(TestListeners.class);

	public static ExtentReports extentReport;
	//public static ExtentTest extentLogger;
	/** The logger. */
	static Logger logger;
	public static ExtentTest test;
	public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	private String sTCName;

	public void onTestStart(ITestResult result) {
		test=extentReport.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		//extentLogger = extentReport.createTest(result.getName());
		log.info("==== Initializing TestCase: " + result.getTestClass().getName() + "." + result.getName() + " ====");
		extentTest.get().log(Status.INFO, result.getMethod().getDescription());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "==== Test Case Passed ====");
		log.info("==== Completed TestCase: " + result.getTestClass().getName() + "." + result.getName()
				+ "Status: Passed ====");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.FAIL, result.getThrowable());
		sTCName = result.getMethod().getMethodName();
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
			//Utilities.screenShotCapture(driver, sTCName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.error("=== Failed TestCase: " + result.getTestClass().getName() + "." + result.getName()
				+ "Status: Failed ====");

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.SKIP, result.getTestClass().getName() + "." + result.getName());
		log.info("==== Completed TestCase: " + result.getTestClass().getName() + "." + result.getName()
				+ "Status: Skipped ====");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		logger = Logger.getLogger("IALog");
		Appender fh = null;
		try {
			fh = new FileAppender(new SimpleLayout(), "AutomationLogFile.log");
			logger.addAppender(fh);
			fh.setLayout(new SimpleLayout());

		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		extentReport = ExtentReportManager.getExtent();
		extentReport.setSystemInfo("Author", "Test1");
		extentReport.setSystemInfo("OS", System.getProperty("os.name"));
		extentReport.setSystemInfo("OS Version", System.getProperty("os.version"));
		extentReport.setSystemInfo("OS Arch", System.getProperty("os.arch"));
		extentReport.setSystemInfo("Environment", "QA");
		extentReport.setSystemInfo("User Name", "QA_User");
	}

	public void onFinish(ITestContext context) {
		extentReport.flush();
	}

}
