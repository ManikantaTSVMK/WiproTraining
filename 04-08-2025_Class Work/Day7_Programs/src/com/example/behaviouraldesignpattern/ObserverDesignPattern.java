package com.example.behaviouraldesignpattern;

import java.util.ArrayList;
import java.util.List;

// It defines a one - to many notifications or relationships so that when one object changes , all dependents get notified
// eg : you tube video upload where there are subscribers and will be notified by the channelowner(youtuber) when he uploads a new video
//Weather forecasting ,, Event-listneres , publish -subscribe systems ,node js  , js 

interface Observer 
{
void update(String msg);	

}

class Subject
{
private final List<Observer> observers  = new ArrayList<>();

void addObserver(Observer o)
{
observers.add(o);	
}

void notifyObservers(String msg)
{
	
observers.forEach(o -> o.update(msg));

}

}

class EmailListener implements Observer
{
private final String email;
EmailListener(String e)
{
email =e;	
}
public void update(String m)
{
	System.out.println("To " + email + " : " + m);
}

}


public class ObserverDesignPattern {

	public static void main(String[] args) {
		
		Subject sub = new Subject();
		// EmailListener will listen the event and notify to all the observers
		sub.addObserver(new EmailListener("a@gmail.com"));  // In actuall it will taking all the mail id's from database 
		sub.addObserver(new EmailListener("b@yahoo.com"));
		
		sub.notifyObservers(" There is a change in weather tomorrow ( Heavy rain forecasted)!");
		

	}

}
