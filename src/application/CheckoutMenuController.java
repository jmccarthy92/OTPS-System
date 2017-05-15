package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class CheckoutMenuController extends AnchorPane implements Initializable
{

	@FXML private  Button makePaymentButton;
	@FXML private Button logInButton ;
	@FXML private  Button createAccountButton ;
	@FXML private Button backToOrder;
	
	@FXML protected void payButtonClick(ActionEvent ae ) throws IOException
	{
		Main.showPaymentMenu();
	}
	
	@FXML protected void logInButtonClick(ActionEvent ae) throws IOException
	{
		Main.showOrderLogInMenu();
	}
	
	@FXML protected void createButtonClick(ActionEvent ae) throws IOException
	{
		Main.showCreateUserOrder();
	}
	
	@FXML protected void backToOrderClick(ActionEvent ae ) throws IOException
	{
		Main.hideCheckoutMenu();
	}
	/*public CheckoutMenuController()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckoutMenu.fxml"));
		loader.setRoot(this);
		loader.setController(this);
		try
		{
			loader.load();
		}
		catch( IOException e)
		{
			throw new RuntimeException(e);
		}
	}*/



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(Main.getUser() != null)
		{
			makePaymentButton.setVisible(true);
			logInButton.setVisible(false);
			createAccountButton.setVisible(false);
		}
		else
		{
			makePaymentButton.setVisible(false);
		}
		
	}
	
}
