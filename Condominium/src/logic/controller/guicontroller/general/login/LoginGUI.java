package logic.controller.guicontroller.general.login;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import logic.controller.applicationcontroller.LoginController;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.general.AlertGUI;
import logic.controller.guicontroller.general.MainGUI;
import logic.controller.guicontroller.general.MenuGUI;
import logic.engineeringclasses.bean.UserBean;

import java.net.URL;
import java.util.ResourceBundle;

import static logic.controller.guicontroller.general.MainGUI.border;

public class LoginGUI implements Initializable{
	
	private final LoginController controller = new LoginController();
	private final ViewController view = new ViewController();
	private final AlertGUI alert = new AlertGUI();
	private final MainGUI main = new MainGUI();

	@FXML private TextField tfEmail;
	@FXML private TextField tfPwd;
	@FXML private ComboBox<String> comboBox;

	@FXML private void onSignupClick(){
		Pane pane = view.getPage("Register");
		border.setCenter(pane);
    }

	@FXML private void onSignClick(){
    	UserBean bean = loginBean(tfEmail.getText(), tfPwd.getText(), comboBox.getValue());
		if (controller.login(bean)) {
			Pane menu = view.getPage("Menu");
			border.setLeft(menu);
			main.fullScreen(true);
		} else {
			alert.alertError("Login/Error", "Incorrect Email or Password", "Please Retry");
			error();
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
		user.setUsrEmail(email);
		user.setUsrPwd(password);
		user.setUsrAddr(address);
		return user;
	}
	
	private void setUp() {
		 tfEmail.setText("admin");
		 tfPwd.setText("admin");
		 comboBox.setValue("Via del Corso 22");
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

