package com.ycm.thread;

import java.util.ArrayList;
import java.util.Random;

class Apple {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Apple(int id) {
		this.id = id;
	}
}

class EatByClass extends Thread {
	private ArrayList<Apple> a = new ArrayList<Apple>();

	public EatByClass() {
		for (int i = 0; i < 5; i++) {
			this.a.add(new Apple(i));
		}
	}

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();

		for (int i = 0; i < a.size();) {
			System.out.println(threadName + "==========目前苹果总数" + a.size());
			Apple appleEated = a.get((int) (Math.random() * a.size()));// 拿到苹果
			System.out.println(threadName + "吃掉的苹果序号" + appleEated.getId());
			a.remove(appleEated);
		}
	}
}

class EatByInter implements Runnable {

	private ArrayList<Apple> a = new ArrayList<Apple>();

	public EatByInter() {
		for (int i = 0; i < 50; i++) {
			this.a.add(new Apple(i));
		}
	}

	// 同步代码块，互斥锁
	/*
	 * public void run() { String threadName = Thread.currentThread().getName(); for
	 * (int i = 0; i < a.size();) { synchronized (a) {
	 * 
	 * if (a.size() > 0) { System.out.println(threadName + "==========目前苹果总数" +
	 * a.size()); Apple appleEated = a.get((int) (Math.random() * a.size()));// 拿到苹果
	 * System.out.println(threadName + "吃掉的苹果序号" + appleEated.getId()); try {
	 * Thread.sleep(200); } catch (InterruptedException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } a.remove(appleEated); } } } }
	 */
	
	
	public void run() {
		String threadName = Thread.currentThread().getName();
		for (int i = 0; i < a.size();) {
			doWork(threadName);
		}
	}
//同步方法
	public synchronized void doWork(String threadName) {
		if (a.size() > 0) {
			System.out.println(threadName + "==========目前苹果总数" + a.size());
			Apple appleEated = a.get((int) (Math.random() * a.size()));// 拿到苹果
			System.out.println(threadName + "吃掉的苹果序号" + appleEated.getId());
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			a.remove(appleEated);
		}
	}
}

public class EatApple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// test1();

		test2();
	}

	private static void test2() {
		EatByInter ebi = new EatByInter();
		new Thread(ebi).start();
		new Thread(ebi).start();

	}

	public static void test1() {
		EatByClass eat1 = new EatByClass();
		eat1.start();

		EatByClass eat2 = new EatByClass();
		eat2.start();
	}

}
