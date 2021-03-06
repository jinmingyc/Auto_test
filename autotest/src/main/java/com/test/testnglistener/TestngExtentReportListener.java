package com.test.testnglistener;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.test.util.ExtentReportManager;

public class TestngExtentReportListener extends TestListenerAdapter {

	private Logger logger = Logger.getLogger(TestngExtentReportListener.class);
	protected ExtentReports extent;
	protected ExtentTest test;

	@Override
	public void onTestStart(ITestResult tr) {
		super.onTestStart(tr);
		logger.info("log4j记录*****onTestStart*****" + tr.getName() + " Start");
		
		extent = ExtentReportManager.getReporter("report1/extent.html");
		test = extent.startTest(tr.getName());
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		super.onTestFailure(tr);
		logger.info("log4j记录*****onTestFailure*****" + tr.getName() + " Failure");
		
		extent = ExtentReportManager.getReporter("report1/extent.html");
		test = extent.startTest(tr.getName());
		
		test.log(LogStatus.FAIL, tr.getThrowable());
		extent.endTest(test);
		extent.flush();
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		super.onTestSkipped(tr);
		logger.info("log4j记录*****onTestSkipped*****" + tr.getName() + " Skipped");
		
		extent = ExtentReportManager.getReporter("report1/extent.html");
		test = extent.startTest(tr.getName());
		
		
		test.log(LogStatus.SKIP, "SKIP");
		extent.endTest(test);
		extent.flush();
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		super.onTestSuccess(tr);
		logger.info("log4j记录*****onTestSuccess*****" + tr.getName() + " Success");
		
		extent = ExtentReportManager.getReporter("report1/extent.html");
		test = extent.startTest(tr.getName());
		
		
		test.log(LogStatus.PASS, "Pass");
		extent.endTest(test);
		extent.flush();
	}

	@Override
	public void onFinish(ITestContext testContext) {
		super.onFinish(testContext);
		
		extent = ExtentReportManager.getReporter("report1/extent.html");
		test = extent.startTest(testContext.getName());
		
		extent.close();
	}
	
}
