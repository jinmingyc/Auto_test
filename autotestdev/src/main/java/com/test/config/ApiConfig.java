package com.test.config;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ApiConfig {

	private Map<String, String> headers = new HashMap<String, String>();
	private String rootUrl;

	public ApiConfig(String path) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(path);
		Element rootElement = document.getRootElement();
		
		rootUrl = rootElement.element("rootUrl").getTextTrim();

		@SuppressWarnings("unchecked")
		List<Element> headerElements = rootElement.element("headers").elements("header");
		headerElements.forEach((ele) -> {
			headers.put(ele.attributeValue("name").trim(), ele.attributeValue("value").trim());
		});
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public String getRootUrl() {
		// TODO Auto-generated method stub
		return rootUrl;
	}
	
	
	// E:\git_hub\Auto_test\autotest\src\main\resources
	public static void main(String[] args) throws DocumentException {
		// TODO Auto-generated method stub
		ApiConfig ac = new ApiConfig("src\\main\\resources\\config.xml");

		Iterator<Map.Entry<String, String>> it = ac.headers.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			System.out.println(entry.getKey() + "==========" + entry.getValue());
		}

	}

	

}
