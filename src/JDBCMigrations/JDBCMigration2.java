package JDBCMigrations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCMigration2 {


	
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
			FileReader fr = new FileReader(new File("C:/Users/globe_000/workspace/OTPS_SYSTEM/src/SQLScripts/OTPSSchema.sql"));
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
