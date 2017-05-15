package application;

import AccountSubsystem.*;
import java.io.IOException;
import javafx.scene.paint.Color;



import java.sql.SQLException;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
/**
 * @author Randy
 */
public class UserLoginControl 
{
    Account_Collection AC = new Account_Collection();
    static User DefaultUser = null; // Main Account Used Throughout User Menu Hence Static Declaration
    User Default = null; // Used for Adding Accounts
    
    //Create Table Columns for the TableSet
    @FXML private TableColumn<User,Number> AccountIdColumn;
    @FXML private TableColumn<User,String> UserNameColumn;
    @FXML private TableColumn<User,String> PasswordColumn;
    @FXML private TableColumn<User,String> EmailColumn;
    @FXML private TableColumn<User,String> PhoneColumn;
    @FXML private TableColumn<User,String> AddressColumn;
    @FXML private TableColumn<User,Boolean> Is_AdminColumn;
    @FXML private TableView<User> userTable; // Create TableView that Holds Users
    private ObservableList<User> data = FXCollections.observableArrayList(); //Create ObservableList to Import Data into TableView
    
    /* GENERALLY USED LABELS */
    @FXML
    private TextField Username;
    @FXML
    private TextField Password;
    @FXML
    private TextField ComparePassword;
    @FXML
    private TextField Email;
    @FXML
    private TextField Phone;
    @FXML
    private TextField Address;
    @FXML
    private Label Result;
    @FXML private Button button;
    /* GENERALLY USED LABELS */
    
    /* START OF - FXML statements related to Navigating to Different Menus */
    //@FXML
    //private void returnHome() throws IOException{Main.showMainMenu();}
    @FXML
    private void returntoUser() throws IOException{Main.hideCreateUser(); }
    @FXML
    private void returntoUserFromFPW() throws IOException {Main.hideForgotPassword();}
   // @FXML
   // private void createAccount() throws IOException{Main.showCreateUser();}
  //  @FXML
   // private void showForgotPassword() throws IOException{Main.showForgotPassword();}
   // @FXML
   // private void editUserInfo() throws IOException {Main.showEditUserInfo();}
    
    
   
    
    
    @FXML
    private void returntoUserMenu() throws IOException{Main.hideUserInfo();}
    /* END OF - FXML statements related to Navigating to Different Menus */
    
    /* START OF - FXML statements related to Editing User Data */
    @FXML
    private void forgotPassword() throws IOException
    {
        //I know in Hindsight this Method Might seem Weird, but That's Mostly Becasue I Don't Have anything to Send an Actual Email to Reset a Password
        //So the Way I thought it out Was to Just Have the User Enter their Username / Email, And If it Matches then Their New Password Would be Set
        //I just skipped the whole, "Open the Email and Click this Varifyable Link That'll Let You Reset Your Password" because I dunno how to Implement that.
        String UsernameText = Username.getText();
        String EmailText = Email.getText();
        String newPassword = Password.getText(); 
        try
        {
            if(AC.forgotPassword(UsernameText, EmailText, newPassword) != null)
            {
                Result.setTextFill(Color.GREEN);
                Result.setText( "Password Changed, New Password : " + AC.forgotPassword(UsernameText, EmailText, newPassword));
            }
            else
            {
                Result.setTextFill(Color.RED);
                Result.setText("Account not Found!");
            }
        }
        catch(SQLException SE) {    } 
        catch (ClassNotFoundException ex) { }
    }
    
