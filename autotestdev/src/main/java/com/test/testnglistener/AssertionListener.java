package com.test.testnglistener;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.test.util.AssertUtil;
import com.test.util.LogUtil;

/**
 * JAVA结合testng断言verify(断言失败不中断继续执行)
原理：

1.自已构造一个断言类，把Assert.assertEquals给try catch住。

2.利用testng的监听类在测试方法运行结束后进行分析
 * @author yuanchengming
 *
 */
public class AssertionListener extends TestListenerAdapter {

	@Override
	public void onTestSuccess(ITestResult tr) {
		// TODO Auto-generated method stub
		
		super.onTestSuccess(tr);
		this.handleAssersion(tr);
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		// TODO Auto-generated method stub
		this.handleAssersion(tr);
		
	}

		@Override
	public void onTestSkipped(ITestResult tr) {
		// TODO Auto-generated method stub
		super.onTestSkipped(tr);
		this.handleAssersion(tr);
	}

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		super.onTestStart(result);
		AssertUtil.flag = true;
		 AssertUtil.errors.clear();
	}
	
	 private int index = 0;
	 
	public void handleAssersion(ITestResult tr) {
		if(!AssertUtil.flag) {
			Throwable throwable = tr.getThrowable();
			if(throwable==null) {
				throwable =new Throwable();
			}
			StackTraceElement[] traces = throwable.getStackTrace();
			StackTraceElement[] alltrace = new StackTraceElement[0];
			
			for (Error e : AssertUtil.errors) {
                StackTraceElement[] errorTraces = e.getStackTrace();
                StackTraceElement[] et = this.getKeyStackTrace(tr, errorTraces);
                StackTraceElement[] message = new StackTraceElement[]{new StackTraceElement("message : "+e.getMessage()+" in method : ", tr.getMethod().getMethodName(), tr.getTestClass().getRealClass().getSimpleName(), index)};
                index = 0;
                alltrace = this.merge(alltrace, message);
                alltrace = this.merge(alltrace, et);
            }
			if(traces!=null){
                traces = this.getKeyStackTrace(tr, traces);
                alltrace = this.merge(alltrace, traces);
            }           
            throwable.setStackTrace(alltrace);
            tr.setThrowable(throwable);
            AssertUtil.flag = true;   
            AssertUtil.errors.clear();
            LogUtil.log(tr.getName()+"--断言异常处理");
            tr.setStatus(ITestResult.FAILURE);
		}
	}
	
	  private StackTraceElement[] getKeyStackTrace(ITestResult tr, StackTraceElement[] stackTraceElements){
	        List<StackTraceElement> ets = new ArrayList<StackTraceElement>();
	        for (StackTraceElement stackTraceElement : stackTraceElements) {           
	            if(stackTraceElement.getClassName().equals(tr.getTestClass().getName())){               
	                ets.add(stackTraceElement);
	                index = stackTraceElement.getLineNumber();
	            }
	        }
	        StackTraceElement[] et = new StackTraceElement[ets.size()];
	        for (int i = 0; i < et.length; i++) {
	            et[i] = ets.get(i);
	        }
	        return et;
	    }
	  
	  private StackTraceElement[] merge(StackTraceElement[] traces1, StackTraceElement[] traces2){
	        StackTraceElement[] ste = new StackTraceElement[traces1.length+traces2.length];
	        for (int i = 0; i < traces1.length; i++) {
	            ste[i] = traces1[i];
	        }
	        for (int i = 0; i < traces2.length; i++) {
	            ste[traces1.length+i] = traces2[i];
	        }
	        return ste;
	    }

}
