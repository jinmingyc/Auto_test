package com.ycm.file;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;

/*File类：用于描述一个文件或者文件夹的。
通过File对象我们可以读取文件或者文件夹的属性数据，如果我们需要读取文件的内容数据，那么我们需要使用IO流数技术。
IO流（Input Output）
IO技术主要的作用是用于解决设备与设备之间的数据传输问题。比如：硬盘-->内存          内存的数据-->硬盘上         把键盘的数据-->内存中
IO流分类：
    如果我们按照数据的流向划分：
        输入流：
        
        输出流：
    如果按照处理的单位划分：
        字节流：字节流读取的都是文件中的二进制数据，读取到的二进制数据不会经过任何的处理。
        
        字符流：字符流读取的数据是以字符为单位的。字符流也是读取文件中的二进制数据，不过会把这些二进制数据转换成我们能识别的字符。
            字符流 = 字节流 + 解码
        
判断使用输入流还是输出流的依据：    
    以当前程序作为参照物，观察数据是流入还是流出，如果流出则使用输出流，如果流入则使用输入流。
输入字节流：
-------------| InputStream 此抽象类是表示字节输入流的所有类的超类。 
------------------| FileInputStream 从文件系统中的某个文件中获得输入字节。
使用FileInputStream读取文件数据的步骤：
    1.找到目标文件
    2.建立数据的输入通道
    3.读取文件中的数据
    4.关闭资源，实际上就是释放资源
推荐使用方式4读取：使用缓冲数组 + 循环 配合读取。

注意事项：
    1.资源文件一旦使用完毕，应该立即释放资源文件
    2.读取文件时，循环读取时，缓冲数组每次都是覆盖，并不是先清空再读取。所以应该采用new String(buff, 0, length)方法而不是new String(buff)
    */
public class FileIutputSreamDemo {

	public static void main(String[] args) throws IOException {
		String path = "f:" + File.separator + "a.txt";
		/*
		 * File file = new File(path); file.createNewFile();
		 * System.out.println(file.getAbsolutePath());
		 * System.out.println(file.exists());
		 */

		 //readFileByByte(path);

		readFileByBuffer(path);

	}

	/**
	 * 按照单字节，循环读取内容
	 * 
	 * @param path:文件路径
	 * @throws IOException
	 */
	public static void readFileByByte(String path) throws IOException {
		long startTime = System.currentTimeMillis();
		File f = new File(path);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
			int data = 0;
			while ((data = fis.read()) != -1) {
				System.out.print((char) data);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fis.close();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("读取文件用时：" + (endTime - startTime));
	}

	private static void readFileByBuffer(String path) throws IOException {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		File f = new File(path);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
			byte[] buff = new byte[1024];
			int length = 0, count = 1;
			while ((length = fis.read(buff)) != -1) {
				System.out.println("使用缓存数组，第" + count + "次读取：" + new String(buff, 0, length));
				count++;

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fis.close();
		}
		long endTime = System.currentTimeMillis();

		System.out.println("使用缓存数组读取文件用时：" + (endTime - startTime));

	}
}
