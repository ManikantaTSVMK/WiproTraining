
package com.wipro;

import java.util.Scanner;

public class StringFunctions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Real use cases
		//Email format
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the string");
		String email = sc.next().trim().toLowerCase();
		if(email.contains("@"))
		{
			System.out.println("valid email format");
		}
		else
		{
			System.out.println("Valid email format  is required");
		}
		
		// Password
	
				System.out.println("Enter the password");
				String password = sc.next();
				if(password.equalsIgnoreCase("Admin@123"))
				{
					System.out.println("Password is correct ");
				}
				else 
				{
					System.out.println("Password is incorrect TryAgain! ");
				}
		//when you connecting with the database  (properties url =" " , username , "" , password ="" )
				
		String text = "ssflkjlk djflkj ksdfjlj";
		
		String[] afterSplitting  = text.split(" ");
		
		String value = afterSplitting[0];
		
		// To reverse
		String values = "Java Class";
		String newvalue = new StringBuilder(values).reverse().toString();
		System.out.println("The new value after reversing is :" + newvalue);

		// String Builder and String Buffer are mutable 
		
		StringBuilder sb = new StringBuilder("Niti");
	}

}

/*java.util.regex.Pattern and java.util.regex.Matcher classes are used.
A common regular expression for basic email validation is:
Java

String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
Explanation of the Regex:
^: Asserts the start of the string.
[A-Za-z0-9+_.-]+: Matches one or more occurrences of alphanumeric characters, plus, underscore, dot, or hyphen for the local part (before @).
@: Matches the literal "@" symbol.
[A-Za-z0-9.-]+: Matches one or more occurrences of alphanumeric characters, dot, or hyphen for the domain name.
\\.: Matches a literal dot, escaping it with a backslash.
[A-Za-z]{2,}: Matches the top-level domain (TLD), which must consist of at least two alphabetic characters. 
$: Asserts the end of the string.
Oops, something went wrong.

*/