    @FXML
    private void setCellValuesForEdit() throws IOException
    {
        AccountIdColumn.setCellValueFactory(cellData ->	new SimpleIntegerProperty(cellData.getValue().getID()));
        UserNameColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getUserName()));
        PasswordColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getPassword()));
        EmailColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getEmail()));
        PhoneColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        AddressColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getAddress()));
        Is_AdminColumn.setCellValueFactory(cellData ->	new SimpleBooleanProperty(cellData.getValue().isAdmin()));
        try {
			Default = AC.searchUser(Main.getUser().getID() );
			data.add(Default);
			userTable.getItems().setAll(this.data);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    private void editInfo() throws IOException
    {
        String newUsername = Username.getText();
        String newEmail = Email.getText();
        String newPassword = Password.getText(); 
        String newPhone = Phone.getText();
        String newAddress = Address.getText();
        data.removeAll(data); //Clear any Previous Data From Table
        
        try
        {
            
            Default = AC.searchUser(Main.getUser().getID() );
            //Basically if Any of the Textboxes are NOT Empty, Edit the User Info Based on What's Filled In
            //Not Super Pretty, Gonna work on Optimizing This Better.
            if(!("".equals(newUsername)))
            {
                if(newUsername.length() > 31)
                {
                    Result.setTextFill(Color.RED);
                    Result.setText("New Username is Too Large");
                }
                else if(AC.editBasicInfo(Default, 1, newUsername))
                {
                    Result.setTextFill(Color.GREEN);
                    Result.setText("Account Info Changed");
                }
                else
                {
                    Result.setTextFill(Color.RED);
                    Result.setText("Username is Already Taken!");
                }
            }
            if(!("".equals(newPassword)))
            {
                if(newPassword.length() > 21)
                {
                    Result.setTextFill(Color.RED);
                    Result.setText("New Password is Too Large");
                }
                if(AC.editBasicInfo(Default, 2, newPassword))
                {
                    Result.setTextFill(Color.GREEN);
                    Result.setText("Account Info Changed");
                }
                else
                {
                    Result.setTextFill(Color.RED);
                    Result.setText("Invalid Password Entered!");
                }
            }
            if(!("".equals(newEmail)))
            {
                if(newEmail.length() > 41)
                {
                    Result.setTextFill(Color.RED);
                    Result.setText("New Email is Too Large");
                }
                if(AC.editBasicInfo(Default, 3, newEmail))
                {
                    Result.setTextFill(Color.GREEN);
                    Result.setText("Account Info Changed");
                }
                else
                {
                    Result.setTextFill(Color.RED);
                    Result.setText("Invalid Email Entered!");
                }
            }
            if(!("".equals(newPhone)))
            {
                int PhoneCheck = Integer.valueOf(Phone.getText()); //Checks if Phone is an Actual Number
                if(newPhone.length() > 11)
                {
                    Result.setTextFill(Color.RED);
                    Result.setText("New PhoneNumber is Too Large");
                }
                if(AC.editBasicInfo(Default, 4, newPhone))
                {
                    Result.setTextFill(Color.GREEN);
                    Result.setText("Account Info Changed");
                }
                else
                {
                    Result.setTextFill(Color.RED);
                    Result.setText("Invalid Phone Number Entered!");
                }
            }
            if(!("".equals(newAddress)))
            {
                if(newAddress.length() > 101)
                {
                    Result.setTextFill(Color.RED);
                    Result.setText("New Address is Too Large");
                }
                if(AC.editBasicInfo(Default, 5, newAddress))
                {
                    Result.setTextFill(Color.GREEN);
                    Result.setText("Account Info Changed");
                }
                else
                {
                    Result.setTextFill(Color.RED);
                    Result.setText("Invalid Address Entered!");
                }
            }
           	Default = AC.searchUser(Main.getUser().getID() );
                data.add(Default);
                userTable.getItems().setAll(this.data);
                setCellValuesForEdit();
        }
        catch(SQLException SE){} 
        catch (ClassNotFoundException ex){}
        catch(NumberFormatException NFE)
        { 
            Result.setTextFill(Color.RED);
            Result.setText("Invalid Phone Number Entered!");
        }
    }
    /* END OF - FXML statements related to Editing User Data */
    
    /* START OF - FXML statements related to Creating and Logging into a User Account */
    @FXML
    private void createAccountDB() throws ClassNotFoundException, SQLException, IOException
    {
        String UsernameEntered = String.valueOf(Username.getText());
        String PasswordEntered = String.valueOf(Password.getText());
        String EmailEntered = String.valueOf(Email.getText());
        String PhoneEntered = String.valueOf(Phone.getText());
        String AddressEntered = String.valueOf(Address.getText());
        String PasswordToCompare = String.valueOf(ComparePassword.getText());
        try
        {
            int PhoneCheck = Integer.valueOf(Phone.getText()); //Checks if Phone is an Actual Number

            if("".equals(UsernameEntered) || UsernameEntered.length() > 31)
            {
                Result.setTextFill(Color.RED);
                if(UsernameEntered.length() > 31)
                    Result.setText("Username Entered is Too Large");
                else
                    Result.setText("No Username Entered");
            }
            else if ("".equals(PasswordEntered) || PasswordEntered.length() > 21)
            {
                Result.setTextFill(Color.RED);
                if(PasswordEntered.length() > 21)
                    Result.setText("Password Entered is Too Large");
                else
                    Result.setText("No Password Entered");
            }
            else if ("".equals(EmailEntered) || EmailEntered.length() > 41)
            {
                Result.setTextFill(Color.RED);
                if(EmailEntered.length() > 41)
                    Result.setText("Email Entered is Too Large");
                else
                    Result.setText("No Email Entered");
            }
            else if ("".equals(PhoneEntered) || PhoneEntered.length() > 11)
            {
                Result.setTextFill(Color.RED);
                if(PhoneEntered.length() > 11)
                    Result.setText("PhoneNumber Entered is Too Large");
                else
                    Result.setText("No PhoneNumber Entered");
            }
            else if ("".equals(AddressEntered) || AddressEntered.length() > 101)
            {
                Result.setTextFill(Color.RED);
                if(AddressEntered.length() > 100)
                    Result.setText("Address Entered is Too Large");
                else
                    Result.setText("No Address Entered");
            }
            else if (PasswordToCompare.equals(PasswordEntered))
            {
                if(AC.addUser(Default = new User(UsernameEntered, PasswordEntered, 0, EmailEntered, PhoneEntered, AddressEntered, false)) == null)
                {
                    Result.setTextFill(Color.RED);
                    Result.setText("Username is Already Registered");
                }
                else
                {
                    //Main.hideCreateUser();
                    Result.setTextFill(Color.GREEN);
                    Result.setText("Account Created! ID:" +Default.getID());
                    Username.setText("");
                    Password.setText("");
                    Email.setText("");
                    Phone.setText("");
                    Address.setText("");
                    ComparePassword.setText("");
                }
            }
            else
            {
                Result.setTextFill(Color.RED);
                Result.setText("Passwords Do Not Match.");
            }
        }
        catch(SQLException SE){} 
        catch (ClassNotFoundException ex){}
        catch(NumberFormatException NFE)
        { 
            Result.setTextFill(Color.RED);
            Result.setText("Invalid Phone Number Entered!");
        }
    }
    
    @FXML
    private void loginUser() throws ClassNotFoundException, SQLException, IOException
    {
        String UsernameEntered = String.valueOf(Username.getText());
        String PasswordEntered = String.valueOf(Password.getText());

        if(AC.loginUser(UsernameEntered, PasswordEntered) == null)
        {
            Result.setTextFill(Color.RED);
            Result.setText("Invalid Username or Password");
        }
        else
        {
            DefaultUser = new User(AC.loginUser(UsernameEntered, PasswordEntered));
            Result.setTextFill(Color.GREEN);
            Result.setText("Login Successful!");
            Main.showMainMenu();
        }        
    }
    /* END OF - FXML statements related to Creating and Logging into a User Account */
    
}
