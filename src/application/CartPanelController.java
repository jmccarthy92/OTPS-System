package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import OrderSubsystem.Order;
import OrderSubsystem.ShoppingCart;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CartPanelController extends BorderPane implements Initializable{

	@FXML private Button checkoutButton;
	@FXML private Button backToMain;
	@FXML private TableView<Product> cartTable;
	
	private ProductCollection pc = new ProductCollection();
	@FXML private TableColumn<Product,String> nameColumn;
	@FXML private TableColumn<Product,String> quantColumn;
	@FXML private TableColumn<Product,String> totalColumn;
	private ObservableList<Product> data = FXCollections.observableArrayList();
	private ShoppingCart cart = new ShoppingCart();
	private static	CheckoutMenuController cmc = new CheckoutMenuController();
	private static  Scene checkout_page_scene = new Scene(cmc);
	private static  Stage checkout_stage;
	
	
	private NumberFormat nFormat = NumberFormat.getCurrencyInstance();
	
	
	private String addProductId;
	private String addProdAmtStr;
	private String removeProdId;
	private String removeProdAmtStr;
	@FXML private Button calcTotal;
	@FXML private Button addProductButton;
	@FXML private Button removeProductButton;
	@FXML private TextField addProdText;
	@FXML private TextField removeProdText;
	@FXML private TextField addProdAmt;

	private Product addedProduct;
	@FXML private Label cartTotal = new Label();
	
	@FXML private Label notEnuffStock;
	@FXML private Label invalidAddId;
	@FXML private Label invalidRemoveId;
	@FXML private Label invalidAmt;
	@FXML private Label invalidAmt2;
	
	private String intCheck( String s)
	{
			int o ;
	
				try
				{
					o = Integer.parseInt(s);
					return s;
				}
				catch(NumberFormatException e)
				{	
					return null;
				}
				catch(Exception e)
				{
					return null;
				}	
	}
	@FXML protected void addButtonClick(ActionEvent ae) throws IOException
	{
		addProductId = addProdText.getText();
		addProductId = intCheck(addProductId);
		addProdAmtStr = addProdAmt.getText();
		addProdAmtStr = intCheck(addProdAmtStr);
		System.out.println(addProdAmtStr);
		try {
			addedProduct = pc.searchProduct_ID(addProductId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int checkLessThanZero = -1;
		if( addProdAmtStr != null  && addProductId != null)
				checkLessThanZero = Integer.parseInt(addProdAmtStr);
		if( addProductId != null && addProdAmtStr != null
				&& addedProduct.getStockQuantity() > checkLessThanZero
				&& checkLessThanZero > 0)
		{
			invalidAmt.setVisible(false);
			invalidAddId.setVisible(false);
			notEnuffStock.setVisible(false);
			addProdText.getStyleClass().remove("error");
			addProdAmt.getStyleClass().remove("error");

			
				cart.addToCart(addedProduct, Integer.parseInt(addProdAmtStr));

			if( addedProduct != null)
			{
				data.add(addedProduct);
			}
			cartTable.getItems().setAll(this.data);
			if( !cart.isEmpty())
			{
				checkoutButton.setVisible(true);
			}
			addProdText.clear();
			addProdAmt.clear();
		}
		else
		{
			if( addProductId == null)
			{
				invalidAddId.setVisible(true);
				addProdText.getStyleClass().add("error");
			}
			if( addProdAmtStr == null)
			{
				invalidAmt.setVisible(true);
				addProdAmt.getStyleClass().add("error");
			}
			if( checkLessThanZero > addedProduct.getStockQuantity())
				notEnuffStock.setVisible(true);
		}
	}
	
	@FXML protected void removeButtonClick(ActionEvent ae) throws IOException
	{
		removeProdId = removeProdText.getText();
		removeProdId = intCheck(removeProdId);

		//int check = Integer.parseInt(removeProdAmtStr);
		if( removeProdId != null)
		{
			invalidRemoveId.setVisible(false);
			removeProdText.getStyleClass().remove("error");
			cart.removeFromCartById(removeProdId);
			data.removeAll(data);
			for( Product p : cart.getCart())
			{
				data.add(p);
			}
			cartTable.getItems().setAll(this.data);
			removeProdText.clear();
			if(cart.isEmpty())
				checkoutButton.setVisible(false);
		}
		else
		{
			invalidRemoveId.setVisible(true);
			removeProdText.getStyleClass().add("error");

		}
		
	}
	
	@FXML protected void calcTotalButtonClick(ActionEvent ae) throws IOException
	{
		String salesTotal = nFormat.format(cart.getTotal());
		cartTotal.setVisible(true);
		cartTotal.setText( "->" +salesTotal );
	}
	
	
	@FXML protected void checkoutButtonClick(ActionEvent ae) throws IOException
	{
		/*checkout_stage = new Stage();
		checkout_stage.setTitle("Cart Menu");
		checkout_stage.setScene(checkout_page_scene);
		checkout_stage.initModality(Modality.WINDOW_MODAL);
		checkout_stage.show();*/
		Main.setCart(cart);
		Main.showCheckoutMenuScreen();
		
	}
	
	@FXML protected void mainMenuButtonclick(ActionEvent ae) throws IOException
	{
		Main.hideMainMenu();
		Main.showMainMenu();
	}


	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		if(cart.isEmpty())
		{
			checkoutButton.setVisible(false);
		}
		notEnuffStock.setVisible(false);
		cartTotal.setVisible(false);
		invalidAddId.setVisible(false);
		invalidRemoveId.setVisible(false);
		invalidAmt.setVisible(false);
	//	invalidAmt2.setVisible(false);
		nameColumn.setCellValueFactory(cellData -> 
							new SimpleStringProperty(cellData.getValue().getProductName()));
		quantColumn.setCellValueFactory(cellData -> 
						new SimpleStringProperty(String.valueOf(cellData.getValue().getCartQuantity() ) ) );
		totalColumn.setCellValueFactory(cellData -> 
						new SimpleStringProperty(String.valueOf(nFormat.format(cellData.getValue().getProductPrice()
												* cellData.getValue().getCartQuantity() ) ) ) );
		data.removeAll(data);
	//	for( Product p : cart.getCart())
	//	{
	//		data.add(p);
	//	}
	//	cartTable.getItems().setAll(this.data);
	}
}
