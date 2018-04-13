package com.ycm.file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {
/*	输出字符流：
	----------------|Writer 输出字符流的基类。抽象类。
	----------------------|FileWriter 向文件输出数据的输出字符流
	FileWriter的使用步骤：
	    1.找到目标文件
	    2.建立数据通道 
	    3.准备数据，把数据写出
	    4.关闭资源
	FileWriter要注意的事项：
	    1.使用FileWriter写数据的时候，FileWriter内部是维护了一个1024个字符数组，写数据的时候会先写入到它内部维护的字符数组中，如果需要把数据真正写到硬盘上，需要调用flush()方法或者close()方法或者填满了内部的 字符数组。
	    2.使用FileWriter写数据的时候，如果目标不存在会自动创建文件。
	    3.使用FileWriter写数据的时候，如果目标文件已经存在了，默认情况会先清空文件中的数据，再写入数据。如果需要在原来的基础上追加数据，需要使用“new FileWriter(Filefile, boolean append)”，第二个参数为true。
*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "f:" + File.separator + "b.txt";
		
		writeData(path);
	}

private static void writeData(String path) {
	File f= new File(path);
	FileWriter fw = null;
	
	try {
		fw= new FileWriter(f,true);
		String data = "测试fljdsflkdfjmklfjdilfjdslkfj";
		fw.write(data);
		fw.flush();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		if(fw!=null) {
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

}
