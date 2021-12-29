package Utils;

import java.io.IOException;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/*
�*@Author: Naveen Mehra
�*@Desc: Class to handle Extent Reporting
�*/
@Listeners(TestListeners.class)
public class ExtentReportManager {

	public static ExtentReports extent;
	public static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;
	public static String reportPathNValue;
	public static String folderPathValue;
	

	public static ExtentReports getExtent() {
		if (extent != null)
			return extent;
		extent = new ExtentReports();
		extent.attachReporter(getHtmlReporter());
		return extent;
	}

	private static ExtentHtmlReporter getHtmlReporter() {
		
		String path=System.getProperty("user.dir")+"//Reports"	;	
		htmlReporter = new ExtentHtmlReporter(path + "/ExtentReport.html");
		// Configuring htmlReporter
		//htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Punchh Server Automation Report");
		htmlReporter.config().setReportName("Basic Flow");
	//	htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
	//	htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		

		return htmlReporter;
	}

	public static ExtentTest createTest(String name, String description) {
		test = extent.createTest(name, description);
		return test;
	}
}