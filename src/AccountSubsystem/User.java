package AccountSubsystem;

/**
 * The User Class Which has it's own Unique Instance Variables.
 * User has setters and getters
 * to retrieve and alter the private attributes tied to the Account.
 * @author Randy Gopaul
 * @version 1.0
 */

/**
 * The member variables for User are listed below. Each of these fields will be stored within the OTPS database for each User account.
 */
public class User extends Account
{
    private String Address;
    private String PhoneNumber;

    /**
     * This is a non-argumentative constructor for the user. Its member variables will be set to default values. However, any end user who wishes to create
     * an account will be forced to provide custom arguments towards User's parameters.
     */
    public User () 
    {
        setAddress("123 Main Street, Middleton, KS 66006");
        setPhoneNum("5551235555");
        setUserName("defaultUser");
        setPassword("root");
        setID(0);
        setEmail("userTest@notreal.com");
        setAdmin(false);
    }
    
    public User ( int AccountID)
    {
    	setID(AccountID);
    }
    
    /**
     * <p>
     * Constructs a User where the parameter is all the instance variables of Account
     * and the Address/CreditCard Information of the User Account.
     * @param userNameParm the Username for the Account
     * @param PasswordParm the Password for the Account
     * @param AccountIDParm the AccountID for the Account
     * @param PhoneNumberParm the PhoneNumber for the Account
     * @param EmailParm the Email for the Account
     * @param AddressParm the Address of the User Account
     */
     public User( String userNameParm, String PasswordParm, int AccountIDParm, String EmailParm, String PhoneNumberParm, String AddressParm, boolean isAdmin )
     {
         setUserName(userNameParm);
         setPassword(PasswordParm);
         setID(AccountIDParm);
         setEmail(EmailParm);
         setPhoneNum(PhoneNumberParm);
         setAddress(AddressParm);
         setAdmin(isAdmin);
     }
     
     /**
     * <p>
     * Constructs a User where the parameter is Another User Account
     * Allowing Methods that Return a User Account to Instantiate a User
     * @param LoggedInAccount The User Account Entered
     */
     public User( User LoggedInAccount )
     {
         setUserName(LoggedInAccount.getUserName());
         setPassword(LoggedInAccount.getPassword());
         setID(LoggedInAccount.getID());
         setEmail(LoggedInAccount.getEmail());
         setPhoneNum(LoggedInAccount.getPhoneNumber());
         setAddress(LoggedInAccount.getAddress());
         setAdmin(LoggedInAccount.isAdmin());
     }

    /**
     * This returns the Address tied to the User
     * @return the User's Address
     */
    public String getAddress() { return Address; }

    /**
     * This returns the PhoneNumber tied to the User
     * @return the User's Address
     */
    public String getPhoneNumber() { return PhoneNumber; }

    /**
     * Mutator used to set the Address instance variable
     * @param newAddress new address to set to account
     */
    public void setAddress(String newAddress) { this.Address = newAddress;}

    /**
     * Mutator used to set the PhoneNumber instance variable
     * @param newPhoneNum new phone number to set to account
     */
    public void setPhoneNum( String newPhoneNum ){ this.PhoneNumber = newPhoneNum;}

    /**
     * Returns the state of all Instance Variables
     * @return A String Displaying the Values of the Instance Variables
     */
    public String toString()
    { 
        String output = "";
		
        output += (super.toString() + ", " + PhoneNumber +", " + Address);
        return output;
    }
}