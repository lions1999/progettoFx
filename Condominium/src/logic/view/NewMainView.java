package logic.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.controller.HomePageController;
import logic.controller.ViewController;
import logic.model.Post;
import logic.model.UserSingleton;

public class NewMainView  extends Application implements Initializable{
	
	
	private HomePageController homeCtrl= new HomePageController();
	private ViewController view = new ViewController();
	private static UserSingleton sg = UserSingleton.getInstance();
	//private SqlDAO ourDb = new SqlDAO();
	
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
    void btnHomeClick() {
    	List<Post> posts = homeCtrl.loadPost(); 
    	System.out.println(posts);
//    	for(int i=posts.size()-1; i>=0;i--) {
//			sg.setPost(posts.get(i));
//			Pane pane = view.getPage("Post");
//			scrollBox.getChildren().add(pane);
//		}
    }

    @FXML
    void btnInfoClick(ActionEvent event) {
    	switch (sg.getRole()) {
		case ADMINISTRATOR:						
			managerWnd("Organize Condominium");
			break;
		case OWNER:
			managerWnd("RateResident");
			break;				
		case RESIDENT:
			managerWnd("AptInfo");
			break;				
    	}  
    }

    @FXML
    void btnMeetingClick(ActionEvent event) {
    	switch (sg.getRole()) {
		case ADMINISTRATOR:						
			managerWnd("OrganizeMeeting");
			break;
		case OWNER:
			managerWnd("RequestMeeting");
			break;				
		case RESIDENT:
			
			break;				
    	}   
    }

    @FXML
    void btnSignOutClick(ActionEvent event) {
    	LoginView log = new LoginView();
    	log.start((Stage) btnSignout.getScene().getWindow());	
    	sg.clearState();
    }
    
    public void setUp() {
    	addPostWindow();
    	btnHomeClick();    	
       	btnColor(btnHome);
       	btnColor(btnMeeting);
       	btnColor(btnSignout);
       	btnColor(btnApartmentinfo); 
       	tfCondominiumCode.setText(sg.getCode());
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
    
    private void addPostWindow() {
    	Pane addPost = view.getPage("AddPost");
    	scrollBox.getChildren().add(addPost);
    }
    
    private void btnColor(Button btn) {
    	btn.setOnMouseEntered(event -> {
       		btn.setStyle("-fx-background-color : #0A0E3F");
        });
    	btn.setOnMouseExited(event -> {
       		btn.setStyle("-fx-background-color : #0C39FF");
        });
    }
      
    public void managerWnd(String page) {
    	try {
    		Pane pane =  view.getPage(page);
    		pane.setMinHeight(1052);
    		pane.setMinWidth(1360);
    	
    	}catch(Exception  e) {
    		JOptionPane.showMessageDialog(null, "File not Found");
    	}
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
    	//setUp();
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception{		
		view.loadPage("Main", primaryStage);
		primaryStage.setTitle("Condominium");
		primaryStage.setMaximized(true);
	}
}
