package selenium.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import selenium.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentReports extent = ExtentReporterNG.getReporterObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		//System.out.println(result.getMethod().getMethodName());
		test = extent.createTest(result.getMethod().getMethodName());
		//Set unique thread ID of the running test using ThreadLocal class
		threadLocal.set(test);
	}
	
	public void onTestSuccess(ITestResult result) {
		threadLocal.get().log(Status.PASS, "Test " + result.getMethod().getMethodName() + " Passed");
	}
	
	public void onTestFailure(ITestResult result) {
		threadLocal.get().log(Status.FAIL, "Test " + result.getMethod().getMethodName() + " Failed");
		threadLocal.get().fail(result.getThrowable());
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
			//System.out.println("In Listeners:" + filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("filePath: " + filePath);
		threadLocal.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
