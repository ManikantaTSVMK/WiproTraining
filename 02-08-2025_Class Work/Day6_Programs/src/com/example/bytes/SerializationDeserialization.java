package com.example.bytes;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class UserCredentials implements Serializable
{
	private static final long serialNumber = 1L;
	
	String userName;
	
	String password;  
	
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
			ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("bfile.dat")));
			//BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("bfile.dat"));
			 os.writeObject(cr);
		//	byte[] data  = {45,34,23,23};
		//	out.write(data);
		//	out.close();
			 os.close();
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
			ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(new FileInputStream("bfile.dat")));
			//BufferedInputStream in = new BufferedInputStream(new FileInputStream("bfile.dat"));
			//UserCredentials user  = (UserCredentials)((ObjectInput) in).readObject();
        //int b;
//			while((b = in.read()) != -1) {
//				System.out.println((char) b + " ");
//		
//			System.out.println("Data is deserialized");
//			//user.display();
			UserCredentials udata = (UserCredentials) is.readObject();
			udata.display();
			}
			
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}


public class SerializationDeserialization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SerializeData sd = new SerializeData();
		sd.serializeData();
		
		DeSerializeData ds = new DeSerializeData();
		ds.deSerializeData();

	}

}