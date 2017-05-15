/*package JDBCMigrations;


import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ProductManagementSystem.*;

public class RemoveProductController
{
	@FXML
	private TextField idField;
	
	@FXML
	private void exitRemoveButton() throws IOException //This is for the exitRemove stage
	{
		Main.exitRemoveStage();
	}
	
	@FXML
	private void submitRemoveButton() throws ClassNotFoundException, SQLException, IOException
	{
		String id = String.valueOf(idField.getText());
		
		//sProduct p = new Product("", "", 0.0, 0, 0.0);
		
		ProductCollection PC = new ProductCollection();
		
		PC.removeProduct(id);
		
		Main.showRemoveProductConfirmStage();
	}
}*/
