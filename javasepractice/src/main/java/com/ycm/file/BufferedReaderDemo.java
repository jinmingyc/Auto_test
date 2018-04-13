package com.ycm.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderDemo {
	/*
	 * 输入字符流： ----------------| Reader 用于读取字符流的抽象类。 ------------------------|
	 * FileReader 用于读取文件的输入字符流 ------------------------| BufferedReader
	 * 缓冲输入字符流。目的是为了提高读取文件字符的效率和扩展了FileReader的功能。 其实该类内部也是维护了一个字符数组。
	 * 注意：缓冲流都不具备读写文件的能力。
	 */

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String path = "f:" + File.separator + "a.txt";

		readDataByLine(path);

		//readDataByByte(path);
	}

	private static void readDataByByte(String path) throws IOException {
		File f = new File(path);
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String content = null;
			int data;
			while ((data = br.read())!=-1) {
				System.out.print((char)data);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	private static void readDataByLine(String path) {
		File f = new File(path);
		FileReader fr= null;
		BufferedReader br= null;		
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String content = null;
			while ((content = br.readLine()) != null) {
				System.out.println(content);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					if(fr!=null) {
						try {
							fr.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}

	}

}
