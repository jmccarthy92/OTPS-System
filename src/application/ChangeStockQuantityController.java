package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import ProductManagementSystem.*;

public class ChangeStockQuantityController
{
	@FXML
	private TextField idField;
	@FXML
	private TextField newQuantityField;
	@FXML
	private Label Result;
	
	@FXML
	private void exitChangeQuantityStageButton() //This is for the exitChangeQuantity stage
	{
		Main.exitChangeQuantityStage();
	}
	
	@FXML
	private void sumbitChangeStockQuantityButton() throws ClassNotFoundException, SQLException, IOException
	{
		String id = String.valueOf(idField.getText());
		String quantity = String.valueOf(newQuantityField.getText());
		
		try
		{	
			if(idField.getText().isEmpty())
			{
				System.out.println("ID field empty");
				Result.setTextFill(Color.RED);
				Result.setText("Enter in ID");
			}
			else if(!idField.getText().matches("[0-9]+"))
			{
				System.out.println("ID contains letter");
				Result.setTextFill(Color.RED);
				Result.setText("ID should be only numbers");
			}
			else if(newQuantityField.getText().isEmpty())
			{
				Result.setTextFill(Color.RED);
				Result.setText("Quantity is empty");
				System.out.println("Quantity is empty");
			}
			else
			{
				Result.setTextFill(Color.GREEN);
			    Result.setText("Product Added");
			    
			    int STOCK = Integer.parseInt(quantity);
			    
			    ProductCollection PC = new ProductCollection();
				PC.changeStockQuantity(id, STOCK);
				Main.showChangeQuantityConfirmStage();
			}
		}
		catch(NumberFormatException e)
		{
			Result.setTextFill(Color.RED);
            Result.setText("Invalid Phone Number Entered!");
		}
	}
}
