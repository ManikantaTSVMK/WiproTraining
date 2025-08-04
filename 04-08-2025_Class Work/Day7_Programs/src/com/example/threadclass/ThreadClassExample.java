package com.example.threadclass;

public class ThreadClassExample extends Thread {

	// to get the thread in a running state 
	public void run()  // Running state
	{
		
		System.out.println("Thread is running :" + Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ThreadClassExample th = new ThreadClassExample(); // New state
		th.setName("Thread 1");
		th.start(); // Runnable state

	}

}
