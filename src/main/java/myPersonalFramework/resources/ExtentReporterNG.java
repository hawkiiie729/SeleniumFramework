package myPersonalFramework.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	// extent;
	
	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "//reports//index.html";// wiil give project path dynamically
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);// it requires a path where report are created
		// reporter object is responsible for making cionfigurations in your reports
		reporter.config().setReportName("Web Automation report");
		reporter.config().setDocumentTitle("Test Results");

		// this is main class,responsible for the reports execution
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter); // attaching the configured report to the main class
		extent.setSystemInfo("Tester", "Sudhanshu");
		return extent;
	}

}
