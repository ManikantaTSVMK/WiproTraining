package com.example.characterstream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;


public class EmployeeManagerData {
	
	private static final Path Dir = Paths.get("FileData");
	//private static final Path File = Dir.resolve("employee.txt");
	private static final Path File = Dir.resolve("employee.ser");
	private static final Scanner sc = new Scanner(System.in);
	
	private static List<Employee> list = new ArrayList();
	
	public static void main(String[] args) throws IOException {
		
		
		Files.createDirectories(Dir);
		if(!Files.exists(File))
			Files.createFile(File);
		
		while(true)
		  {
		   System.out.println("\n1.Add , 2.Delete , 3. Search , 4. View All , 5. save&Exit");
		   int choice = sc.nextInt();
		   switch(choice)
		   {
		   case 1  -> addEmployee();
		  // case 2 -> deleteEmployee();
		  // case 3 -> searchEmployee();
		   case 4-> viewAll();
		   case 6 -> {Save_Exit(); return;}
		   
		   }
		  }
		

	}
	
	
//	private static void loadFromFile() 
//	{
//		
//		
//		
//	}
	
	private static void addEmployee()
	{
		try(FileWriter writer = new FileWriter(File.toFile() ,true)) // true is for append mode
		//try(BufferedWriter writer = Files.newBufferedWriter(File, StandardOpenOption.APPEND))
		{
		System.out.println("id , name and Salary");
		//list.add(new Employee(sc.nextInt() , sc.next(), sc.nextDouble()));
	   Employee e = new Employee(sc.nextInt() , sc.next(), sc.nextDouble());
		writer.write(e.toString());
		System.out.println("Employee Added");
				
	}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
		}
		
}
	private static void viewAll()
	{
		try(BufferedReader reader = Files.newBufferedReader(File))
		{
		System.out.println("Data from files");
		String line;
		boolean found = false;
		while((line = reader.readLine()) != null)
		{
			System.out.println(line);
		
		}
		
	    
		
				
	}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
		}

}
	
	private static void Save_Exit()
	{
		
		return;
	}
}
