package com.test.httpclient;


import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

public class httpClientTest {
  @Test
  public void f() {
	  
	  CloseableHttpClient httpClient = null;
		HttpGet httpGet = null;
	    try {
	    	httpClient = HttpClients.createDefault();
	    	RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();      
	        String url = "https://www.baidu.com";
	    	httpGet = new HttpGet(url);
	    	httpGet.setConfig(requestConfig);
			CloseableHttpResponse response = httpClient.execute(httpGet);
			HttpEntity httpEntity = response.getEntity();
			System.out.println(EntityUtils.toString(httpEntity,"utf-8"));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(httpGet!=null){
					httpGet.releaseConnection();
				}
				if(httpClient!=null){
					httpClient.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	    
  }
}
