package application;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import OrderSubsystem.Order;
import OrderSubsystem.OrderCollection;
import OrderSubsystem.OrderStatus;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.layout.BorderPane;

public class viewAllOrderMenuController extends BorderPane implements Initializable{
	private OrderCollection oc = new OrderCollection();
	private HashMap<String,Order> orderList;
	private ObservableList<Order> data = FXCollections.observableArrayList();
	@FXML private TableView<Order> orderTable;
	
	@FXML private Button refreshOrder;
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
	
	@FXML private Label invalidSearchLabel;
	@FXML private Label invalidRemove;
	@FXML private Label invalidShipLabel;
	@FXML private TextField searchBox = new TextField();
	@FXML private TextField shipOrderField = new TextField();
	@FXML private TextField removeOrderField = new TextField();

	@FXML private Button searchOrderStatus;
	@FXML private TextField searchOStatusField;
	private NumberFormat nFormat = NumberFormat.getCurrencyInstance();
	
	@FXML private Button searchButton;
	@FXML private Button removeOrder;
	@FXML private Button shipOrder;
	
	private String removeOrderVal;
	private String shipOrderVal;
	private String searchOrderVal;
	private String osStringVal;
	private OrderStatus osVal;
	private OrderStatus [] oTypes = new OrderStatus [] {OrderStatus.Cancelled, OrderStatus.Shipped, OrderStatus.UnShipped };
	private DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	private DateFormat expDf = new SimpleDateFormat("MM/dd/yyyy");
	@FXML protected void getAllOrderClick(ActionEvent ae) throws IOException
	{
		orderList = oc.getAllOrders();
		data.removeAll(data);
		for(String key : orderList.keySet())
		{
		    Order order = new Order (orderList.get(key).getOrderId(), orderList.get(key).getProudctList(), orderList.get(key).getSalesTotal()
		    					, orderList.get(key).getAddress(), orderList.get(key).getCreditCard(), orderList.get(key).getAccount()
		    					,orderList.get(key).getOrderDate(), orderList.get(key).getOrderStatusForTable());
		    order.setPaymentStatus(orderList.get(key).getPaymentStatus());
		    data.add(order);
		}

		orderTable.getItems().setAll(this.data);
		
	}
	
