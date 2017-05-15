package OrderSubsystem;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;

import ProductManagementSystem.Product;
import AccountSubsystem.Account;
import AccountSubsystem.User;

/**
 * 
 * This class is the JDBC Class for the Order management subsystem
 * This class provides the queries for the following methods (use cases)
 * -createOrder
 * -cancelOrder
 * -shipOrder
 * -viewOrderHIstory
 * @author James McCarthy
 * 
 * @version 1.0
 *
 */

public class OrderCollectionJDBC 
{
	/**
	 * <p>
	 * Private variables includes are
	 * -conn  The connection object for the DB connection
	 * -stmt The Statement Object used to execute queries
	 * -JDBC_DRIVER - string representing the driver extension
	 * -DB_URL - string representing the Database URL
	 * -DB_NAME - '' ' represents the DB NAME YOU WORK ON.
	 * -USER - User name for the mySql local server on your computer
	 * -PASSWORD - Password for the mySql local server on your computer
	 */
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement ptsmt = null;
	
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	
		
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL =	/*"jdbc:mysql://fdb17.biz.nf:3306/";// +*/"jdbc:mysql://127.0.0.1:3306/";
										
	private static final String DB_NAME =   /* "2343435_otps" ;//+*/"OTPS_SYSTEM";
	
	private static final String USER = /* "2343435_otps"; */"root";
	private static final String PASS = /* "OTPSroot1234" */"root";
	
