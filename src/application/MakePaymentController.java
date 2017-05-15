package application;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
//import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import ProductManagementSystem.Product;
import ProductManagementSystem.ProductCollection;
import OrderSubsystem.Address;
import OrderSubsystem.CreditCard;
import OrderSubsystem.CreditCardType;
import OrderSubsystem.Order;
import OrderSubsystem.OrderCollection;
import OrderSubsystem.ShoppingCart;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import AccountSubsystem.Account;
import AccountSubsystem.User;

public class MakePaymentController extends BorderPane implements Initializable
{
	@FXML private Button exitPaymentMenu;
	@FXML private Button submitPaymentButton;
	@FXML private TextField addressField;
	@FXML private TextField zipField;
	@FXML private TextField nameField;
	@FXML private TextField lnameField;
	@FXML private TextField ccNumField;
	@FXML private TextField ccNumField1;
	@FXML private TextField ccNumField2;
	@FXML private TextField ccNumField3;
	@FXML private TextField csvField;
	@FXML private ChoiceBox<String> ccTypeBox;
	@FXML private DatePicker expDate;
	
	private String visa = "Visa";
	private String mCard = "MasterCard";
	private String discover = "Discover";
	private String aExpress = "AmericanExpress";
	private String none = "None";
	
	private ProductCollection pc = new ProductCollection();
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	//private LinkedList<Product> testPList = new LinkedList<Product>();
	private ShoppingCart cart;
	private LinkedList<Product> cartLList;
	//private CreditCard cc;
	//private Address addrObj;
	private Stage mpStage;
	private Order createOrder;
	private OrderCollection oc = new OrderCollection();
	
	@FXML private Label invAddr;
	@FXML private Label invZip;
	@FXML private Label invCC;
	@FXML private Label invCSV;
	@FXML private Label invType;
	@FXML private Label invDate;
	private String address;
	private String ccNum;
	private String csvNum;
	private String ccType;
	private CreditCardType ordccType;
	private LocalDate ldExpDate;
	private Date ordExpDate;
	
	private Timeline addressFlasher;
	private Timeline zipFlasher;
	private Timeline ccFlasher;
	private Timeline csvFlasher;
	private Timeline expFlasher;
	private Timeline ccTypeFlasher;
	
	private int seconds = 0;
	private Timer timer = new Timer();
	private TimerTask task = new TimerTask() {
		public void run(){
			if(seconds < 10)
				{
				
				seconds++;
				}
			else
			{
				seconds = 0;
				invAddr.setVisible(false);
				addressField.getStyleClass().remove("error");
				invDate.setVisible(false);
				expDate.getStyleClass().remove("error");
				invZip.setVisible(false);
				zipField.getStyleClass().remove("error");
				invCC.setVisible(false);
				ccNumField.getStyleClass().remove("error");
				ccNumField1.getStyleClass().remove("error");
				ccNumField2.getStyleClass().remove("error");
				ccNumField3.getStyleClass().remove("error");
				invCSV.setVisible(false);
				csvField.getStyleClass().remove("error");
				invType.setVisible(false);
				ccTypeBox.getStyleClass().remove("error");
				addressFlasher.stop();
				zipFlasher.stop();
				 ccTypeFlasher.stop();
				 expFlasher.stop();
				 csvFlasher.stop();
				 ccFlasher.stop();
				 timer.cancel();
				 task.cancel();
			}
		}
	};
	
	private void update()
	{
	    seconds = 0;
	    timer = new Timer();
		 task = new TimerTask() {
			public void run(){
				if(seconds < 10)
					{
					
					seconds++;
					}
				else
				{
					seconds = 0;
					invAddr.setVisible(false);
					addressField.getStyleClass().remove("error");
					invDate.setVisible(false);
					expDate.getStyleClass().remove("error");
					invZip.setVisible(false);
					zipField.getStyleClass().remove("error");
					invCC.setVisible(false);
					ccNumField.getStyleClass().remove("error");
					ccNumField1.getStyleClass().remove("error");
					ccNumField2.getStyleClass().remove("error");
					ccNumField3.getStyleClass().remove("error");
					invCSV.setVisible(false);
					csvField.getStyleClass().remove("error");
					invType.setVisible(false);
					ccTypeBox.getStyleClass().remove("error");
					addressFlasher.stop();
					zipFlasher.stop();
					 ccTypeFlasher.stop();
					 expFlasher.stop();
					 csvFlasher.stop();
					 ccFlasher.stop();
					 timer.cancel();
					 task.cancel();
				}
			}
		};
		
	}
	
	private ConfirmationDisplayController cdc;
	// test account
//	private Account account = new Account (1);
	private User account = Main.getUser();
	// test sales total
	private double sTotal = 50.0;
	
