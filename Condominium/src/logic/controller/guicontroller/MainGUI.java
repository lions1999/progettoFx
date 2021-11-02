package logic.controller.guicontroller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.engineeringclasses.bean.PostBean;
import logic.controller.applicationcontroller.PostController;
import logic.controller.applicationcontroller.ViewController;
import logic.model.Post;
import logic.model.UserSingleton;

public class MainGUI  extends Application implements Initializable{
	
	private PostController homeCtrl= new PostController();
	private ViewController view = new ViewController();
	private static UserSingleton sg = UserSingleton.getInstance();
	
    @FXML
    private Font x1;
    @FXML
    private Color x2;
    @FXML
    private Font x3;
    @FXML
    private Color x4;
    @FXML
    private Label lbTitle;
    @FXML
    private ImageView imgUser;
    @FXML
    private Label lbnome;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnMeeting;
    @FXML
    private Button btnApartmentinfo;
    @FXML
    private Button btnSignout;
    @FXML
    private Label tfCondominiumCode;
    @FXML
    private VBox scrollBox;
    @FXML
    private AnchorPane detailsView;
    
    @FXML
    void btnHomeClick() {    	
    	scrollBox.getChildren().clear();
    	Pane addPost = view.getPage("AddPost");
    	scrollBox.getChildren().add(addPost);
    	ObservableList<Post> posts = homeCtrl.loadPost(); 
    	loadPosts(posts);
    }

    @FXML
    void btnInfoClick(ActionEvent event) throws Exception{
		scrollBox.getChildren().clear();
    	switch (sg.getRole()) {
		case ADMINISTRATOR:
			FXMLLoader loader = view.loader("Prova");
			Parent root = loader.load();
			Prova pr = loader.getController();
			//pr.prova();
			scrollBox.getChildren().add(root);
					FXMLLoader load = view.loader("Prova");
					Parent rot = load.load();
					detailsView.getChildren().add(rot);
			

			break;
		case OWNER:
			//TODO
			break;				
		case RESIDENT:
			//TODO
			break;
    	}
    }
    
    @FXML
    void btnMeetingClick(ActionEvent event) throws Exception{
    	scrollBox.getChildren().clear();
    	switch (sg.getRole()) {
		case ADMINISTRATOR:		
			FXMLLoader loader = view.loader("TWRegistration");
    		Parent root = loader.load();
    		TWRegistrationGUI reg = loader.getController();
    		reg.setUpRegistration(sg.getAddress());
    		scrollBox.getChildren().add(root);
			break;
		case OWNER:
			//TODO
			break;				
		case RESIDENT:
			//TODO
			break;				
    	}   
    }

    @FXML
    void btnSignOutClick(ActionEvent event) {
    	LoginGUI log = new LoginGUI();
    	log.start((Stage) btnSignout.getScene().getWindow());	
    	sg.clearState();
    }
    
    public void setUp() {
    	btnHomeClick();    	
       	btnColor(btnHome);
       	btnColor(btnMeeting);
       	btnColor(btnSignout);
       	btnColor(btnApartmentinfo); 
       	tfCondominiumCode.setText(sg.getAddress());
       	switch (sg.getRole()) {
			case ADMINISTRATOR:						
				lbnome.setText(sg.getAdministrator().getName());
				btnApartmentinfo.setText("Condominium Info");
				btnMeeting.setText("Organize Meeting");
				break;
			case OWNER:
				lbnome.setText(sg.getOwner().getName());
				btnMeeting.setText("Request Meeting");
				btnApartmentinfo.setText("Rate Resident");
				break;				
			case RESIDENT:
				lbnome.setText(sg.getResident().getName());
				break;				
       	}
    }
        
    private void loadPosts(ObservableList<Post> posts) {
    	try {  		
	    	for(int i=posts.size()-1; i>=0;i--) {
	    		PostBean bean = setUpPost(posts.get(i));
	    		FXMLLoader loader = view.loader("Post");
	    		Parent root = loader.load();
	    		PostGUI postgui = loader.getController();
	    		postgui.setUpPost(bean);
	    		scrollBox.getChildren().add(root);
			}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public PostBean setUpPost(Post curr) {
    	PostBean post = new PostBean();
    	post.setId(curr.getId());
    	post.setImage(curr.getImage());
    	post.setText(curr.getText());
    	post.setUser(curr.getUser());
    	return post;
    }
    
    private void btnColor(Button btn) {
    	btn.setOnMouseEntered(event -> {
       		btn.setStyle("-fx-background-color : #0A0E3F");
        });
    	btn.setOnMouseExited(event -> {
       		btn.setStyle("-fx-background-color : #0C39FF");
        });
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
    	setUp();
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception{		
		view.loadPage("Main", primaryStage);
		primaryStage.setMaximized(true);
		primaryStage.setTitle("Condominium");
	}
}
