package logic.controller;

import java.io.IOException;
import java.net.URL;

import javax.swing.JOptionPane;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.model.Condominium;
import logic.view.MainView;

public class ViewController{
	
	private Pane view;
	
	public  MainView mainController;
	
	public void loadPage(String page ,Stage primaryStage){				
		FXMLLoader loader  = new FXMLLoader(getClass().getResource("/logic/view/fxml/"+page+".fxml"));		
		Parent root;		
		try {			
			root  = loader.load();	
			Scene scene = new Scene(root);			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Condominium/"+page);
			primaryStage.show();			
		}catch(IOException e){
			e.printStackTrace();
		}	
	}	
	
	public Pane getPage(String fileName) {
		try {
			URL fileUrl = Condominium.class.getResource("/logic/view/fxml/"+fileName+".fxml");
			if(fileUrl == null) {
				throw new java.io.FileNotFoundException("File Not Found");
			}
			new FXMLLoader();
			view = FXMLLoader.load(fileUrl);
		}catch(Exception e) {
			
		}
		return view;
	}	

}
