package com.test.util;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

/**
 * JAVA结合testng断言verify(断言失败不中断继续执行)
原理：
1.自已构造一个断言类，把Assert.assertEquals给try catch住。
2.利用testng的监听类在测试方法运行结束后进行分析。
 * @author yuanchengming
 *
 */
public class AssertUtil {
	
	public static boolean flag = true;
	public static List<Error> errors  = new ArrayList<Error>();
	
	public static void AssertByCode(int actualCode,int expectedCode) {
		
		
		try {
			Assert.assertEquals(actualCode, expectedCode,"返回状态码预期为："+expectedCode+"实际状态码为："+actualCode);
		} catch (Error e) {
			errors.add(e);
			flag = false;
		}
		
	}

}
