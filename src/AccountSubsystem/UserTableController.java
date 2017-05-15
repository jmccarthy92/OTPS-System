///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package MainGUI.User;
//
//import Main.Account_Collection;
//import Main.User;
//import static MainGUI.User.UserLoginControl.DefaultUser;
//import java.io.IOException;
//import java.sql.SQLException;
//import javafx.beans.property.SimpleBooleanProperty;
//import javafx.beans.property.SimpleIntegerProperty;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.paint.Color;
//
///**
// *
// * @author Randy
// */
//public class UserTableController 
//{
//    Account_Collection AC = new Account_Collection();
//    static User DefaultUser = (UserLoginControl.getUser()); // Main Account Used Throughout User Menu Hence Static Declaration
//    
//    //Create Table Columns for the TableSet
//    @FXML private TableColumn<User,Number> AccountIdColumn;
//    @FXML private TableColumn<User,String> UserNameColumn;
//    @FXML private TableColumn<User,String> PasswordColumn;
//    @FXML private TableColumn<User,String> EmailColumn;
//    @FXML private TableColumn<User,String> PhoneColumn;
//    @FXML private TableColumn<User,String> AddressColumn;
//    @FXML private TableColumn<User,Boolean> Is_AdminColumn;
//    @FXML private TableView<User> userTable; // Create TableView that Holds Users
//    private ObservableList<User> data = FXCollections.observableArrayList(); //Create ObservableList to Import Data into TableView
//    
//    /* GENERALLY USED LABELS */
//    @FXML
//    private TextField Username;
//    @FXML
//    private TextField Password;
//    @FXML
//    private TextField Email;
//    @FXML
//    private TextField Phone;
//    @FXML
//    private TextField Address;
//    @FXML
//    private Label Result;
//    /* GENERALLY USED LABELS */
//    
//    @FXML
//    public void initialize() 
//    {
//        //Initialize Column Values Based on Getters from User Class
//        AccountIdColumn.setCellValueFactory(cellData ->	new SimpleIntegerProperty(cellData.getValue().getID()));
//        UserNameColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getUserName()));
//        PasswordColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getPassword()));
//        EmailColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getEmail()));
//        PhoneColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
//        AddressColumn.setCellValueFactory(cellData ->	new SimpleStringProperty(cellData.getValue().getAddress()));
//        Is_AdminColumn.setCellValueFactory(cellData ->	new SimpleBooleanProperty(cellData.getValue().isAdmin()));
//        
//        data.add(DefaultUser); //Display User Changes
//        userTable.setItems(data);
//    }
//    
//    @FXML
//    private void editInfo() throws IOException
//    {
//        String newUsername = Username.getText();
//        String newEmail = Email.getText();
//        String newPassword = Password.getText(); 
//        String newPhone = Phone.getText();
//        String newAddress = Address.getText();
//        data.removeAll(data); //Clear any Previous Data From Table
//
//        try
//        {
//            //Basically if Any of the Textboxes are NOT Empty, Edit the User Info Based on What's Filled In
//            //Not Super Pretty, Gonna work on Optimizing This Better.
//            if(!("".equals(newUsername)))
//            {
//                if(AC.editBasicInfo(DefaultUser, 1, newUsername))
//                {
//                    Result.setTextFill(Color.GREEN);
//                    Result.setText("Account Info Changed");
//                }
//                else
//                {
//                    Result.setTextFill(Color.RED);
//                    Result.setText("Username is Already Taken!");
//                }
//            }
//            if(!("".equals(newPassword)))
//            {
//                if(AC.editBasicInfo(DefaultUser, 2, newPassword))
//                {
//                    Result.setTextFill(Color.GREEN);
//                    Result.setText("Account Info Changed");
//                }
//                else
//                {
//                    Result.setTextFill(Color.RED);
//                    Result.setText("Invalid Password Entered!");
//                }
//            }
//            if(!("".equals(newEmail)))
//            {
//                if(AC.editBasicInfo(DefaultUser, 3, newEmail))
//                {
//                    Result.setTextFill(Color.GREEN);
//                    Result.setText("Account Info Changed");
//                }
//                else
//                {
//                    Result.setTextFill(Color.RED);
//                    Result.setText("Invalid Email Entered!");
//                }
//            }
//            if(!("".equals(newPhone)))
//            {
//                if(AC.editBasicInfo(DefaultUser, 4, newPhone))
//                {
//                    Result.setTextFill(Color.GREEN);
//                    Result.setText("Account Info Changed");
//                }
//                else
//                {
//                    Result.setTextFill(Color.RED);
//                    Result.setText("Invalid Phone Number Entered!");
//                }
//            }
//            if(!("".equals(newAddress)))
//            {
//                if(AC.editBasicInfo(DefaultUser, 5, newAddress))
//                {
//                    Result.setTextFill(Color.GREEN);
//                    Result.setText("Account Info Changed");
//                }
//                else
//                {
//                    Result.setTextFill(Color.RED);
//                    Result.setText("Invalid Address Entered!");
//                }
//            }
//            data.add(DefaultUser); //Display User Changes
//            userTable.setItems(data);
//        }
//        catch(SQLException SE) {    } 
//        catch (ClassNotFoundException ex) { }
//    }
//    
//}
