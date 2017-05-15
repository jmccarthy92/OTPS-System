package application;

import java.io.IOException;

import javafx.fxml.FXML;
import ProductManagementSystem.*;

public class AddProductConfirmController
{
	@FXML
	//This function exits the addProductStage
	private void exitAddProductConfirmButton() throws IOException
	{
		Main.exitAddStage();
	}
}
