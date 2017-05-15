package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

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
	private Label Result;
	
	@FXML
	private void exitChangePriceStageButton() //This is for the exitChangePrice stage
	{
		Main.exitChangePriceStage();
	}
	
	@FXML
	private void submitChangePriceButton() throws ClassNotFoundException, SQLException, IOException
	{
		String id = String.valueOf(idField.getText());
		String price = String.valueOf(priceField.getText());
		
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
			else if(priceField.getText().isEmpty() )
			{
			    System.out.println("ERROR_Price");
				Result.setTextFill(Color.RED);
	            Result.setText("Enter in Price");         
			}
			else
			{
				Result.setTextFill(Color.GREEN);
			    Result.setText("Product Added");
			    
			    double PRICE = Double.parseDouble(price);
			    
				ProductCollection PC = new ProductCollection();
				PC.changePrice(id, PRICE);
				Main.showChangePriceConfirmStage();
			}
		}
		catch(NumberFormatException e)
		{
			Result.setTextFill(Color.RED);
            Result.setText("Invalid Phone Number Entered!");
		}
	}
}
