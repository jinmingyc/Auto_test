package com.ycm.thread;

public class RunnableDemo implements Runnable {

	private Thread t;
	private String threadName;
	private long waitTime;

	public RunnableDemo(String threadName, long waitTime) {
		this.threadName = threadName;
		this.waitTime = waitTime;
	}

	public void run() {
		// TODO Auto-generated method stub

		for (int i = 0; i < 50; i++) {
			System.out.println(threadName + "输出第" + i + "次");
			try {
				Thread.currentThread().sleep(waitTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void start() {
		System.out.println("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RunnableDemo rd1 =new RunnableDemo("thead1",10);
		
		rd1.start();
		RunnableDemo rd2 =new RunnableDemo("thead2",10);
		rd2.start();
	}

}
