package application;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import OrderSubsystem.OrderCollection;
import OrderSubsystem.Order;

import OrderSubsystem.OrderStatus;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author James McCarthy
 *
 */
public class ViewOrderHistoryMenuController extends BorderPane implements Initializable
{
	
	private OrderCollection oc = new OrderCollection();
	private HashMap<String,Order> orderList;
	private ObservableList<Order> data = FXCollections.observableArrayList();
	@FXML private TableView<Order> orderTable;
	
	@FXML private TableColumn<Order,String> orderIdColumn;
	@FXML private TableColumn<Order,String> accountIdColumn;
	@FXML private TableColumn<Order,String> salesTotalColumn;
	@FXML private TableColumn<Order,String> orderDateColumn;
	@FXML private TableColumn<Order,String> orderStatusColumn;
	@FXML private TableColumn<Order,String> orderAddressColumn;
	@FXML private TableColumn<Order,String> creditCardColumn;
	@FXML private TableColumn<Order,String> creditCardTypeColumn;
	@FXML private TableColumn<Order,String> csvColumn;
	@FXML private TableColumn<Order,String> expColumn;
	@FXML private TableColumn<Order,String>	isPaidColumn;
	@FXML private TableColumn<Order,String> pListColumn;
	private NumberFormat nFormat = NumberFormat.getCurrencyInstance();
	
	@FXML private Button backButton;
	
	@FXML private Button searchOrderStatus;
	@FXML private TextField searchOStatusField;
	
	@FXML private Button searchOrder;
	@FXML private TextField searchOrderIDField;
	
	@FXML private Button cancelOrder;
	@FXML private TextField cancelOrderField = new TextField();
	private String cancelOrderVal;
	private String searchOrdIDVal;
	
	@FXML private Label invalidSearchField;
	
	@FXML private Label invalidField;
	private Order searchedOrder;
	
	
	private OrderStatus [] oTypes = new OrderStatus [] {OrderStatus.Cancelled, OrderStatus.Shipped, OrderStatus.UnShipped };
	private OrderStatus osVal;
	private String osStringVal;
	
	private DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	private DateFormat expDf = new SimpleDateFormat("MM/dd/yyyy");
	@FXML protected void getAllOrderClick(ActionEvent ae) throws IOException
	{
		
		String Account =String.valueOf(Main.getUser().getID());
		orderList = oc.ViewOrderHistory(Account);
		data.removeAll(data);
		for(String key : orderList.keySet())
		{
		    Order order =new Order (orderList.get(key).getOrderId(), orderList.get(key).getProudctList(), orderList.get(key).getSalesTotal()
		    					, orderList.get(key).getAddress(), orderList.get(key).getCreditCard(), orderList.get(key).getAccount(), 
		    					orderList.get(key).getOrderDate(), orderList.get(key).getOrderStatusForTable() );
		    order.setPaymentStatus(orderList.get(key).getPaymentStatus());
		    data.add(order);
		}

		orderTable.getItems().setAll(this.data);
		
	}
	
	private OrderStatus whichOSType( String e)
	{
		OrderStatus oStat = null ;
		switch( e.toLowerCase())
		{
		case "shipped":
			oStat=oTypes[1];
			break;
		case "unshipped":
			oStat = oTypes[2];
			break;
		case "cancelled":
			oStat = oTypes[0];
			break;
			
		}
		
		return oStat;
	}
	
