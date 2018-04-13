package com.ycm.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Login {

	static Scanner scanner = new Scanner(System.in);
	static String path = "f:" + File.separator + "user.txt";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		while (true) {
			System.out.println("please enter the key:b(login) or a(register)");
			String opt = scanner.next();
			if (opt.equalsIgnoreCase("A")) {
				register();

				showAll();
			} else if (opt.equalsIgnoreCase("B")) {
				login();
			} else {
				System.out.println("retry");
			}
		}
	}

	private static void login() throws IOException {

		System.out.println("请输入登录用户名：");
		String userName = scanner.next();
		System.out.println("请输入登录密码：");
		String passwd = scanner.next();

		File f = new File(path);
		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		String data = null;
		boolean flag = false;
		while ((data = br.readLine()) != null) {
			String[] split = data.split(",");
			if (userName.equals(split[0]) & passwd.equals(split[1])) {
				flag = true;
				break;
			}
		}

		if (flag) {
			System.out.println("登录成功");
		} else {
			System.out.println("登录失败");
		}

	}

	private static void showAll() throws IOException {
		// TODO Auto-generated method stub
		File f = new File(path);
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String data = null;
			System.out.println("==================================");
			while ((data = br.readLine()) != null) {
				System.out.println(data);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	private static void register() {
		System.out.println("register");
		while (true) {
			System.out.println("请输入用户名");
			String username = scanner.next();
			String passwd = scanner.next();

			File f = new File(path);
			FileReader fr = null;
			BufferedReader br = null;
			try {
				fr = new FileReader(f);
				br = new BufferedReader(fr);

				String data = null;
				boolean flag = false;
				while ((data = br.readLine()) != null) {
					String[] userInf = data.split(",");
					if (username.equalsIgnoreCase(userInf[0])) {
						flag = true;
						System.out.println("用户名已存在，请重新输入：");
						break;
					}

				}
				if (flag) {
					break;
				}
				// 写入
				System.out.println(flag + "xieru");

				FileWriter fw = new FileWriter(f, true);
				BufferedWriter bw = new BufferedWriter(fw);

				bw.write(username + "," + passwd);
				bw.newLine();
				bw.flush();
				System.out.println("恭喜" + username + "用户注册成功。。。");
				break;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (fr != null) {
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
