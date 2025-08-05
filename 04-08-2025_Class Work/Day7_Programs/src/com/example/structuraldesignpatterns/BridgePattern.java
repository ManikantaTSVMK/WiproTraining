package com.example.structuraldesignpatterns;



//seperate abstraction and implementation so that they will be independent
// Message Abstraction and the Platform which you will using

interface MessageSender
{
	
void send(String message);
}


class EmailSender implements MessageSender
{

	@Override
	public void send(String msg) {
		// TODO Auto-generated method stub
		
		System.out.println("Sending an Email :"+ msg);
	}
	
}


class SmsSender implements MessageSender
{

	@Override
	public void send(String msg) {
		// TODO Auto-generated method stub
		
		System.out.println("Sending an Sms :"+ msg);
	}
	
}


abstract class Message{
	
	protected MessageSender sender;
	
	public Message(MessageSender sender)
	{
		
		this.sender = sender;
	}
	public abstract void send(String msg);
	
}

class AlertMessage extends Message
{
	
public AlertMessage(MessageSender sender) {
	super(sender);
	
}

@Override
public void send(String msg) {
	// TODO Auto-generated method stub
	sender.send("Alert messages  : " + msg);
}

}
public class BridgePattern {

	public static void main(String[] args) {
		
		Message msg1 = new AlertMessage(new EmailSender());
		Message msg2 = new AlertMessage(new SmsSender());
		
		msg1.send("Email Sent");
		msg2.send("Sms Sent");
	}

}
