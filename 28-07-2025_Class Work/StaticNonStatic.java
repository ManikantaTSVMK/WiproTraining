package com.wipro;

public class StaticNonStatic {
	
	static int studentCount =0;
	String name;
	int age;
	
	StaticNonStatic()
	{
		studentCount++;
	}
	
	void show()
	{
		
		System.out.println(studentCount);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		StaticNonStatic  staticobject = new StaticNonStatic();
		staticobject.show(); //1
		
		StaticNonStatic  staticobject1 = new StaticNonStatic();
		staticobject1.show(); //2
		
		StaticNonStatic  staticobject2 = new StaticNonStatic();
		staticobject2.show(); //3
	}

}
