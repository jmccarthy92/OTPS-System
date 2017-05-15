package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.ResourceBundle;

import AccountSubsystem.Account_Collection;
import ProductManagementSystem.Product;
import ProductManagementSystem.ProductCollection;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**I'm really cool man jdfkjsdjfksjdfkjskd fjsd fjs
 * <p>
 * jkawjdkajwd
 * @author globe_000
 * @param hello world 
 *@return jksdjfksdjfk
 *
 *<h3> hi
 */

public class RootPanelController extends BorderPane implements Initializable
{
	@FXML private Button editButton;
	@FXML private Button logOutButton;
	@FXML private Button cartButton;
	@FXML private Button rateProductButton;
	@FXML private Button viewButton;
	
	@FXML private Button logOnButton;
	@FXML private PasswordField passwordField;
	@FXML private TextField usernameField;
	
	@FXML private TextField searchID;
	private Account_Collection ac = new Account_Collection();
	
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
	
	
	//private variables for the logout button Controller, Scene, and Stage
	private static 		PopUpPanelController pupc = new PopUpPanelController();
	private static 		Scene popUp_page_scene = new Scene(pupc);
	private static      Stage popUp_stage;
	private static		CartPanelController cpc = new CartPanelController();
	private static		Scene cartPanel_page_scene = new Scene(cpc);
	private static		Stage cartPanel_stage;
	
	private static final int WINDOW_LIMIT = 1;
	private static int WINDOW_COUNT = 0;
	


	@FXML protected void viewOrderHistClick(ActionEvent ae) throws IOException
	{
		Main.showViewOrderHistory();
	}

	@FXML protected void cartButtonClick(ActionEvent ae) throws IOException
	{

		Main.showCartMenu();
			
	}

	@FXML protected void logInButtonClick(ActionEvent ae) throws IOException
	{
		String userName = usernameField.getText();
		String pw = passwordField.getText();
		try {
			if( ac.loginUser(userName, pw) != null)
			{
				Main.setUser(ac.loginUser(userName, pw));
				Main.hideMainMenu();
				Main.showMainMenu();
			}
			else
			{
				System.out.println(" NO account FOUND");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML protected void onMouseEntered(MouseEvent event)
	{
	//	cartButton.setStyle("-fx-font-weight: bold;");
	}

	@FXML protected void onMouseExit(MouseEvent event)
	{
		try
		{
	//		cartButton.setStyle("-fx-font-weight: regular;");
		}
		catch (Exception e){};
	}
	
	@FXML protected void searchButtonClick(ActionEvent ae ) throws IOException
	{
		String productId = searchID.getText();
		Product o = null;
		try {
			
			o = PC.searchProduct_ID(productId);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.removeAll(data);

		data.add(o);
		productTable.getItems().setAll(this.data);
	}
	
	@FXML protected void rateProductClick(ActionEvent ae) throws IOException
	{
		Main.showProductRating();
		
	}
	
	@FXML protected void logoutButtonClick(ActionEvent ae) throws IOException
	{
		Main.setUser(null);
		System.out.println("LOGGGING OUT....");
		popUp_stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
		popUp_stage.setScene(popUp_page_scene);
		Main.hideMainMenu();
		popUp_stage.show();
	}
	
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
	
	
	
	@FXML protected void editButtonClick(ActionEvent ae) throws IOException
	{
		Main.showEditUserInfo();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//invalidField.setVisible(false);
		if (Main.getUser() == null)
		{
			logOnButton.setVisible(true);
			passwordField.setVisible(true);
			usernameField.setVisible(true);
			editButton.setVisible(false);
			viewButton.setVisible(false);
			
		}
		else
		{
			logOnButton.setVisible(false);
			passwordField.setVisible(false);
			usernameField.setVisible(false);
			editButton.setVisible(true);
			viewButton.setVisible(true);
		}
		//String Account = "1";
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
		//productTable.sort();
	}
	

}
