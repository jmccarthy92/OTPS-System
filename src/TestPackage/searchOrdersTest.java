package TestPackage;

import OrderSubsystem.Order;
import OrderSubsystem.OrderCollection;

public class searchOrdersTest {
	
	public static void main(String [] args)
	{
		OrderCollection oc = new OrderCollection();
		Order order = oc.searchOrders("1");
		System.out.println(order);
	}

}
