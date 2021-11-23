package logic.controller.guicontroller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import logic.controller.applicationcontroller.PostController;
import logic.model.UserSingleton;

public class AddPostGUI implements Initializable{

	private static final MenuGUI menu = new MenuGUI();
	private final AlertGUI alert = new AlertGUI();
	private final PostController controller = new PostController();
	private File file;
	UserSingleton sg = UserSingleton.getInstance();
	
    @FXML private TextField txtMsg;
    @FXML private Button btnAddPost;
    @FXML private Button btnPublish;
	@FXML private ImageView imgUserPost; //TODO
    
    @FXML private void addFileClick() {
		btnAddPost.setDisable(true);
		this.file = controller.selectFile();
		if (this.file != null) {
			btnAddPost.setText(this.file.getName());
		}
		btnAddPost.setDisable(false);
	}






   
    @FXML private void onPublishClick() {
		if(btnAddPost.getText().equals("Add File")) {
			alert.alertWarn("Condominium/Home/Warning", "Ops... Missing File", "Please select one File");
			txtMsg.setText("");
		}
	     else if(txtMsg.getText().isEmpty()) {
	    	 alert.alertWarn("Condominium/Home/Warning", "Ops... Missing Text", "Please insert Text");
	    	 btnAddPost.setText("Add File");
	    } else {
	    	try {
				//view.loadPage("Post");
				//TODO ANTEPRIMA POST
				controller.addPost(sg.getUserID(), txtMsg.getText(),this.file,sg.getAddress());
				menu.btnHomeClick();
	    	} catch(Exception e) {
	    		alert.alertError("Condominium/Home/Error", "Ops... Something goes wrong", "Please Retry");
	    		e.printStackTrace();
	    	}
	    }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
    	btnColor(btnPublish);
    	btnColor(btnAddPost);
    }
    
    private void btnColor(Button btn) {
    	btn.setOnMouseEntered(event -> btn.setStyle("-fx-background-color : #0A0E3F"));
    	btn.setOnMouseExited(event -> btn.setStyle("-fx-background-color : #0C39FF"));
		//TODO ADD TO CSS
    }

}
