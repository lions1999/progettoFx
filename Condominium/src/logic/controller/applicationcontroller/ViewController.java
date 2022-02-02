package logic.controller.applicationcontroller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import logic.model.Condominium;

import java.io.IOException;
import java.net.URL;

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
}
