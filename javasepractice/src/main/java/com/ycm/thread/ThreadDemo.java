package com.ycm.thread;

public class ThreadDemo extends Thread{
	String threadName;
	int waitTime;
	static int num;
	
	public ThreadDemo(String threadName,int waitTime) {
		// TODO Auto-generated constructor stub
		this.threadName = threadName;
		this.waitTime = waitTime;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();	
		
		for (int i = 0; i < 50; i++) {
			System.out.println(threadName+"输出第"+i+"次"+num);
			try {
				Thread.currentThread().sleep(waitTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			num++;
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread t1= new ThreadDemo("t1",50);
		
		t1.start();
		t1.join();
		Thread t2= new ThreadDemo("t2",50);
		t2.start();
		t2.join();
		Thread t3= new ThreadDemo("t3",50);
		t3.start();
		t3.join();
		System.out.println("====================主线程结束");
		
	}

}
