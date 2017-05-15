//this class contains all the buttons that will be on the main stage 

/*package JDBCMigrations;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
		String id;
		Main.showAdminControl();
		Main.hideMainView();
	}
	
	@FXML
	private void submitButtonID() throws ClassNotFoundException, SQLException
	{
		String id = String.valueOf(idField.getText());
		p = PC.searchProduct_ID(id);
		data.removeAll(data);
		data.add(p);
		productTable.getItems().setAll(this.data);	
	}
	
	@FXML
	private void submitButtonName() throws ClassNotFoundException, SQLException
	{
		String name =  String.valueOf(nameField.getText());
		p = PC.searchProduct_Name(name);
		data.removeAll(data);
		data.add(p);
		productTable.getItems().setAll(this.data);
	}
	
	@FXML
	private void submitButtonPrice() throws ClassNotFoundException, SQLException
	{
		int choice = 0;
		
		if(price_equalto.isSelected())
		{
			choice = 1;
		}
		else if(price_lessthan.isSelected())
		{
			choice = 2;
		}
		else if(price_greaterthan.isSelected())
		{
			choice = 3;
		}
		
		double price = Double.valueOf(priceField.getText());
		productList = PC.searchProduct_Price(price, choice);
		data.removeAll(data);
		
		for(String key : productList.keySet())
		{
			System.out.println(productList.get(key));
			Product product = new Product (productList.get(key).getProductId(), productList.get(key).getProductName(),productList.get(key).getProductPrice(), productList.get(key).getStockQuantity(), productList.get(key).getProductRating());
			data.add(product);
		}
		productTable.getItems().setAll(this.data);	
	}
	
	
	
	@FXML
	private void refreshList() throws IOException
	{
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
}*/
