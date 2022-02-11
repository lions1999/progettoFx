package logic.controller.applicationcontroller;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.model.Condominium;
import logic.model.Role;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;

import static logic.controller.guicontroller.second.general.Main2GUI.secondBorder;

public class ViewController{
	
	private Pane view;
	private final String firstUrl = "/logic/view/first/";
	private final String secondUrl = "/logic/view/second/";
	private final String finalUrl = "View.fxml";

	public Pane getPage(String fileName,int gui) {
		URL fileUrl = null;
		try {
			switch (gui){
				case 1:
					fileUrl = Condominium.class.getResource(firstUrl +fileName+ finalUrl);
					break;
				case 2:
					fileUrl = Condominium.class.getResource(secondUrl +fileName+ finalUrl);
					break;
			}
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
	
	public FXMLLoader loader(String fileName,int gui){
		FXMLLoader loader = null;
		switch (gui){
			case 1:
				loader = new FXMLLoader(getClass().getResource(firstUrl +fileName+ finalUrl));
				break;
			case 2:
				loader = new FXMLLoader(getClass().getResource(secondUrl +fileName+ finalUrl));
				break;
		}
		return loader;
	}

	public String addImage(Role role) {
		String path = "logic/view/Icon/";
		String png = "";
		switch (role){
			case RESIDENT:
				png = path+"R.png";
				break;
			case OWNER:
				png = path+"O.png";
				break;
			case ADMINISTRATOR:
				png = path+"A.png";
				break;
		}
		return png;
	}

	public void secondRight(Parent parent, Button btnAction){
		VBox vBox = new VBox();
		HBox hBox = new HBox();
		Button btnCan = new Button("Cancel");
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(30);
		vBox.getStylesheets().add("/logic/view/second/style.css");
		btnAction.getStyleClass().add("btnGeneral");
		btnCan.getStyleClass().add("btnGeneral");
		btnAction.setMaxWidth(150);
		btnCan.setMaxWidth(150);
		btnCan.setOnAction(e-> secondBorder.setRight(null));
		hBox.getChildren().addAll(btnAction,btnCan);
		vBox.getChildren().addAll(parent,hBox);
		secondBorder.setRight(vBox);
	}
}
