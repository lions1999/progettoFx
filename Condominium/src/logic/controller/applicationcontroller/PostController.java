package logic.controller.applicationcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.controller.guicontroller.first.general.Menu1GUI;
import logic.engineeringclasses.dao.PostDAO;
import logic.model.Post;
import logic.model.UserSingleton;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class PostController {

	UserSingleton sg = UserSingleton.getInstance();
	private final PostDAO post = new PostDAO();

	public File selectFile() {
			Stage stage = new Stage();
			stage.initOwner(Menu1GUI.firstBorder.getScene().getWindow());
			stage.initModality(Modality.WINDOW_MODAL);
			FileChooser fc = new FileChooser();
			FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("JPEG/JPG/PNG/GIF file", "*.jpg", "*.jpeg", "*.png", "*.gif");
			fc.getExtensionFilters().add(fileExtensions);
			fc.setTitle("Condominium/Home/AddImageToPost");
		return fc.showOpenDialog(stage);
	}

	public ObservableList<Post> loadPost() {
		ObservableList<Post> list = FXCollections.observableArrayList();
		try {
			list = post.checkListPost(sg.getAddress());
			return list ;
		}catch(Exception e) {
			return list;
		}
	}

	public void addPost(String userID, String text, File file, String address) {
		try {
			post.addPost(userID, text, file, address);
		}catch(SQLException| IOException e){
			System.out.println("Ops...");
		}
	}

	public void deletePost(String postID) {
		try {
			post.deletePost(postID);
		}catch(SQLException e){
			System.out.println("Ops...");
		}
	}
}
