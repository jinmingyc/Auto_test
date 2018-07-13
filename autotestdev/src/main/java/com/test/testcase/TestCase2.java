package com.test.testcase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.crypto.Data;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.dom4j.DocumentException;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.test.beans.DataBean;
import com.test.config.ApiConfig;
import com.test.util.AssertUtil;
import com.test.util.ExcelUtil;
import com.test.util.HttpClientManager;


@Listeners({com.test.testnglistener.AssertionListener.class})
//@Listeners({com.test.testnglistener.TestngExtentReportListener.class})
public class TestCase2 extends BaseTest {

	/**
	 * 所有可执行用例的集合
	 */
	public List<DataBean> dataBeanList = new ArrayList<DataBean>();

	private Header[] publicHeaders;

	private String rootUrl;

	private boolean RootUrlEndWithSlash = false;

	private static HttpClient client;

	@BeforeSuite
	public void init() throws DocumentException {
		ApiConfig apiConfig = new ApiConfig("src\\main\\resources\\config.xml");
		// host
		rootUrl = apiConfig.getRootUrl();
		RootUrlEndWithSlash = rootUrl.endsWith("/");
		// set headers
		List<Header> headers = new ArrayList<Header>();
		apiConfig.getHeaders().forEach((key, value) -> {
			Header header = new BasicHeader(key, value);
			headers.add(header);
		});
		publicHeaders = headers.toArray(new Header[headers.size()]);
	}

	/**
	 * 根据指定的文件名、sheet名，读取数据
	 * 
	 * @param filePath
	 * @param fileName
	 */
	@Parameters({ "excelPath", "sheetName" })
	@BeforeTest
	public void readData(@Optional("api-data.xls") String filePath, @Optional("Sheet1") String sheetName) {
		logger.info("log4j==============readdata");
		dataBeanList = readExcelData(DataBean.class, filePath, sheetName);
	}

	/**
	 * 根据Excel配置，获取用例,可以扩展为支持多文件，多sheet
	 * 
	 * @param class1
	 *            需要转换的类
	 * @param filePath
	 *            配置用例的文件名
	 * @param sheetName
	 *            sheet名
	 * @return
	 */
	private List<DataBean> readExcelData(Class<DataBean> class1, String filePath, String sheetName) {

		// TODO Auto-generated method stub
		List<DataBean> temDataList = new ArrayList<DataBean>();

		temDataList = ExcelUtil.readExcel(class1, filePath, sheetName);
		// logger.info("log4j==============readExcelData"+temDataList.toString());

		return temDataList;
	}

	/**
	 * 过滤执行的用例
	 * 
	 * @param context
	 * @return
	 */
	@DataProvider(name = "apiDatas")
	public Iterator<Object[]> getApiData(ITestContext context) {
		List<Object[]> dataProvider = new ArrayList<Object[]>();
		for (DataBean data : dataBeanList) {
			if (data.isRun()) {
				dataProvider.add(new Object[] { data });
			}
		}
		return dataProvider.iterator();
	}

	/**
	 * 用例
	 * 
	 * @param dataBean
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test(dataProvider = "apiDatas")
	public void apiTest(DataBean dataBean) throws InterruptedException, IOException {
		logger.info("TestCase2--- test start ---");

		logger.info("TestCase2数据--- " + dataBean.toString());

		if (dataBean.getSleep() > 0) {
			logger.info(String.format("sleep %s seconds", dataBean.getSleep()));
			Thread.sleep(dataBean.getSleep() * 1000);
		}
		String apiParam = buildRequestParam(dataBean);

		HttpUriRequest method = parseHttpRequest(dataBean.getUrl(), dataBean.getMethod(), dataBean.getParam());

		//
		CloseableHttpResponse response = null;
		client = HttpClientManager.getHttpClient();
		try {
			response = (CloseableHttpResponse) client.execute(method);
			
			int statusCode=response.getStatusLine().getStatusCode();
				//if(statusCode!=200) {
					AssertUtil.AssertByCode(statusCode, 20000);
				//}
					

			logger.info("响应状态码：" + response.getStatusLine().getStatusCode());

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// long len = entity.getContentLength();

				String resContentType = response.getEntity().getContentType().getValue();
				logger.info("响应的类型：" + resContentType);
				//json 格式
				if (resContentType.contains("json")) {

					BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
					StringBuffer sb = new StringBuffer("");
					String line = "";
					String NL = System.getProperty("line.separator");
					while ((line = in.readLine()) != null) {
						sb.append(line + NL);
					}
					in.close();
					String content = sb.toString();

					logger.info("EntityUtils 内容：" + content);

				}

			} else {
				logger.info("EntityUtils 内容：空");
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			response.close();
		}

	}

	/**
	 * 通过url，method类型，参数列表，封装请求
	 * 
	 * @param url
	 *            Excel中短url
	 * @param method
	 *            Excel中方法类型
	 * @param param
	 *            Excel中参数
	 * @return
	 */
	private HttpUriRequest parseHttpRequest(String url, String method, String param) {
		// TODO Auto-generated method stub
		url = parseUrl(url);

		logger.info("请求的url：" + url);
		logger.info("请求的method：" + method);
		logger.info("请求的param串：" + param);
		if ("get".equals(method)) {
			HttpGet getMethod = new HttpGet(url);
			getMethod.setHeaders(publicHeaders);

			// 设置header
			return getMethod;
		} else {
			HttpPost postMethod = new HttpPost(url);
			postMethod.setHeaders(publicHeaders);

			HttpEntity entity = parseEntity(param);
			postMethod.setEntity(entity);

		}

		return null;
	}

	/**
	 * 拼接url
	 * 
	 * @param shortUrl
	 * @return
	 */
	private String parseUrl(String shortUrl) {
		// TODO Auto-generated method stub
		logger.info("传入url" + shortUrl + RootUrlEndWithSlash);

		if (shortUrl.startsWith("http")) {
			logger.info("shorturl 为http开头" + shortUrl);
			return shortUrl;
		}
		if (RootUrlEndWithSlash == true && RootUrlEndWithSlash == shortUrl.startsWith("/")) {
			shortUrl = shortUrl.replaceFirst("/", "");
		} else if (RootUrlEndWithSlash == false && RootUrlEndWithSlash == shortUrl.startsWith("/")) {
			shortUrl = "/" + shortUrl;
		} else {
			shortUrl = shortUrl;
		}
		logger.info("拼接url" + shortUrl);
		return rootUrl + shortUrl;

	}

	private HttpEntity parseEntity(String param) {
		// TODO Auto-generated method stub
		return null;
	}

	private String buildRequestParam(DataBean dataBean) {
		// TODO Auto-generated method stub
		return dataBean.getParam();
	}
}
