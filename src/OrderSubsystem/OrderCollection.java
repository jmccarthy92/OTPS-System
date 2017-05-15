package OrderSubsystem;

import AccountSubsystem.Account;
import AccountSubsystem.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import ProductManagementSystem.Product;

/**
 * 
 * This class Implements the Collection of Orders 
 * and the Cart Data Structure
 * It contain the use cases to create an order
 * the Shopping cart use case functions
 * The Make Payment use case involved in the Order payment
 * The cancel order used to get rid of an order that has been created
 * and has not been shipped yet, this will change the objects status to cancelled
 * however it will still be in the viewOrderHistory
 * This class also provides the ShipOrder Use case for the Administrator
 * as well as the viewOrderHistory to view the current users order History.
 * 
 * @author James McCarthy
 * 
 * @version 1.0
 *
 */


public class OrderCollection
{
	
	
	/**
	 * <p>
	 * Order History - A Hash mapped used to save the Orders the account has.
	 * Shopping Cart- a temporary data structure used by the User in the Order
	 * 					Management subsystem
	 * Order - An Order Object the collection can work with,
	 */
	private HashMap<String,Order> orderHistory;
	private LinkedList<Product> shoppingCart;
	private Order order;
	private OrderCollectionJDBC orderDB;
	private Account account;
	//private User user;
	//private Admin admin;
	
	
	/**<p>
	 * This is the default constructor for the OrderCollections class
	 * it creates the Order Collection for a guest who has not logged in
	 * as a User or an Admin.
	 * Creates the two data structures used in the Order Management Subsystem
	 * One being the Shopping Cart Linked List, b/c of quick insert and removal
	 * 
	 */
	public OrderCollection(  )
	{
		orderDB = new OrderCollectionJDBC();
		shoppingCart = new LinkedList<Product>();
		orderHistory = new HashMap<String,Order>();
	}
	
	
	/**<p>
	 * OverLoaded Constructor constructs the Order Collections with an Order History that will be filled
	 * by the DB passed into the Constructors
	 * @param orderHistory - The current Order History that it already tied to the
	 *					 		User Account. This is done by filling the hash array
	 *							with the appropriate Orders from the Users Order
	 *							stored in the DB.
	 * 			
	 */
	//--needs db--
	public OrderCollection(  String accountId)
	{
		orderDB = new OrderCollectionJDBC();
		shoppingCart = new LinkedList<Product>();
		//HashMap<String,Order> orderHistory = new HashMap<String,Order>();
		orderHistory = orderDB.orderHistQuery(accountId);

	}
	
	

	//--needs db--
	
	
	/**	  After the Order Object is created with payment information and
	 * all requires attributes filled. The order may be created and added
	 * to the DB in the appropriate table.
	 * @param prodList   - The Shopping Cart tied to the order
	 * @param salesTotal - The Sales total of all items in the cart
	 * @param accountIn - The ID of the account making the order
	 * @return
	 */
	public boolean createOrder (LinkedList<Product> prodList, double salesTotal,
								User accountIn, String address, String ccNum,
								CreditCardType ccType, String CSV, Date expirationDate)//(Order order)
	{
		
		order = new Order (prodList, salesTotal, accountIn);
		Address addr = new Address(address);
		CreditCard cc = new CreditCard(ccNum, CSV, expirationDate, ccType);
		order = makePayment(order,addr, cc);
		order.setPaymentStatus(true);
		//order.setOrderId("005");
		//Receipt r = null;
		boolean successful = orderDB.createOrderQuery(order);
		//orderHistory.put(order.getOrderId(), order );
		
		return successful;
		
	}
	
