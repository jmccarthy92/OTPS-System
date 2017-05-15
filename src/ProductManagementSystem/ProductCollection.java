package ProductManagementSystem;

import java.sql.SQLException;
import java.util.HashMap;

public class ProductCollection
{
	JDBC DB = new JDBC();
	
	public Product addProduct(Product p) throws SQLException, ClassNotFoundException
	{
		DB.PB(); // calling the open connection to database
		DB.AddProduct(p); // calling the add product function from JDBC class
		return p;
	}

	public Product removeProduct(String productId) throws ClassNotFoundException, SQLException
	{
		Product po = new Product("", "", 0.0, 0, 0.0); //making a empty product 
		
		DB.PB(); // calling the open connection to database
		po = DB.RemoveProduct(productId); // calling the remove product function from JDBC class
		return po;
	}

	public double rateProduct(String ID, double rating, String userID) throws ClassNotFoundException, SQLException
	{
		DB.PB(); // calling the open connection to database
		DB.rateProduct(ID, rating, userID); // calling the rates product function from JDBC class
		return rating;
	}
	
	public Product searchProduct_ID(String productId) throws ClassNotFoundException, SQLException
	{
		Product po = new Product("", "", 0.0, 0, 0.0); //making a empty product 
		
		DB.PB(); // calling the open connection to database
		po = DB.searchProduct_ID(productId); // calling the search product by ID function from JDBC class
		//also making a product equal the product it is searching for
		return po; //reaturns the product you are searching for 
	}
		
	public Product searchProduct_Name(String name) throws ClassNotFoundException, SQLException
	{
		Product po = new Product("", "", 0.0, 0, 0.0); //making a empty product 
		
		DB.PB(); // calling the open connection to database
		po = DB.searchProduct_Name(name); // calling the search product by name function from JDBC claas
		return po;
	}
	
	public HashMap searchProduct_Price(Double price, int option) throws ClassNotFoundException, SQLException
	{
		HashMap<String,Product> hmap1;
		DB.PB(); // calling the open connection to database
		hmap1 = DB.searchProduct_Price(price, option); // calling the search product by price function from JDBC class
		return hmap1;
	}
	
	public HashMap<String,Product> viewProductList() throws ClassNotFoundException, SQLException
	{
		HashMap<String,Product> hmap1;
		DB.PB(); // calling the open connection to database
		hmap1 = DB.ViewProductList(); // calling the view product list function from JDBC class
		return hmap1;
	}
	
	public int changeStockQuantityOrder(String productId, int Quantity) throws ClassNotFoundException, SQLException
	{
		DB.PB();
		return DB.changeStockQuantityOrder(productId, Quantity);
	}
	public int changeStockQuantity(String productId, int Quantity) throws ClassNotFoundException, SQLException
	{
		DB.PB(); // calling the open connection for DB
		DB.changeStockQuantity(productId, Quantity); // calls the changeStockQuantity function from JDBC class
		return Quantity;
	}
	
	public double changePrice(String productId, double newPrice) throws ClassNotFoundException, SQLException
	{
		DB.PB(); // calling the open connection to database
		DB.changeProductPrice(productId, newPrice); // calling the changeprice function from JDBC class
		return newPrice;
	}
}
