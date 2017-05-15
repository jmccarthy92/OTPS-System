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

public class ConfirmationDisplayController extends BorderPane implements Initializable
{
	
	@FXML private Label orderText = new Label();
	@FXML private Button confirmButton;
	
	//private Stage parentStage;
	//private Stage thisStage;
	private Order createdOrder;

	public void showConfirmationDisplay(Stage stage, Order order)
	{
	
	/*	try
		{
			//thisStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ConfirmationDisplay.fxml"));
			BorderPane cDisplay = loader.load();
			Stage cDStage = new Stage();
			thisStage = cDStage;
			Scene scene = new Scene(cDisplay);
			cDStage.setTitle("Confirmation Display");
			cDStage.initOwner(this.parentStage);
			cDStage.initModality(Modality.WINDOW_MODAL);
			cDStage.setScene(scene);
			cDStage.show();
		}
		catch( Exception e) { e.printStackTrace();}*/
	}
	
	@FXML protected void confirmClick( ActionEvent ae ) throws IOException
	{
		Main.hideMainMenu();
		Main.hideConfirmationDisplay();
		Main.showMainMenu();
		//thisStage.close();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		createdOrder = Main.getCreatedOrder();
		orderText.setText("Order for Account: " + createdOrder.getAccount().getID()
						+  "\t With Sales Total:" + createdOrder.getSalesTotal() );
		
	}


	
}
