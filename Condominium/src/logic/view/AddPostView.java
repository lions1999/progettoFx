package logic.view;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import logic.controller.AddPostController;
import logic.dao.SqlDAO;
import logic.model.UserSingleton;

public class AddPostView implements Initializable{
	
	
	private SqlDAO ourDb = new SqlDAO();
	private AddPostController controller = new AddPostController();
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
    		btnAddPost.setText("File Selected");
    	}  
    }
   
    @FXML
    void onPublishClick() {
		if(btnAddPost.getText().equals("Add File")) {
			JOptionPane.showMessageDialog(null, "Ops... Missing File");
			txtfield.setText("");
		}
	     else if(txtfield.getText().isEmpty()) {	
	    	 JOptionPane.showMessageDialog(null, "Ops... Missing Text");
	    	 btnAddPost.setText("Add File");
	     	
	    } else {
	    	try {
	    		ourDb.addPost(sg.getUserID(),txtfield.getText(),this.file,sg.getCode());
	    		
	    	} catch(Exception e) {
	    		JOptionPane.showMessageDialog(null, "Ops... Something goes wrong, please retry");
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
