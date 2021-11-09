package logic.controller.guicontroller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import logic.engineeringclasses.bean.UserBean;
import logic.controller.applicationcontroller.LoginController;

public class LoginGUI extends MainGUI implements Initializable{
	
	private final LoginController controller = new LoginController();
	private int typError;

	@FXML private TextField tfEmail;
	@FXML private TextField tfPwd;
	@FXML private ComboBox<String> comboBox;

	@FXML private void onSignupClick(){
		Pane pane = view.getPage("Register");
		border.setCenter(pane);
    }

	@FXML private void onSignClick(){
    	UserBean bean = loginBean(tfEmail.getText(), tfPwd.getText(), comboBox.getValue());
		try {
			this.typError = controller.login(bean);							
		} finally {
			String title = "Login/Error";
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
				Pane menu = view.getPage("Menu");
				border.setLeft(menu);
				fullScreen(true);
				break;
			}
		}
	}
    
    private void error() {
    	errorTf(tfEmail);
		errorTf(tfPwd);
		comboBox.setValue(null);
    }
    
    private void errorTf(TextField tf){
    	tf.setText("");
    	tf.setStyle("-fx-border-color: red;");
    	tf.setOnMouseEntered(event -> tf.setStyle("-fx-border-color: transparent;"));
    }

	public UserBean loginBean(String email, String password, String address) {
		UserBean user = new UserBean();
		user.setEmail(email);
		user.setPassword(password);
		user.setAddress(address);
		return user;
	}
	
	private void setUp() {
		 tfEmail.setText("admin");
		 tfPwd.setText("admin");
		 try {
			 comboBox.setItems(controller.loadAddresses());
		 }catch(Exception e) {
			 alert.alertError("DATA BASE ERROR","DATA BASE not connected ","Please Restart the Application");
			 Platform.exit();
		 }
	}

	@Override public void initialize(URL location, ResourceBundle resources) {
    	setUp();
    }
}

