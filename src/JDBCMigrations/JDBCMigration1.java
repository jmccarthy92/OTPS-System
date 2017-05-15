package JDBCMigrations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;


/**
 * @author James McCarthy
 *
 */
public class JDBCMigration1 
{

	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/";
	private static final String DB_NAME = "";
	
	private static final String USER = "root";
	private static final String PASS = "root";
	
	public static void main ( String [] args)
	{
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ptsmt = null;
		String s = new String();
		StringBuffer sb = new StringBuffer();
		
		try
		{
			Class.forName(JDBC_DRIVER);
			FileReader fr = new FileReader(new File("C:/Users/globe_000/workspace/Test1/src/SQLScripts/OTPSSchema.sql"));
			BufferedReader br = new BufferedReader(fr);
		
			while((s = br.readLine()) != null)
			{
				sb.append(s);
			}
			br.close();
		
			String [] temp = sb.toString().split(";");
			System.out.println("Attempting to connecto to the Database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			System.out.println("Creating the Database on your mysql local server....");
			stmt = conn.createStatement();
			String dropDB = "DROP DATABASE IF EXISTS OTPS_SYSTEM;";
			stmt.execute(dropDB);
			String createDB = "CREATE DATABASE IF NOT EXISTS OTPS_SYSTEM;";
			stmt.execute(createDB);
			String useDB = "USE OTPS_SYSTEM;";
			stmt.execute(useDB);
			String dropAccount = "DROP TABLE IF EXISTS ACCOUNT;";
			stmt.execute(dropAccount);
			String dropUser = "DROP TABLE IF EXISTS USER;";
			stmt.execute(dropUser);
			String dropAdmin = "DROP TABLE IF EXISTS ADMIN;";
			stmt.execute(dropAdmin);
			String dropOrders = "DROP TABLE IF EXISTS ORDERS;";
			stmt.execute(dropOrders);
			String dropProductList ="DROP TABLE IF EXISTS PRODUCTLIST;";
			stmt.execute(dropProductList);
			String dropProduct ="DROP TABLE IF EXISTS PRODUCT;";
			stmt.execute(dropProduct);
			String createAccount = "CREATE TABLE ACCOUNT "+
								  "(ACC_ID VARCHAR(30) NOT NULL PRIMARY KEY,"+
								  "ACC_USERNAME VARCHAR(30) NOT NULL,"+
								  "ACC_PASSWORD VARCHAR(20) NOT NULL,"+
								  "ACC_EMAIL VARCHAR(40) NOT NULL,"+
								  "ACC_PHONE VARCHAR(10));";
			stmt.execute(createAccount);
			String createUser = "CREATE TABLE USER "+
								"(ACC_ID VARCHAR(30) NOT NULL PRIMARY KEY,"+
								"FOREIGN KEY(ACC_ID) REFERENCES ACCOUNT(ACC_ID));";
			stmt.execute(createUser);
			String createAdmin = "CREATE TABLE ADMIN"+
								 "(ACC_ID VARCHAR(30) NOT NULL PRIMARY KEY,"+
								 "ADMIN_CODE VARCHAR(20) NOT NULL,"+
								 "FOREIGN KEY(ACC_ID) REFERENCES ACCOUNT(ACC_ID));";
			stmt.execute(createAdmin);
			String createOrders = "CREATE TABLE ORDERS "+
								  "(ORDER_ID VARCHAR(30) NOT NULL PRIMARY KEY,"+
								  "ACC_ID VARCHAR(30) NOT NULL,"+
								  "SALES_TOTAL DECIMAL(5,2) NOT NULL,"+
								  "ORDER_DATE TIMESTAMP NOT NULL,"+ 
								  "ORDER_STATUS ENUM('Shipped','UnShipped','Cancelled') NOT NULL,"+
								  "ORDER_ADDRESS VARCHAR(100) NOT NULL,"+
								  "CC_NUMBER VARCHAR(20) NOT NULL,"+
								  "CC_TYPE ENUM('Discover','Visa','MasterCard','AmericanExpress','none') NOT NULL,"+
								  "CSV_NUM VARCHAR(4) NOT NULL,"+
								  "EXP_DATE DATE NOT NULL,"+
								  "ORDER_ISPAID BOOLEAN NOT NULL,"+
								  "FOREIGN KEY(ACC_ID) REFERENCES USER(ACC_ID));";
			stmt.execute(createOrders);
			String createProduct = "CREATE TABLE PRODUCT "+
								   "(PROD_ID VARCHAR(30) NOT NULL PRIMARY KEY,"+
								   "PROD_NAME VARCHAR(100) NOT NULL,"+
								   "PROD_PRICE DECIMAL(5,2) NOT NULL,"+
								   "PROD_RATING DECIMAL(1,1) NOT NULL,"+
								   "PROD_QUANTITY SMALLINT NOT NULL );";
			stmt.execute(createProduct);
			String createProductList = "CREATE TABLE PRODUCTLIST "+
									    "(ORDER_ID VARCHAR(30) NOT NULL ,"+
									    "PROD_ID VARCHAR(30) NOT NULL ,"+
									    "PL_QUANTITY SMALLINT NOT NULL,"+
									    "CONSTRAINT PL_ID PRIMARY KEY (ORDER_ID,PROD_ID),"+
									    "FOREIGN KEY(ORDER_ID) REFERENCES ORDERS(ORDER_ID),"+
									    "FOREIGN KEY(PROD_ID) REFERENCES PRODUCT(PROD_ID));";
			stmt.execute(createProductList);
			System.out.println("MIGRATION SUCESSFUL.....!");
			

			
			for( int i = 0; i < temp.length; i++)
			{
				if(!temp[i].trim().equals(""))
				{
					stmt.executeUpdate(temp[i]);
					System.out.println("--->" + temp[i]);
				}
			}
			System.out.println("MIGRATION SUCESSFUL.....!");
			
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(stmt!=null)
					stmt.close();				
			}
			catch(SQLException se2)	{}
			try
			{
				if(conn!=null)
					conn.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
	}
	
}
