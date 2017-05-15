package TestPackage;

import java.util.HashMap;

import OrderSubsystem.Order;
import OrderSubsystem.OrderCollection;

public class getAllOrdersTest
{

	
	public static void main (String [] args)
	{
	//	String j = "100";
		OrderCollection oc = new OrderCollection();
		HashMap<String,Order> hMap = oc.getAllOrders();
		for(String key : hMap.keySet() )
		{
			System.out.println("Key: " + key + "\tOrders info: "
					+ "\t" +hMap.get(key).getOrderId()+ "\t"+ hMap.get(key).getSalesTotal() + "\t"
					+ hMap.get(key).getOrderDate()+
					"\t"+hMap.get(key).getOrderStatus() +"\t"+  hMap.get(key).getPaymentStatus() 
					+"\t"+hMap.get(key).printProductList()+ "\t"+ hMap.get(key).getCreditCard().getCCType());
		}
		
		System.out.println("jaksdjaskd");
	}
}
