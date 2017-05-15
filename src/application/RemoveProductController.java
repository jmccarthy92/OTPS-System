package application;


import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import ProductManagementSystem.*;

public class RemoveProductController
{
	@FXML
	private TextField idField;
	@FXML
	private Label Result;
	
	@FXML
	private void exitRemoveButton() throws IOException //This is for the exitRemove stage
	{
		Main.exitRemoveStage();
	}
	
	@FXML
	private void submitRemoveButton() throws ClassNotFoundException, SQLException, IOException
	{
		String id = String.valueOf(idField.getText());
		
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
			else
			{
				System.out.println("All good");
				Result.setTextFill(Color.GREEN);
			    Result.setText("Product Added");
			    
				ProductCollection PC = new ProductCollection();
				PC.removeProduct(id);
				Main.showRemoveProductConfirmStage();
			}
		}
		catch(NumberFormatException e)
		{
			Result.setTextFill(Color.RED);
            Result.setText("Invalid Phone Number Entered!");
		}
	}
}
