package com.test.testnglistener;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.test.util.LogUtil;


public class AutoTestListener extends TestListenerAdapter {
	
	
	@Override  
    public void onTestStart(ITestResult itr) {  
        super.onTestStart(itr);  
        
        LogUtil.log("test start"+itr.getName());
        /*logger.info("logger>> "+itr.getName() + " method START---------->>");  
        doReport(itr,"Reporter "+itr.getName()+ " method START---------->> "+"<br>");  */
        //Reporter.setCurrentTestResult(itr);  
        //Reporter.log("Reporter "+itr.getName()+ " method START---------->>");  
    }  
	
	   //@Override
	    public void onTestSuccess(ITestResult tr) {
		   LogUtil.log(tr.getName()+ "--Test method success\n");
	    }
	 
	    @Override
	    public void onTestFailure(ITestResult tr) {
	    	LogUtil.log(tr.getName()+"--Test method faillure\n--");
	    }
	    
	    @Override
	    public void onTestSkipped(ITestResult tr) {
	    	LogUtil.log(tr.getName()+"--Test method skipped\n--");
	    }
	
}
