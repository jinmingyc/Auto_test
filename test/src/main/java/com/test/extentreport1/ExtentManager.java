package com.test.extentreport1;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

public class ExtentManager {
	static ExtentReports extent;
    final static String filePath = "report/Extent.html";
    
    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            extent = new ExtentReports(filePath, true,NetworkMode.OFFLINE);
        }
        
        return extent;
    }

}
