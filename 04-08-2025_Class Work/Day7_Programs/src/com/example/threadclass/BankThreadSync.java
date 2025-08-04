package com.example.threadclass;


//With synchronization ( locked the object and also it is safe (Thread safe)
/* Without Synchronization ( it will not lock and it is unsafe (Not a thread safe )
and also may lead to the race condition which could cause the false readability or wrong balance*/

class BankAccount
{
private int balance = 10000;

public int getBalance() {
return balance;
}
public  void withdrawUnSyncMethod(int amount)
{
	if(balance >=amount)
	{
		System.out.println(Thread.currentThread().getName() + "is withdrawing the amount : " + amount);
		balance -= amount;
		System.out.println("The remaining balance :" + balance);
	}
	else
	{
		System.out.println(Thread.currentThread().getName() + " Insufficient funds !");
	}
	
}

public synchronized void withdrawSyncMethod(int amount)
{
	if(balance >=amount)
	{
		System.out.println(Thread.currentThread().getName() + "is withdrawing the amount : " + amount);
		balance -= amount;
		System.out.println("The remaining balance :" + balance);
	}
	else
	{
		System.out.println(Thread.currentThread().getName() + " Insufficient funds !");
	}
	
}

public void depositSyncMethod(int amount)
{
	
	synchronized(this)
	{
		System.out.println(Thread.currentThread().getName() + " is depositing the :" + amount);
		balance += amount;
		System.out.println(" The new updated balance is : " + balance);
	}
	}




public void depositUnSyncMethod(int amount)
{
	
	
	
		System.out.println(Thread.currentThread().getName() + " is depositing the :" + amount);
		balance += amount;
		System.out.println(" The new updated balance is : " + balance);
	
	}


}
public class BankThreadSync {

	
	public static void main(String[] args) {
		
		BankAccount account = new BankAccount();
		
		Thread t1 = new Thread(() -> {
		for(int i =0 ; i<3 ; i++)
		{
		 account.withdrawSyncMethod(400);  // we are calling 3 times
		 System.out.println(account.getBalance());
		
		}
		}, "User A");
		
		Thread t2 = new Thread(() -> {
			for(int i =0 ; i<3 ; i++)
			{
			 account.withdrawSyncMethod(300);  // we are calling 3 times
			 
			// account.depositSyncMethod(200);
			}
			}, "User B");
		
		t1.start();// All three times it's executed 
		t2.start();

	}

}
