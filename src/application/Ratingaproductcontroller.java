package application;

import java.io.IOException;
import java.sql.SQLException;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import ProductManagementSystem.*;

public class Ratingaproductcontroller {
	
	ObservableList<String> ChoiceList = FXCollections.observableArrayList("1","1.5","2","2.5",
			"3","3.5","4","4.5", "5");

	//private Main main;
	

	@FXML
	private TextField nameField;
	
	@FXML
	private TextField UserField;
	
	@FXML
	private ComboBox choice;
	
	@FXML
	private void initialize()
	{
		choice.setValue("1");
		choice.setItems(ChoiceList);
	}

	//calling the method when a rating has been completed
	@FXML
	private void ratingcomplete() throws IOException, ClassNotFoundException, SQLException
	{
		String ID = String.valueOf(nameField.getText()); 
		String userID = String.valueOf(UserField.getText()); 
		
		double rating = Double.parseDouble(String.valueOf(choice.getSelectionModel().getSelectedItem()));

		ProductCollection PC = new ProductCollection();
		//System.out.println(po.toString());
		 //PC.rateProduct(ID, newrating, userID);
		PC.rateProduct(ID, rating, userID);
	//	main.showsubmitbutton();
	}
	
	//closing stage
	@FXML
	private void closeStage() throws IOException
	{
		Main.hideProductRating();
	}
}
