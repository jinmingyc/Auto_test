package com.test.testcase;

import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import com.test.beans.DataBean;
import com.test.util.ExcelUtil;

public class TestCase2 {

	/**
	 * 所有可执行用例的集合
	 */
	public List<DataBean> dataBeanList = new ArrayList<DataBean>();

	@Test
	public void f() {
	}

	@BeforeSuite
	public void init() {

	}

	/**
	 * 根据指定的文件名、sheet名，读取数据
	 * 
	 * @param filePath
	 * @param fileName
	 */
	@BeforeTest
	public void readData(@Optional("api-data.xls") String filePath, @Optional("Sheet1") String sheetName) {
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
		
		return temDataList;
	}
	
	
}






















