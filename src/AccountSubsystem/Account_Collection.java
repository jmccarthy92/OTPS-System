package AccountSubsystem;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * @author Randy Gopaul
 * @author Nicholas Themeli
 * This is the Account Collection API.
 * The Class is responsible for using methods to connect to the OTPS_SYSTEM Database 
 * and Executing Methods within the Account_DB Object to Alter Accounts.
 */
public class Account_Collection
{
    //private HashMap <Integer, User> UserCollection;
    Account_DB DB = new Account_DB();

    /**
     * Adds a User into the DataBase
     * @param newUser the New User to Be Entered
     * @return newUser the New User That Was Entered
     */
    public User addUser(User newUser) throws SQLException, ClassNotFoundException
    {
        DB.PB();
        return DB.addUser( newUser );
    }
    
    public String forgotPassword(String username, String email, String password) throws SQLException, ClassNotFoundException
    {
        DB.PB();
        return DB.forgotPassword(username, email, password);
    }
    
    /**
     * Adds an Admin into the DataBase
     * @param newAdmin the New Admin to Be Entered
     * @return newAdmin the New Admin That Was Entered
     */
    public Account addAdmin(Account newAdmin) throws SQLException, ClassNotFoundException
    {
        DB.PB();
        return DB.addAdmin(newAdmin);
    }

    /**
     * Deletes a User from the Database
     * @param accountID the ID of the Account to Delete
     * @return TmpAccount the Account That was Deleted
     */
    public User deleteUser(int accountID) throws SQLException, ClassNotFoundException
    {
       DB.PB();
       return DB.deleteUser( accountID );
    }
    
    /**
     * Deletes a User from the Database
     * @param accountID the ID of the Admin Account to delete.
     * @return TmpAccount the Account That was Deleted
     */
    public Account deleteAdmin(int accountID) throws SQLException, ClassNotFoundException
    {
       DB.PB();
       return DB.deleteAdmin( accountID );
    }
    
    /**
     * Searches the Database and Returns the Account Found
     * @param accountID the Account ID to search for the User
     * @return AccountFound the Account That Was Found
     */
    public User searchUser(int accountID) throws SQLException, ClassNotFoundException
    {
       DB.PB();
       return DB.searchUser( accountID );
    }
    
    /**
     * Searches the Database and Returns the Account Found
     * @param accountID the Account ID to search for the Admin
     * @return AccountFound the Account That Was Found
     */
    public Account searchAdmin(int accountID) throws SQLException, ClassNotFoundException
    {
       DB.PB();
       return DB.searchAdmin( accountID );
    }

    /**
     * Returns a List of All Accounts in a Hashmap
     */
    public HashMap<Integer,User> viewAccounts() throws SQLException, ClassNotFoundException // NOT DONE.
    {
        DB.PB();
        HashMap <Integer, User> UserCollection = new HashMap<>();
        UserCollection = DB.viewAccounts(UserCollection);
        System.out.println(UserCollection.toString());
       // UserCollection.clear();
        return UserCollection;
    }
    /**
     * Logs an Account into the DB
     * @param username username for the account to login
     * @param password password for the account to login
     * @return The User Account That Was Logged in
     */
    public User loginUser(String username, String password) throws SQLException, ClassNotFoundException
    {
        DB.PB();
        return DB.loginUser(username, password);
    }
    
    /**
     * Logs an Admin into the DB
     * @param username username for the account to login
     * @param password password for the account to login
     * @return The Admin Account That Was Logged in
     */
    public Account loginAdmin(String username, String password) throws SQLException, ClassNotFoundException
    {
        DB.PB();
        return DB.loginAdmin(username, password);
    }
    
    /**
    * Allows the User the Edit Their Basic Account Info and Alter their Password, Username, and Address info.
    * @param editUser the User who's account info will be edited
    */
    public boolean editBasicInfo(User editUser, int choice, String newfield) throws SQLException, ClassNotFoundException
    {
        DB.PB();
        switch (choice)
        {
            case 1:
                if(DB.editBasicInfo(editUser, choice, newfield))
                    editUser.setUserName(newfield);
                else
                    return false;
                break;
            case 2:
                if(DB.editBasicInfo(editUser, choice, newfield))
                    editUser.setPassword(newfield);
                else
                    return false;
                break;
            case 3:
                if(DB.editBasicInfo(editUser, choice, newfield))
                    editUser.setEmail(newfield);
                else
                    return false;
                break;
            case 4:
                if(DB.editBasicInfo(editUser, choice, newfield))
                    editUser.setPhoneNum(newfield);
                else
                    return false;
                break;
            case 5:
                if(DB.editBasicInfo(editUser, choice, newfield))
                    editUser.setAddress(newfield);
                else
                    return false;
                break;
        }
        
        return true;
    }
    
}
