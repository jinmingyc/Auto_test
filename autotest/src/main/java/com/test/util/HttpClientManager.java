package com.test.util;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientManager {
	public static CloseableHttpClient httpClient;

	private HttpClientManager() {

	}

	public static CloseableHttpClient getHttpClient() {
		if (httpClient != null) {
			httpClient = HttpClients.createDefault();
		}
		return httpClient;
	}

	/*
	 * public static CloseableHttpClient getHttpClientWithConfig() {
	 * if(httpClient==null) { httpClient =
	 * HttpClients.custom().setConnectionManager(connManager);
	 * 
	 * } }
	 */

}
