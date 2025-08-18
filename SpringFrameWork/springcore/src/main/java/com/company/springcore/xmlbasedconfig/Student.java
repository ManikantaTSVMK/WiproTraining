package com.company.springcore.xmlbasedconfig;

public class Student {

	
	//Bean class properties 
	
	private int sid;
	private String sname;
	private String saddr;
	
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
		System.out.println("Student id is injected ");
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
		System.out.println("Student name is injected ");
	}
	public String getSaddr() {
		return saddr;
	}
	public void setSaddr(String saddr) {
		this.saddr = saddr;
		System.out.println("Student address is injected ");
	}
	public Student(int sid, String sname, String saddr) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.saddr = saddr;
		
		System.out.println("Values are injected through constructor");
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", saddr=" + saddr + "]";
	}
	
	
}