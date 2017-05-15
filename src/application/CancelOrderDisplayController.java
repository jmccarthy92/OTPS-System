package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.BorderPane;


public class CancelOrderDisplayController  extends BorderPane implements Initializable
{

//	@FXML private Label cancelText = new Label();
	@FXML private Button confirmButton;

	
	@FXML protected void confirmButtonClick( ActionEvent ae ) throws IOException
	{
		Main.hideCancelDisplay();
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	
	}


}
