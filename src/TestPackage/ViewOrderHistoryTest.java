package TestPackage;

import java.util.HashMap;

import OrderSubsystem.Order;
import OrderSubsystem.OrderCollection;

public class ViewOrderHistoryTest 
{

	public static void main (String [] args)
	{
		String j = "1";
		OrderCollection oc = new OrderCollection(j);
		HashMap<String,Order> hMap = oc.getViewOrderHistory();
		for(String key : hMap.keySet() )
		{
			System.out.println("Key: " + key + "\tOrders info: "
					+ "\t" +hMap.get(key).getOrderId()+ "\t"+ hMap.get(key).getSalesTotal() + "\t"
					+ hMap.get(key).getOrderDate()+
					"\t"+hMap.get(key).getOrderStatus() +"\t"+  hMap.get(key).getPaymentStatus() 
					+"\t"+hMap.get(key).printProductList()+ "\t"+ hMap.get(key).getCreditCard().getCCType());
		}
		

	}
}
