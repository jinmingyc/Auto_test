package com.test.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

import org.apache.commons.collections4.SetValuedMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONObject;
import com.test.beans.DataBean;

public class ExcelUtil {

	public static String filepath = null;
	public static String sheetname = null;

	/**
	 * @param tid
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 *             根据首列内容匹配（首列唯一），将首列的内容按list返回
	 */
	public static ArrayList<String> getApiData(String tid, String filename) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub

		ArrayList<String> url = new ArrayList<String>();
		// －－－－－－－－－－－－从xls读出数据

		// 获取文件路径
		URL path = ExcelUtil.class.getClassLoader().getResource(filename);
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
	 * @param interfaceName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 *             读取表格，返回变量-值对
	 */
	public static Object[][] getApiDataObject(String interfaceName, String filename)
			throws FileNotFoundException, IOException {

		// 获取文件路径
		URL path = ExcelUtil.class.getClassLoader().getResource(filename);
		// System.out.println(path);
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
				if (row1.getCell(0).toString().equals(interfaceName.toString())) {
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

		/*
		 * ArrayList<String> url = getApiData("a","2.xls");
		 * System.out.println(url.toString());
		 */

		Object[][] array1 = getApiDataObject("interfaceName1", "interfaceName1.xls");
		for (int i = 0; i < array1.length; i++) {
			for (int j = 0; j < array1[i].length; j++) {
				System.out.println(array1[i][j].toString());
			}

		}

	}

	/**
	 * 获取指定sheet的表数据
	 * 
	 * @param class1
	 * @param filePath2
	 * @param sheetName2
	 * @return
	 */
	public static List<DataBean> readExcel(Class<DataBean> class1, String filePath, String sheetName2) {
		if (null == filePath || "".equals(filePath)) {
			return null;
		}
		InputStream is;
		Workbook wb;

		try {
			is = new FileInputStream(filePath);
			if (filePath.endsWith(".xls")) {
				wb = new HSSFWorkbook(is);
			} else {
				wb = new XSSFWorkbook(new FileInputStream(new File(filePath)));
			}
			is.close();
			return transToObject(class1, wb, sheetName2);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("转换excel文件失败：" + e.getMessage());
		}
		// return null;
	}

	private static List<DataBean> transToObject(Class<DataBean> class1, Workbook wb, String sheetName2)
			throws IllegalArgumentException, InvocationTargetException, InstantiationException {
		// TODO Auto-generated method stub
		List<DataBean> list = new ArrayList<DataBean>();
		org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheet(sheetName2);
		Row firstRow = sheet.getRow(0);

		if (null == firstRow) {
			return list;
		}

		List<Object> heads = getRow(firstRow);
		// 添加sheetName字段，用于封装至bean中，与bean中的字段相匹配。
		heads.add("sheetName");
		Map<String, Method> headMethod = getSetMethod(class1, heads);

		for (int rowNum = 1; rowNum < sheet.getLastRowNum(); rowNum++) {
			Row row = sheet.getRow(rowNum);

			try {
				if (null == row) {
					continue;
				}
				DataBean t = class1.newInstance();
				List<Object> data = getRow(row);
				// 如果发现表数据的列数小于表头的列数，则自动填充为null，最后一位不动，用于添加sheetName数据
				while (data.size() + 1 < heads.size()) {
					data.add("");
				}
				data.add("");
				SetValued(t, data, heads, headMethod);
				list.add(t);

			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
		// return null;
	}

	/**
	 * ???????????????
	 * 
	 * @param obj
	 * @param data
	 * @param heads
	 * @param methods
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	private static void SetValued(DataBean obj, List<Object> data, List<Object> heads, Map<String, Method> methods)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (Map.Entry<String, Method> entry : methods.entrySet()) {
			Object value = "";
			int dataIndex = heads.indexOf(entry.getKey());
			if (dataIndex < data.size()) {
				value = data.get(heads.indexOf(entry.getKey()));
			}
			Method method = entry.getValue();
			Class<?> param = method.getParameterTypes()[0];
			if (String.class.equals(param)) {
				method.invoke(obj, value);
			} else if (Integer.class.equals(param) || int.class.equals(param)) {
				if (value.toString() == "") {
					value = 0;
				}
				method.invoke(obj, new BigDecimal(value.toString()).intValue());
			} else if (Long.class.equals(param) || long.class.equals(param)) {
				if (value.toString() == "") {
					value = 0;
				}
				method.invoke(obj, new BigDecimal(value.toString()).longValue());
			} else if (Short.class.equals(param) || short.class.equals(param)) {
				if (value.toString() == "") {
					value = 0;
				}
				method.invoke(obj, new BigDecimal(value.toString()).shortValue());
			} else if (Boolean.class.equals(param) || boolean.class.equals(param)) {
				method.invoke(obj, Boolean.valueOf(value.toString()) || value.toString().toLowerCase().equals("y"));
			} else if (JSONObject.class.equals(param) || JSONObject.class.equals(param)) {
				method.invoke(obj, JSONObject.parseObject(value.toString()));
			} else {
				// Date
				method.invoke(obj, value);
			}
		}

	}

	private static Map<String, Method> getSetMethod(Class<DataBean> class1, List<Object> heads) {
		Map<String, Method> map = new HashMap<String, Method>();
		Method[] methods = class1.getMethods();

		for (Object head : heads) {
			for (Method method : methods) {
				if (method.getName().toLowerCase().equals("set" + head.toString().toLowerCase())
						&& method.getParameterTypes().length == 1) {
					map.put(head.toString(), method);
					break;
				}
			}
		}
		return map;

		// return null;
	}

	/**
	 * 返回行内容
	 * 
	 * @param row
	 * @return
	 */
	private static List<Object> getRow(Row row) {
		// TODO Auto-generated method stub
		List<Object> cells = new ArrayList<Object>();
		if (null != row) {
			for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
				Cell cell = row.getCell(cellNum);
				cells.add(getVaule(cell));
			}
			return cells;
		}
		return cells;
	}

	/**
	 * 返回不同类型数据
	 * 
	 * @param cell
	 * @return
	 */
	private static String getVaule(Cell cell) {
		if (null == cell) {
			return "";
		} else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
			// 返回布尔类型值
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellTypeEnum() == CellType.NUMERIC) {
			// 返回数字类型值
			return String.valueOf(cell.getNumericCellValue());
		} else {
			// 返回字符串类型值
			return String.valueOf(cell.getStringCellValue());
		}
	}
}
