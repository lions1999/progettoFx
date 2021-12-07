package logic.controller.applicationcontroller;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Pane;
import logic.model.Condominium;

import java.io.IOException;
import java.net.URL;

public class ViewController{
	
	private Pane view;
	private final String url1 = "/logic/view/";
	private final String url2 = "View.fxml";
	private final String url3 = "Dialog.fxml";

//	public void loadPage(String page ,Stage primaryStage){
//		FXMLLoader loader  = new FXMLLoader(getClass().getResource(url1+page+url2));
//		Parent root;
//		try {
//			root  = loader.load();
//			Scene scene = new Scene(root);
//			primaryStage.setScene(scene);
//			primaryStage.setTitle("Condominium/"+page);
//			primaryStage.show();
//		}catch(IOException e){
//			e.printStackTrace();
//		}
//	}

//	public void waitPage(Parent parent){
//		Stage stage = new Stage();
//		stage.initOwner(MenuGUI.border.getScene().getWindow());
//		stage.initModality(Modality.WINDOW_MODAL);
//		Scene scene = new Scene(parent);
//		stage.setScene(scene);
//		stage.showAndWait();
//	}
	public DialogPane dialog(String fileName) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource(url1+fileName+url3));
		return loader.load();


	}

//	public Stage loadPage(String page){
//		FXMLLoader loader  = new FXMLLoader(getClass().getResource(url1+page+url2));
//		Parent root;
//		try {
//			root  =  loader.load();
//			Stage stage = new Stage();
//			stage.setScene(new Scene(root));
//			//stage.showAndWait();
//			return stage;
//		}catch(IOException e){
//			e.printStackTrace();
//			return null;
//
//		}
//	}

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
