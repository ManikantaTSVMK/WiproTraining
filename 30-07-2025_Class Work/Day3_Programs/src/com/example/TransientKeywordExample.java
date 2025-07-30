package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class UserCredentials implements Serializable
{
	private static final long serialNumber = 1L;
	
	String userName;
	
	transient String password;  // Don't want to make a part of serializable , excluding from saving and retreiving

	public UserCredentials(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public void display()
	{
		
		System.out.println("UserName : " + userName);
		System.out.println("Password : " + password);
	}

}
class SerializeData{
	
	public void serializeData()
	{
		
		
		UserCredentials cr = new UserCredentials("Niti" , "3434sdfdsf");
		
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("bfile.ser"));
			out.writeObject(cr);
			out.close();
			System.out.println("Data is serialized");
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
class DeSerializeData{
	
	public void deSerializeData()
	{
		
		
		
		
		try
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("bfile.ser"));
			UserCredentials user  = (UserCredentials)in.readObject();
			in.close();
			System.out.println("Data is deserialized");
			user.display();
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
public class TransientKeywordExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SerializeData sd = new SerializeData();
		sd.serializeData();
		
		DeSerializeData ds = new DeSerializeData();
		ds.deSerializeData();

	}

}