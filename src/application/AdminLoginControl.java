package application;

//import MainGUI.Main;
import java.io.IOException;
import javafx.scene.paint.Color;

//import Main.Account_Collection;
//import Main.Account;
//import Main.User;

import java.sql.SQLException;
import java.util.HashMap;

import AccountSubsystem.Account;
import AccountSubsystem.Account_Collection;
import AccountSubsystem.User;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * @author Randy
 */
public class AdminLoginControl 
{
    private Account_Collection AC = new Account_Collection();
    private Account Default = null;
    
    /*GENERALLY USED LABELS*/
    @FXML
    private Label Result;
    @FXML
    private TextField Username;    
    @FXML
    private TextField Password;
    @FXML
    private TextField ComparePassword;
    @FXML
    private TextField Email;
    @FXML
    private TextField Code;
    /*GENERALLY USED LABELS*/
    
    /* START OF - FXML statements related to Navigating to Different Menus */
 //   @FXML
  //  private void returnHome() throws IOException{Main.showMainMenu();}
   // @FXML
   // private void returnAdmin() throws IOException{Main.showAdminLogin();}
   // @FXML
   // private void viewAccountMenu() throws IOException{Main.showViewAccountMenu();}
    @FXML
    private void backButtonAcc() throws IOException{Main.showAdminControl(); Main.hideViewAccountMenu();
    												}
    
    @FXML
    private void adminMenu() throws IOException{Main.hideCreateAdmin();Main.showAdminControl();}  
    //@FXML
    //private void createAdminMenu() throws IOException{Main.showCreateAdminMenu();}
    /* END OF - FXML statements related to Navigating to Different Menus */
    
    /* START OF - FXML statements Related to Creating and Logging Into an Account. */
    @FXML
    private void createAccountDB() throws ClassNotFoundException, SQLException, IOException
    {
    	 if("".equals(Code.getText()))
         {
             Result.setTextFill(Color.RED);
             Result.setText("No Code Entered");
         }
         else
         {
             try
             {
                 String UsernameEntered = String.valueOf(Username.getText());
                 String PasswordEntered = String.valueOf(Password.getText());
                 String EmailEntered = String.valueOf(Email.getText()); 
                 String PasswordToCompare = String.valueOf(ComparePassword.getText());

                 int CodeEntered = Integer.valueOf(Code.getText());

                 if("".equals(UsernameEntered) || UsernameEntered.length() > 31) //Max 30 Char
                 {
                     Result.setTextFill(Color.RED);
                     if(UsernameEntered.length() > 31)
                         Result.setText("Username Entered is Too Large");
                     else
                         Result.setText("No Username Entered");
                 }
                 else if ("".equals(PasswordEntered) || PasswordEntered.length() > 21) //Max 20 Char
                 {
                     Result.setTextFill(Color.RED);
                     if(PasswordEntered.length() > 21)
                         Result.setText("Password Entered is Too Large");
                     else
                         Result.setText("No Password Entered");
                 }
                 else if ("".equals(EmailEntered) || EmailEntered.length() > 41) //Max 40 Char
                 {
                     Result.setTextFill(Color.RED);
                     if(EmailEntered.length() > 41)
                         Result.setText("Email Entered is Too Large");
                     else
                         Result.setText("No Email Entered");
                 }
                 else if(CodeEntered > 21) //Max 20
                 {
                     Result.setTextFill(Color.RED);
                     Result.setText("Admin Code is Too Large");
                 }
                 else if (PasswordToCompare.equals(PasswordEntered))
                 {
                     if(AC.addAdmin(Default = new Account(UsernameEntered, PasswordEntered, 0, EmailEntered, CodeEntered, true)) == null)
                     {
                         Result.setTextFill(Color.RED);
                         Result.setText("Username is Already Registered");
                     }
                     else
                     {
                         Result.setTextFill(Color.GREEN);
                         Result.setText("Account Created! ID:" +Default.getID());
                         Username.setText("");
                         Password.setText("");
                         Email.setText("");
                         Code.setText("");
                         ComparePassword.setText("");
                     }
                 }
                 else
                 {
                     Result.setTextFill(Color.RED);
                     Result.setText("Passwords Do Not Match.");
                 }
             }
             catch(NumberFormatException NFE)
             {
                 Result.setTextFill(Color.RED);
                 Result.setText("Invalid Code Entered!");
             }
         }
    }
    

    /* END OF - FXML statements Related to Creating and Logging Into an  Account. */
    
    /* START OF - FXML statements Related to Methods that Utilize TableView (viewAccounts, searchUser, etc). */
    HashMap<Integer, User> hmap = new HashMap <>();
    
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
    private HashMap<Integer, User> userList = new HashMap <Integer,User>(); //Create Empty HashMap