	private final CreditCardType[] ccTypes = new CreditCardType[] { CreditCardType.Discover, CreditCardType.Visa,
											CreditCardType.MasterCard, CreditCardType.AmericanExpress, CreditCardType.none  };
	
	@FXML protected void exitPaymentClick(ActionEvent ae) throws IOException
	{
		Main.hidePaymentMenu();
	}
	@FXML protected void submitPayClick( ActionEvent ae ) throws IOException
	{
		
		if(validateAddressField() == true && validateZipField() == true
			&& validateCreditCard() == true && validateCSVNum() == true
			&& validateExpDate() == true && validateCCType() == true)
		{
			cdc = new ConfirmationDisplayController();
			createOrderTrigger();
		//cdc.showConfirmationDisplay(mpStage, createOrder);
			Main.showConfirmationDisplay();
		}
	//	Main.hideMainMenu();
	//	Main.showMainMenu();
	}
	
	
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

	
	private boolean validateZipField()
	{
		String zip = zipField.getText();
		zip = intCheck(zip);
		if( zip != null  && zipField.getText().toString().length() < 6)
		{
			invZip.setVisible(false);
			zipField.getStyleClass().remove("error");
			return true;
		}
		else
		{
			update();
			//invZip.setVisible(true);
			zipFlasher.play();
			timer.scheduleAtFixedRate(task, 1000, 1000);
			zipField.getStyleClass().add("error");
			return false;
		}
		
	}
	
	private boolean validateCreditCard()
	{
		String cc1 = ccNumField1.getText();
		String cc2 = ccNumField.getText();
		String cc3 = ccNumField2.getText();
		String cc4 = ccNumField3.getText();
		cc1 = intCheck(cc1);
		cc2 = intCheck(cc2);
		cc3 = intCheck(cc3);
		cc4 = intCheck(cc4);
		if( cc1 != null  && cc2!= null && cc3 !=null && cc4!=null
				&& cc1.length() < 5 && cc2.length() < 5 && cc3.length() < 5
				&& cc4.length() < 5)
		{
			invCC.setVisible(false);
			ccNumField.getStyleClass().remove("error");
			ccNumField1.getStyleClass().remove("error");
			ccNumField2.getStyleClass().remove("error");
			ccNumField3.getStyleClass().remove("error");
			return true;
		}
		else
		{
			//invCC.setVisible(true);
			update();
			ccFlasher.play();
			timer.scheduleAtFixedRate(task, 1000, 1000);
			ccNumField.getStyleClass().add("error");
			ccNumField1.getStyleClass().add("error");
			ccNumField2.getStyleClass().add("error");
			ccNumField3.getStyleClass().add("error");
			return false;
		}
	}
	
	private boolean validateCSVNum()
	{
		String csv = csvField.getText();
		csv = intCheck(csv);
		if( csv!= null && csvField.getText().toString().length() < 4)
		{
			invCSV.setVisible(false);
			csvField.getStyleClass().remove("error");
			return true;
		}
		else
		{
			update();
			csvFlasher.play();
			timer.scheduleAtFixedRate(task, 1000, 1000);
			csvField.getStyleClass().add("error");
			return false;
		}
	}
	
	private boolean validateExpDate()
	{
		if( expDate.getValue() != null)
		{
		

			//invDate.setVisible(false);
			expDate.getStyleClass().remove("error");
			return true;
		}
		else
		{
			update();
			expFlasher.play();
			timer.scheduleAtFixedRate(task, 1000, 1000);
			invDate.setVisible(true);
			expDate.getStyleClass().add("error");
			return false;
		}
	}
	
	private boolean validateCCType()
	{
		if( ccTypeBox.getValue() != null)
		{
			invType.setVisible(false);
			ccTypeBox.getStyleClass().remove("error");
			return true;
		}
		else
		{
			//invType.setVisible(true);
			update();
			ccTypeFlasher.play();
			timer.scheduleAtFixedRate(task, 1000, 1000);
			ccTypeBox.getStyleClass().add("error");
			return false;
		}
	}
	private boolean validateAddressField()
	{
		if(addressField.getText() == null || addressField.getText().trim().isEmpty())
		{
		//	invAddr.setVisible(true);
			update();
			addressFlasher.play();
			timer.scheduleAtFixedRate(task, 1000, 1000);
			addressField.getStyleClass().add("error");
			return false;
		}
		else if (addressField.getText().toString().length() < 5)
		{	
			update();
			addressFlasher.play();
			timer.scheduleAtFixedRate(task, 1000, 1000);
			//invAddr.setVisible(true);
			addressField.getStyleClass().add("error");
			return false;
		}
		else
		{
			
			invAddr.setVisible(false);
			addressField.getStyleClass().remove("error");
			return true;
		}
	}
	
