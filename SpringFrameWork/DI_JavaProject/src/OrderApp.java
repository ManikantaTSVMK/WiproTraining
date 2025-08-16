
class PaymentProcessing
{
	
void pay(double actualAmount)
{
System.out.println("Processing payment of Rs :" + actualAmount);	
}
}

class CreditCard extends PaymentProcessing
{
	
	@Override
	void pay(double actualAmount)
	{
		System.out.println(" payment of Rs :" + actualAmount + " via Credit Card is done");
	}
	
	// we also add a business logic of processing fees etc. or discount ...

}

class Upi extends PaymentProcessing
{
	
	@Override
	void pay(double actualAmount)
	{
		System.out.println(" payment of Rs :" + actualAmount + " via Upi is done");
	}
	
	// we also add a business logic of processing fees etc. or discount ...

}
class OrderService
{
	
private PaymentProcessing paymentProcessing;

// constructor injecting
public OrderService()
{

	// paymentProcessing = new CreditCard();
	paymentProcessing = new Upi(); // creating a object manually which is tightly coupled
}

public void placeOrder(double actualAmount)
{
	
	paymentProcessing.pay(actualAmount);
	System.out.println("Order placed successfully");
}

}

public class OrderApp {

	public static void main(String[] args) {
		
		OrderService orderService = new OrderService();
		orderService.placeOrder(1000);

	}

}
// Whenever you are adding a new payment method like upi then you also need to make changes in OrderService class constructor and create an object of it 

// Here based on payment method decision developers are manually changing the orderservice code and manually creating an object