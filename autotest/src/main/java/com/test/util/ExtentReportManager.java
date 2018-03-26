package com.test.util;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

public class ExtentReportManager {
	private static ExtentReports extent;
    
    public synchronized static ExtentReports getReporter(String filePath) {
        if (extent == null) {
            extent = new ExtentReports(filePath, true,NetworkMode.OFFLINE);
            
            extent
                .addSystemInfo("Host Name", "Anshoo")
                .addSystemInfo("Environment", "QA");
        }
        
        return extent;
    }

}
