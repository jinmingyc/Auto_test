package com.test.util;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.testng.Reporter;

public class LogUtil {
	private static String splitTimeAndMsg = "==================";

	public static void log(String msg) {
		long timeMillis = Calendar.getInstance().getTimeInMillis();
		Reporter.log("testngReportlong-----------" + timeMillis + splitTimeAndMsg + msg, true);
	}
	
}
