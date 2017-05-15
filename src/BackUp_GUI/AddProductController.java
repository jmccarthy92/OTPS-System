//this class contains all the contains of the AddProduct stage
/*
package application;

import java.io.IOException;
import java.sql.SQLException;
import ProductManagementSystem.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class AddProductController
{

	
	@FXML
	private TextField nameField;
	@FXML
	private TextField priceField;
	@FXML
	private TextField stockField;

	
	@FXML
	private Label Result;
	
	@FXML
	private void exitAddButton() throws IOException 
	{
		Main.exitAddStage();
	}
	
	@FXML
	//This function is calling the add function from the collections and then showing the confirm stage
	private void submitAddButton() throws ClassNotFoundException, SQLException, IOException
	{
		double rating = 0.0;
		String id = "";
	
		String name = String.valueOf(nameField.getText());
		String price = String.valueOf(priceField.getText());
		String stock = String.valueOf(stockField.getText());
		
		try
		{		
			if(nameField.getText().isEmpty())
			{
	            System.out.println("ERROR_Name");
	            Result.setTextFill(Color.RED);
	            Result.setText("Enter in Name");
			}
			else if(!nameField.getText().matches("[a-zA-Z]+"))
			{
				System.out.println("ERROR_Name_Numbers");
	            Result.setTextFill(Color.RED);
	            Result.setText("Entered in Numbers");
			}
			else if(priceField.getText().isEmpty() )
			{
			    System.out.println("ERROR_Price");
				Result.setTextFill(Color.RED);
	            Result.setText("Enter in Price");         
			}
			else if(stockField.getText().isEmpty())
			{
				System.out.println("ERROR_Stock");
				Result.setTextFill(Color.RED);
	            Result.setText("Enter in stock");
			}	
			else
			{
				Result.setTextFill(Color.GREEN);
			    Result.setText("Product Added");
			    int STOCK = Integer.parseInt(stock);
				double PRICE = Double.parseDouble(price);
				Product p = new Product(id, name, PRICE, STOCK, rating);
				//System.out.println("Would have been added");
				ProductCollection PC = new ProductCollection();
				PC.addProduct(p);
				Main.showAddProductConfirmStage();
			}		
		}
        catch(NumberFormatException NFE)
        { 
            Result.setTextFill(Color.RED);
            Result.setText("Invalid Phone Number Entered!");
        }
	}
}*/
