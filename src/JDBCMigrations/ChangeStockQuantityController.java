/*package JDBCMigrations;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ProductManagementSystem.*;

public class ChangeStockQuantityController
{
	@FXML
	private TextField idField;
	@FXML
	private TextField newQuantityField;
	
	@FXML
	private void exitChangeQuantityStageButton() //This is for the exitChangeQuantity stage
	{
		Main.exitChangeQuantityStage();
	}
	
	@FXML
	private void sumbitChangeStockQuantityButton() throws ClassNotFoundException, SQLException, IOException
	{
		String id = String.valueOf(idField.getText());
		int quantity = Integer.valueOf(newQuantityField.getText());
		
		ProductCollection PC = new ProductCollection();
		PC.changeStockQuantity(id, quantity);
		
		Main.showChangeQuantityConfirmStage();
	}
}*/
