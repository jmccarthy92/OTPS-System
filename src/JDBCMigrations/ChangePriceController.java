/*package JDBCMigrations;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

import ProductManagementSystem.*;

public class ChangePriceController
{
	@FXML
	private TextField idField;
	@FXML
	private TextField priceField;
	
	@FXML
	private void exitChangePriceStageButton() //This is for the exitChangePrice stage
	{
		Main.exitChangePriceStage();
	}
	
	@FXML
	private void submitChangePriceButton() throws ClassNotFoundException, SQLException, IOException
	{
		String id = String.valueOf(idField.getText());
		double price = Double.valueOf(priceField.getText());
		
		ProductCollection PC = new ProductCollection();
		PC.changePrice(id, price);
		
		Main.showChangePriceConfirmStage();
	}
}
*/