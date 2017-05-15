


package application;
	
import java.io.File;
import java.io.IOException;
import java.net.URL;

import AccountSubsystem.Account;
import AccountSubsystem.User;
import OrderSubsystem.Order;
import OrderSubsystem.ShoppingCart;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;



/**
 * @author James McCarthy
 * 
 *
 */
public class Main extends Application {
	
	private static Stage primaryStage;
	private static Stage menuStage;
	private static Stage cartMenuStage;
	private static Stage checkoutMenuStageVar;
	private static Stage paymentMenuStage;
	private static Stage viewOrderAdminStage;
	private static Stage adminLogInStage;
	private static Stage confirmationDisplay;
	private static Stage removedDisplay;
	private static Stage shippedDisplay;
	private static Stage cancelDisplay;
	private static Stage orderLogDisplay;
	private static Stage productRatingDisplay;
	private static Stage createOrderDisplay;
	private static Stage editInfoDisplay;
	private static Stage forgotPWDisplay;
	private static Stage createAdminDisplay;
	private static Stage adminControl;
	private static Stage viewAccountStage;
	private static Stage viewProductAdminStage;
	
	private static Scene mainMenuScene;
	private static  Order removedOrder;
	private static Order createdOrder;
	private static ShoppingCart cart;
	
	private static Account account;
	private static User user;
	public static void setAccount(Account accountIn) { accountIn = account;}
	public static Account getAccount(){return account;}
	public static void setUser( User userIn ) { user = userIn;}
	public static User getUser() { return user;}
	public static void setRemovedOrder( Order orderIn) {removedOrder = orderIn;}
	public static Order getRemovedOrder(){return removedOrder;}
	public static void setCreateOrder(Order orderIn) {createdOrder = orderIn;}
	public static Order getCreatedOrder(){return createdOrder;}
	public static void setCart(ShoppingCart cartIn) { cart = cartIn;}
	public static ShoppingCart getCart(){return cart;}
	
	@Override
	public void start(Stage primaryStage) {
		try {
		//	RootPanelController rpc = new RootPanelController();
			this.primaryStage = primaryStage;
			PopUpPanelController pupc = new PopUpPanelController();
			Scene scene = new Scene(pupc);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void showProductRating() throws IOException
	{
		FXMLLoader loader= new FXMLLoader();//Create loader to load the doument into main class
		loader.setLocation(Main.class.getResource("Ratingaproduct.fxml")); //use loader to load the document
		BorderPane prPane = loader.load();//Tells loader to load the document
		Stage prStage = new Stage();
		productRatingDisplay = prStage;
		Scene scene = new Scene(prPane);
		prStage.setTitle("Prouct Rating Window");
		prStage.initOwner(menuStage);
		prStage.setScene(scene);
		
		prStage.show();
		
	}
	
	public static void hideCreateUser() throws IOException
	{
		createOrderDisplay.close();
	}
    public static void showCreateUser() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("CreateUser.fxml"));
        AnchorPane CreateUser = loader.load();
        Stage createStage = new Stage();
        createOrderDisplay = createStage;
        Scene scene = new Scene(CreateUser);
        createStage.setTitle("Create User Window");
        createStage.initOwner(primaryStage);
        createStage.initModality(Modality.WINDOW_MODAL);
        createStage.setScene(scene);
        createStage.showAndWait();
    }
    
