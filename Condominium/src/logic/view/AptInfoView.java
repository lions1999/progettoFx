package logic.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.controller.ViewController;
import logic.model.UserSingleton;

public class AptInfoView extends Application{
	private ViewController view = new ViewController();
	private static UserSingleton sg = UserSingleton.getInstance();
	
    @FXML
    private Label apt;
	
	@Override
	public void start(Stage primaryStage) {		
		view.loadPage("AptInfo", primaryStage);
	}	
}
