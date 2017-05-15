package ProductManagementSystem;


//Step 1: import requiered packages
import java.sql.*;
import java.util.HashMap;

public class JDBC
{		
	//JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/otps_system";
	
	//Database credentails
	static final String USER = "root";
	static final String PASS = "root";
	
	//Creating the statement and connection variables
	private Connection conn = null;
	private Statement stmt = null;
	
	
	public void PB() throws ClassNotFoundException, SQLException
	{
		//Step 2: Register JDBC driver
		Class.forName("com.mysql.jdbc.Driver");
		
		//Step 3: Open a connection
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(DB_URL,USER,PASS);

		String use = "USE otps_system;"; //sets up the use Query to use control the OTPS_system
		stmt = conn.createStatement();
		stmt.executeQuery(use);	
	}
	
	public Product AddProduct(Product p) throws SQLException
	{
		try
		{
		String statement = "'" + p.getProductName() + "'," + p.getProductPrice() + ", " + p.getStockQuantity(); // this is getting all the infromation of the product and putting it into a string to be used later 
		String sql = "INSERT INTO PRODUCT(PROD_NAME, PROD_PRICE, PROD_QUANTITY)" + "VALUES(" + statement + ");"; // this creates the SQL query to insert a new product into the system
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		
		sql = "SELECT PROD_ID FROM PRODUCT WHERE PROD_NAME =" + "'" + p.getProductName() + "'" + ";"; //this is creating a query to find the product ID
		stmt = conn.createStatement();
		ResultSet set = stmt.executeQuery(sql);
		
		String ID = "";	
		while(set.next()) //this loop is getting the ID into a variable
		{
	         String PROD_ID = set.getString("PROD_ID");
	         ID = PROD_ID;
		}
	    set.close();
	    
	    p.setProductID(ID); //setting the ID 
	    
		//Clean up environment 
		}catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){}
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }}
		
		return p; //returning the product item with all the information
	}	
	
	public Product RemoveProduct(String productID) throws SQLException
	{	
		Product p = new Product("", "", 0.0, 0, 0.0); //making a empty product 
		
		try
		{
			System.out.println("Not entering the delte stat");
			String sql = "DELETE FROM PRODUCT WHERE PROD_ID = " + productID + ";" ; //creating the query to remove a product by ID
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		
		//Clean up Enviroment
		catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){}
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }}
		
		return p; //return product with all the info of the removed item
	}
	
	public int changeStockQuantity(String productID, int Quantity)
	{
		try
		{
			String sql = "UPDATE PRODUCT SET PROD_QUANTITY = " + Quantity + " WHERE PROD_ID = " + productID + ";" ; //creates the query to change price of a product of a certain ID
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		
		//Clean up enviroment 
		catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
		   e.printStackTrace();
		}finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){}
	      try{
	    	  if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }}
		
		return Quantity; // returns the new quantity of the product
	}
	
	public int changeStockQuantityOrder(String productID, int Quantity)
	{
		try
		{
			String sql =  "UPDATE PRODUCT SET PROD_QUANTITY = PROD_QUANTITY - " + Quantity + " WHERE PROD_ID = " + productID + ";" ;  //creates the query to change price of a product of a certain ID
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		
		//Clean up enviroment 
		catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
		   e.printStackTrace();
		}finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){}
	      try{
	    	  if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }}
		
		return Quantity; // returns the new quantity of the product
	}
	
	
	
	public Product searchProduct_ID(String productID)
	{	
		Product p = new Product("", "", 0.0, 0, 0.0);
		
		try
		{
			String sql = "SELECT PROD_ID, PROD_NAME, PROD_PRICE, PROD_QUANTITY " + "FROM PRODUCT WHERE PROD_ID = " + "'" +  productID + "'" + ";"; //creating a query to search for product by a certain ID
			stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery(sql);
						
			String name = "";
			double price = 0.0;
			int quantity = 0;
			while(set.next()) //This loop is used to get the information into a variable
			{
				String PROD_NAME = set.getString("PROD_NAME");
		        name = PROD_NAME;
		        
		        double PROD_PRICE = set.getDouble("PROD_PRICE");
		        price = PROD_PRICE;
		        
		        int PROD_QUANTITY = set.getInt("PROD_QUANTITY");
		        quantity = PROD_QUANTITY;
			}
			set.close();
			
			sql = "SELECT PROD_RATING " + "FROM PRODUCT_RATING WHERE PROD_ID = " + "'" +  productID + "'" + ";"; //creating a query to get the rating of the searched product since it is in a different table
			stmt = conn.createStatement();
			set = stmt.executeQuery(sql);
						
			double rating = 0.0;
			while(set.next()) //This loop is used to get the rating into a variable
			{
		        double PROD_RATING = set.getDouble("PROD_RATING");
		        rating = PROD_RATING;
			}
			set.close();
			
			//These are all setting the information to a product item
			p.setProductID(productID);
			p.setProductName(name);
			p.setProductPrice(price);
			p.setProductQuantity(quantity);
			p.setProductRating(rating);		
		}
		
		//Clean up enviroment
		catch(SQLException se){
	      se.printStackTrace();
		}catch(Exception e){
	      e.printStackTrace();
		}finally{
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){  
	         se.printStackTrace();
	      }}
		
		return p; //returns the product you are searching for
	}
	
	public Product searchProduct_Name(String productName)
	{
		Product p = new Product("", "", 0.0, 0, 0.0); //making a empty product 
		
		try
		{
			String sql = "SELECT PROD_ID, PROD_NAME, PROD_PRICE, PROD_QUANTITY " + "FROM PRODUCT WHERE PROD_NAME = " + "'" +  productName + "'" + ";"; //creates the query to get all products with that name
			stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery(sql);
						
			String id = "";
			double price = 0.0;
			int quantity = 0;
			while(set.next()) //Thie loop is getting all data into variables
			{
				String PROD_ID = set.getString("PROD_ID");
		        id = PROD_ID;
		        
		        double PROD_PRICE = set.getDouble("PROD_PRICE");
		        price = PROD_PRICE;
		        
		        int PROD_QUANTITY = set.getInt("PROD_QUANTITY");
		        quantity = PROD_QUANTITY;
			}
			set.close();
			
			sql = "SELECT PROD_RATING " + "FROM PRODUCT_RATING WHERE PROD_ID = " + "'" +  id + "'" + ";"; //creates the query to get all the rating of all products 
			stmt = conn.createStatement();
			set = stmt.executeQuery(sql);
						
			double rating = 0.0;
			while(set.next()) //This loop is getting all rating into variables
			{
		        double PROD_RATING = set.getDouble("PROD_RATING");
		        rating = PROD_RATING;
			}
			set.close();
			
			//These are all setting the information to a product item
			p.setProductID(id);
			p.setProductName(productName);
			p.setProductPrice(price);
			p.setProductQuantity(quantity);
			p.setProductRating(rating);		
		}
		
		//Clean up enviroment 
		catch(SQLException se){
	      se.printStackTrace();
		}catch(Exception e){
	      e.printStackTrace();
		}finally{
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){}
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){  
	         se.printStackTrace();
	      }}
		return p; //Returns the product you searched for
	}
	
	public HashMap searchProduct_Price(Double price, int option)
	{
		HashMap<String, Product> hmap = new HashMap <String, Product>(); //This creates the hashMap
		
		if(option == 1) //equal to price
		{
			try
			{
				String sql = "SELECT PROD_ID, PROD_NAME, PROD_PRICE, PROD_QUANTITY " + "FROM PRODUCT WHERE PROD_PRICE = " + price + " ORDER BY PROD_ID ASC;"; //creates a query to search for products by a given price
				stmt = conn.createStatement();
				ResultSet set = stmt.executeQuery(sql);
							
				while(set.next()) //This loop is getting all the infor from the product into variables
				{
					String id = set.getString("PROD_ID");	
					String name = set.getString("PROD_NAME");
			        double price1 = set.getDouble("PROD_PRICE");
			        int quantity = set.getInt("PROD_QUANTITY"); 
			        double rating = 0.0;
				
			        Product po = new Product(id, name, price, quantity, rating); //creates a new product and put all the data into it
					hmap.put(id, po); //stores the data in to the hashmap
				}
				set.close();
			}
			
			//Clean up enviroment 
			catch(SQLException se){
		      se.printStackTrace();
			}catch(Exception e){
		      e.printStackTrace();
			}finally{
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){}
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){  
		         se.printStackTrace();
		      }
		   }
		}
		else if(option == 2) //less than price
		{
			try
			{
				String sql = "SELECT PROD_ID, PROD_NAME, PROD_PRICE, PROD_QUANTITY " + "FROM PRODUCT WHERE PROD_PRICE < " + price + " ORDER BY PROD_ID ASC;"; //creates a query to search for products by a given price
				stmt = conn.createStatement();
				ResultSet set = stmt.executeQuery(sql);
								
				while(set.next()) //This loop is getting all the infor from the product into variables
				{
					String id = set.getString("PROD_ID");	
					String name = set.getString("PROD_NAME");
			        double price1 = set.getDouble("PROD_PRICE");
			        int quantity = set.getInt("PROD_QUANTITY"); 
			        double rating = 0.0;
				
			        Product po = new Product(id, name, price1, quantity, rating); //creates a new product and put all the data into it
					hmap.put(id, po); //stores the data in to the hashmap
				}
				set.close();
			}
			
			//Clean up enviroment 
			catch(SQLException se){
		      se.printStackTrace();
			}catch(Exception e){
		      e.printStackTrace();
			}finally{
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){}
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){  
		         se.printStackTrace();
		      }
		   }	
		}
		else if(option == 3) // greater than price
		{
			try
			{
				String sql = "SELECT PROD_ID, PROD_NAME, PROD_PRICE, PROD_QUANTITY " + "FROM PRODUCT WHERE PROD_PRICE > " + price + " ORDER BY PROD_ID ASC;"; //creates a query to search for products by a given price
				stmt = conn.createStatement();
				ResultSet set = stmt.executeQuery(sql);
								
				while(set.next()) //This loop is getting all the infor from the product into variables
				{
					String id = set.getString("PROD_ID");	
					String name = set.getString("PROD_NAME");
			        double price1 = set.getDouble("PROD_PRICE");
			        int quantity = set.getInt("PROD_QUANTITY"); 
			        double rating = 0.0;
				
			        Product po = new Product(id, name, price1, quantity, rating); //creates a new product and put all the data into it
					hmap.put(id, po); //stores the data in to the hashmap
				}
				set.close();
			}
			
			//Clean up enviroment 
			catch(SQLException se){
		      se.printStackTrace();
			}catch(Exception e){
		      e.printStackTrace();
			}finally{
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){}
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){  
		         se.printStackTrace();
		      }
		   }	
		}
		return hmap; //returns the hashmap with all the products
	}
	
	public HashMap<String,Product> ViewProductList() throws SQLException
	{
		HashMap<String, Product> hmap = new HashMap <String, Product>(); //This creates the hashMap
		
		try
		{
			String sql = "Select * From Product ORDER BY PROD_ID ASC"; //creates the query to get all products in the system
			stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery(sql);
			
			while(set.next()) //This loop stores all values of the products into variables
			{
				String id = set.getString("PROD_ID");
				String name = set.getString("PROD_NAME");
				double price = set.getDouble("PROD_PRICE");
				int quantity = set.getInt("PROD_QUANTITY");
				double rating = 0.0;
				String sql2 = "SELECT AVG(PROD_RATING) as AVERAGE FROM PRODUCT_RATING WHERE PROD_ID = " + id +" ORDER BY PROD_ID ASC";			
				stmt = conn.createStatement();
				ResultSet set2 = stmt.executeQuery(sql2);
				
				while(set2.next())
				{
					rating = set2.getDouble(1);
				}
				set2.close();
				Product po = new Product(id, name, price, quantity, rating); //creates a new product and put all the data into it
				hmap.put(id, po); //stores the data in to the hashmap
			}
			set.close();
		
		//Clean up environment 
		}catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){}
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }
		return hmap; // returns the hashmap with all the products
	}	
	
	public Double changeProductPrice(String productId, double newPrice)
	{
		try
		{
			String sql = "UPDATE PRODUCT SET PROD_PRICE = " + newPrice + "WHERE PROD_ID = " + productId; //creates the query to change price of a product of a certain ID
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		
		//Clean up enviroment 
		catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
		   e.printStackTrace();
		}finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){}
	      try{
	    	  if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
		}
		return newPrice; //Returns the new price of the product
	}
	

	public Double rateProduct(String ID, double rating, String userID)
	{
	String sql2= "UPDATE PRODUCT_RATING SET PROD_RATING = "+rating+" WHERE PROD_ID ='"+ID+"' AND USER_ID ='"+userID+"';";
		try
		{
			//String statement = "'" + ID + "," + "'" +  "'" + userID + ", " + "'" + rating; // this is getting all the infromation of the product and putting it into a string to be used later 
			String sql = "INSERT INTO PRODUCT_RATING(PROD_ID, USER_ID, PROD_RATING)" + "VALUES(" + "'" + ID + "'," + "'" + userID + "'," + rating + ");"; // this creates the SQL query to insert a new product into the system 
		
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		
		//Clean up enviroment 
		catch(SQLException se){
			try {
				stmt.executeUpdate(sql2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      //se.printStackTrace();
		}catch(Exception e){
	      e.printStackTrace();
		}finally{
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){}
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){  
	         se.printStackTrace();
	      }
	   }
		return rating;	
	}
		
	public double getPrice(String ID) //this function is created to get the current price of a product for the change price function
	{
		double price = 0.0;
		try
		{
			String sql = "SELECT PROD_PRICE FROM PRODUCT WHERE PROD_ID =" + ID; //creates a query to get the price of a product by a ID
			stmt = conn.createStatement();
			ResultSet set = stmt.executeQuery(sql);
		
			while(set.next()) //This loop puts the price of the product into a varaible
			{
	         double PROD_PRICE = set.getDouble("PROD_PRICE");
	         price = PROD_PRICE;
			}
	      set.close();
		}
		
		//Cleanr up enviroment
		catch(SQLException se){
	      se.printStackTrace();
		}catch(Exception e){
	      e.printStackTrace();
		}finally{
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){}
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){  
	         se.printStackTrace();
	      }
	   }
	   
		return price; //returns the price of the product
	}
}