	private void refreshOrders () throws IOException
	{
		orderList = oc.getAllOrders();
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
	
	
	@FXML protected void removeOrderClick(ActionEvent ae) throws IOException
	{
		removeOrderVal = removeOrderField.getText();
		Order j = oc.removeOrder(removeOrderVal);
		if( j != null)
		{
			Main.setRemovedOrder( j);
			Main.showRemovedDisplay();
			refreshOrders();
			removeOrderField.clear();
			removeOrderField.getStyleClass().remove("error");
			invalidRemove.setVisible(false);
		}
		else
		{
			update();
			removeFlasher.play();
			invalidRemove.setVisible(true);
			removeOrderField.getStyleClass().add("error");
		}
		//System.out.println(j);
	}
	
	@FXML protected void backButtonClick(ActionEvent ae) throws IOException
	{
		Main.showAdminControl();
		Main.hideAdminOrderMenu();
	}
	
	@FXML protected void shipOrderClick(ActionEvent ae) throws IOException
	{
		shipOrderVal = shipOrderField.getText();
		boolean j = oc.shipOrder(shipOrderVal);
		if( j == true)
		{
			Main.showShippedDisplay();
			refreshOrders();
			shipOrderField.clear();
			invalidShipLabel.setVisible(false);
			shipOrderField.getStyleClass().remove("error");
		}
		else
		{
			update();
			flasher.play();
			
			invalidShipLabel.setVisible(true);
			shipOrderField.getStyleClass().add("error");
			//System.out.println(j);
		}
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
	
	
	
	@FXML protected void searchOrder(ActionEvent ae) throws IOException
	{
		searchOrderVal = searchBox.getText();
		searchOrderVal = intCheck(searchOrderVal);
		if(searchOrderVal != null)
		{
			Order searchedOrder = oc.searchOrders(searchOrderVal);
			data.removeAll(data);
			invalidSearchLabel.setVisible(false);

			if( searchedOrder != null)
			{
				data.add(searchedOrder);
			}
		
			orderTable.getItems().setAll(this.data);
			searchBox.getStyleClass().remove("error");
			searchBox.clear();
		}
		else
		{
			update();
			searchFlasher.play();
			invalidSearchLabel.setVisible(true);
			searchBox.getStyleClass().add("error");
		}
			
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
					searchBox.clear();
					shipOrderField.clear();
					invalidShipLabel.setVisible(false);
					shipOrderField.getStyleClass().remove("error");
					searchBox.getStyleClass().remove("error");
					removeOrderField.getStyleClass().remove("error");
					invalidRemove.setVisible(false);
					invalidSearchLabel.setVisible(false);
				//	invalidSearchField.setVisible(false);
					flasher.stop();
					searchFlasher.stop();
					timer.cancel();
					task.cancel();
				}
			}
		};
		
	}
	
	private Timeline flasher;
	private Timeline searchFlasher;
	private Timeline removeFlasher;
		
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
				searchBox.clear();
				shipOrderField.clear();
				invalidSearchLabel.setVisible(false);
				invalidShipLabel.setVisible(false);
				shipOrderField.getStyleClass().remove("error");
				searchBox.getStyleClass().remove("error");
				removeOrderField.getStyleClass().remove("error");
				invalidRemove.setVisible(false);
				
			//	invalidSearchField.setVisible(false);
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
			invalidShipLabel.setVisible(true);
		}),
				  new KeyFrame(javafx.util.Duration.seconds(3.0), e ->
		{
			invalidShipLabel.setVisible(false);
		}) 
		);
		searchFlasher = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.2), e -> 
		{
			invalidSearchLabel.setVisible(true);
		}),
				  new KeyFrame(javafx.util.Duration.seconds(3.0), e ->
		{
			invalidSearchLabel.setVisible(false);
		}) 
		);
		removeFlasher = new Timeline(new KeyFrame(javafx.util.Duration.seconds(0.2), e -> 
		{
			invalidRemove.setVisible(true);
		}),
				  new KeyFrame(javafx.util.Duration.seconds(3.0), e ->
		{
			invalidRemove.setVisible(false);
		}) 
		);
		flasher.setCycleCount(Animation.INDEFINITE);
		searchFlasher.setCycleCount(Animation.INDEFINITE);
		removeFlasher.setCycleCount(Animation.INDEFINITE);
		invalidSearchLabel.setVisible(false);
		invalidRemove.setVisible(false);
		invalidShipLabel.setVisible(false);
		orderList = oc.getAllOrders();
		orderIdColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty(cellData.getValue().getOrderId() ) );
		accountIdColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty( String.valueOf(cellData.getValue().getAccount().getID() ) ) );
		salesTotalColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty(String.valueOf(nFormat.format(cellData.getValue().getSalesTotal() ) ) ) );
		orderDateColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty(df.format( cellData.getValue().getOrderDate()  ) ) );
		orderStatusColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty(cellData.getValue().getOrderStatus())  );
		orderAddressColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty(cellData.getValue().getAddress().getAddressForDB() )  );
		creditCardColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty(cellData.getValue().getCreditCard().getCCNum() )  );
		creditCardTypeColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty(cellData.getValue().getCreditCard().getCCType() )  );
		expColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty(expDf.format(cellData.getValue().getCreditCard().getExpDate() ) )  );
		isPaidColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty( String.valueOf(cellData.getValue().getPaymentStatus()))  );
		csvColumn.setCellValueFactory(cellData ->
					new SimpleStringProperty(cellData.getValue().getCreditCard().getCSV() ) );
		pListColumn.setCellValueFactory(cellData ->
		new SimpleStringProperty(cellData.getValue().getProudctList().toString()) );

		
		for(String key : orderList.keySet())
		{
		    Order order =new Order (orderList.get(key).getOrderId(), orderList.get(key).getProudctList(), orderList.get(key).getSalesTotal()
		    					, orderList.get(key).getAddress(), orderList.get(key).getCreditCard(), orderList.get(key).getAccount()
		    					, orderList.get(key).getOrderDate(), orderList.get(key).getOrderStatusForTable());
		    order.setPaymentStatus(orderList.get(key).getPaymentStatus());
		    data.add(order);
		}

		orderTable.getItems().setAll(this.data);

	}
}
