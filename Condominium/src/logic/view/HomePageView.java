package logic.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import logic.controller.ViewController;
import logic.dao.SqlDAO;
import logic.model.Post;
import logic.model.UserSingleton;

public class HomePageView  extends Application implements Initializable {

	private SqlDAO ourDb = new SqlDAO();
	private ViewController view = new ViewController();
	UserSingleton sg = UserSingleton.getInstance();
	
    @FXML
    private VBox scrollBox;
       
    @Override
	public void initialize(URL location, ResourceBundle resources){
    	setUp();
    }	
    
    public void setUp() {
    	Pane addPost = view.getPage("AddPost");
    	
   	    scrollBox.getChildren().add(addPost);
    	try {
    	List<Post> posts = ourDb.checkListPost(sg.getCode());
		for(int i=posts.size()-1; i>=0;i--) {
			//sg.setPost(posts.get(i));
			Pane pane = view.getPage("Post");
			scrollBox.getChildren().add(pane);
			}
		} catch(SQLException e){
			e.printStackTrace();
		}	

    }
      
	@Override
	public void start(Stage primaryStage) throws Exception{		
		view.loadPage("HomePage", primaryStage);
		primaryStage.centerOnScreen();
	}
}
