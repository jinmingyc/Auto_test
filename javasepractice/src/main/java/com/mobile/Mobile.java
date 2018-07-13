package com.mobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Mobile {

	public static String PID(String PackageName) throws IOException {

		String PID = null;
		Runtime runtime = Runtime.getRuntime();
		Process proc = runtime.exec("adb shell ps |grep " + PackageName);
		try {
			if (proc.waitFor() != 0) {
				System.err.println("exit value = " + proc.exitValue());
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			StringBuffer stringBuffer = new StringBuffer();
			String line = null;
			while ((line = in.readLine()) != null) {
				stringBuffer.append(line + " ");
			}
			String str1 = stringBuffer.toString();
			String str2 = str1.substring(str1.indexOf(" " + PackageName) - 46, str1.indexOf(" " + PackageName));
			String str3 = str2.substring(0, 7);
			str3 = str3.trim();
			PID = str3;
		} catch (InterruptedException e) {
			System.err.println(e);
		} finally {
			try {
				proc.destroy();
			} catch (Exception e2) {
			}
		}

		return PID;
	}

	// 获取下载流量
	public static double GetFlow(String PackageName) throws IOException {
		double FlowSize = 0;
		String Pid = PID(PackageName);
		try {
			Runtime runtime = Runtime.getRuntime();
			Process proc = runtime.exec("adb shell cat /proc/" + Pid + "/net/dev");
			try {
				if (proc.waitFor() != 0) {
					System.err.println("exit value = " + proc.exitValue());
				}
				BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				StringBuffer stringBuffer = new StringBuffer();
				String line = null;
				while ((line = in.readLine()) != null) {
					stringBuffer.append(line + " ");

				}
				String str1 = stringBuffer.toString();
				String str2 = str1.substring(str1.indexOf("wlan0:"), str1.indexOf("wlan0:") + 90);
				String str4 = str2.substring(7, 16);
				str4 = str4.trim();
				int Flow = Integer.parseInt(str4);
				FlowSize = Flow / 1024;

			} catch (InterruptedException e) {
				System.err.println(e);
			} finally {
				try {
					proc.destroy();
				} catch (Exception e2) {
				}
			}
		} catch (Exception StringIndexOutOfBoundsException) {

		}

		return FlowSize;
	}

	//获取每秒下载流量
	public static double Flow(String PackageName) throws IOException, InterruptedException
    {

        double Flow1=GetFlow(PackageName);
        Thread.sleep(1000);
        double Flow=GetFlow(PackageName)-Flow1;
        //System.out.println(GetFlow()-Flow1);
        return Flow ;

    }
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String packageName = "com.tc.mobile";
		String pid = null;
		double FlowSize = 0;
		pid = PID(packageName);
		
		
		FlowSize=GetFlow(packageName);

		System.out.println(pid+"---------"+FlowSize);

	}

}