    @FXML
    private void viewAccounts() throws IOException, SQLException, ClassNotFoundException
    {
        userList = AC.viewAccounts(); //Retrieve HashMap of User Accounts
        data.removeAll(data); //Clear any Previous Data
        
        //Initialize Column Values Based on Getters from User Class
        AccountIdColumn.setCellValueFactory(cellData ->	new SimpleIntegerProperty(cellData.getValue().getID()));
        UserNameColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getUserName()));
        PasswordColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getPassword()));
        EmailColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getEmail()));
        PhoneColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        AddressColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getAddress()));
        Is_AdminColumn.setCellValueFactory(cellData ->	new SimpleBooleanProperty(cellData.getValue().isAdmin()));
        
        for(int key : userList.keySet()) //Go Through the HashMap
        {
            System.out.println(userList.get(key));
            
            User user = new User (userList.get(key).getUserName(), userList.get(key).getPassword(), 
                                  userList.get(key).getID(), userList.get(key).getEmail(), 
                                  userList.get(key).getPhoneNumber(), userList.get(key).getAddress(), 
                                  userList.get(key).isAdmin());
            data.add(user); //Add User into List
        }
        userTable.setItems(data); //Add User into the Table to View		
		
    }
    
    @FXML
    private TextField AccountID;
    @FXML
    private Label searchResult;
    
    @FXML
    private void findAccount() throws IOException, SQLException, ClassNotFoundException
    {
        userList = AC.viewAccounts();
        data.removeAll(data);
        
        //Assign Column Values Based on Getters from User Class (Working on a Way were I'll only need to Use this Block of Code ONCE
        AccountIdColumn.setCellValueFactory(cellData ->	new SimpleIntegerProperty(cellData.getValue().getID()));
        UserNameColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getUserName()));
        PasswordColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getPassword()));
        EmailColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getEmail()));
        PhoneColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        AddressColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getAddress()));
        Is_AdminColumn.setCellValueFactory(cellData ->	new SimpleBooleanProperty(cellData.getValue().isAdmin()));
        
        try
        {
            int IDtoCatch = Integer.parseInt(AccountID.getText());
            if("".equals(AccountID.getText()))
            {
                searchResult.setTextFill(Color.RED);
                searchResult.setText("AccountID not Entered!");
                viewAccounts();
            }
            else if(AC.searchUser(Integer.parseInt(AccountID.getText())) != null)
            {
                User user = new User (AC.searchUser(Integer.parseInt(AccountID.getText())));
                data.add(user);
                userTable.setItems(data);
                searchResult.setTextFill(Color.GREEN);
                searchResult.setText("Account Found!");
            }
            else
            {
                searchResult.setTextFill(Color.RED);
                searchResult.setText("Account not Found!");
                viewAccounts();
            }
        }
        catch(NumberFormatException NFE)
        {
            searchResult.setTextFill(Color.RED);
            searchResult.setText("Invalid AccountID Entered!");
            viewAccounts();
        }
		
    }
    /* END OF - FXML statements Related to Methods that Utilize TableView (viewAccounts, searchUser, etc). */
    
    /* START OF - FXML statements Related to Deleting Accounts. */
    @FXML
    private TextField DelAccountID;
    
    @FXML
    private void deleteUser() throws IOException, SQLException, ClassNotFoundException
    {
        //Assign Column Values Based on Getters from User Class (Working on a Way were I'll only need to Use this Block of Code ONCE
        AccountIdColumn.setCellValueFactory(cellData ->	new SimpleIntegerProperty(cellData.getValue().getID()));
        UserNameColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getUserName()));
        PasswordColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getPassword()));
        EmailColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getEmail()));
        PhoneColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        AddressColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getAddress()));
        Is_AdminColumn.setCellValueFactory(cellData ->	new SimpleBooleanProperty(cellData.getValue().isAdmin()));
        
        data.removeAll(data);
        try
        {
            //int IDtoCatch = Integer.parseInt(DelAccountID.getText());
            if("".equals(DelAccountID.getText()))
            {
                Result.setTextFill(Color.RED);
                Result.setText("AccountID not Entered!");
                viewAccounts();
            }
            else if(AC.searchUser(Integer.parseInt(DelAccountID.getText())) != null)
            {
                User user = new User (AC.deleteUser(Integer.parseInt(DelAccountID.getText())));

                data.add(user);
                userTable.setItems(data); //Display User Deleted

                Result.setTextFill(Color.GREEN);
                Result.setText("User Deleted!");
            }
            else
            {
                Result.setTextFill(Color.RED);
                Result.setText("Account not Found!");
                viewAccounts();
            }
        }
        catch(NumberFormatException NFE)
        {
            Result.setTextFill(Color.RED);
            Result.setText("Invalid AccountID Entered!");
            viewAccounts();
        }
    }
    
    @FXML
    private void deleteAdmin() throws IOException, SQLException, ClassNotFoundException
    {
        
        //Assign Column Values Based on Getters from User Class (Working on a Way were I'll only need to Use this Block of Code ONCE)
        AccountIdColumn.setCellValueFactory(cellData ->	new SimpleIntegerProperty(cellData.getValue().getID()));
        UserNameColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getUserName()));
        PasswordColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getPassword()));
        EmailColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getEmail()));
        PhoneColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        AddressColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getAddress()));
        Is_AdminColumn.setCellValueFactory(cellData ->	new SimpleBooleanProperty(cellData.getValue().isAdmin()));
        
        data.removeAll(data);
        
        try
        {
            //int IDtoCatch = Integer.parseInt(DelAccountID.getText());
            if("".equals(DelAccountID.getText()))
            {
                Result.setTextFill(Color.RED);
                Result.setText("AccountID not Entered!");
                viewAccounts();
            }
            else if(AC.searchAdmin(Integer.parseInt(DelAccountID.getText())) != null)
            {
                Account admin = new Account (AC.deleteAdmin(Integer.parseInt(DelAccountID.getText())));
                User TmpUser = new User(admin.getUserName(), admin.getPassword(), admin.getID(), admin.getEmail(), null, null, admin.isAdmin());

                data.add(TmpUser);
                userTable.setItems(data); //Display Admin Deleted

                Result.setTextFill(Color.GREEN);
                Result.setText("Admin Deleted!");
            }
            else
            {
                Result.setTextFill(Color.RED);
                Result.setText("Account not Found!");
                viewAccounts();
            }	
        }
        catch(NumberFormatException NFE)
        {
            Result.setTextFill(Color.RED);
            Result.setText("Invalid AccountID Entered!");
            viewAccounts();
        }
    }
    /* END OF - FXML statements Related to Deleting Accounts. */

}
