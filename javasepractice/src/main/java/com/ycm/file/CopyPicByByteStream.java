package com.ycm.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyPicByByteStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String SourcePic = "f:" + File.separator + "testpic.gif";
		String DesPic = "f:" + File.separator + "testpic2.gif";

		copyPic(SourcePic, DesPic);
	}

	private static void copyPic(String sourcePic, String desPic) {
		// TODO Auto-generated method stub
		File fSourPic=new File(sourcePic);
		File fDesPic = new File(desPic);
		FileInputStream fis =null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(fSourPic);
			fos = new FileOutputStream(fDesPic);
			byte [] buffer = new byte[1024*1024];
			int length = 0;
			while((length = fis.read(buffer))!=-1) {
				fos.write(buffer, 0, length);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("复制出错");
			e.printStackTrace();
		}finally {	
			try {
				if(fos!=null) {
					fos.close();					
					System.out.println("关闭输出流成功");
				}
			} catch (Exception e2) {
				System.out.println("关闭输出流失败");
				e2.printStackTrace();
			}finally {
				if(fis!=null) {
					try {
						fis.close();
						System.out.println("关闭输入流成功");
					} catch (Exception e2) {
						System.out.println("关闭输入流失败");
						e2.printStackTrace();
					}
				}
				
			}
		}	
	}	
}
	