	/**
	 * <p>
	 * Constructor that establishes the connection to the mySql server
	 * through JDBC and the private variables declared above.
	 */
	public OrderCollectionJDBC()
	{
		try
		{
			String sql = "USE OTPS_SYSTEM;";
			Class.forName(JDBC_DRIVER).newInstance();
			conn = DriverManager.getConnection(DB_URL + DB_NAME,USER,PASS);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);

		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
	}
	
	
	public LinkedList<Product> getOrdersProductsQuery(int orderId)
	{
		String sql = "SELECT DISTINCT PRODUCTLIST.PROD_ID, PROD_NAME, PROD_PRICE, PL_QUANTITY " +
				 	 "FROM PRODUCT, PRODUCTLIST "
				 	 + "WHERE PRODUCTLIST.ORDER_ID ='"+orderId+"'"
				 	 		+ " AND PRODUCT.PROD_ID = PRODUCTLIST.PROD_ID "
				 	 		+ "ORDER BY ORDER_ID ASC; ";
				// 	 + "AND	  PRODUCTLIST.PROD_ID = PRODUCT.PROD_ID"+";";
		
		LinkedList<Product> productList = new LinkedList<Product>();
		try 
		{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(sql);
			Product prod;
			while(rs.next())
			{
				prod = new Product(rs.getString("PRODUCTLIST.PROD_ID"),rs.getString("PROD_NAME")
									,rs.getDouble("PROD_PRICE"), rs.getInt("PL_QUANTITY"));
				productList.add(prod);
			}
			rs.close();
		} 
		catch (SQLException se) 
		{
			// TODO Auto-generated catch block
			se.printStackTrace();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		return productList;
	
	}
	
	/**Creates the Order History HashMap that will be used to
	 * fill the OrderHistory Display for the User when looking at
	 * past Orders they have made. The HashMap is filled by a
	 * select query with the accountId = "the users account ID" conditional
	 * to find the Orders in the DB only associated with that AccountID.
	 * @param accountId - the Id used by the User
	 * @return hMap - Hash map that has all Orders Made by User
	 */
	public HashMap<String,Order> orderHistQuery(String accountIdIn)
	{
		int accountId = Integer.parseInt(accountIdIn);
		HashMap<String,Order> hMap = new HashMap<String,Order>();
		String sql = "SELECT * FROM ORDERS "
				+    "WHERE	ACC_ID='"+accountId+"' ORDER BY ORDER_ID ASC;";
		try 
		{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(sql);

			LinkedList<Product> productList;
			Order order ;
			CreditCard cc;
			CreditCardType type;
			Integer orderIdInt;
			Integer accId;
			String acc;
			boolean isPaid;
			Address address;
			User account;
			while(rs.next())
			{
				orderIdInt =  new Integer(rs.getInt("ORDER_ID"));
				String orderId = orderIdInt.toString();
				productList = getOrdersProductsQuery(orderIdInt);
				OrderStatus OType = OrderStatus.valueOf(rs.getString("ORDER_STATUS"));
				type = CreditCardType.valueOf(rs.getString("CC_TYPE"));
				cc = new CreditCard(rs.getString("CC_NUMBER"),rs.getString("CSV_NUM"),rs.getDate("EXP_DATE"),type);
				address = new Address(rs.getString("ORDER_ADDRESS"));
				accId = new Integer(rs.getInt("ACC_ID"));
				acc = accId.toString();
				isPaid = rs.getBoolean("ORDER_ISPAID");
				account = new User (accId);
				order = new Order(orderId,productList,rs.getDouble("SALES_TOTAL"), address,cc,account,rs.getTimestamp("ORDER_DATE"), OType);
				order.setPaymentStatus(isPaid);
				hMap.put(orderId, order);
			}
			rs.close();
		} 
		catch (SQLException se) 
		{
			// TODO Auto-generated catch block
			se.printStackTrace();
			return null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		return hMap;
	}
	
	/** **For Client Use** Similar to the Order History Query however this one takes no parms
	 * because it returns all of the Orders in the DB for display to the
	 * Client.
	 * @return HashMap with all the Orders extracted from the DB.
	 */
	public HashMap<String,Order> getAllOrderQuery()
	{
		HashMap<String,Order> hMap = new HashMap<String,Order>();
		
		String sql = "SELECT * FROM ORDERS ORDER BY ORDER_ID ASC;";
				
		try 
		{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(sql);
			
			Order order ;
			int parsedOrderId;
			String orderId;
			LinkedList<Product> productList;
			CreditCard cc;
			CreditCardType type;
			Address address;
			User account;
			boolean isPaid;
			while(rs.next())
			{
				orderId = rs.getString("ORDER_ID");
				parsedOrderId = Integer.parseInt(orderId);
				productList = getOrdersProductsQuery(parsedOrderId);
				type = CreditCardType.valueOf(rs.getString("CC_TYPE"));
				OrderStatus OType = OrderStatus.valueOf(rs.getString("ORDER_STATUS"));
				cc = new CreditCard(rs.getString("CC_NUMBER"),rs.getString("CSV_NUM"),rs.getDate("EXP_DATE"),type);
				address = new Address(rs.getString("ORDER_ADDRESS"));
				Integer acc = new Integer (rs.getInt("ACC_ID"));
				isPaid = rs.getBoolean("ORDER_ISPAID");
				String accId = acc.toString();
				account = new User (acc);
				order = new Order(orderId,productList,rs.getDouble("SALES_TOTAL"), address,cc,account,rs.getTimestamp("ORDER_DATE"),OType);
				order.setPaymentStatus(isPaid);
				hMap.put(orderId, order);
			}
			rs.close();
		} 
		catch (SQLException se) 
		{
			// TODO Auto-generated catch block
			se.printStackTrace();
			return null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return hMap;
	}
	
	public Order searchOrderQuery(String orderIdIn)
	{
		int orderId = Integer.parseInt(orderIdIn);
		String sql = //"SELECT ORDER_ID, SALES_TOTAL, ORDER_ADDRESS, CC_NUMBER, CC_TYPE, CSV_NUM, EXP_DATE, ORDER_DATE,ACC_ID"
					"SELECT *"
				+    " FROM ORDERS"
				+	 " WHERE ORDER_ID ='"+orderId+"';";
		Order order = null ;
		try 
		{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
			//stmt.executeUpdate(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
		
			CreditCard cc;
			CreditCardType type;
			Address address;
			User account;
			boolean isPaid;
		//	String orderID;
			LinkedList<Product> productList;
			
			while(rs.next())
			{
			//	orderID = rs.getString("ORDER_ID");
				OrderStatus OType = OrderStatus.valueOf(rs.getString("ORDER_STATUS"));
				productList = getOrdersProductsQuery(orderId);
				type = CreditCardType.valueOf(rs.getString("CC_TYPE"));
				cc = new CreditCard(rs.getString("CC_NUMBER"),rs.getString("CSV_NUM"),rs.getDate("EXP_DATE"),type);
				address = new Address(rs.getString("ORDER_ADDRESS"));
				Integer accId = new Integer (rs.getInt("ACC_ID"));
				String acc = accId.toString();
				account = new User (accId);
				isPaid = rs.getBoolean("ORDER_ISPAID");
				order = new Order(orderIdIn,productList,rs.getDouble("SALES_TOTAL"), address,cc,account,rs.getTimestamp("ORDER_DATE"), OType);
				order.setPaymentStatus(isPaid);
			}
			rs.close();
	
		} 
		catch (SQLException se) 
		{
			// TODO Auto-generated catch block
			se.printStackTrace();
			return null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return order;
	}
	
	public HashMap<String,Order> searchOrderStatusQuery(OrderStatus orderStatIn)
	{
		HashMap<String,Order> hMap = new HashMap<String,Order>();
	//	int orderId = Integer.parseInt(orderIdIn);
		String sql = //"SELECT ORDER_ID, SALES_TOTAL, ORDER_ADDRESS, CC_NUMBER, CC_TYPE, CSV_NUM, EXP_DATE, ORDER_DATE,ACC_ID"
					"SELECT *"
				+    " FROM ORDERS"
				+	 " WHERE ORDER_STATUS ='"+orderStatIn+"' "
						+ "ORDER BY ORDER_ID ASC;";
		Order order = null ;
		try 
		{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
			//stmt.executeUpdate(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
		
			CreditCard cc;
			CreditCardType type;
			Address address;
			String orderID;
			User account;
			boolean isPaid;
			Integer ordIdInt;
		//	String orderID;
			LinkedList<Product> productList;
			
			while(rs.next())
			{
				orderID = rs.getString("ORDER_ID");
				ordIdInt = new Integer(orderID);
				OrderStatus OType = OrderStatus.valueOf(rs.getString("ORDER_STATUS"));
				productList = getOrdersProductsQuery(ordIdInt);
				type = CreditCardType.valueOf(rs.getString("CC_TYPE"));
				cc = new CreditCard(rs.getString("CC_NUMBER"),rs.getString("CSV_NUM"),rs.getDate("EXP_DATE"),type);
				address = new Address(rs.getString("ORDER_ADDRESS"));
				Integer accId = new Integer (rs.getInt("ACC_ID"));
				String acc = accId.toString();
				account = new User (accId);
				isPaid = rs.getBoolean("ORDER_ISPAID");
				order = new Order(orderID,productList,rs.getDouble("SALES_TOTAL"), address,cc,account,rs.getTimestamp("ORDER_DATE"), OType);
				order.setPaymentStatus(isPaid);
				hMap.put(orderID, order);
			}
			rs.close();
	
		} 
		catch (SQLException se) 
		{
			// TODO Auto-generated catch block
			se.printStackTrace();
			return null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return hMap;
	}
	
	
	/**
	 * **For Client Use** Takes the OrderId searches for it in the DB, than
	 * alters the OrderStatus column to shipped.
	 * @param OrderId - The Unique ID of the Order to be shipped.
	 * @return True or False - if the operation was successful or not
	 */
	public boolean shipOrderQuery(String orderId)
	{
		boolean shipped;
		String sql = "UPDATE ORDERS " +
				 "SET ORDER_STATUS = 'SHIPPED', ORDER_ISPAID = TRUE "
			   + "WHERE ORDER_ID ='"+orderId+"' "
			   		+ "AND ORDER_STATUS = 'UNSHIPPED';";
		try 
		{
			stmt = conn.createStatement();
			int o =stmt.executeUpdate(sql);
			if( o == 0)
				shipped = false;
			else
				shipped = true;
		} 
		catch (SQLException se) 
		{
			// TODO Auto-generated catch block
			se.printStackTrace();
			return false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return shipped;
	}
	
	/**
	 *  Takes the ORder ID searches for it in the DB, than alters the
	 *  order status column to Cancelled.
	 * @param OrderId - the Unique Id of the Order 
	 * @return True or False - If operation was successful or not
	 */
	public boolean cancelOrderQuery(String orderId)
	{
		boolean cancelled;
		String sql = "UPDATE ORDERS " +
					 "SET ORDER_STATUS = 'CANCELLED', ORDER_ISPAID = FALSE "
				   + "WHERE ORDER_ID ='"+orderId+"' "
				   		+ "AND ORDER_STATUS = 'UNSHIPPED';";
		try 
		{
			stmt = conn.createStatement();
			int o = stmt.executeUpdate(sql);
			if( o == 0)
				cancelled = false;
			else
				cancelled = true;
		} 
		catch (SQLException se) 
		{
			// TODO Auto-generated catch block
			//.cancelled.se.printStackTrace();
			return false;
		}
		catch (Exception e)
		{
	//		e.printStackTrace();
			return false;
		}
		return cancelled;
	}
	
	/**
	 * Used by the Admin (Client) to remove cancelled orders from the Database that 
	 * no longer need to be seen or used in the System.
	 * @param orderId - Id of the order to be removed.
	 * @return Order - returns the ORder that was deleted
	 * 			null - order never existed.
	 */
	public Order removeOrderQuery(String orderId)
	{
		
		Order order = null;
		String sql1 = "SELECT * "
					+"FROM ORDERS "
					+"WHERE ORDER_ID ='"+orderId+"';";
		String sql = "DELETE FROM ORDERS " 
				   + "WHERE ORDER_ID ='"+orderId+"';";
		try 
		{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
			//stmt.executeUpdate(sql);
			ResultSet rs = stmt.executeQuery(sql1);
			rs.first();
			
			int orderIdInt = Integer.parseInt(orderId);
			
			LinkedList<Product> productList = getOrdersProductsQuery(orderIdInt);
			OrderStatus OType = OrderStatus.valueOf(rs.getString("ORDER_STATUS"));
			CreditCardType type = CreditCardType.valueOf(rs.getString("CC_TYPE"));
			CreditCard cc = new CreditCard(rs.getString("CC_NUMBER"),rs.getString("CSV_NUM"),rs.getDate("EXP_DATE"),type);
			Address address = new Address(rs.getString("ORDER_ADDRESS"));
			Integer accId = new Integer (rs.getInt("ACC_ID"));
			String acc = accId.toString();
			User account = new User (accId);
			boolean isPaid = rs.getBoolean("ORDER_ISPAID");
			order = new Order(orderId,productList,rs.getDouble("SALES_TOTAL"), address,cc,account,rs.getTimestamp("ORDER_DATE"),OType);
			order.setPaymentStatus(isPaid);
			
			// Deletes the Record
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		}
		catch(NumberFormatException nfe)
		{
			
		}
		catch (SQLException se) 
		{
			// TODO Auto-generated catch block
			//se.printStackTrace();
			//return order;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		//	return order;
		}
		return order;
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * Inserts the Order created by the User into the DB Orders table
	 * with all the attributes and values entered by the User 
	 * @param order - The Order to be inserted into the DB
	 * @return true or false -if the operation was Successful
	 */
	public boolean createOrderQuery(Order order)
	{
		String parsedOrderStatus = order.getOrderStatus();
		String expDate = sf.format(order.getCreditCard().getExpDate());
	//	System.out.println(order.getAccount().getID());
		int accId = order.getAccount().getID() ; //Integer.parseInt(order.getAccount().getId());
	//	int orderId = Integer.parseInt(order.getOrderId());
	//	Double parsedDouble = new Double(order.getSalesTotal());
	//	String stringSales = parsedDouble.toString();
		String sql = "INSERT INTO ORDERS ( ACC_ID, SALES_TOTAL,"
				+ " ORDER_DATE, ORDER_STATUS, ORDER_ADDRESS, "+
				   "CC_NUMBER, CC_TYPE, CSV_NUM, EXP_DATE, "+
				   "ORDER_ISPAID)" +
					 "VALUES ( "+ //orderId+ // (SELECT ACC_ID FROM ACCOUNT WHERE ACC_ID="
					 "'"+accId/*+order.getAccount().getId()*/+"' , "+order.getSalesTotal()+", '"+sdf.format(order.getOrderDate()) + "', "
					 +"'"+parsedOrderStatus.toString()+"', '" + order.getAddress().getAddressForDB()+"' , '"+ order.getCreditCard().getCCNum()+
					 "', '"+order.getCreditCard().getCCType()+"', '" + order.getCreditCard().getCSV()+"', '"
					 +expDate+"', " + order.getPaymentStatus()+");";
		String sql2 = "SELECT ORDER_ID FROM ORDERS WHERE ACC_ID = "+accId+" AND "+
					  "ORDER_DATE = '" +sdf.format(order.getOrderDate())+ "' ;";
		System.out.println(order.getOrderDate());
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(sql2);
			rs.first();
			order.setOrderId( String.valueOf(rs.getInt("ORDER_ID")));
			createProductListQuery(order, order.getProudctList());
			
		} 
		catch (SQLException se) 
		{
			// TODO Auto-generated catch block
			se.printStackTrace();
			return false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean createProductListQuery(Order order, LinkedList<Product> sc)
	{
		System.out.println(order);
		int ordId = Integer.parseInt(order.getOrderId());
		int prodId =0;
		int prodQuant = 0;
		
		String sql = "INSERT INTO PRODUCTLIST ( ORDER_ID, PROD_ID, PL_QUANTITY) "
				+ "VALUES ( "+ordId+ ", "+prodId+", "+prodQuant+");";
		
		try 
		{
			stmt = conn.createStatement();
			
			for( Product p : sc)
			{
				
				prodId = Integer.parseInt(p.getProductId());
				prodQuant = p.getCartQuantity();
				 sql = "INSERT INTO PRODUCTLIST ( ORDER_ID, PROD_ID, PL_QUANTITY) "
							+ "VALUES ( "+ordId+ ", "+prodId+", "+prodQuant+");";
				stmt.executeUpdate(sql);
			}
	
		} 
		catch (SQLException se) 
		{
			// TODO Auto-generated catch block
			se.printStackTrace();
			return false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	/**<p>
	 * Closes the connection for the JDBC in case
	 * they haven't been closes already
	 * @return True or False- If operation is successful
	 */
	public boolean closeConnection()
	{
		try
		{
			if(stmt!=null)
				ptsmt.close();
		        stmt.close();
		}
		catch(SQLException se2)
		{    }// nothing we can do
		try
		{
			if(conn!=null)
				conn.close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		return true;
	}
		 				
}
