package logic.controller.guicontroller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import logic.engineeringclasses.bean.UserBean;
import logic.controller.applicationcontroller.LoginController;
import logic.controller.applicationcontroller.ViewController;

public class LoginGUI extends Application implements Initializable{
	
	private LoginController controller = new LoginController();
	private ViewController view = new ViewController();
	private AlertGUI alert = new AlertGUI();
	private int typError;
	private String title = "Login/Error";	
	@FXML
	private TextField tfemail;
	@FXML
	private TextField tfpassword;
    @FXML
    private Button btnSignin;
    @FXML
    private Button btnSignup;
    @FXML
    private ComboBox<String> box;    
    @FXML
    private void onSignupClick(){	    	
    	RegisterGUI reg = new RegisterGUI();
    	reg.start((Stage) btnSignup.getScene().getWindow());	   	    	
    }    	    
    @FXML
    private void onSigninClick() throws Exception{
    	UserBean bean = loginBean(tfemail.getText(), tfpassword.getText(),box.getValue());
		try {
			this.typError = controller.login(bean);							
		} finally {
			switch(this.typError) {
			case 1:			
				alert.alertError(title,"Incorrect/Empty Credential","Please Retry");
				error();
				break;
			case 2:
				alert.alertError(title,"DATA BASE not connected ","Please Retry");
				error();
				break;
			default:
				MainGUI home = new MainGUI();
				home.start((Stage) btnSignin.getScene().getWindow());
				break;
			}
		}
	}
    
    private void error() {
    	errorTf(tfemail);
		errorTf(tfpassword);
		box.setValue(null);
    }
    
    private void errorTf(TextField tf){
    	tf.setText("");
    	tf.setStyle("-fx-border-color: red;");
    	tf.setOnMouseEntered(event -> {
    		tf.setStyle("-fx-border-color: transparent;");
        });
    }

	public UserBean loginBean(String email, String password, String address) {
		UserBean user = new UserBean();
		user.setEmail(email);
		user.setPassword(password);
		user.setAddress(address);
		return user;
	}
	
	private void setUp() {
		 tfemail.setText("admin");
		 tfpassword.setText("admin");
		 try {
			 box.setItems(controller.loadAddresses());
		 }catch(Exception e) {
			 alert.alertError("DATA BASE ERROR","DATA BASE not connected ","Please Restart the Application");
			 Platform.exit();
		 }
	}		
		
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	setUp();
    }
	 		 
	@Override
	public void start(Stage primaryStage) {		
		view.loadPage("Login", primaryStage);
		primaryStage.centerOnScreen();
		primaryStage.getIcons().add(new Image("/logic/view/icon/1x/outline_apartment_white_24dp.png"));
	}	
	
	public static void main(String[] args) {
		launch(args);
	}
}

