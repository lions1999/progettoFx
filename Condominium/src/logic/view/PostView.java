package logic.view;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import logic.bean.PostBean;
import logic.dao.SqlDAO;
import logic.model.UserSingleton;

public class PostView  implements Initializable{

	private PostBean bean = new PostBean();
	UserSingleton sg = UserSingleton.getInstance();
	private SqlDAO ourDb = new SqlDAO(); 
	
    @FXML
    private AnchorPane rootId;
    @FXML
    private Label usrName;
    @FXML
    private ImageView usrImg;
    @FXML
    private TextField posTxt;
    @FXML
    private ImageView postImg;
    @FXML
    private Button btnDelete;
    
    @FXML
    void onDeletePress(ActionEvent event) {
    	switch (sg.getRole()) {
		case ADMINISTRATOR:						
	//		try {
//				if(confirmationDisplay()) {
//					ourDb.deletePost(btnDelete.getAccessibleText());
//				
//				}
				
				//System.out.println("ce la faremo -> "+ main.mainPage.lookup("#mainPage"));
			try {
				ourDb.deletePost(btnDelete.getAccessibleText());
				HomePageView home = new HomePageView();
				home.setUp();
			}catch(Exception e) {
				
			}
				
				//loader.getController().getClass().getFields();
//			}catch(Exception e) {
//				
//			}
			break;
		case OWNER:
			JOptionPane.showMessageDialog(null, "Sorry you are not allowed to do that");
			break;				
		case RESIDENT:
			JOptionPane.showMessageDialog(null, "Sorry you are not allowed to do that");
			break;				
    	}
    }
    
    private void setUpPost() {
    	btnDelete.setAccessibleText(this.bean.getPost().getId());
    	usrName.setText(this.bean.getPost().getUser());
    	posTxt.setText(this.bean.getPost().getText());
    	posTxt.setEditable(false);
    	InputStream input = this.bean.getPost().getImage();
    	try {
    	if (input != null && input.available() > 1) {
            Image imge = new Image(input);          
            postImg.setImage(imge);
    	}
    	}catch(Exception e){
    		
    	}
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources){
    	setUpPost();
//    	String postId = sg.getPost().getId();
//    	btnDelete.setAccessibleText(postId);
//    	usrName.setText(sg.getPost().getUser());
//    	posTxt.setText(sg.getPost().getText());
//    	posTxt.setEditable(false);
//    	InputStream input = sg.getPost().getImage();
//    	try {
//    	if (input != null && input.available() > 1) {
//            Image imge = new Image(input);          
//            postImg.setImage(imge);
//    	}
//    	}catch(Exception e){
//    		
//    	}
    	//String postId = this.bean.getPost().getId();

    	
    	
    }
    
    private boolean confirmationDisplay() {
    	ConfirmBox box = new ConfirmBox();
    	boolean i = box.display("Condominium/HomePage/Confirm","Are you sure about it?");
    	return i;
    }


}