	@FXML protected void searchOrderStatusClick(ActionEvent ae) throws IOException
	{
		osStringVal = searchOStatusField.getText();
		osVal = whichOSType(osStringVal);
		orderList= oc.searchOrderStatus(osVal);
		data.removeAll(data);
		
		for(String key : orderList.keySet())
		{
		    Order order =new Order (orderList.get(key).getOrderId(), orderList.get(key).getProudctList(), orderList.get(key).getSalesTotal()
					, orderList.get(key).getAddress(), orderList.get(key).getCreditCard(), orderList.get(key).getAccount(), 
					orderList.get(key).getOrderDate(), orderList.get(key).getOrderStatusForTable() );
		    order.setPaymentStatus(orderList.get(key).getPaymentStatus());
		    data.add(order);
		}
		orderTable.getItems().setAll(this.data);
		searchOStatusField.clear();
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
					//System.out.println( e.getMessage() + " There was  non-numeric letter(s) ");
					//System.out.println(" PLEASE ENTER  INTEGERS!! :");
					//i = scan.nextLine();
					return null;
				}
				catch(Exception e)
				{
					//System.out.print("Enter your next digits: ");
					//i = scan.nextLine();
					return null;
				}	
	}
	
	
	@FXML protected void searchOrderClick(ActionEvent ae) throws IOException
	{
		searchOrdIDVal = searchOrderIDField.getText();
		searchOrdIDVal = intCheck(searchOrdIDVal);
		if(searchOrdIDVal != null)
		{
			invalidSearchField.setVisible(false);
			searchedOrder =  oc.searchOrders(searchOrdIDVal);
			data.removeAll(data);
		
			if( searchedOrder != null)
				data.add(searchedOrder);
			orderTable.getItems().setAll(this.data);
			searchOrderIDField.getStyleClass().remove("error");
			searchOrderIDField.clear();
		}
		else
		{
			update();
			//invalidSearchField.setVisible(true);
			searchFlasher.play();
			timer.scheduleAtFixedRate(task, 1000, 1000);
			searchOrderIDField.getStyleClass().add("error");
		}
	}
	
	
	@FXML protected void backButtonClick() throws IOException
	{
		Main.hideViewOrderHistoryMenu();
	}
	
	private void refreshOrders () throws IOException
	{
		String Account =String.valueOf(Main.getUser().getID());
		orderList = oc.ViewOrderHistory(Account);
		data.removeAll(data);
		for(String key : orderList.keySet())
		{
	
		    Order order =new Order (orderList.get(key).getOrderId(), orderList.get(key).getProudctList(), orderList.get(key).getSalesTotal()
		    					, orderList.get(key).getAddress(), orderList.get(key).getCreditCard(), orderList.get(key).getAccount()
		    					,orderList.get(key).getOrderDate(), orderList.get(key).getOrderStatusForTable());
		    order.setPaymentStatus(orderList.get(key).getPaymentStatus());
		    data.add(order);
		}

		orderTable.getItems().setAll(this.data);
	}
	
	//PauseTransition visiblePause = new PauseTransition( Duration.seconds(3));
	private Timeline flasher;
	private Timeline searchFlasher;
	
	@FXML protected void cancelOrderClick(ActionEvent ae) throws IOException
	{
	//	timer = new Timer();
	//	task.run();
		cancelOrderVal= cancelOrderField.getText();
		boolean j =oc.cancelOrder(cancelOrderVal);
		if( j == true)
		{
			Main.showCancelDisplay();
			refreshOrders();
			cancelOrderField.clear();
			invalidField.setVisible(false);
			cancelOrderField.getStyleClass().remove("error");
		}
		else
		{
			update();
			
			//invalidField.setVisible(true);
			flasher.play();
			timer.scheduleAtFixedRate(task, 1000, 1000);
		
			cancelOrderField.getStyleClass().add("error");
		}
		
	}
	
	private void addStyleSheet()
	{
		orderIdColumn.getStyleClass().add("header-column");
		accountIdColumn.getStyleClass().add("header-column");
		salesTotalColumn.getStyleClass().add("header-column");
		orderDateColumn.getStyleClass().add("header-column");
		orderStatusColumn.getStyleClass().add("header-column");
		orderAddressColumn.getStyleClass().add("header-column");
		creditCardColumn.getStyleClass().add("header-column");
		creditCardTypeColumn.getStyleClass().add("header-column");
		expColumn.getStyleClass().add("header-column");
		csvColumn.getStyleClass().add("header-column");
		isPaidColumn.getStyleClass().add("header-column");
	}
	

	
	private void setNonResizable()
	{
		orderIdColumn.setResizable(false);
		accountIdColumn.setResizable(false);
		salesTotalColumn.setResizable(false);
		orderDateColumn.setResizable(false);
		orderStatusColumn.setResizable(false);
		orderAddressColumn.setResizable(false);
		creditCardColumn.setResizable(false);
		creditCardTypeColumn.setResizable(false);
		expColumn.setResizable(false);
		csvColumn.setResizable(false);
		isPaidColumn.setResizable(false);
	}
	
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
				//	task.cancel();
					seconds = 0;
					searchOrderIDField.clear();
					cancelOrderField.clear();
					invalidField.setVisible(false);
					cancelOrderField.getStyleClass().remove("error");
					searchOrderIDField.getStyleClass().remove("error");
					invalidSearchField.setVisible(false);
					flasher.stop();
					searchFlasher.stop();
					timer.cancel();
					task.cancel();
				}
			}
		};
		
	}
	//time shit
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
			//	task.cancel();
				seconds = 0;
				searchOrderIDField.clear();
				cancelOrderField.clear();
				invalidField.setVisible(false);
				cancelOrderField.getStyleClass().remove("error");
				searchOrderIDField.getStyleClass().remove("error");
				invalidSearchField.setVisible(false);
				flasher.stop();
				searchFlasher.stop();
				timer.cancel();
				task.cancel();
			}
		}
	};
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		flasher = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.2), e -> 
		{
			invalidField.setVisible(true);
		}),
				  new KeyFrame(javafx.util.Duration.seconds(3.0), e ->
		{
			invalidField.setVisible(false);
		}) 
		);
		searchFlasher = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.2), e -> 
		{
			invalidSearchField.setVisible(true);
		}),
				  new KeyFrame(javafx.util.Duration.seconds(3.0), e ->
		{
			invalidSearchField.setVisible(false);
		}) 
		);
		flasher.setCycleCount(Animation.INDEFINITE);
		searchFlasher.setCycleCount(Animation.INDEFINITE);
		invalidField.setVisible(false);
		String Account =String.valueOf(Main.getUser().getID());
		orderList = oc.ViewOrderHistory(Account);
		invalidSearchField.setVisible(false);
		orderIdColumn.setCellValueFactory(cellData ->	
					new SimpleStringProperty(cellData.getValue().getOrderId()));
		
		accountIdColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty(String.valueOf(cellData.getValue().getAccount().getID() ) ) );
		
		salesTotalColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty(String.valueOf(nFormat.format(cellData.getValue().getSalesTotal() ) ) ) );
		
		orderDateColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty(df.format((cellData.getValue().getOrderDate()) ) ) );
		
		orderStatusColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty(cellData.getValue().getOrderStatus())  );
		
		orderAddressColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty(cellData.getValue().getAddress().getAddressForDB())  );
		
		creditCardColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty(cellData.getValue().getCreditCard().getCCNum())  );
		
		creditCardTypeColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty(cellData.getValue().getCreditCard().getCCType())  );
		
		expColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty(expDf.format(cellData.getValue().getCreditCard().getExpDate()))  );
		
		csvColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty(cellData.getValue().getCreditCard().getCSV() ) );
		
		isPaidColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty( String.valueOf(cellData.getValue().getPaymentStatus()))  );
		pListColumn.setCellValueFactory(cellData ->
		new SimpleStringProperty(cellData.getValue().getProudctList().toString()) );
		
		for(String key : orderList.keySet())
		{
		    Order order = new Order (orderList.get(key).getOrderId(), orderList.get(key).getProudctList(), orderList.get(key).getSalesTotal()
		    					, orderList.get(key).getAddress(), orderList.get(key).getCreditCard(), orderList.get(key).getAccount()
		    					, orderList.get(key).getOrderDate(), orderList.get(key).getOrderStatusForTable());
		    order.setPaymentStatus(orderList.get(key).getPaymentStatus());
		    data.add(order);
		}
	
		orderTable.getItems().setAll(this.data);
		
		setNonResizable();
		addStyleSheet();
		
		//addErroHandlerCancelOrder();
		
	}
}

