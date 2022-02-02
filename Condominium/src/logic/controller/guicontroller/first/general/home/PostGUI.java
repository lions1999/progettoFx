package logic.controller.guicontroller.first.general.home;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.controller.applicationcontroller.PostController;
import logic.controller.guicontroller.AlertGUI;
import logic.controller.guicontroller.first.general.Menu1GUI;
import logic.engineeringclasses.bean.PostBean;
import logic.model.UserSingleton;

import java.io.InputStream;

public class PostGUI {

	private static final Menu1GUI menu = new Menu1GUI();
	private final AlertGUI alert = new AlertGUI();
	private final PostController controller = new PostController();
	UserSingleton sg = UserSingleton.getInstance();

	@FXML private Label usrName;
    @FXML private ImageView usrImg; //TODO
    @FXML private TextField posTxt;
    @FXML private ImageView postImg;
    @FXML private Button btnDelete;
    
    @FXML private void onDeletePress() {
		String title = "Condominium/Home/";
		switch (sg.getRole()) {
		case ADMINISTRATOR:						
			try {
				if(alert.alertConfirm(title +"Confirmation", "Are you sure you want to delete this post?", "Press OK to delete")) {
					controller.deletePost(btnDelete.getAccessibleText());
					menu.btnHomeClick();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			break;
		case OWNER:
			case RESIDENT:
				alert.alertInfo(title +"Info",  "Sorry you are not allowed to do that", "");
			break;
		}
    }
    
    public void setUpPost(PostBean bean) {
    	btnDelete.setAccessibleText(bean.getId());
    	usrName.setText(bean.getUser());
    	posTxt.setText(bean.getText());
    	posTxt.setEditable(false);
    	InputStream input = bean.getImage();
    	try {
    	if (input != null && input.available() > 1) {
            Image image = new Image(input);
            postImg.setImage(image);
    	}
    	}catch(Exception e){
    		System.out.println("SET UP POST EXCEPTION");
    	}
    }
}