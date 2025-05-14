package myPersonalFramework.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import myPersonalFramework.resources.ExtentReporterNG;

//import org.testng.ITestListener;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;

	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // to make every test Thread safe
	// thread safe means,no matter you run concurrently ,each object creation have
	// its own thread,so it wont interrrupt the other overriding variable

	@Override
	public void onTestStart(ITestResult result) {
		// before running any test,control will move to this block
		test = extent.createTest(result.getMethod().getMethodName());// create entry for your test
		// result.getMethod().getMethodName()->gives the test case name
		extentTest.set(test); // it will assign a unique thread id to each test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());// it will print that error message
		// extentTest.get() will retrive the thread id of the test that is being
		// currently executed,as thread id will be unique for each test object

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			// why we are doing like this?
			// because field are part of class not methods
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// screenshot
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
			// filepath is the path where ss gets saved
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		// it is the final method that is going to execute before generating the report
		extent.flush();// used for report generationß

	}

}
