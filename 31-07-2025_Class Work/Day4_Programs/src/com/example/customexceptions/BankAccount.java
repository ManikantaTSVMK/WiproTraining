package com.example.customexceptions;

public class BankAccount {
	
	private String accountHolder;
	private double balance;
	
	public BankAccount(String accountHolder, double balance) {
		super();
		this.accountHolder = accountHolder;
		this.balance = balance;
	}
	
	// throws keyword used with this method which might throw the exception 
	public void withdraw(double amount) throws InsufficientFundsException
	{
		
		if(amount<0)
		{
			
			throw new IllegalArgumentException(" Cannot withdraw a negative amount");
		}
		if (amount > balance)
		{
			// throw creates and throws the exception
			throw new InsufficientFundsException(" Have insufficient funds !" + balance);
		}
		balance -= amount;
		System.out.println( amount + "Amount debited :"  + " and now balance is : " +  balance );
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public double getBalance() {
		return balance;
	}
	
	
	

}