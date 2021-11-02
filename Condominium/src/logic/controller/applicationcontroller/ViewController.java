package logic.controller.applicationcontroller;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.model.Condominium;

public class ViewController{
	
	private Pane view;
	private final String url1 = "/logic/view/";
	private final String url2 = "View.fxml";

	public void loadPage(String page ,Stage primaryStage){				
		FXMLLoader loader  = new FXMLLoader(getClass().getResource(url1+page+url2));		
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
			URL fileUrl = Condominium.class.getResource(url1+fileName+url2);
			if(fileUrl == null) {
				throw new java.io.FileNotFoundException("File Not Found");
			}
			new FXMLLoader();
			view = FXMLLoader.load(fileUrl);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return view;
	}	
	
	public FXMLLoader loader(String name){
		return new FXMLLoader(getClass().getResource(url1+name+url2)); 
	}	
}
