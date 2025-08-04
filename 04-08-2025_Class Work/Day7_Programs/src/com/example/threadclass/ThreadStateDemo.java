package com.example.threadclass;


public class ThreadStateDemo {

	public static void main(String[] args) throws InterruptedException {
		
		Thread t = new Thread(() ->{
			
			try
			{
				System.out.println("Thread1  Called ");
				Thread.sleep(4000); // Thread1 will go on a sleep mode
				System.out.println("Thread1 Run completed");
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			
		});
		
		System.out.println("State after creation :" + t.getState()); // Thread t = new Thread()// Thread 1 is created 
		
		t.start();
		System.out.println("State after start :" + t.getState());// Thread t will be in runnable state
		
		Thread.sleep(4000);  // Main thread will go on sleep mode so that thread1 will go in waiting state  and thread1 will start and enter in a sleep mode
		System.out.println("During a thread  sleep :" + t.getState()); // after 4 seconds main thread will wake up and This is executed by the main thread but it is checking the state of t(thread-1)
		
		t.join(); // Here main will wait for thread t to finish   // Main thread will wait for Thread1 to finish
		System.out.println("After Join(  Main thread terminated  :" + t.getState());
	}

}
