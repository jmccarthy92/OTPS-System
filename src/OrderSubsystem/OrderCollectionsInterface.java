package OrderSubsystem;

import java.util.HashMap;

import ProductManagementSystem.Product;

//import java.util.HashMap;
//import java.util.LinkedList;

/**
 * Interface for the Order Collections class 
 * implemented by the OrderCollections
 * @author James McCarthy
 * @version 1.0
 * 
 *
 */
public interface OrderCollectionsInterface 
{
	/**
	 * After the Order Object is created with payment information and
	 * all requires attributes filled. The order may be created and added
	 * to the DB in the appropriate table.
	 * @param order - Order passed into to create the receipt
	 * @return The Receipt of Purchase for displaying to the user
	 */
	public Receipt createOrder(Order order);
	/**
	 * Used to add Products to the Cart data Structure for the Order
	 * @param product - Product to be added to the cart
	 * @return true or false if the product is added successfully
	 */
	public  boolean  addToCart(Product product);
	/**
	 * Used to remove Products from the Cart data Structure for the Order
	 * @param productId - The ID of the Product/
	 * @return Returns true or false if the products is removed from the cart
	 */
	public boolean removeFromCart(String productId);
	
	public Order searchOrder(String orderId);
	/**
	 * Used to updates the quantity of a specific item in the cart.
	 * @param productId - The ID of the Product
	 * @return returns true or false if the quantity is updated successfully
	 */
	public boolean updateCartQuantity(String productId, int quantity);
	/**
	 * Returns an Order that can be used by the Create Order Method
	 * @return Order that has been filled with the payment information
	 */
	public Order makePayment(Order order);
	/**
	 * Used by the User to Cancel an Order from their Order History
	 * By Passing in the Order ID of the Order to be cancelled.
	 * @param OrderId - Id of the Order
	 * @return True or False whether or not the Order was Cancelled or not
	 */
	public boolean cancelOrder( String OrderId );
	/**
	 * Used by the Client to ship the User Order from their Client
	 * interface
	 * @param OrderId - Id of the Order
	 * @return  True or false whether the Order has been shipped or not
	 */
	public boolean shipOrder( String OrderId );
	/**
	 * Displays the Order History for the User.
	 * The Orders the User has made are extracted from the
	 * DB and displays them.
	 * @return Returns the Hash Map for the ORder History
	 */
	public HashMap<String,Order> viewOrderHistory();
}
