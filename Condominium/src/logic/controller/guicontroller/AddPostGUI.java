package logic.controller.guicontroller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import logic.controller.applicationcontroller.AddPostController;
import logic.engineeringclasses.dao.PostDAO;
import logic.model.UserSingleton;

public class AddPostGUI implements Initializable{
	
	private final AlertGUI alert = new AlertGUI();
	private final PostDAO post = new PostDAO();
	private final AddPostController controller = new AddPostController();
	private File file;
	UserSingleton sg = UserSingleton.getInstance();
	
    @FXML
    private TextField txtfield;
    @FXML
    private Button btnAddPost;
    @FXML
    private ImageView imgUserPost;
    @FXML
    public Button btnPublish;
    
    @FXML
    void addFileClick() {
    	this.file = controller.selectFile();
    	if (this.file != null) {
    		btnAddPost.setText(this.file.getName());
    	}  
    }
   
    @FXML
    void onPublishClick() {
		if(btnAddPost.getText().equals("Add File")) {
			alert.alertWarn("Condominium/Home/Warning", "Ops... Missing File", "Please select one File");
			txtfield.setText("");
		}
	     else if(txtfield.getText().isEmpty()) {	
	    	 alert.alertWarn("Condominium/Home/Warning", "Ops... Missing Text", "Please insert Text");
	    	 btnAddPost.setText("Add File");
	    } else {
	    	try {
				post.addPost(sg.getUserID(),txtfield.getText(),this.file,sg.getAddress());
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
    	btn.setOnMouseEntered(event -> {
       		btn.setStyle("-fx-background-color : #0A0E3F");
        });
    	btn.setOnMouseExited(event -> {
       		btn.setStyle("-fx-background-color : #0C39FF");
        });
    }
}
