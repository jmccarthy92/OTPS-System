package application;

import java.io.IOException;
import java.sql.SQLException;

import AccountSubsystem.Account;
import AccountSubsystem.Account_Collection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AdministratorLogInController extends AnchorPane {
	@FXML private Button backButton;
	@FXML private Button adminLogInButton;
	@FXML private TextField adminUserName;
	@FXML private TextField adminPassword;
	@FXML private Label Result;
	private Account_Collection ac = new Account_Collection();
	 private   Account Default = null;
	@FXML protected void adminLogInButtonClick(ActionEvent ae) throws IOException{

	        String UsernameEntered = String.valueOf(adminUserName.getText());
	        String PasswordEntered = String.valueOf(adminPassword.getText());

	        try {
				if(ac.loginAdmin(UsernameEntered, PasswordEntered) == null)
				{
				    Result.setTextFill(Color.RED);
				    Result.setText("Invalid Username or Password");
				    adminUserName.getStyleClass().add("error");
				    adminPassword.getStyleClass().add("error");
				}
				else
				{
					adminUserName.getStyleClass().remove("error");
					adminPassword.getStyleClass().remove("error");
				    Default = new Account(ac.loginAdmin(UsernameEntered, PasswordEntered));
				    Result.setTextFill(Color.GREEN);
				    Result.setText("Login Successful!");
				   // Main.showAdminMenu();
				   // Main.showAdminOrderMenu();
				    Main.showAdminControl();
				    Main.hideLogInMenu();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    
	}
	@FXML protected void backButtonClick(ActionEvent ae) throws IOException{
		Main.hdieAdminLogin();
		Main.showLoginMenu();
	}
	
	
/*	public AdministratorLogInController()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AdministratorLogIn.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try{
			loader.load();
		}
		catch( IOException e)
		{
			throw new RuntimeException(e);
		}
	}*/
}
