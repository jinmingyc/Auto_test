package com.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class ExcelEngine {

	public static String filepath = null;
	public static String sheetname = null;

	/**
	 * @param tid
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 *             根据首列内容匹配（首列唯一），将首列的内容按list返回
	 */
	public static ArrayList<String> getApiData(String tid,String filename) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		ArrayList<String> url = new ArrayList<String>();
		// －－－－－－－－－－－－从xls读出数据

		// 获取文件路径
		URL path = ExcelEngine.class.getClassLoader().getResource(filename);
		System.out.println(path);
		File file = new File(path.getFile());

		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = sheet.getRow(0);
		// 获得EXCEL行数
		int rowLength = sheet.getLastRowNum();
		// 获得Excell列数
		int columnLength = row.getLastCellNum();

		for (int i = 0; i < rowLength; i++) {
			HSSFRow row1 = sheet.getRow(i);

			HSSFCell cell1 = row1.getCell(0);
			if (cell1 != null) {
				if (cell1.toString().equals(tid)) {
					for (int j = 0; j < columnLength; j++) {
						// System.out.println(row1.getCell(j).toString());
						url.add(row1.getCell(j).toString());
					}
				} else {
					// System.out.println(cell1.toString());
				}
			} else {
				// System.out.println("第"+i+"行首列是空");
			}

		}
		return url;

	}

	// }

	// 参数二维数组
	public static Object[][] paramArray;
	public static ArrayList<Map<String, String>> paramMapList = new ArrayList();

	/**
	 * @param tid
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 *             读取表格，返回变量-值对
	 */
	public static Object[][] getApiDataObject(String tid,String filename) throws FileNotFoundException, IOException {
		
		// 获取文件路径
				URL path = ExcelEngine.class.getClassLoader().getResource(filename);
				//System.out.println(path);
				File file = new File(path.getFile());
				
		// 1.Excel对象
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
		// 2.Excel工作簿对象
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		// 3.excel工作表对象
		HSSFSheet sheet = wb.getSheet("Sheet1");
		// 总行数
		int trLenth = sheet.getLastRowNum();
		// 4.得到工作表的行
		HSSFRow row = sheet.getRow(0);
		// 总列数
		int tdLength = row.getLastCellNum();

		for (int i = 0; i < trLenth; i++) {

			HSSFRow row1 = sheet.getRow(i);

			// 判断第一列是否为空且与传入值相等，此时将整行内容传入map
			Map<String, String> paramMap = new HashMap();

			if (row1.getCell(0) != null) {
				if (row1.getCell(0).toString().equals(tid.toString())) {
					for (int j = 0; j < tdLength; j++) {
						// excel中，参数空处理
						if (row1.getCell(j) != null) {
							paramMap.put(row.getCell(j).toString(), row1.getCell(j).toString());
						} else {
							paramMap.put(row.getCell(j).toString(), null);
						}

					}
				}
			} else {
				System.out.println("首列 cell is null");
			}

			// 非空行
			if (paramMap.size() > 0) {
				paramMapList.add(paramMap);
			}

		}

		// 利用paramMapList初始化二维数组
		if (paramMapList.size() > 0) {
			paramArray = new Object[paramMapList.size()][];
			for (int i = 0; i < paramMapList.size(); i++) {
				paramArray[i] = new Object[] { paramMapList.get(i) };
			}

		} else {
			System.out.println("paramMapList为空");
		}

		// if (paramArray != null) {
		return paramArray;
		// }else {
		// System.out.print("接口信息为空");
		// return paramArray;
		// }

		// System.out.print(ApiData.toString() + "\t\t\t");

	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		// getExcelAsFile("Sheet1");

		ArrayList<String> url = getApiData("a","2.xls");
		System.out.println(url.toString());
		
		
		
		
	}

}
