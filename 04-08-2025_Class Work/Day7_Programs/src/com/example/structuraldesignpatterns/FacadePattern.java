package com.example.structuraldesignpatterns;


/* When a system is too complex and needs a simple interface for clients
 * When multiple subsystems interact and you want to manage them centrally
 * 
 * For eg:  you are deploying an application to a cloud platform like AWS which requires setting of network , databases and some security policies .
 * Instead of dealing with each component separately we can use a CloudFacade
 * Hide subsystem details and promoting loose coupling
 * 
 * */

class ComputeService
{
void startServer()
{
System.out.println("compute server started : ");	
}

}

class DatabaseService
{
void configureDatabase()
{
System.out.println("Database Configured : ");	
}

}

class SecurityService
{
void setSecurityRules()
{
System.out.println("Security Rules Applied: ");	
}

}


class AWSCloudFacade{
	
	private ComputeService compute ;
	private DatabaseService database ;
	private SecurityService security ;
	
	AWSCloudFacade()
	{
//		 ComputeService compute = new ComputeService();
//	     DatabaseService database = new DatabaseService();
//		 SecurityService security = new SecurityService();
		 compute = new ComputeService();
	     database = new DatabaseService();
		 security = new SecurityService();
		
	}
	
	public void deployApplication()
	{
		
		System.out.println("Starting Cloud Deployement ..");
		compute.startServer();
		database.configureDatabase();
		security.setSecurityRules();
	}
}


// client 
public class FacadePattern {

	public static void main(String[] args) {
		
		AWSCloudFacade clf = new AWSCloudFacade();
		clf.deployApplication();  //Reduces the complexity of calling different services , you are giving one single entry point for deployement

	}

}
