package com.ycm.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*输出字节流：
------------| OutputStream 此抽象类是表示输出字节流的所有类的超类。输出流接受输出字节并将这些字节发送到某个接收器。 
------------------| FileOutputStream 文件输出流是用于将数据写入 File 或 FileDescriptor 的输出流
FileOutputSream使用步骤：
    1.找到目标文件
    2.建立数据 的输出通道
    3.把数据输出
    4.关闭资源
    
FileOutputSream要注意的细节：
    1.使用FileOutputStream(File file)写入数据的时候，如果目标文件存在，则在创建FileOutputStream输出通道的时候会自动创建目标文件。
    2.使用FileOutputStream(File file)写入数据的时候，如果目标文件不存在，则在创建FileOutputSream输出通道的时候，则会先清空目标文件中原有的数据，然后再写入数据。
    3.使用FileOutputStream(File file, boolean append)写入数据的时候，是在原来数据基础上追加数据，第二个参数为true。
    4.使用FileOutputStream的write(int i)方法写数据的时候，虽然接收的是一个int类型（int 4字节  32位）的数据，但是真正写出的只是一个字节的数据，只是把低八位的二进制数据写出，其他二十四位数据全部丢弃。*/

public class FileOutputSreamDemo {
	
	public static void main(String args[]) throws IOException {
		String path = "f:"+File.separator+"b.txt";
		
		writeDataByByte(path);
		
		writeDataByByteArray(path);
	}

	private static void writeDataByByte(String path) {
		// TODO Auto-generated method stub
		File f = new File(path);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(f);
			fos.write('a');
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private static void writeDataByByteArray(String path) throws IOException {
		// TODO Auto-generated method stub
		
		File f= new File(path);
		FileOutputStream fos = null;
		String data= "lkdsjfoijfdlkfndklgjdoigjd";
		try {
			fos = new FileOutputStream(f,true);
			fos.write(data.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			fos.close();
		}
		
	}

}
