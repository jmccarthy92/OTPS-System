//this class contains all the buttons that will be on the main stage 

package application;
//Control all the buttons on the main view stage 

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
//import productcontrol.Main;
import ProductManagementSystem.*;

public class AdminProductViewController implements Initializable
{
	HashMap<String, Product> hmap = new HashMap <String, Product>();
	@FXML private TableColumn<Product,String> ProductIdColumn;
	@FXML private TableColumn<Product,String> ProductNameColumn;
	@FXML private TableColumn<Product,String> ProductPriceColumn;
	@FXML private TableColumn<Product,String> ProductquantityColumn;
	@FXML private TableColumn<Product,String> ProductratingColumn;
	@FXML private TableView<Product> productTable;
	private ObservableList<Product> data = FXCollections.observableArrayList();
	
	private ProductCollection PC = new ProductCollection();
	private HashMap<String,Product> productList = new HashMap <String,Product>();
	private NumberFormat nFormat = NumberFormat.getCurrencyInstance();
	
	Product p = new Product("", "", 0.0, 0, 0.0);
	
	@FXML private TextField idField;
	@FXML private TextField priceField;
	@FXML private TextField nameField;
	
	@FXML private CheckBox price_equalto; 
	@FXML private CheckBox price_lessthan;
	@FXML private CheckBox price_greaterthan;
	
	@FXML private Label Result;
	
	
	@FXML
	private void addProductButton() throws IOException //This is the addButton
	{
		Main.showAddProductStage(); //when the function addButton is called by pressing the add button it will run the AddStage() functions 
		refreshList();
	}
	
	@FXML
	private void removeProductButton() throws IOException //This is the remove button
	{
		Main.showRemoveProductStage();
		refreshList();
	}
	
	@FXML
	private void changePriceButton() throws IOException //This is the change price button
	{
		Main.showChangePriceStage();
		refreshList();
	}
	
	@FXML
	private void changeQuantityButton() throws IOException //This is the change quantity button 
	{
		Main.showChangeQuantityStage();
		refreshList();
	}
	
	
	@FXML
	private void exitButton() throws IOException //This is the exit button
	{
		Main.showAdminControl();
		Main.hideMainView();
	}
	
	@FXML
	private void submitButtonID() throws ClassNotFoundException, SQLException
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
				Result.setTextFill(Color.GREEN);
			    Result.setText("Product Found");
			    
				p = PC.searchProduct_ID(id);
				data.removeAll(data);
				data.add(p);
				productTable.getItems().setAll(this.data);	
			}
		}
		catch(NumberFormatException e)
		{
			Result.setTextFill(Color.RED);
            Result.setText("Invalid Phone Number Entered!");
		}	
	}
	
	@FXML
	private void submitButtonName() throws ClassNotFoundException, SQLException
	{
		String name =  String.valueOf(nameField.getText());
		
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
			else
			{
				Result.setTextFill(Color.GREEN);
			    Result.setText("Product Found");
				
				p = PC.searchProduct_Name(name);
				data.removeAll(data);
				data.add(p);
				productTable.getItems().setAll(this.data);
			}
		}
		catch(NumberFormatException NFE)
		{
			Result.setTextFill(Color.RED);
            Result.setText("Invalid Phone Number Entered!");
		}
	}
	
	@FXML
	private void submitButtonPrice() throws ClassNotFoundException, SQLException
	{
		int choice = 0;
		
		if(price_equalto.isSelected())
		{
			choice = 1;
			price_lessthan.setSelected(false);
			price_greaterthan.setSelected(false);
		}
		else if(price_lessthan.isSelected())
		{
			choice = 2;
			price_equalto.setSelected(false);
			price_greaterthan.setSelected(false);
		}
		else if(price_greaterthan.isSelected())
		{
			choice = 3;
			
			price_lessthan.setSelected(false);
			price_equalto.setSelected(false);
		}
		
		
		String price = String.valueOf(priceField.getText());
		
		try
		{
			if(priceField.getText().isEmpty() )
			{
			    System.out.println("ERROR_Price");
				Result.setTextFill(Color.RED);
	            Result.setText("Enter in Price");         
			}
			else
			{
				Result.setTextFill(Color.GREEN);
				Result.setText("Found Product");
				
				double PRICE = Double.parseDouble(price);
				
				productList = PC.searchProduct_Price(PRICE, choice);
				data.removeAll(data);
				
				for(String key : productList.keySet())
				{
					System.out.println(productList.get(key));
					Product product = new Product (productList.get(key).getProductId(), productList.get(key).getProductName(),productList.get(key).getProductPrice(), productList.get(key).getStockQuantity(), productList.get(key).getProductRating());
					data.add(product);
				}
				productTable.getItems().setAll(this.data);	
			}
		}
		catch(NumberFormatException NFE)
		{
			 Result.setTextFill(Color.RED);
	         Result.setText("Invalid Phone Number Entered!");
		}
	}
	

	@FXML
	private void refreshList() throws IOException
	{
		data.removeAll(data);
		try {
			productList = PC.viewProductList();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String key : productList.keySet())
		{
			//System.out.println(product);
			  Product product = new Product (productList.get(key).getProductId(), productList.get(key).getProductName(), 
			productList.get(key).getProductPrice(), productList.get(key).getStockQuantity(), productList.get(key).getProductRating());
			  data.add(product);
		}
		productTable.getItems().setAll(this.data);
	}
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			productList = PC.viewProductList();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProductIdColumn.setCellValueFactory(cellData ->	
					new SimpleStringProperty(cellData.getValue().getProductId()));
		
		
		ProductNameColumn.setCellValueFactory(cellData ->	
		new SimpleStringProperty(cellData.getValue().getProductName()));
		
		
		
		
		ProductPriceColumn.setCellValueFactory(cellData ->
		new SimpleStringProperty ((String.valueOf(nFormat.format(cellData.getValue().getProductPrice() ) ) ) ));
		
		
		
		
		ProductquantityColumn.setCellValueFactory(cellData ->	
		new SimpleStringProperty((String.valueOf(cellData.getValue().getStockQuantity()))));
		
		
		
		
		ProductratingColumn.setCellValueFactory(cellData ->	
		new SimpleStringProperty((String.valueOf(cellData.getValue().getProductRating()))));
		
		
		for(String key : productList.keySet())
		{
			//System.out.println(product);
			  Product product = new Product (productList.get(key).getProductId(), productList.get(key).getProductName(), 
			productList.get(key).getProductPrice(), productList.get(key).getStockQuantity(), productList.get(key).getProductRating());
			  data.add(product);
		}
		productTable.getItems().setAll(this.data);
	}
}
