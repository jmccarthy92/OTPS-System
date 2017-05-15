package application;
import java.io.IOException;
import java.net.URL;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
//import OTPS.Product;
//import OTPS.ProductCollection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
//import productview.Main;
import ProductManagementSystem.*;

public class ViewProductListController extends BorderPane implements Initializable {
	
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
	//viewlist
	@FXML
	private void showlist(ActionEvent ae) throws ClassNotFoundException, SQLException, IllegalStateException, IOException
    {
		Product po = new Product("", "", 0.0, 0, 0.0);
		
		productList = PC.viewProductList();
		data.removeAll(data);
		for(String key : productList.keySet())
		{
			System.out.println(productList.get(key));
			  Product product = new Product (productList.get(key).getProductId(), productList.get(key).getProductName(), 
			productList.get(key).getProductPrice(), productList.get(key).getStockQuantity(), productList.get(key).getProductRating());
			  data.add(product);
		}
		productTable.getItems().setAll(this.data);
		

		
    }
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		//invalidField.setVisible(false);
		String Account = "1";
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
		new SimpleStringProperty ((String.valueOf(cellData.getValue().getProductPrice() ) ) ));
		
		
		
		
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

		
		
		
	
	//close viewproductlist
	@FXML
	private void closeViewproductlist() throws IOException
	{
	//	Main.Closeproductlist();
	}
	
}
