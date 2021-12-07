package logic.controller.applicationcontroller;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class AddPostController {
	
	public File selectFile() {
		Stage stage = new Stage();
		File file;
		FileChooser fc = new FileChooser();
		FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("JPEG/JPG/PNG/GIF file", "*.jpg", "*.jpeg","*.png","*.gif");
		fc.getExtensionFilters().add(fileExtensions);
		fc.setTitle("Condominium/Home/AddImageToPost");	
		file = fc.showOpenDialog(stage);
		return file;
	}
}