	private void createOrderTrigger() throws IOException
	{
		cart = Main.getCart();
		cartLList = cart.getCart();
		sTotal = cart.getTotal();
		address = addressField.getText()+ "  " + zipField.getText() ;
		//addrObj = new Address(address);
		ccNum = ccNumField1.getText() + "-"+ ccNumField.getText() +"-"+ ccNumField2.getText() +"-"+ ccNumField3.getText();
		csvNum = csvField.getText();
		ccType = ccTypeBox.getValue();		
		ordccType = whichCCType(ccType);
		ldExpDate = expDate.getValue();
		ordExpDate = Date.from(ldExpDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		//cc = new CreditCard(ccNum, csvNum, ordExpDate, ordccType);
		createOrder = new Order(cartLList, sTotal, account);
		Main.setCreateOrder(createOrder);
		oc.createOrder(cartLList, sTotal, account, address, ccNum, ordccType, csvNum, ordExpDate);
		try
		{
			for( Product p: cart.getCart())
			{
				pc.changeStockQuantityOrder(p.getProductId(), p.getCartQuantity());
			}
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch( SQLException e)
		{
			e.printStackTrace();
		}
			
	}
	
	private CreditCardType whichCCType( String e)
	{
		CreditCardType ccTypeR = ccTypes[4] ;
		switch( e)
		{
		case "Visa":
			ccTypeR = ccTypes[1];
			break;
		case "Mastercard":
			ccTypeR = ccTypes[2];
			break;
		case "Discover":
			ccTypeR = ccTypes[0];
			break;
		case "AmericanExpress":
			ccTypeR = ccTypes[3];
			break;
		case "None":
			ccTypeR = ccTypes[4];
			break;				
		}
		System.out.println(ccTypeR.toString());
		return ccTypeR;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		addressFlasher = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.2), e -> 
		{
			//invalidField.setVisible(true);
			invAddr.setVisible(true);
		}),
				  new KeyFrame(javafx.util.Duration.seconds(3.0), e ->
		{
			
			//invalidField.setVisible(false);
			invAddr.setVisible(false);
		}) 
		);
		zipFlasher = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.2), e -> 
		{
			invZip.setVisible(true);
		//	invalidSearchField.setVisible(true);
		}),
				  new KeyFrame(javafx.util.Duration.seconds(3.0), e ->
		{
			invZip.setVisible(false);
		//	invalidSearchField.setVisible(false);
		}) 
		);
		ccFlasher = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.2), e -> 
		{
			invCC.setVisible(true);
			//invalidField.setVisible(true);
		}),
				  new KeyFrame(javafx.util.Duration.seconds(3.0), e ->
		{
			invCC.setVisible(false);
			//invalidField.setVisible(false);
		}) 
		);
		csvFlasher = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.2), e -> 
		{
			invCSV.setVisible(true);
		//	invalidSearchField.setVisible(true);
		}),
				  new KeyFrame(javafx.util.Duration.seconds(3.0), e ->
		{
			invCSV.setVisible(false);
		//	invalidSearchField.setVisible(false);
		}) 
		);
		expFlasher = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.2), e -> 
		{
			invDate.setVisible(true);
			//invalidField.setVisible(true);
		}),
				  new KeyFrame(javafx.util.Duration.seconds(3.0), e ->
		{
			invDate.setVisible(false);
			//invalidField.setVisible(false);
		}) 
		);
		ccTypeFlasher = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.2), e -> 
		{
			invType.setVisible(true);
		//	invalidSearchField.setVisible(true);
		}),
				  new KeyFrame(javafx.util.Duration.seconds(3.0), e ->
		{
			invType.setVisible(false);
		//	invalidSearchField.setVisible(false);
		}) 
		);
		addressFlasher.setCycleCount(Animation.INDEFINITE);
		zipFlasher.setCycleCount(Animation.INDEFINITE);
		ccFlasher.setCycleCount(Animation.INDEFINITE);
		csvFlasher.setCycleCount(Animation.INDEFINITE);
		expFlasher.setCycleCount(Animation.INDEFINITE);
		ccTypeFlasher.setCycleCount(Animation.INDEFINITE);
		
		
		
		
		invAddr.setVisible(false);
		invZip.setVisible(false);
		invCC.setVisible(false);
		invCSV.setVisible(false);
		invDate.setVisible(false);
		invType.setVisible(false);
		System.out.println( account);
		if( Main.getUser() != null)
		{
			System.out.println(account);
			account = Main.getUser();
		}
		ccTypeBox.getItems().addAll(FXCollections.observableArrayList(visa,mCard,discover,aExpress,none));
		ccTypeBox.setTooltip(new Tooltip("Select the CC Tye"));
		mpStage = Main.getPMenuStage();
	}
	
	
}
																																																																																																																																																																	