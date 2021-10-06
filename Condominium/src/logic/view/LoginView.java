package logic.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.bean.UserBean;
import logic.controller.LoginController;
import logic.controller.ViewController;

public class LoginView extends Application implements Initializable{
	
	private ViewController view = new ViewController();
	private AlertView alert = new AlertView();
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
    private TextField tfcc;	    

    @FXML
    private void onSignupClick(){	    	
    	RegisterView reg = new RegisterView();
    	reg.start((Stage) btnSignup.getScene().getWindow());	   	    	
    }    	    
    @FXML
    private void onSigninClick() throws Exception{
    	UserBean bean = loginBean(tfemail.getText(), tfpassword.getText(),tfcc.getText());
		LoginController controller = new LoginController();
		try {
			this.typError = controller.login(bean);							
		} finally {
			switch(this.typError) {
			case 1:			
				alert.alertError(title,"Incorrect Credential","Please Retry");
				error();
				break;
			case 2:
				alert.alertError(title,"DATA BASE not connected ","Please Retry");
				error();
				break;
			default:
				NewMainView home = new NewMainView();
				home.start((Stage) btnSignin.getScene().getWindow());
				break;
			}
		}
	}
    
    private void error() {
    	errorTf(tfemail);
		errorTf(tfpassword);
		errorTf(tfcc);
    }
    
    private void errorTf(TextField tf){
    	tf.setText("");
    	tf.setStyle("-fx-border-color: red;");
    	tf.setOnMouseEntered(event -> {
    		tf.setStyle("-fx-border-color: transparent;");
        });
    }

	public UserBean loginBean(String email, String password, String condominiumCode) {
		UserBean user = new UserBean();
		user.setEmail(email);
		user.setPassword(password);
		user.setcondominiumCode(condominiumCode);
		return user;
	}
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
	  tfemail.setText("admin@admin.com");
	  tfpassword.setText("admin");
	  tfcc.setText("67890");
    }
	 		 
	@Override
	public void start(Stage primaryStage) {		
		view.loadPage("Login", primaryStage);
		primaryStage.centerOnScreen();
	}	
	
	public static void main(String[] args) {
		launch(args);
	}
}

