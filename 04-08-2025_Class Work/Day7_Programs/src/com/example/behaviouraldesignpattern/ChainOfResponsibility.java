package com.example.behaviouraldesignpattern;


/* where it passes a request along a chain of handlers - each may process or forward it */
/*  Logging Framework , authentication pipelines , or customer support workflow */

abstract class SupportHandler
{
	
protected SupportHandler successor;

public void setSuccessor(SupportHandler s) {
	successor = s;
}

abstract void handle (String request);

}


class  LowLevelHandler extends SupportHandler
{

	@Override
	void handle(String request) {
		
		if("low".equals(request)) 
		System.out.println("First Low level team has tried and allocated to High Level team :");
		else if (successor != null) successor.handle(request);
			
	}
}

class  HighLevelHandler extends SupportHandler
{

	@Override
	void handle(String request) {
		
		if("high".equals(request)) 
		System.out.println("High level team has handled the request :");
		else System.out.println("Unhandled Request :");
			
	}
}




public class ChainOfResponsibility {

	public static void main(String[] args) {
		SupportHandler h1 = new LowLevelHandler();
		SupportHandler h2 = new HighLevelHandler();
	//	SupportHandler h3 = new NextHighLevelHandler();
		
		h1.handle("low"); // low level handler has resolved 
		
	   //h1.setSuccessor(h2); // In h1 object you have the object of h2 
		h2.handle("high");

		
		//h2.setSuccessor(h3); // In h1 object you have the object of h2 
		h2.handle("superhigh");
		
		// This is also an example of polymorphism where at run time you are calling the handlers dynamically
		
	}

}