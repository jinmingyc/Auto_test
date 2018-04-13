package com.test.httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientTest2 {
	private static CloseableHttpClient httpClient;
	private static CloseableHttpResponse httpResponse;
	private static URI uri;
	private static HttpGet httpGet;
	private static HttpPost httpPost;

	/**
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	public static void doGetTest() throws URISyntaxException, IOException {
		httpClient = getHttpclient();
		uri = new URIBuilder().setScheme("http").setHost("172.26.14.232:8181").setPath("/lsms_xin").build();
		System.out.println(uri); // 输出url
		httpGet = new HttpGet(uri);
		// 配置
		configHttpClient(httpGet);

		httpResponse = httpClient.execute(httpGet);
		try {
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				System.out.println("请求成功，返回内容" + EntityUtils.toString(httpResponse.getEntity(), Consts.UTF_8));
			} else {
				System.out.println("请求失败，返回内容" + EntityUtils.toString(httpResponse.getEntity(), Consts.UTF_8));
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			httpResponse.close();
			shutDownHttpClient(httpClient);
		}
	}

	/**
	 * @return
	 * @throws IOException
	 */
	public static CloseableHttpResponse doPostTest() throws IOException {

		CloseableHttpResponse httpResponse = null;
		httpClient = getHttpclient();
		HttpPost httpPost = new HttpPost("http://172.26.14.234:8181/lsms/io/Terminal/login.do");

		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("s_echarts", "534809"));
		list.add(new BasicNameValuePair("s_ajaxCallType", "true"));
		list.add(new BasicNameValuePair("s_userName", "duanyan.gd"));
		list.add(new BasicNameValuePair("s_password", "96e79218965eb72c92a549dd5a330112"));
		list.add(new BasicNameValuePair("s_androidRegId	", "100d855909648988c9e"));

		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list, Consts.UTF_8);
		formEntity.setContentType("application/x-www-form-urlencoded");
		formEntity.setContentEncoding("UTF-8");
		httpPost.setEntity(formEntity);

		// 设置请求头
		setHttpPostHeater(httpPost);
		// System.out.print(formEntity.toString());
		// System.out.print(httpPost.toString());
		try {
			System.out.println(httpPost.getURI().toString());
			System.out.println(EntityUtils.toString(formEntity));

			httpResponse = httpClient.execute(httpPost);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// httpResponse.close();
			// httpclient.close();
		}
		return httpResponse;

	}

	/**
	 * 获取httpClient
	 * 
	 * @return
	 */
	public static CloseableHttpClient getHttpclient() {
		if (httpClient == null) {
			httpClient = HttpClients.createDefault();
		}
		return httpClient;
	}

	/**
	 * 返回链接池的httpClient
	 * 
	 * @return
	 */
	public static CloseableHttpClient getHttpClientWithConnManager() {
		if (httpClient == null) {
			httpClient = HttpClients.custom().setConnectionManager(getConnManager()).build();
		}
		return httpClient;
	}

	/**
	 * 关闭httpClient
	 * 
	 * @param httpClient
	 * @throws IOException
	 */
	public static void shutDownHttpClient(CloseableHttpClient httpClient) throws IOException {
		if (httpClient != null) {
			httpClient.close();
		}
	}

	/**
	 * 获取链接池
	 * 
	 * @return
	 */
	public static PoolingHttpClientConnectionManager getConnManager() {

		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		connManager.setMaxTotal(200); // Increase max total connection to 200
		connManager.setDefaultMaxPerRoute(20); // Increase default max connection per route to 20

		return connManager;
	}

	/**
	 * 配置httpget
	 * 
	 * @param httpGet
	 */
	public static void configHttpClient(HttpGet httpGet) {
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(2000)
				.setConnectionRequestTimeout(3000).build();
		httpGet.setConfig(requestConfig);
	}

	/**
	 * 设置post请求header
	 * 
	 * @param httpPost
	 */
	private static void setHttpPostHeater(HttpPost httpPost) {
		// TODO Auto-generated method stub
		httpPost.setHeader("Connection", "keep-alive");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
	}

	public static void main(String[] args) throws URISyntaxException, ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		// doGetTest();

		httpResponse = doPostTest();
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			System.out.println("请求成功，返回内容" + EntityUtils.toString(httpResponse.getEntity(), Consts.UTF_8));
		} else {
			System.out.println("请求失败，返回内容" + EntityUtils.toString(httpResponse.getEntity(), Consts.UTF_8));
		}

	}
}
