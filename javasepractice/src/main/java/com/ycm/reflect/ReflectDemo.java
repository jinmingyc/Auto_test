package com.ycm.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*Class类提供了四个public方法，用于获取某个类的构造方法。
Constructor getConstructor(Class[] params)     根据构造函数的参数，返回一个具体的具有public属性的构造函数
Constructor getConstructors()     返回所有具有public属性的构造函数数组
Constructor getDeclaredConstructor(Class[] params)     根据构造函数的参数，返回一个具体的构造函数（不分public和非public属性）
Constructor getDeclaredConstructors()    返回该类中所有的构造函数数组（不分public和非public属性）
四种获取成员方法的方法
Method getMethod(String name, Class[] params)    根据方法名和参数，返回一个具体的具有public属性的方法
Method[] getMethods()    返回所有具有public属性的方法数组
Method getDeclaredMethod(String name, Class[] params)    根据方法名和参数，返回一个具体的方法（不分public和非public属性）
Method[] getDeclaredMethods()    返回该类中的所有的方法数组（不分public和非public属性）
四种获取成员属性的方法
Field getField(String name)    根据变量名，返回一个具体的具有public属性的成员变量
Field[] getFields()    返回具有public属性的成员变量的数组
Field getDeclaredField(String name)    根据变量名，返回一个成员变量（不分public和非public属性）
Field[] getDelcaredField()    返回所有成员变量组成的数组（不分public和非public属性）*/

public class ReflectDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Integer.MAX_VALUE);
		
		Class<?> class1 = getClassByThreeMethod();

		getAllMethod(class1);

		getAllInterface(class1);

		getSuperClass(class1);

		getAllConstructors(class1);

		getAllFields(class1);
		// 默认构造器，创建实例
		Person p = getNewInstance(class1);
		// 一个参数构造器，创建实例
		Person p1 = getNewInstance(class1, String.class);
		Person p2 = getNewInstance(class1, String.class, String.class);

		pracReflect1(p);
		pracReflect2(class1);

		pracReflect3(class1);

	}

	private static Person getNewInstance(Class<?> class1, Class<String> class2, Class<String> class3) {
		// TODO Auto-generated method stub
		Person p = null;

		try {
			Constructor constructor = class1.getConstructor(class2, class3);
			p = (Person) constructor.newInstance("120", "person120");
			System.out.println("========================两个参数的构造器，构造的Person" + p.toString());
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

		return p;
	}

	private static Person getNewInstance(Class<?> class1, Class<String> class2) {
		// TODO Auto-generated method stub
		Person p = null;
		try {
			Constructor constructor = class1.getConstructor(class2);
			p = (Person) constructor.newInstance("110");
			System.out.println("========================一个参数的构造器，构造的Person" + p.toString());
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

		return p;
	}

	public static void pracReflect3(Class<?> class1) {
		Person person = getNewInstance(class1);
		try {
			Field fId = class1.getDeclaredField("id");
			fId.setAccessible(true);
			// setId方法
			Method method = class1.getDeclaredMethod("setId", String.class);
			method.invoke(person, "102");
			// setName方法
			Method mSetName = class1.getDeclaredMethod("setName", String.class);
			mSetName.invoke(person, "person3");
			// getId方法
			Method mGetName = class1.getMethod("getName");
			String name = (String) mGetName.invoke(person);

			System.out.println("========================字段赋值name:" + name);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
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

	/**
	 * 通过反射获取对象字段属性，并且赋值
	 * 
	 * @param class1
	 */
	public static void pracReflect2(Class<?> class1) {

		Person person = getNewInstance(class1);

		try {
			Field fId = class1.getDeclaredField("id");
			fId.setAccessible(true);
			fId.set(person, "101");

			System.out.println("========================字段赋值Id:" + fId.get(person));
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 通过反射，获取对象实例，并且操作对象的方法
	 * 
	 * @param p
	 */
	public static void pracReflect1(Person p) {

		p.setId("100");
		p.setName("person1");

		System.out.println("========================Id:" + p.getId() + "name:" + p.getName());
	}

	/**
	 * 获取类的实例
	 * 
	 * @param class1
	 * @return
	 */
	public static Person getNewInstance(Class<?> class1) {
		Person person = null;
		try {
			Object object = class1.newInstance();
			person = (Person) object;

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return person;
	}

	/**
	 * 获取所有字段
	 * 
	 * @param class1
	 */
	public static void getAllFields(Class<?> class1) {
		/*
		 * getDeclaredFields()获得某个类的所有申明的字段，即包括public、private和proteced，但是不包括父类的申明字段。
		 * getFields()获得某个类的所有的公共（public）的字段，包括父类。
		 */

		Field[] field = class1.getDeclaredFields();
		System.out.println("========================类的所有字段：");
		for (Field f : field) {
			System.out.println(f);
		}
	}

	/**
	 * 获取所有构造函数
	 * 
	 * @param class1
	 */
	public static void getAllConstructors(Class<?> class1) {
		Constructor<?>[] constructors = class1.getConstructors();
		System.out.println("========================类的构造函数：");
		for (Constructor<?> c : constructors) {
			System.out.println(c);
		}
	}

	/**
	 * 获取父类
	 * 
	 * @param class1
	 */
	public static void getSuperClass(Class<?> class1) {
		Class<?> superClass = class1.getSuperclass();
		System.out.println("========================类的父类：");
		System.out.println(superClass);
	}

	/**
	 * 获取类实现的所有接口
	 * 
	 * @param class1
	 */
	public static void getAllInterface(Class<?> class1) {
		Class<?>[] interfaces = class1.getInterfaces();
		System.out.println("========================类实现的接口：");
		for (Class<?> c : interfaces) {
			System.out.println(c);
		}
	}

	/**
	 * 获取类的所有方法
	 * 
	 * @param class1
	 */
	public static void getAllMethod(Class<?> class1) {
		Method[] methods = class1.getMethods();
		System.out.println("========================类的所有方法：");
		for (Method m : methods) {
			System.out.println(m);
		}
	}

	/**
	 * 通过三种方式获取class
	 * 
	 * @return
	 */
	public static Class<?> getClassByThreeMethod() {
		// 第一种--常用
		try {
			Class<?> class1 = Class.forName("com.ycm.reflect.Person");

			System.out.println(class1);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 第二种
		Class<?> class2 = Person.class;
		System.out.println(class2);
		// 第三种
		Person person = new Person();
		Class<?> class3 = person.getClass();
		System.out.println(class3);

		return class3;
	}

}
