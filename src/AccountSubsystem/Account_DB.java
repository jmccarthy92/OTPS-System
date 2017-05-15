package AccountSubsystem;

import java.sql.*;
import java.util.HashMap;

/**
 * @author Randy Gopaul
 * This is the Account Database API.
 * The Class contains methods to add, delete, view, search, and modify Users and Admins within the OTPS_SYSTEM Database.
 */
public class Account_DB
{	
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/OTPS_SYSTEM";

    //Database credentails
    static final String USER = "root";
    static final String PASS = "root";

    //Creating the statement and connection variables
    private Connection conn = null;
    private Statement stmt = null;
    
    /**
     * Establishes a connection to the OTPS_SYSTEM Database to Allow Changes
     */
    public void PB() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");

        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);

        String use = "USE otps_system;"; //sets up the use Query to use control the OTPS_system
        stmt = conn.createStatement();
        stmt.executeQuery(use);
    }


    /**
     * Adds a User into the DataBase
     * @param newUser the New User to Be Entered
     * @return newUser the New User That Was Entered
     */
    public User addUser(User newUser) throws SQLException
    {
        try
        {
            String statement = "'" + newUser.getID() // Turing All Instance Variables of the Account into a String
                                   + "', '" 
                                   + newUser.getUserName() 
                                   + "', '" 
                                   + newUser.getPassword()
                                   + "', '" 
                                   + newUser.getEmail() 
                                   + "', '" 
                                   + newUser.getPhoneNumber()
                                   + "', '" 
                                   + newUser.getAddress()
                                   + "', " 
                                   + newUser.isAdmin()
                                   ;
            
            //CHECKS IF A DUPLICATE USERNAME HAS BEEN ENTERED.
            String sql = "SELECT * FROM user WHERE USER_USERNAME = \""+ newUser.getUserName() +"\" "+ ";";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next())
                return null; //Duplicate Found.
            else //Add into Database.
                sql = "INSERT INTO USER(USER_ID, USER_USERNAME, USER_PASSWORD, USER_EMAIL, USER_PHONE, USER_ADDRESS, USER_ISADMIN)" 
                         + "VALUES(" + statement + ");"; // this creates the SQL query that will be run to the OTPS_System DB
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            //SET ACCOUNT ID TO USER OBJECT. Since DB AutoIncrements
            sql = "SELECT * FROM user WHERE USER_USERNAME = \""+ newUser.getUserName() +"\" "+ ";";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next())
                newUser.setID(rs.getInt(1));

        }
        catch(SQLException se){se.printStackTrace();} //Handle errors for JDBC
        catch(Exception e){e.printStackTrace();} //Handle errors for Class.forName

        finally //finally block used to close resources
        {
          try
          {
            if(stmt!=null)
                stmt.close();
          }
          catch(SQLException se2){}// nothing we can do
          try
          {
            if(conn!=null)
                conn.close();
          }
          catch(SQLException se){se.printStackTrace();}//end finally try
        }//end try
        
        return newUser;
    }
    
    /**
     * Adds an Admin into the DataBase
     * @param newAdmin the New Admin to Be Entered
     * @return newAdmin the New Admin That Was Entered
     */
    public Account addAdmin(Account newAdmin) throws SQLException
    {
        try
        {
            String statement = "'" + newAdmin.getID() // Turing All Instance Variables of the Account into a String
                                   + "', '" 
                                   + newAdmin.getUserName() 
                                   + "', '" 
                                   + newAdmin.getPassword()
                                   + "', '" 
                                   + newAdmin.getEmail()
                                   + "', " 
                                   + newAdmin.isAdmin()
                                   ;
            
            //CHECKS IF A DUPLICATE USERNAME HAS BEEN ENTERED.
            String sql = "SELECT * FROM user WHERE USER_USERNAME = \""+ newAdmin.getUserName() +"\" "+ ";";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next())
                return null; //Duplicate Found.
            else //Add into Database.
                sql = "INSERT INTO USER(USER_ID, USER_USERNAME, USER_PASSWORD, USER_EMAIL, USER_ISADMIN)" 
                         + "VALUES(" + statement + ");"; // this creates the SQL query that will be run to the OTPS_System DB
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            
            //SET ACCOUNT ID TO USER OBJECT. Since DB AutoIncrements
            sql = "SELECT * FROM user WHERE USER_USERNAME = \""+ newAdmin.getUserName() +"\" "+ ";";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next())
                newAdmin.setID(rs.getInt(1));
            
            //INSERT INTO ADMIN TABLE
            sql = "INSERT INTO ADMIN(USER_ID, ADMIN_CODE)" 
                         + "VALUES(" + newAdmin.getID() + "," + newAdmin.getAdminCode() + ");";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        }
        catch(SQLException se){se.printStackTrace();} //Handle errors for JDBC
        catch(Exception e){e.printStackTrace();} //Handle errors for Class.forName

        finally //finally block used to close resources
        {
          try
          {
            if(stmt!=null)
                stmt.close();
          }
          catch(SQLException se2){}// nothing we can do
          try
          {
            if(conn!=null)
                conn.close();
          }
          catch(SQLException se){se.printStackTrace();}//end finally try
        }//end try
        
        return newAdmin;
    }

    /**
     * Deletes a User from the Database
     * @param accountID the ID of the Account to Delete
     * @return TmpAccount the Account That was Deleted
     */
    public User deleteUser(int accountID) throws SQLException
    {
        User TmpAccount = new User();
        
        try
        {

            String sql = "SELECT * FROM admin WHERE USER_ID = " + accountID +";"; //Checks if Account is an Admin to Prevent Deleting it
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            

            if(rs.next()) //Returns Null if the Account Was an Admin
                return null;
            
            sql = "SELECT * FROM user WHERE USER_ID = " + accountID +";";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            if(!rs.next()) //Returns Null if the Account Was not Found
                return null;

                TmpAccount.setID(rs.getInt(1));
                TmpAccount.setUserName(rs.getString(2));
                TmpAccount.setPassword(rs.getString(3));
                TmpAccount.setEmail(rs.getString(4));
                TmpAccount.setPhoneNum(rs.getString(5));
                TmpAccount.setAddress(rs.getString(6));
                TmpAccount.setAdmin(rs.getBoolean(7));
            

            sql = "DELETE FROM user WHERE USER_ID = " + accountID +";";
            stmt.executeUpdate(sql);

        }
        catch(SQLException se){se.printStackTrace();} //Handle errors for JDBC
        catch(Exception e){e.printStackTrace();} //Handle errors for Class.forName

        finally //finally block used to close resources
        {
          try
          {
            if(stmt!=null)
                stmt.close();
          }
          catch(SQLException se2){}// nothing we can do
          try
          {
            if(conn!=null)
                conn.close();
          }
          catch(SQLException se){se.printStackTrace();}//end finally try
        }//end try
         return TmpAccount;
    }

    /**
     * Searches the Database and Returns the User Account Found
     * @param accountID the Account ID to search for the User
     * @return AccountFound the Account That Was Found
     */
    public User searchUser(int accountID) throws SQLException
    {
        User AccountFound = new User();

        try
        {
            String sql = "SELECT * FROM admin WHERE USER_ID = " + accountID +";"; //Checks if Account is an Admin to Prevent Deleting it
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            

            if(rs.next()) //Returns Null if the Account Was an Admin
                return null;
        
            sql = "SELECT * FROM user WHERE USER_ID = " + accountID +";";

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if(!rs.next())
                return null;

            AccountFound.setID(rs.getInt(1));
            AccountFound.setUserName(rs.getString(2));
            AccountFound.setPassword(rs.getString(3));
            AccountFound.setEmail(rs.getString(4));
            AccountFound.setPhoneNum(rs.getString(5));
            AccountFound.setAddress(rs.getString(6));

        }
        catch(SQLException se){se.printStackTrace();} //Handle errors for JDBC
        catch(Exception e){e.printStackTrace();} //Handle errors for Class.forName

        finally //finally block used to close resources
        {
          try
          {
            if(stmt!=null)
                stmt.close();
          }
          catch(SQLException se2){}// nothing we can do
          try
          {
            if(conn!=null)
                conn.close();
          }
          catch(SQLException se){se.printStackTrace();}//end finally try
        }//end try
        return AccountFound;
    }
    
    /**
     * Searches the Database and Returns the Admin Account Found
     * @param accountID the Account ID to search for the User
     * @return AccountFound the Account That Was Found
     */
    public Account searchAdmin(int accountID) throws SQLException
    {
        Account AccountFound = new Account();

        try
        {
            String sql = "SELECT * FROM user WHERE USER_ID = " + accountID +";";

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if(!rs.next())
                return null;

            AccountFound.setID(rs.getInt(1));
            AccountFound.setUserName(rs.getString(2));
            AccountFound.setPassword(rs.getString(3));
            AccountFound.setEmail(rs.getString(4));
            AccountFound.setAdmin(rs.getBoolean(7));

            
            //Check if Account is in Admin Table
            sql = "SELECT * FROM admin WHERE USER_ID = " + AccountFound.getID() +";";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            if(!rs.next())
              return null;

            AccountFound.setID(rs.getInt(1));
            AccountFound.setAdminCode(rs.getInt(2));

        }
        catch(SQLException se){se.printStackTrace();} //Handle errors for JDBC
        catch(Exception e){e.printStackTrace();} //Handle errors for Class.forName

        finally //finally block used to close resources
        {
          try
          {
            if(stmt!=null)
                stmt.close();
          }
          catch(SQLException se2){}// nothing we can do
          try
          {
            if(conn!=null)
                conn.close();
          }
          catch(SQLException se){se.printStackTrace();}//end finally try
        }//end try
        return AccountFound;
    }
    
    /**
     * Returns a List of All Accounts in the Database
     * @return A HashMap Containing a List of All Accounts in the Database
     */
    public HashMap<Integer,User> viewAccounts(HashMap <Integer, User> AccountstoView)throws SQLException//NOT DONE.
    {
        try
        {
            String sql = "SELECT * FROM user;";

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);           
            
            while(rs.next())
            {
                int id = (rs.getInt(1));
                String Username = (rs.getString(2));
                String Password = (rs.getString(3));
                String Email = (rs.getString(4));
                String Phone = (rs.getString(5));
                String Address = (rs.getString(6));
                Boolean isAdmin = (rs.getBoolean(7));
                User AccountFound = new User(Username, Password, id, Email, Phone, Address, isAdmin);
                AccountstoView.put(id, AccountFound);         
            }
            rs.close();
        }
        catch(SQLException se){se.printStackTrace();} //Handle errors for JDBC
        catch(Exception e){e.printStackTrace();} //Handle errors for Class.forName

        finally //finally block used to close resources
        {
          try
          {
            if(stmt!=null)
                stmt.close();
          }
          catch(SQLException se2){}// nothing we can do
          try
          {
            if(conn!=null)
                conn.close();
          }
          catch(SQLException se){se.printStackTrace();}//end finally try
        }//end try

        return AccountstoView;
    }
    
    /**
     * Searches the Database for Matching Username and Email
     * @param Username the Username Related to the Account Trying to Recover Their Password
     * @param Email the Email Related to the Account Trying to Recover Their Password
     * @return Password related to the Account
     */
    public String forgotPassword(String Username, String Email, String Password) throws SQLException
    {
        String newPassword = "";
        try
        {
            String sql = "SELECT * FROM user WHERE USER_USERNAME = " + "'" + Username + "'" + " AND USER_EMAIL = " + "'" + Email + "'" + ";";

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if(!rs.next())
                return null;
            
            //Update Password After Reset
            sql = "UPDATE USER SET USER_PASSWORD = \""+ Password +"\" " + "WHERE USER_USERNAME = \"" + Username + "\";";
            System.out.println(sql);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }
        catch(SQLException se){se.printStackTrace();} //Handle errors for JDBC
        catch(Exception e){e.printStackTrace();} //Handle errors for Class.forName

        finally //finally block used to close resources
        {
          try
          {
            if(stmt!=null)
                stmt.close();
          }
          catch(SQLException se2){}// nothing we can do
          try
          {
            if(conn!=null)
                conn.close();
          }
          catch(SQLException se){se.printStackTrace();}//end finally try
        }//end try
        return Password;
    }
    

    /**
    * Checks if the Username and Password is in the Database and Logs them In
    * @return returns true if the user is found and both username and password match
    */
    public User loginUser(String username, String password) throws SQLException
    {
        User Account = new User();
        try
        {
            String sql = "SELECT * FROM user WHERE USER_USERNAME = \""+ username +"\" and USER_PASSWORD = \""+password+"\"";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if(!rs.next())
              return null;

            Account.setID(rs.getInt(1));
            Account.setUserName(rs.getString(2));
            Account.setPassword(rs.getString(3));
            Account.setEmail(rs.getString(4));
            Account.setPhoneNum(rs.getString(5));
            Account.setAddress(rs.getString(6));
            Account.setAdmin(rs.getBoolean(7));
            
            //Prevents an Admin from Loggin in as a User
            sql = "SELECT * FROM admin WHERE USER_ID = " + Account.getID() +";";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if(rs.next())
              return null;
        }
        catch(SQLException se){se.printStackTrace();} //Handle errors for JDBC
        catch(Exception e){e.printStackTrace();} //Handle errors for Class.forName

        finally //finally block used to close resources
        {
          try
          {
            if(stmt!=null)
                stmt.close();
          }
          catch(SQLException se2){}// nothing we can do
          try
          {
            if(conn!=null)
                conn.close();
          }
          catch(SQLException se){se.printStackTrace();}//end finally try
        }//end try

        return Account;
    }
    /**
     * Logs an Admin into the DB
     * @param username username for the account to login
     * @param password password for the account to login
     * @return The Admin Account That Was Logged in
     */
    public Account loginAdmin(String username, String password)
    {
        Account Account = new Account();
        try
        {
            String sql = "SELECT * FROM user WHERE USER_USERNAME = \""+ username +"\" and USER_PASSWORD = \""+password+"\"";
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if(!rs.next())
              return null;

            Account.setID(rs.getInt(1));
            Account.setUserName(rs.getString(2));
            Account.setPassword(rs.getString(3));
            Account.setEmail(rs.getString(4));
            Account.setAdmin(rs.getBoolean(7));
            
            sql = "SELECT * FROM admin WHERE USER_ID = " + Account.getID() +";";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if(!rs.next())
              return null;

            Account.setID(rs.getInt(1));
            Account.setAdminCode(rs.getInt(2));
        }
        catch(SQLException se){se.printStackTrace();} //Handle errors for JDBC
        catch(Exception e){e.printStackTrace();} //Handle errors for Class.forName

        finally //finally block used to close resources
        {
          try
          {
            if(stmt!=null)
                stmt.close();
          }
          catch(SQLException se2){}// nothing we can do
          try
          {
            if(conn!=null)
                conn.close();
          }
          catch(SQLException se){se.printStackTrace();}//end finally try
        }//end try

        return Account;
    }
    
    /**
    * Allows the User the Edit Their Basic Account Info and Alter their Password, Username, and Address info.
    * @param editUser the User who's account info will be edited in the Database
    */
    public boolean editBasicInfo(User editUser, int choice, String newinfo) throws SQLException
    {
        try
        {
            String sql = "";
            
            switch (choice)
            {
                case 1:
                    //CHECKS IF A DUPLICATE USERNAME HAS BEEN ENTERED.
                    sql = "SELECT * FROM user WHERE USER_USERNAME = \""+ newinfo +"\" "+ ";";
                    stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);

                    if(rs.next())
                        return false; //Duplicate Username found, Username Cannot be Changed
                            
                    sql = "UPDATE USER SET USER_USERNAME = \""+ newinfo +"\" " + "WHERE USER_ID = " + editUser.getID() + ";";
                    System.out.println(sql);
                    stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                    return true;
                case 2:
                    sql = "UPDATE USER SET USER_PASSWORD = \""+ newinfo +"\" WHERE USER_ID = " + editUser.getID() + ";";
                    stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                    return true;
                case 3:
                    sql = "UPDATE USER SET USER_EMAIL = \""+ newinfo +"\" WHERE USER_ID = " + editUser.getID() + ";";
                    stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                    return true;
                case 4:
                    sql = "UPDATE USER SET USER_PHONE = \""+ newinfo +"\" WHERE USER_ID = " + editUser.getID() + ";";
                    stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                    return true;
                case 5:
                    sql = "UPDATE USER SET USER_ADDRESS = \""+ newinfo +"\" WHERE USER_ID = " + editUser.getID() + ";";
                    stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                    return true;
                default:
                    return false;
            }
            
        }
        catch(SQLException se){se.printStackTrace(); return false;} //Handle errors for JDBC
        catch(Exception e){e.printStackTrace();} //Handle errors for Class.forName

        finally //finally block used to close resources
        {
          try
          {
            if(stmt!=null)
                stmt.close();
          }
          catch(SQLException se2){}// nothing we can do
          try
          {
            if(conn!=null)
                conn.close();
          }
          catch(SQLException se){se.printStackTrace();}//end finally try
        }//end try
        
        return true;
    }
    
    /**
     * Deletes an Admin from the Database
     * @param accountID the ID of the Admin Account to delete.
     * @return TmpAccount the Account That was Deleted
     */
    public Account deleteAdmin(int accountID) throws SQLException
    {
        Account TmpAccount = new Account();

        try
        {
            String sql = "SELECT * FROM admin WHERE USER_ID = " + accountID +";"; //Checks if Account is an Admin to Prevent Deleting a User
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if(!rs.next()) //Would Return Null if a User ID within the Account DB was not Found
                return null;
            
            sql = "SELECT * FROM user WHERE USER_ID = " + accountID +";";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                TmpAccount.setID(rs.getInt(1));
                TmpAccount.setUserName(rs.getString(2));
                TmpAccount.setPassword(rs.getString(3));
                TmpAccount.setEmail(rs.getString(4));
                TmpAccount.setAdmin(rs.getBoolean(7));
            }
            
            sql = "SELECT * FROM admin WHERE USER_ID = " + accountID +";";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                TmpAccount.setID(rs.getInt(1));
                TmpAccount.setAdminCode(rs.getInt(2));
            }

            sql = "DELETE FROM admin WHERE USER_ID = " + accountID +";";
            stmt.executeUpdate(sql);

             sql = "DELETE FROM user WHERE USER_ID = " + accountID +";";
            stmt.executeUpdate(sql);
            

        }
        catch(SQLException se){se.printStackTrace();} //Handle errors for JDBC
        catch(Exception e){e.printStackTrace();} //Handle errors for Class.forName

        finally //finally block used to close resources
        {
          try
          {
            if(stmt!=null)
                stmt.close();
          }
          catch(SQLException se2){}// nothing we can do
          try
          {
            if(conn!=null)
                conn.close();
          }
        catch(SQLException se){se.printStackTrace();}//end finally try
        }//end try
        return TmpAccount;
    }

}