package com.example.creationaldesignpatterns;



abstract class Document implements Cloneable
{
	
	public String content;

	public Document(String content) {
		super();
		this.content = content;
	}
	
	public abstract void print();

	public Document clone()
	{
		try {
			
			return (Document)super.clone();
			
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return null;
		
		
	}

}

class Invoice extends Document
{

	public Invoice(String content) {
		super(content);
		// TODO Auto-generated constructor stub
	}
	
	public void print()
	{
		
		System.out.println("Invoice content :" + content);
	}

}

public class Prototype {

	public static void main(String[] args) {
		
		Invoice invoriginal = new Invoice("the orginal invoice amount is 15000");
		
		
		Invoice copy1_0 = (Invoice) invoriginal.clone();
		
		Invoice copy1_1 = (Invoice) invoriginal.clone();
		
		invoriginal.print();
		copy1_0.content = " Cloned Invoice for rs 15000 + GST";
		copy1_0.print();
		
		copy1_1.content = " Cloned Invoice for rs 15000 + GST + Commission Charges";
		copy1_1.print();

	}

}
