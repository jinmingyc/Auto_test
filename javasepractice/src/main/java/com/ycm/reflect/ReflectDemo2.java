package com.ycm.reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Properties;

public class ReflectDemo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// System.out.println(System.getProperty("user.dir"));

		String className = getValue("className");
		String methodName = getValue("methodName");

		try {
			Class<?> stuClass = Class.forName(className);
			Method method = stuClass.getDeclaredMethod(methodName);
			method.invoke(stuClass.getConstructor().newInstance());

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getValue(String key) {
		String value = null;
		Properties p = new Properties();
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File("src/main/resources/pro.tx"));
			p.load(fis);
			fis.close();
			value = p.getProperty(key);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

}
