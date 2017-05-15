package AccountSubsystem;

/**
 * @author Randy
 */
public class Account 
{  
    private String userName;
    private String Password;
    private int AccountID;
    private String Email;
    private int AdminCode;
    boolean Is_Admin = false;

    /**
     * This is a non-argumentative constructor for the Account. Its member variables will be set to default values. However, any end user who wishes to create
     * an Account will be forced to provide custom arguments towards the Account parameters.
     */
    public Account () 
    {
        this.userName = "root";
        this.Password = "root";
        this.AccountID = 0;
        this.Email = "test@notreal.com";
        this.AdminCode = 1001;
        this.Is_Admin = true;
    }
    
    /**
     * <p>
     * Constructs a User where the parameter is all the instance variables of Account
     * and the Address/CreditCard Information of the User Account.
     * @param userNameParm the Username for the Account
     * @param PasswordParm the Password for the Account
     * @param AccountIDParm the AccountID for the Account
     * @param EmailParm the Email for the Account
     * @param CodeParm the Admin Code for the Account
     */
     public Account( String userNameParm, String PasswordParm, int AccountIDParm, String EmailParm, int CodeParm, boolean isAdmin )
     {
         setUserName(userNameParm);
         setPassword(PasswordParm);
         setID(AccountIDParm);
         setEmail(EmailParm);
         setAdminCode(CodeParm);
         setAdmin(isAdmin);
     }
     
     // For Orders Subsystem because table view just displays the account ID from the order atble.
     public Account( int accountID)
     {
    	 setID(accountID);
     }
     
     /**
     * <p>
     * Constructs a User where the parameter is Another User Account
     * Allowing Methods that Return a User Account to Instantiate a User
     * @param LoggedInAccount The Account Used to Construct a new Account
     */
     public Account( Account LoggedInAccount )
     {
         setUserName(LoggedInAccount.getUserName());
         setPassword(LoggedInAccount.getPassword());
         setID(LoggedInAccount.getID());
         setEmail(LoggedInAccount.getEmail());
         setAdminCode(LoggedInAccount.getAdminCode());
         setAdmin(LoggedInAccount.isAdmin());
     }

    /**
     * This returns the userName tied to the Account
     * @return the User's Username
     */
    public String getUserName() { return userName; }

    /**
     * This returns the Password tied to the Account
     * @return the User's Password
     */
    public String getPassword() { return Password; }

    /**
     * This returns the Email tied to the Account
     * @return the User's Email
     */
    public String getEmail() { return Email; }

    /**
     * This returns the AccountID tied to the Account
     * @return the User's ID
     */
    public int getID() { return AccountID; }

    /**
     * Mutator used to set the password instance variable
     * @param newPassword new password to set to account
     */
    public void setPassword( String newPassword ){ this.Password = newPassword;}

    /**
     * Mutator used to set the cc instance variable
     * @param newCreditCard new credit card to set to account
     */
    //public void setCreditCardInfo( CreditCard newCreditCard ){ this.CreditCardUser = newCreditCard;}

    /**
     * Mutator used to set the userName instance variable
     * @param newUserName
     */
    public void setUserName( String newUserName ){ this.userName = newUserName;}

    /**
     * Mutator used to set the Email instance variable
     * @param newEmail
     */
    public void setEmail( String newEmail ){ this.Email = newEmail;}

    /**
     * Mutator used to set the AccountID instance variable
     * @param newID
     */
    public void setID( int newID ){ this.AccountID = newID;}
    
    /**
     * Accessor that returns true if the Account Has Admin Rights
     * @return Returns True if The Account is has Admin Rights and False if Otherwise
     */
    public boolean isAdmin(){return this.Is_Admin;}
    
    /**
     * Mutator that sets if the Account Has Admin Rights
     * @param Admin_Bool Determines if the Account has Admin Rights Based on If It's True or False
     */
    public void setAdmin(boolean Admin_Bool){this.Is_Admin = Admin_Bool;}
    
    /**
     * Accessor used to Obtain an AdminCode
     * @return AdminCode
     */
    public int getAdminCode(){return AdminCode;}

    /**
     * Mutator used to set the AdminCode
     * @param newAdminCode the Admin Code Applied to the Admin Account
     */
    public void setAdminCode(int newAdminCode){this.AdminCode = newAdminCode;}

    /**
     * Returns the state of all Instance Variables
     * @return A String Displaying the Values of the Instance Variables
     */
    public String toString()
    { 
        String output = "";
		
        output += (userName + ", " + AccountID + ", " + Email + ", " + Password + ", " + Is_Admin);
        return output;
    }
    
}
