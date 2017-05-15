package application;

import java.io.IOException;
import java.sql.SQLException;

import AccountSubsystem.Account;
import AccountSubsystem.Account_Collection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class AdminControlScreenController 
{
	
	@FXML private Button omcControl;
	@FXML private Button pmcControl;
	@FXML private Button amcControl;
	@FXML private Button back;
	@FXML private Button addControl;
	
	private static 		PopUpPanelController pupc = new PopUpPanelController();
	private static 		Scene popUp_page_scene = new Scene(pupc);
	private static      Stage popUp_stage;
	
	@FXML protected void omcClick(ActionEvent ae) throws IOException
	{
		Main.hideAdminControl();
		Main.showAdminOrderMenu();
		
	}
	
	@FXML protected void pmcClick(ActionEvent ae) throws IOException
	{
		Main.hideAdminControl();
		Main.showMainView();
		
	}
	
	@FXML protected void amcClick(ActionEvent ae) throws IOException
	{
		Main.hideAdminControl();
		Main.showViewAccountMenu();
	}
	
	@FXML protected void addClick(ActionEvent ae) throws IOException
	{
		Main.hideAdminControl();
		Main.showCreateAdminMenu();
	}
	
	@FXML protected void backClick(ActionEvent ae) throws IOException
	{
		Main.setUser(null);
		System.out.println("LOGGGING OUT....");
		//popUp_stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
		//popUp_stage.setScene(popUp_page_scene);
		Main.hideAdminControl();
		Main.showLoginMenu();
		//popUp_stage.show();
	}
	

}
