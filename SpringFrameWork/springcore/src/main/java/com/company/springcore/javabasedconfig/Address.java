package com.company.springcore.javabasedconfig;

public class Address {
	
	private int pin;
	private String street;
	private String city;
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Address(int pin, String street, String city) {
		super();
		this.pin = pin;
		this.street = street;
		this.city = city;
	}
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Address [pin=" + pin + ", street=" + street + ", city=" + city + "]";
	}
	
	

}