package com.test.util;

import java.io.IOException;import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.html.parser.Entity;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class HttpUtils {	

	  public static  CloseableHttpResponse get(String url,CloseableHttpClient httpClient) {
		  
		  HttpGet httpGet = null;		  
		  RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();      		  
		  httpGet = new HttpGet(url);
		  httpGet.setConfig(requestConfig);
		  CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
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
		return response;   
		    
	  }
	
	/*public CloseableHttpResponse get(String url, Map<String, String> params,CloseableHttpClient httpclient){
	　　}
	*/

	
	public static CloseableHttpResponse post(String url, Map<String, String> params) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClient = HttpClientManager.getHttpClient();
		
		//HttpPost httpPost = new HttpPost(url);
		
		HttpPost httpPost = new HttpPost(url);
	      //装填参数
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		if (params!=null) {
			for(Entry<String,String> entry:params.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
	     //设置参数到请求对象中
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		
/*		System.out.println("请求地址："+url);
        System.out.println("请求参数："+nvps.toString());*/
        
        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        
        
        CloseableHttpResponse response = httpClient.execute(httpPost);         
               
		return response;
        
	}
        

	
	

	
	
	
/*	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub
			String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=httpclient&oq=%25E6%258E%25A5%25E5%258F%25A3%25E8%2587%25AA%25E5%258A%25A8%25E5%258C%2596%25E6%25B5%258B%25E8%25AF%2595%25E6%25A1%2586%25E6%259E%25B6%25E6%2590%25AD%25E5%25BB%25BA&rsv_pq=8249b32b00003337&rsv_t=830eqgeVSWohyH1PiShzsoVBdq54i%2Fgr5X6p0rbrociZ%2FAfdbaEqgkjCiQc&rqlang=cn&rsv_enter=1&inputT=1833&rsv_sug3=89&rsv_sug1=48&rsv_sug7=100&bs=%E6%8E%A5%E5%8F%A3%E8%87%AA%E5%8A%A8%E5%8C%96%E6%B5%8B%E8%AF%95%E6%A1%86%E6%9E%B6%E6%90%AD%E5%BB%BA";
		  CloseableHttpClient httpClient = HttpClientManager.getHttpClient();
		  
		  CloseableHttpResponse httpResponse = get(url, httpClient);
		  HttpEntity httpEntity = httpResponse.getEntity();
		  
		  System.out.println(EntityUtils.toString(httpEntity,"utf-8"));
		  
	}*/

}
