
// WAP to  create two users user-a  and user-b to perform some task (FileUpload)( they both are thread thread-A and thread-B  )
//Main thread will wait for both users before showing the task is completed of user-a and user b

package com.example.threadclass;

class UploadFile extends Thread{
	String username;
	
	public UploadFile(String username) {
		this.username = username;
	}
	
	public void run() {
		System.out.println(username + " upload file");
		try {
			Thread.sleep(1000);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(username + " uploaded file");
	}
}

public class ClassTask {
	public static void main(String[] args) throws InterruptedException {
		UploadFile threadA = new UploadFile("Mani");
		UploadFile threadB = new UploadFile("Manish");
		
		threadA.start();
		threadA.join();
		threadB.start();
		threadB.join();
		
		System.out.println("Task completed");
	}
}