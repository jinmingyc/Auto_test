package com.ycm.matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MacherDemo {

	public static void main(String[] args) {

		// 邮箱验证规则
		macherSring("service@xsoftlab.net", "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}");
		// 汉子
		findString("的骄傲了巨大飞机的离开房间代理费", "[\u0391-\uFFE5]+");
		
		
		macherSring("${fsdf}", "$(.*)");
	}

	private static void findString(String data, String regEx) {
		// TODO Auto-generated method stub
		Pattern pattern = Pattern.compile(regEx);
		Matcher macher = pattern.matcher(data);

		while (macher.find()) {
			System.out.println(macher.group(0));
		}
	}

	/**
	 * //邮箱
	 * 
	 * @param data
	 * @param regEx
	 */
	public static void macherSring(String data, String regEx) {

		// 编译正则表达式
		Pattern pattern = Pattern.compile(regEx);
		// 忽略大小写的写法
		// Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(data);
		// 字符串是否与正则表达式相匹配
		boolean rs = matcher.matches();
		System.out.println(rs);
	}
}