package com.test.util;

import com.aventstack.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

public class ExtentReportManager {
	private static ExtentReports extent;
	private static com.relevantcodes.extentreports.ExtentTest extentTest;
    
    public synchronized static ExtentReports getReporter(String filePath) {
        if (extent == null) {
            extent = new ExtentReports(filePath, true,NetworkMode.OFFLINE);
            
            
                  
            extent
                .addSystemInfo("Host Name", "Anshoo")
                .addSystemInfo("Environment", "QA");
        }
        
        return extent;
    }
    public static com.relevantcodes.extentreports.ExtentTest getExtentTest(ExtentReports report,String ExtentTestName) {
    	
    	if(extentTest==null) {
    		extentTest=report.startTest(ExtentTestName);
    		
    		extentTest.assignCategory("Category1");
    		
    	}
    	
    	
    	
		return extentTest;
    	
    }

}
