package com.ycm.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*字节流：字节流读取的是文件中的二进制数据，读到的数据并不会帮你转换成你看得懂的字符。
字符流：字符流会把读取到的二进制数据进行对应的编码与解码工作。   字符流 = 字节流 + 编码（解码）
输入字符流：
----------------| Reader 用于读取字符流的抽象类。
------------------------| FileReader 用于读取文件的输入字符流
FileReader的用法：
    1.找到目标文件 
    2.建立数据的输入通道
    3.读取文件数据
    4.关闭资源*/

public class FileReaderDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "f:" + File.separator + "a.txt";
		
		//readDataByChar(path);
		
		readDataByBuffer(path);
	}

	private static void readDataByBuffer(String path) {
		// TODO Auto-generated method stub
		File f =new File(path);
		FileReader fr = null;
		try {
			fr = new FileReader(f);
			char [] buffer = new char[1024*1024];
			int length = 0;
			while((length = fr.read(buffer))!=-1) {
				System.out.print(new String(buffer,0,length));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void readDataByChar(String path) {
		File f =new File(path);
		FileReader fr = null;
		
		try {
			fr= new FileReader(f);
			int content = 0;
			while((content=fr.read())!=-1) {
				System.out.print((char)content);
			}
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
