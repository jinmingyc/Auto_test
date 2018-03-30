package com.test.testcase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.crypto.Data;

import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.test.beans.DataBean;
import com.test.util.ExcelUtil;

public class TestCase2 extends BaseTest{

	/**
	 * 所有可执行用例的集合
	 */
	public List<DataBean> dataBeanList = new ArrayList<DataBean>();

	@BeforeSuite
	public void init() {

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
	 * @param class1 需要转换的类
	 * @param filePath	配置用例的文件名
	 * @param sheetName	sheet名
	 * @return
	 */
	private List<DataBean> readExcelData(Class<DataBean> class1, String filePath, String sheetName) {
	
		// TODO Auto-generated method stub
		List<DataBean> temDataList = new ArrayList<DataBean>();
		
		temDataList = ExcelUtil.readExcel(class1,filePath,sheetName);	
		//logger.info("log4j==============readExcelData"+temDataList.toString());
		
		return temDataList;
	}
	
	/**
	 * 过滤执行的用例
	 * @param context
	 * @return
	 */
	@DataProvider(name = "apiDatas")
	public Iterator<Object[]> getApiData(ITestContext context){
		List<Object[]> dataProvider = new ArrayList<Object[]>();
		for(DataBean data:dataBeanList) {
			if(data.isRun()) {
				dataProvider.add(new Object[] {data});
			}
		}
		return dataProvider.iterator();
	}
	
	@Test(dataProvider = "apiDatas")
	public void apiTest(DataBean dataBean) throws InterruptedException {
		logger.info("TestCase2--- test start ---");
		
		if (dataBean.getSleep() >0) {
			logger.info(String.format("slep %s seconds",dataBean.getSleep()));
			Thread.sleep(dataBean.getSleep()*1000);
		}
		String apiParam = buildRequestParam(dataBean);
	}

	private String buildRequestParam(DataBean dataBean) {
		// TODO Auto-generated method stub
		return null;
	}
}






















