package com.test.dataprovider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.testng.*;

import org.testng.annotations.Test;

import com.test.util.ExcelEngine;

public class DataProvider {
	@org.testng.annotations.DataProvider
	public static Object[][] dpa() throws FileNotFoundException, IOException {

		return ExcelEngine.getApiDataObject("a", "1.xls");

	}

	@org.testng.annotations.DataProvider
	public static Object[][] dpb() throws FileNotFoundException, IOException {

		return ExcelEngine.getApiDataObject("b", "1.xls");

	}

	@Test(dataProvider = "dpb", dataProviderClass = com.test.dataprovider.DataProvider.class)
	public void fb(Map m) {

		System.out.println("接口名称:" + m.get("interfaceName") + ";参数一:" + m.get("name") + ";参数二:" + m.get("passwd"));

	}
}
