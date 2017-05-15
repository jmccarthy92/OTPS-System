package application;

import AccountSubsystem.*;


import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PopUpPanelController extends AnchorPane
{
	//FXML Scene Builder generated Button Variables//
	@FXML private Button testButton;
	@FXML private Button adminButton;
	@FXML private Button guestButton;
	@FXML private Button createAdmin;
	
	@FXML private PasswordField passBox;
	
	 @FXML private Label Result;
	//@FXML private TextField pwBox;
	@FXML private TextField userBox;
	
	private Account_Collection ac = new Account_Collection();
	//private static Stage loginStage;
	
	//private variables for setting the scene for the main menu, along with the mainMenuController(RootPanel Controller)//
	private static RootPanelController rpc = new RootPanelController();
	private static Scene root_page_scene = new Scene(rpc);
	private static Stage main_stage;
	
	//Private variables for setting the scene for the main menu , along with the AdminLoginController()//
	private static AdministratorLogInController alc = new AdministratorLogInController();
	private static Scene adminScene= new Scene(alc);
	private static Stage adminStage;
	
	
	@FXML protected void createAdminClick(ActionEvent ae) throws IOException
	{
		Main.showCreateAdminMenu();
	}
	
	@FXML protected void createUserClick(ActionEvent ae ) throws IOException
	{
		Main.showCreateUser();
	}
	@FXML protected void guestButtonClick(ActionEvent ae) throws IOException
	{
		Main.hideMainMenu();
		Main.showMainMenu();
	}
	
	@FXML protected void forgotPasswordClick(ActionEvent ae) throws IOException
	{
		Main.showForgotPassword();
	}
	
	
	@FXML protected void exitProgram(ActionEvent ae) throws IOException
	{
		System.exit(0);
	}
	
	@FXML protected void adminButtonClick(ActionEvent ae) throws IOException
	{
		Main.showAdminLogInMenu();
	}
	
	@FXML protected void testButtonClick(ActionEvent ae) throws IOException
	{
		String userName = userBox.getText();
		//	String pw = pwBox.getText();
			String pass = passBox.getText();
			try 
	                {
	                    if( ac.loginUser(userName, pass) != null)
	                    {
	                        Main.setUser(ac.loginUser(userName, pass));
	                        Result.setVisible(false);
	                   //     Result.setText("");
	                        Main.hideMainMenu();
	                        Main.showMainMenu();
	                    }
	                    else
	                    {
	                    	Result.setVisible(true);
	                        Result.setTextFill(Color.RED);
	                        Result.setText("Invalid Username or Password");
	                    }
	                     
	                {
	            
	                }
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public PopUpPanelController()
	{
		//Result.setText("");
	//	Result.setVisible(false);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene2.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try{
			loader.load();
		}
		catch( IOException e)
		{
			throw new RuntimeException(e);
		}
	}
}
