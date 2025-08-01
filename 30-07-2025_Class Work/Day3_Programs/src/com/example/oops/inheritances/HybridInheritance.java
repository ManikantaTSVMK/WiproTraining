package com.example.oops.inheritances;

class Cloth extends Product implements IDiscountable{

	public Cloth(String name, double price) {
		super(name, price);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getDiscount() {
		// TODO Auto-generated method stub
		return price*0.18;
		
	}
	public void show() {
		System.out.println("name: " + name + "Price: " + price + "Discount:" + getDiscount());
		
		
	}
	
	
	
}
public class HybridInheritance {
	public static void main(String[] args) {
	 Cloth cl = new Cloth("Raymond", 15000 ); 
	 cl.show();
	}

}