	// doesn't need db

	
	/**
	 * Takes the unfinished order object from the create
	 * Order method than sets the private variables of the
	 * Order Object that have not been such as the CreditCard
	 * and the Address
	 * @param unfinishedOrder - Unfinished order object passed in (  from create order)
	 * @return Order Object that has been successfully filled with the proper attributes 
	 * 			so it can be inserted into th DB.
	 */
	private Order makePayment(Order unfinishedOrder, Address address, CreditCard cc) 
	{
		//unfinishedOrder.setPaymentStatus(true);
		unfinishedOrder.setAddress(address);
		unfinishedOrder.setCreditCard(cc);
		unfinishedOrder.setOrderDate();
		
		return unfinishedOrder;
	
	}
	
	//--doesn't need db--
	/* (non-Javadoc)
	 * @see application.OrderCollectionsInterface#addToCart(application.Product)
	 */
	
	public boolean addToCart(Product productId)
	{
		//Product product = null;
		
		shoppingCart.add(productId);
		return true;
	}
	
	
	//---doesn't need db--
	/* (non-Javadoc)
	 * @see application.OrderCollectionsInterface#removeFromCart(java.lang.String)
	 */
	
	public boolean removeFromCart( String productId)
	{	
		for( Product p : shoppingCart)
		{
			if(productId.trim() ==p.getProductId()) 
			{
				shoppingCart.remove(p);
				return true;
			}
		}
		return false;

	}
	// doesn't need db
	/* (non-Javadoc)
	 * @see application.OrderCollectionsInterface#updateCartQuantity()
	 */
	
	public boolean updateCartQuantity(String productId, int quantity)
	{
		return true;
	}
	
	

	/** Removed the Order from the Orders table in the DB
	 * and returns the Order object that was deleted.
	 * @param orderId - Id of the object to be deleted
	 * @return Order - The Order Object that was deleted
	 * 			null - if order was not found.
	 */
	public Order removeOrder(String orderId)
	{
		return orderDB.removeOrderQuery(orderId);
	}

	// needs db
	/* (non-Javadoc)
	 * @see application.OrderCollectionsInterface#cancelOrder(java.lang.String)
	 */
	
	public boolean cancelOrder( String orderId )
	{
		return orderDB.cancelOrderQuery(orderId);
	}
	
	// needs db
	/* (non-Javadoc)
	 * @see application.OrderCollectionsInterface#shipOrder()
	 */

	public boolean shipOrder(String orderId)
	{
		return orderDB.shipOrderQuery(orderId);
	}
	
	// needs db
	/* (non-Javadoc)
	 * @see application.OrderCollectionsInterface#viewOrderHistory()
	 */
	//public LinkedList<Product> getProductList(String orderId)
	//{
//		LinkedList<Product> productList = orderDB.getOrdersProductsQuery(orderId);
//		return productList;
//	}
	
	public Order searchOrders(String orderId)
	{
		Order order;
		order = orderDB.searchOrderQuery(orderId);
		return order;
	}
	public HashMap<String,Order> searchOrderStatus(OrderStatus os)
	{
		return orderDB.searchOrderStatusQuery(os);
	}
	public HashMap<String,Order> getAllOrders()
	{
		HashMap<String,Order>  orderList = new HashMap<String,Order>();
		orderList = orderDB.getAllOrderQuery();
		return orderList;
	}

	
	
	/**
	 * Displays the Order History for the User.
	 * The Orders the User has made are extracted from the
	 * DB and displays them.
	 * @param  AccId- The id of the account you would like to get the order history for.
	 * @return Returns the Hash Map for the ORder History
	 */
	public HashMap<String,Order> ViewOrderHistory(String accId)
	{
		//HashMap<String,Order> hashMapReturned = new HashMap<String,Order>();
		orderHistory = orderDB.orderHistQuery(accId);
		return orderHistory;
		//return orderHistory;
	}
	
	public HashMap<String,Order> getViewOrderHistory()
	{
		return orderHistory;
	}
	
	/*public HashMap<String,Order> getAllOrders()
	{
		HashMap<String,Order> hashMapReturned = new HashMap<String,Order>();
		return hashMapReturned;
	}*/
	
	
}
