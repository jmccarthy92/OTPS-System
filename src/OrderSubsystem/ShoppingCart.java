package OrderSubsystem;
import java.util.ArrayList;
import java.util.LinkedList;

import ProductManagementSystem.Product;

public class ShoppingCart {
	
	private LinkedList<Product> cart;
	
	public ShoppingCart()
	{
		cart = new LinkedList<Product>();
	}
	
	public LinkedList<Product> getCart()
	{
		return cart;
	}
	
	public boolean isEmpty()
	{
		if( cart.isEmpty())
			return true;
		else
			return false;
	}
	public boolean removeFromCartById(String id)
	{
		for( Product p : cart)
		{
			if( p.getProductId().equals(id.trim()))
			{
				 cart.remove(p);
				 return true;
			}
		}
		return false;
	}
	public double getTotal ()
	{
		double total = 0.0;
		for( Product p : cart)
		{
			System.out.println(total + "   " + p.getProductPrice() + "  " + p.getCartQuantity());
			total += (p.getProductPrice() * p.getCartQuantity());
		}
		return total;
	}
	public boolean addToCart(Product P, int quantity)
	{
	//	boolean cartProduct = true;
		for( Product nProducts : cart)
		{
			if(nProducts.getProductId() == P.getProductId())
			{
				return false;
			}
		}
		P.setCartQuantity(quantity);
		return cart.add(P);
	}
	
	public boolean removeFromCart(Product p)
	{
		return cart.remove(p);
	}
	
	public int updateCartQuantity(Product P, int quantity)
	{
		int ind = cart.indexOf(P);
		P = cart.get(ind);
		P.setCartQuantity( P.getCartQuantity() + quantity);
		return P.getCartQuantity();
	}
}
