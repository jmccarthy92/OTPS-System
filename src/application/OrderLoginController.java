package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import OrderSubsystem.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import AccountSubsystem.*;


public class OrderLoginController extends BorderPane implements Initializable  {

	private Account_Collection ac = new Account_Collection();
	@FXML private TextField userField;
	@FXML private PasswordField pwField;
	
	@FXML private Button loginButton;
	@FXML private Button backButton;
	
	@FXML protected void backButtonClick(ActionEvent ae) throws IOException
	{
		Main.hideOrderLogInMenu();
	}
	@FXML protected void loginButtonClick(ActionEvent ae) throws IOException
	{
		String userName = userField.getText();
		String pw = pwField.getText();
		try {
			if( ac.loginUser(userName, pw) != null)
			{
				Main.setUser(ac.loginUser(userName, pw));
				Main.hideOrderLogInMenu();
				Main.hideCheckoutMenu();
				Main.showCheckoutMenuScreen();
				//Main.hideMainMenu();
				//Main.showMainMenu();
			}
			else
			{
				System.out.println(" NO account FOUND");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
