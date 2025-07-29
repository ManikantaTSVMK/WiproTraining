package com.wipro;

public class StringExample {
	public static void main(String[] args) {
	
	
		//Creating a string object using a literal
	
		String s="Mani"; //String pool Heap
		System.out.println(s.hashCode()); 
		
		// Creating a String object using new keyword
		// this gives the actual memory identity where it is stored
		System.out.println(System.identityHashCode(s));
		// creating a string object using new keyword
	
		String s1=new String("Mani");//Heap
		
		System.out.println(s1.hashCode()); //if same content as S object has then it will give the same hash
		System.out.println(System.identityHashCode(s1)); //different memory and different value

		
		// Whenever the String class overrides
		// the hashCode() method then it is computing on
		// of the content not the memory location
		
		System.out.println(s==s1); //different memory references
		System.out.println(s.equals(s1)); //Its will check the content
		
		
		//2nd use case with String concatenation how it works
		
		String svalue1="Mani";
		String svalue2="Ma"+"ni"; //compiler will treat it as String svalue2="Mani" at compile time
		
		System.out.println(svalue1==svalue2);
		
		String svalue3 = "Ma";
		String svalue4 = "ni";
		String oldvalue = "Mani";
		String newvalue = svalue3+svalue4;
		System.out.println(oldvalue == newvalue); // false
		System.out.println(oldvalue.equals(newvalue)); //true
		
		//3d case
		
		final String svalue5 = "Ma";
		final String svalue6 = "ni";
		String oldvalue1 = "Mani";
		String newvalue1 = svalue5+svalue6; //it will be constant at compile time only -- compile time constant
		System.out.println(newvalue1);
		System.out.println(oldvalue1 == newvalue1); // true
		System.out.println(oldvalue1.equals(newvalue1)); // true
		
		
		
		String a = "Mani";
		String b = new String("Mani");
		
		String c = b; 
		
		System.out.println(a==b); //false 
		System.out.println(a==c); // false
		
		
		String a1 = "Mani";
		String b1 = new String("Mani");
		
		String c1 = b1.intern(); // we are manually telling to the compiler to put this string into the pool 
		
		System.out.println(a1==b1); //false 
		System.out.println(a1==c1); // true
		
	}

}

//literal
//new keyword
//hashCode()
//identityHashCode()
//concatenation at compile time and runtime
//== (comparing the memory reference)
//.equals (comparing the content)
//.intern() (forcing manually to use it as a string pool version)
