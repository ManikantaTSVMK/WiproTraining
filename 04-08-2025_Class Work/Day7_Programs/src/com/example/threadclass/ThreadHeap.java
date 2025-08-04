package com.example.threadclass;


class SharedData
{
 int sharedValue =0;  // which will be in heap 	
}

 class UsingThreadHeap extends Thread {

	private final SharedData data;
	
	public UsingThreadHeap(SharedData data , String name)
	{
		super(name);
		this.data = data;
	}
	
	public void run()
	{
		int localValue = 0 ; // this is a local variable  -- created in each thread's a different memory stack
		
		String threadName = Thread.currentThread().getName();
		
		for(int i=0 ; i<=3 ; i++)
		{
			localValue++;
			data.sharedValue++;
			
			System.out.println( threadName + " at i :" + i + " the localValue is :" + localValue + " and sharedValue is from :" +  data.sharedValue);
		}
		
		
	}
	
}
public class ThreadHeap 
{
	public static void main(String[] args) throws InterruptedException {
		SharedData data = new SharedData(); // shared object and instance variable is stored in Heap
		
		
		UsingThreadHeap t1 = new UsingThreadHeap(data , "Thread 1");  // same heap memory but different Stack area
		UsingThreadHeap t2 = new UsingThreadHeap(data , "Thread 2");

		t1.start();	
		t2.start();
		
	
		
		
		}
	
	
}
