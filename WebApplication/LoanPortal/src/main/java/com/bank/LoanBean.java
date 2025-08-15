package com.bank;

//Bean, Pojo, Entity class(where we private fields, constructor, toString() ).
//to share the data between jsp pages
public class LoanBean {
	private String customerName;
	private double loanAmount;
	private int tenure;
	private double emi;
	
	
	
	public LoanBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoanBean(String customerName, double loanAmount, int tenure, double emi) {
		super();
		this.customerName = customerName;
		this.loanAmount = loanAmount;
		this.tenure = tenure;
		this.emi = emi;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public double getEmi() {
		return emi;
	}
	public void setEmi(double emi) {
		this.emi = emi;
	}
	
	
}
