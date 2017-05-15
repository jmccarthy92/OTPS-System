package TestPackage;

import OrderSubsystem.OrderCollection;

public class ShipOrderTest {
	
	public static void main ( String [] args)
	{
		OrderCollection oc = new OrderCollection();
		oc.shipOrder("1");
	}

}
