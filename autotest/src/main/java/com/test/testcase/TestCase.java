package com.test.testcase;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;
import com.test.testnglistener.TestngExtentReportListener;
import com.test.util.HttpClientManager;
import com.test.util.HttpUtils;
import com.test.util.LogUtil;
import com.test.util.StaticGlobalVariable;



//@Listeners({ com.test.testnglistener.AutoTestListener.class })
public class TestCase extends BaseTest{


@Test(dataProvider="dpa",dataProviderClass = com.test.dataprovider.DataProvider.class)
  public void interfaceName1(Map map1) throws ParseException, IOException {	
	
	//test=extent.startTest("first test interfaceName");
	
	
	  
	String interfaceName=map1.get("interfaceName").toString() ;
	String method = map1.get("method").toString();
	String protocol = map1.get("protocol").toString();	
	String hostname = map1.get("hostname").toString();
	String hostnumber = map1.get("hostnumber").toString();	
	String name = map1.get("name").toString();
	String passwd= map1.get("passwd").toString();
	String url = map1.get("url").toString();
	
	
	Map <String,String> params = new HashMap();
	params.put("name", name);
	params.put("passwd", passwd);
	
	CloseableHttpClient httpClient = HttpClientManager.getHttpClient();
//	System.out.println(interfaceName);
	
	CloseableHttpResponse httpResponse= null;
	//get请求post
	if (method.equals("get")) {
		 httpResponse = HttpUtils.get(url,httpClient);
	} else if(method.equals("post") ){
		httpResponse = HttpUtils.post(url, params);
	}
	
	 
	// System.out.println(EntityUtils.toString(httpEntity,"utf-8"));
	 
	 //System.out.print(httpResponse.getStatusLine().getStatusCode());
	HttpEntity httpEntity = httpResponse.getEntity();
	int statusCode = httpResponse.getStatusLine().getStatusCode();
	LogUtil.log("test 执行 log 返回状态码"+Integer.toString(statusCode));
	logger.info("log4日志---------"+"返回状态码："+Integer.toString(statusCode));
	 assertEquals(statusCode, StaticGlobalVariable.httpReturnCod_successCode);
		 

  }



}
