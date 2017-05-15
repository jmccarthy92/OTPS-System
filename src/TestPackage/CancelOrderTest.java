package TestPackage;

import OrderSubsystem.OrderCollection;

public class CancelOrderTest {
	
	public static void main (String [] args)
	{
		OrderCollection oc = new OrderCollection();
		oc.cancelOrder("1");
	}

}