    public static void showCreateUserOrder() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("CreateUser.fxml"));
        AnchorPane CreateUser = loader.load();
        Stage createStage = new Stage();
        createOrderDisplay = createStage;
        Scene scene = new Scene(CreateUser);
        createStage.setTitle("Create User Window");
        createStage.initOwner(checkoutMenuStageVar);
        createStage.initModality(Modality.WINDOW_MODAL);
        createStage.setScene(scene);
        createStage.showAndWait();
    }
    
    public static void showCreateAdminMenu() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("CreateAdmin.fxml"));
        AnchorPane CreateAdmin = loader.load();
        Scene scene = new Scene(CreateAdmin);
        Stage createAStage = new Stage();
        createAdminDisplay = createAStage;
        createAStage.setTitle("Create Admin Window");
        createAStage.initOwner(menuStage);
        createAStage.initModality(Modality.WINDOW_MODAL);
        createAStage.setScene(scene);
        createAStage.showAndWait();
        //primaryStage.setScene(scene);
        //primaryStage.show();
    }
    
    public static void hideCreateAdmin() throws IOException
    {
    	createAdminDisplay.close();
    }
    public static void hideForgotPassword() throws IOException
    {
    	forgotPWDisplay.close();
    }
    
    public static void showForgotPassword() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("forgotPassword.fxml"));
        AnchorPane forgotMenu = loader.load();
        Stage pwStage = new Stage();
        forgotPWDisplay = pwStage;
        Scene scene = new Scene(forgotMenu);
        pwStage.setTitle("Forgot Password Stage");
        pwStage.initOwner(primaryStage);
        pwStage.initModality(Modality.WINDOW_MODAL);
        pwStage.setScene(scene);
        pwStage.showAndWait();
        //    primaryStage.setScene(scene);
     //   primaryStage.show();
    }
    
    public static void showLoginMenu() throws IOException
    {
    	primaryStage.show();
    }
    public static void hideLogInMenu() throws IOException
    {
    	primaryStage.close();
    }
    
    public static void showEditUserInfo() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("editUser.fxml"));
        AnchorPane editInfo = loader.load();
        Stage editStage = new Stage();
        editInfoDisplay = editStage;
        Scene scene = new Scene(editInfo);
        editStage.setTitle("Create User Window");
        editStage.initOwner(menuStage);
        editStage.initModality(Modality.WINDOW_MODAL);
        editStage.setScene(scene);
        editStage.showAndWait();
     
        
    }
    
    public static void hideUserInfo() throws IOException
    {
    	editInfoDisplay.close();
    }
    
	
	public static void hideProductRating() throws IOException
	{
		productRatingDisplay.close();
	}
	public static Stage getPMenuStage() { return paymentMenuStage;}
	
	public static void hideAdminControl() throws IOException
	{
		adminControl.close();
	}
	public static void showAdminControl() throws IOException
	{
		adminLogInStage.close();
		primaryStage.hide();
		//cartMenuStage = null;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("AdminControlScreen.fxml"));
	//	loader.setRoot(RootPanelController);
		BorderPane adminMenu = loader.load();
		Stage adminMenuStage = new Stage();
		adminControl = adminMenuStage;
		adminMenuStage.setTitle("Admin Menu");
	//	mainMenuStage.initModality(Modality.WINDOW_MODAL);
		adminMenuStage.initOwner(adminLogInStage);
		Scene scene = new Scene(adminMenu);
		//mainMenuScene = scene;
		adminMenuStage.setScene(scene);
		primaryStage.hide();
		adminMenuStage.show();
	}
	
	public static void showMainMenu() throws IOException
	{
		primaryStage.hide();
		cartMenuStage = null;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("MainMenu.fxml"));
	//	loader.setRoot(RootPanelController);
		BorderPane mainMenu = loader.load();
		Stage mainMenuStage = new Stage();
		menuStage = mainMenuStage;
		mainMenuStage.setTitle("Main Menu");
	//	mainMenuStage.initModality(Modality.WINDOW_MODAL);
		mainMenuStage.initOwner(primaryStage);
		Scene scene = new Scene(mainMenu);
		mainMenuScene = scene;
		mainMenuStage.setScene(scene);

		mainMenuStage.show();
	}
	
	public static void showOrderLogInMenu() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("OrderLogInScreen.fxml"));
		BorderPane sDisplay = loader.load();
		Stage sStage = new Stage();
		orderLogDisplay = sStage;
		Scene scene = new Scene(sDisplay);
		sStage.setTitle("Order Log In Display");
		sStage.initOwner(viewOrderAdminStage);
		sStage.initModality(Modality.WINDOW_MODAL);
		sStage.setScene(scene);
		
		sStage.showAndWait();
	}
	public static void hideOrderLogInMenu() throws IOException
	{
		orderLogDisplay.hide();
	}
	public static void showShippedDisplay() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("ShippedOrderDisplay.fxml"));
		BorderPane sDisplay = loader.load();
		Stage sStage = new Stage();
		shippedDisplay = sStage;
		Scene scene = new Scene(sDisplay);
		sStage.setTitle("Remove Order Display");
		sStage.initOwner(checkoutMenuStageVar);
		sStage.initModality(Modality.WINDOW_MODAL);
		sStage.setScene(scene);
		
		sStage.showAndWait();
	}
	
	public static void showCancelDisplay() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("CancelOrderDisplay.fxml"));
		BorderPane cDisplay = loader.load();
		Stage cStage = new Stage();
		cancelDisplay = cStage;
		Scene scene = new Scene(cDisplay);
		cStage.setTitle("Remove Order Display");
		cStage.initOwner(viewOrderAdminStage);
		cStage.initModality(Modality.WINDOW_MODAL);
		cStage.setScene(scene);
		
		cStage.showAndWait();
	}
	
	
	public static void showRemovedDisplay() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("RemoveOrderDisplay.fxml"));
		BorderPane rDisplay = loader.load();
		Stage rStage = new Stage();
		removedDisplay = rStage;
		Scene scene = new Scene(rDisplay);
		rStage.setTitle("Remove Order Display");
		rStage.initOwner(viewOrderAdminStage);
		rStage.initModality(Modality.WINDOW_MODAL);
		rStage.setScene(scene);
		
		rStage.showAndWait();
	}
	
	public static void showConfirmationDisplay() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("ConfirmationDisplay.fxml"));
		BorderPane cDisplay = loader.load();
		Stage cDStage = new Stage();
		confirmationDisplay = cDStage;
		Scene scene = new Scene(cDisplay);
		cDStage.setTitle("Confirmation Display");
		cDStage.initOwner(paymentMenuStage);
		cDStage.initModality(Modality.WINDOW_MODAL);
		cDStage.setScene(scene);
		cDStage.showAndWait();
	}
	
	
	
	public static void hideCancelDisplay() throws IOException
	{
		cancelDisplay.close();
	}
	
	public static void hideShippedDisplay() throws IOException
	{
		shippedDisplay.close();
	}
	
	public static void hideRemoveDisplay() throws IOException
	{
		removedDisplay.close();
	}
	public static void hideConfirmationDisplay() throws IOException
	{
		confirmationDisplay.close();
	}
	
	public static void showAdminLogInMenu() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("AdministratorLogIn.fxml"));
		AnchorPane aMenu = loader.load();
		Stage aStage = new Stage();
		adminLogInStage = aStage;
		aStage.setTitle("Admin Log-In");
		aStage.initOwner(primaryStage);
		aStage.initModality(Modality.WINDOW_MODAL);
		Scene scene = new Scene(aMenu);
		aStage.setScene(scene);
		aStage.showAndWait();
		
	}
	public static void hdieAdminLogin() throws IOException
	{
		adminLogInStage.hide();
	}
	public static void showAdminOrderMenu() throws IOException
	{
		adminLogInStage.hide();
		primaryStage.hide();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("viewAllOrderMenu.fxml"));
		BorderPane ordMenu = loader.load();
		Stage ordStage = new Stage();
		//paymentMenuStage = payStage;
		viewOrderAdminStage = ordStage;
		ordStage.setTitle("Admin Order Control Menu");
	//	ordStage.initModality(Modality.WINDOW_MODAL);
	//	ordStage.initOwner(checkoutMenuStageVar);
		Scene scene = new Scene(ordMenu);
		ordStage.setScene(scene);
		ordStage.show();
	}
	
	 public static void showViewAccountMenu() throws IOException
	    {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("SearchAccounts.fxml"));
	        AnchorPane mainLayout = loader.load();
	        Scene scene = new Scene(mainLayout);
	        Stage viewAccStage = new Stage();
	        viewAccountStage = viewAccStage;
	    	viewAccStage.setTitle("Admin ACcount Control Menu");
	    	//	ordStage.initModality(Modality.WINDOW_MODAL);
	    	//	ordStage.initOwner(checkoutMenuStageVar);
	    	//	Scene scene = new Scene(mainLayout);
	    		viewAccStage.setScene(scene);
	    		viewAccStage.show();
	    		primaryStage.hide();
	    		//admin
	    }
	 

		public static void showMainView() throws IOException //This functions creates a mainView stage and sets it up
		{
			FXMLLoader loader = new FXMLLoader(); //creates the loader
			loader.setLocation(Main.class.getResource("MainView.fxml")); //sets location of the loader
			BorderPane mainLayout = loader.load(); // loads the layout into the loaders
			Scene scene = new Scene(mainLayout); //creates the scene and sets it to the layout
			Stage viewProdStage = new Stage();
			viewProductAdminStage = viewProdStage;
			viewProdStage.setTitle("Admin Product Control Menu");
			viewProdStage.setScene(scene); //sets the scene into the primary stage
			viewProdStage.show(); // shows the primary stage
		}
		
		public static void hideMainView() throws IOException
		{
			viewProductAdminStage.hide();
		}
		
		public static Stage addProductStage;
		public static Stage addProductConfirmStage;
		
		public static Stage removeProductStage;
		public static Stage removeProductConfirmStage;
		
		public static Stage changePriceStage;
		public static Stage changePriceConfirmStage;
		
		public static Stage changeQuantityStage;
		public static Stage changeQuantityConfirmStage;
			
		
		/******************************************************************************/
		
		public static void showAddProductStage() throws IOException //This functions creates a addProduct stage and sets it up
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("AddProduct.fxml"));
			BorderPane addProduct = loader.load();
			
			Stage addProductStage1 = new Stage(); //creates a new stage
			addProductStage = addProductStage1; //gives the stage a value
			addProductStage1.setTitle("Add Product");
			addProductStage1.initModality(Modality.WINDOW_MODAL); 
			addProductStage1.initOwner(viewProductAdminStage); 
			Scene scene = new Scene (addProduct);
			addProductStage1.setScene(scene);
			addProductStage1.show();
		}
		
		public static void showAddProductConfirmStage() throws IOException //This functions creates a addProductConfirm stage and sets it up
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("AddProductConfirm.fxml"));
			BorderPane addProductConfirm = loader.load();
			
			Stage addProductConfirmStage1 = new Stage();
			addProductConfirmStage = addProductConfirmStage1;
			addProductConfirmStage1.setTitle("Add Product Confirm");
			addProductConfirmStage1.initModality(Modality.WINDOW_MODAL);
			addProductConfirmStage1.initOwner(addProductStage); 
			
			Scene scene = new Scene (addProductConfirm);
			addProductConfirmStage1.setScene(scene);
			addProductConfirmStage1.show();
		}
		
		public static void showRemoveProductStage() throws IOException //This functions creates a removeProduct stage and sets it up
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("RemoveProduct.fxml"));
			BorderPane removeProduct = loader.load();
			
			Stage removeProductStage1 = new Stage();
			removeProductStage = removeProductStage1;
			removeProductStage1.setTitle("Remove Product");
			removeProductStage1.initModality(Modality.WINDOW_MODAL);
			removeProductStage1.initOwner(viewProductAdminStage);
			
			Scene scene = new Scene (removeProduct);
			removeProductStage1.setScene(scene);
			removeProductStage1.showAndWait();
		}
		
		public static void showRemoveProductConfirmStage() throws IOException //This function creates a removeProductConfirm stage and sets it up
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("RemoveProductConfirm.fxml"));
			BorderPane removeProductConfirm = loader.load();
			
			Stage removeProductConfirmStage1 = new Stage();
			removeProductConfirmStage = removeProductConfirmStage1;
			removeProductConfirmStage1.setTitle("Remove Product Confirm");
			removeProductConfirmStage1.initModality(Modality.WINDOW_MODAL);
			removeProductConfirmStage1.initOwner(removeProductStage);
			
			Scene scene = new Scene (removeProductConfirm);
			removeProductConfirmStage1.setScene(scene);
			removeProductConfirmStage1.showAndWait();
		}
		
		public static void showChangePriceStage() throws IOException //This functions creates a changePrice stage and sets it up
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ChangePrice.fxml"));
			BorderPane changePrice = loader.load();
			
			Stage changePriceStage1 = new Stage();
			changePriceStage = changePriceStage1;
			changePriceStage1.setTitle("Change Price");
			changePriceStage1.initModality(Modality.WINDOW_MODAL);
			changePriceStage1.initOwner(viewProductAdminStage);
			
			Scene scene = new Scene (changePrice);
			changePriceStage1.setScene(scene);
			changePriceStage1.showAndWait();
		}
		
		public static void showChangePriceConfirmStage() throws IOException// This function creates a changePrice confirm stage and sets it up
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ChangePriceConfirm.fxml"));
			BorderPane changePriceConfirm = loader.load();
			
			Stage changePriceConfirmStage1 = new Stage();
			changePriceConfirmStage = changePriceConfirmStage1;
			changePriceConfirmStage1.setTitle("Change Price Confirm");
			changePriceConfirmStage1.initModality(Modality.WINDOW_MODAL);
			changePriceConfirmStage1.initOwner(changePriceStage);
			
			Scene scene = new Scene (changePriceConfirm);
			changePriceConfirmStage1.setScene(scene);
			changePriceConfirmStage1.showAndWait();
		}
		
		public static void showChangeQuantityStage() throws IOException //This functions creates a changeQuantity stage and sets it up
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ChangeStockQuantity.fxml"));
			BorderPane changeQuantity = loader.load();
			
			Stage changeQuantityStage1 = new Stage();
			changeQuantityStage = changeQuantityStage1;
			changeQuantityStage1.setTitle("Change Stock Quantity");
			changeQuantityStage1.initModality(Modality.WINDOW_MODAL);
			changeQuantityStage1.initOwner(viewProductAdminStage);
			
			Scene scene = new Scene (changeQuantity);
			changeQuantityStage1.setScene(scene);
			changeQuantityStage1.showAndWait();
		}
		
		public static void showChangeQuantityConfirmStage() throws IOException //This functions creates a changeQuantity stage and sets it up
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ChangeStockQuantityConfirm.fxml"));
			BorderPane changeQuantityConfirm = loader.load();
			
			Stage changeQuantityConfirmStage1 = new Stage();
			changeQuantityConfirmStage = changeQuantityConfirmStage1;
			changeQuantityConfirmStage1.setTitle("Change Stock Quantity Confirm");
			changeQuantityConfirmStage1.initModality(Modality.WINDOW_MODAL);
			changeQuantityConfirmStage1.initOwner(changeQuantityStage);
			
			Scene scene = new Scene (changeQuantityConfirm);
			changeQuantityConfirmStage1.setScene(scene);
			changeQuantityConfirmStage1.showAndWait();
		}
		
		public static void exitAddStage() throws IOException, IllegalStateException //This creates the back button for addStage
		{	
			addProductStage.close();
		}
		
		public static void exitRemoveStage() //This creates the back button for the removeStage
		{
			removeProductStage.close();
		}
			
		public static void exitChangePriceStage() //This creates the back button for the changePriceStage
		{
			changePriceStage.close();
		}
		
		public static void exitChangeQuantityStage() //This creates the back button for the changeQuantityStage
		{
			changeQuantityStage.close();
		}
		
		
		
		 /******************************************************************************/
			
		
	 public static void hideViewAccountMenu() throws IOException
	 {
		 viewAccountStage.close();
	 }
	
	public static void hideAdminOrderMenu() throws IOException
	{
		viewOrderAdminStage.close();
	}
	
	public static void hideCartMenu() throws IOException
	{
		cartMenuStage = null;
		cartMenuStage.hide();
		
	}
	
	public static void hideViewOrderHistoryMenu() throws IOException
	{
		menuStage.setScene(mainMenuScene);
		//viewOrderHistoryStage.hide();
	}
	public static void showCartMenu()	throws IOException
	{
		if(cartMenuStage == null)
		{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("CartMenu.fxml"));
			BorderPane cartMenu = loader.load();
			Stage cartStage = new Stage();
			cartMenuStage = cartStage;
			cartStage.setTitle("Cart Menu");
	//	cartStage.initModality(MOdality.);
			cartStage.initOwner(menuStage);
			Scene scene = new Scene(cartMenu);
			cartStage.setScene(scene);
			cartStage.show();
	 }
						
	}
	
	public static void showViewOrderHistory() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("viewOrderHistoryMenu.fxml"));
		BorderPane viewOrderHistMenu = loader.load();
		Scene scene = new Scene(viewOrderHistMenu);
		menuStage.setScene(scene);
	//	primaryStage.show();
		
	}
	
	public static void showPaymentMenu() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("MakePaymentMenu.fxml"));
		BorderPane payMenu = loader.load();
		Stage payStage = new Stage();
		paymentMenuStage = payStage;
		payStage.setTitle("Make Payment Menu");
		payStage.initModality(Modality.WINDOW_MODAL);
		payStage.initOwner(checkoutMenuStageVar);
		Scene scene = new Scene(payMenu);
		payStage.setScene(scene);
		payStage.showAndWait();
		
	}
	
	public static void hideCheckoutMenu() throws IOException
	{
		checkoutMenuStageVar.hide();
	}
	
	public static void hideMainMenu() throws IOException
	{
		if(menuStage != null)
			menuStage.close();
	}
	
	public static  void hidePaymentMenu() throws IOException
	{
		paymentMenuStage.hide();
	}
	
	public static void showCheckoutMenuScreen() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("CheckoutMenu.fxml"));
		//loader.setRoot
		AnchorPane checkoutMenu = loader.load();
		Stage checkoutMenuStage = new Stage();
		checkoutMenuStageVar = checkoutMenuStage;
		checkoutMenuStage.setTitle("Checkout Menu");
		checkoutMenuStage.initModality(Modality.WINDOW_MODAL);
		checkoutMenuStage.initOwner(cartMenuStage);
		Scene scene = new Scene(checkoutMenu);
		checkoutMenuStage.setScene(scene);
		checkoutMenuStage.showAndWait();
		//checkoutMenuStage.
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
