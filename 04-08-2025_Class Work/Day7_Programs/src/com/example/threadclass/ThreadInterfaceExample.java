
package com.example.threadclass;

public class ThreadInterfaceExample implements Runnable{

	public static void main(String[] args) {
		ThreadInterfaceExample tr= new ThreadInterfaceExample();
		Thread tc = new Thread(tr);
		tc.setName("Thread 1");
		tc.start();
		

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Thread is running :" + Thread.currentThread().getName());
	}

}
