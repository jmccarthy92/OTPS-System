package application;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import OrderSubsystem.Order;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RemoveOrderDisplayController  extends BorderPane implements Initializable
{

	@FXML private Label removedText = new Label();
	@FXML private Button confirmButton;
	private Order removedOrder;
	
	@FXML protected void confirmButtonClick( ActionEvent ae ) throws IOException
	{
		Main.hideRemoveDisplay();
		
	}
	
	
	/*public RemoveOrderDisplayController( Order order)
	{
		this.removedOrder = order;
	}*/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		removedOrder = Main.getRemovedOrder();
		removedText.setText("Order  : \t" +removedOrder.getOrderId() + "\t\t By Account: \t" 
				+removedOrder.getAccount().getID()+ "\t\tOn date:\t" + removedOrder.getOrderDate() );
	}

}
