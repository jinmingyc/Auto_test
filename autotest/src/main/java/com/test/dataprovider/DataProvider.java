package com.test.dataprovider;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.test.util.ExcelUtil;


public class DataProvider {
	
	@org.testng.annotations.DataProvider
	public static Object[][] dpa() throws FileNotFoundException, IOException {

		return ExcelUtil.getApiDataObject("interfaceName1", "interfaceName1.xls");

	}
	
	
}
