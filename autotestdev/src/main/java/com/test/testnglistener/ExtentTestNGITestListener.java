package com.test.testnglistener;

import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.test.util.ExtentManager;

public class ExtentTestNGITestListener implements ITestListener {
	
	private static ExtentReports extent = ExtentManager.getInstance("report2/extent.html");
    private static ThreadLocal test = new ThreadLocal();
    //public static MacacaClient driver;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		  test.set(extent.createTest(result.getMethod().getMethodName()));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		 ((ExtentTest)test.get()).pass("Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		 ((ExtentTest)test.get()).fail(result.getThrowable());
/*	        File directory = new File("test-output");
	        try {
	            String screenPath = directory.getCanonicalPath() + "/";
	            File file = new File(screenPath);
	            if (!file.exists()){
	                file.mkdirs();
	            }
	            String fileName = result.getMethod().getMethodName() + ".png";
	            driver.saveScreenshot(screenPath + fileName);
	            ((ExtentTest)test.get()).addScreenCaptureFromPath(screenPath + fileName);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }*/
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		 ((ExtentTest)test.get()).skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

}
