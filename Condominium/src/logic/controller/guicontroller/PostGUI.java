package logic.controller.guicontroller;

import java.io.InputStream;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.engineeringclasses.bean.PostBean;
import logic.engineeringclasses.dao.PostDAO;
import logic.model.UserSingleton;

public class PostGUI{

	UserSingleton sg = UserSingleton.getInstance(); 
	private final AlertGUI alert = new AlertGUI();
	private final PostDAO post = new PostDAO();

	@FXML
    private Label usrName;
    @FXML
    private ImageView usrImg; //TODO
    @FXML
    private TextField posTxt;
    @FXML
    private ImageView postImg;
    @FXML
    private Button btnDelete;
    
    @FXML
    void onDeletePress() {
		String title = "Condominium/Home/";
		switch (sg.getRole()) {
		case ADMINISTRATOR:						
			try {
				if(alert.alertConfirm(title +"Confirmation", "Are you sure you want to delete this post?", "Press OK to delete")) {
					post.deletePost(btnDelete.getAccessibleText());
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