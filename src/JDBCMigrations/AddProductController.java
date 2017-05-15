//this class contains all the contains of the AddProduct stage
/*
package JDBCMigrations;
//import OTPS.ProductCollection;
//import OTPS.Product;

import java.io.IOException;
import java.sql.SQLException;
import ProductManagementSystem.*;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
//import productcontrol.Main;

public class AddProductController
{
	@FXML
	private TextField nameField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField stockField;

	@FXML
	private void exitAddButton() throws IOException 
	{
		Main.exitAddStage();
	}
	
	@FXML
	//This function is calling the add function from the collections and then showing the confirm stage
	private void submitAddButton() throws ClassNotFoundException, SQLException, IllegalStateException, IOException
	{
		String name = String.valueOf(nameField.getText());  
		double price = Double.valueOf(priceField.getText());
		int stock = Integer.valueOf(stockField.getText());
		double rating = 0.0;
		String id = "";
		
		Product p = new Product(id, name, price, stock, rating);
		
		ProductCollection PC = new ProductCollection();
		
		PC.addProduct(p);
		
		Main.showAddProductConfirmStage();
	}
}
*/