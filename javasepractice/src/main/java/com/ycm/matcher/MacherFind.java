package com.ycm.matcher;

import java.security.acl.Group;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MacherFind {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String data = "baike.xsoftlab.net";
		String regEx = "baike.*";

		Pattern pattern = Pattern.compile(regEx);
		Matcher macher = pattern.matcher(data);

		boolean b = macher.matches();
		// int startIndex = macher.start();

		System.out.println(b);
		
		groupTest();

	}

	public static void groupTest() {
		String regEx = "count(\\d+)(df)";
		String s = "count000dfdfsdffaaaa1";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(s);
		if (mat.find()) {
			System.out.println(mat.group(2));
		}
	}
}
