package com.example.structuraldesignpatterns;


// Eg :  Remote control working with Multiple Devices (TV , Radio)
interface Device
{
  void turnOn();
  void turnOff();
}

// Concrete  Implementations    --  some more example samsung , Apple manufacture where they have given the implementation of sms and calling
class TV implements Device
{

	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		
		System.out.println("Tv is on ");
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		System.out.println("Tv is off ");
	}
	
}

class Radio implements Device
{

	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		
		System.out.println("Radio is on ");
	}

	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		System.out.println("Radio is off ");
	}
	
}

// Abstraction -- giving the relevant features 
class RemoteControl
{
protected Device device;
public RemoteControl(Device device)  // In constructor you have to pass the device whether it is of samsung or apple phone device
{
this.device = device	;
}

public void turnOn()
{
device.turnOn();	
}

public void turnOff()
{
device.turnOff();	
}
}



public class BridgePattern2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RemoteControl remote = new RemoteControl(new Radio());
		remote.turnOn();
		remote.turnOff();
	}

}