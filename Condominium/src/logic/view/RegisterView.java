package logic.view;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.bean.UserBean;
import logic.controller.RegisterController;
import logic.controller.ViewController;
import logic.controller.exception.PatternException;

public class RegisterView extends Application{
	 
	private ViewController view = new ViewController();	
	private AlertView alert = new AlertView();
	private String noRole = "No Role Selected";
	private String title = "Condominium/Register/Error";
	private String pls = "\nPlease Retry";
	private int typError;
	
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfSurname;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private PasswordField tfOkPwd;
    @FXML
    private MenuItem mnuResident;
    @FXML
    private MenuItem mnuOwner;
    @FXML
    private Label lbRole;
    @FXML
    private TextField tfCondominiumCode;
    @FXML
    private Button btnSignup;
    @FXML
    private Button btnSignin;
    @FXML
    void onMnuResidentClick() {
    	lbRole.setText(mnuResident.getText());
    }
    @FXML
    void onMnuOwnerClick() {
    	lbRole.setText(mnuOwner.getText());
    }
    @FXML
    void onSigninClick() {
    	LoginView reg = new LoginView();
    	reg.start((Stage) btnSignup.getScene().getWindow());
    }
    @FXML
    void onSignupClick()throws SQLException,PatternException{
    	UserBean bean = registerBean(
    			tfName.getText(),tfSurname.getText(),tfEmail.getText(),tfPassword.getText(),tfOkPwd.getText(),lbRole.getText(),tfCondominiumCode.getText());
    	RegisterController controller = new RegisterController();
    	try {
    		this.typError = controller.registration(bean);  		
    	}finally{
    		switch(this.typError) {
    		case 1:
    			alert.alertError(title, "Incorrect Name : "+bean.getName()+pls,"ERROR TYPES:\n-Empty Field\n-Contains Numbers\n-Over 15 characters");
    			errorTf(tfName);
    			break;
    		case 2:		
    			alert.alertError(title,"Incorrect Surname : "+bean.getSurname()+pls, "ERROR TYPES:\n-Empty Field\n-Contains Numbers\n-Over 15 characters");
    			errorTf(tfSurname);					
    			break;
    		case 3:		
    			alert.alertError( title,"Incorrect Email : "+bean.getEmail()+pls,"ERROR TYPES:\n-Empty Field\n-Not Email Pattern");
    			errorTf(tfEmail);					
    			break;		
    		case 4:
    			alert.alertError( title,"Incorrect Password"+pls,"ERROR TYPES:\n-Empty Field\n-Minor then 4 charater/digits\n-Greater then 20 charater/digits\n-With spaces");
    			errorTf(tfPassword);
				break;				
    		case 5:
    			alert.alertError(title,"Password Mismatch"+pls,"ERROR TYPES:\n-Empty Field\n-Different from Password field");
				errorTf(tfOkPwd);
				break;	
    		case 6:
    			alert.alertError(title,noRole+pls,"ERROR TYPES:\n-Empty Field");
    			lbRole.setText(noRole);
    			break;
    		case 7:
    			alert.alertError(title,"Incorrect Condominium Code : "+bean.getCondominiumCode()+pls,"ERROR TYPES:\n-Empty Field\n-This Code does not exist");
    			errorTf(tfCondominiumCode);
    			break;
    		case 8:
    			alert.alertError(title,"DATA BASE not connected","Please Retry");
    			errorTf(tfCondominiumCode);
    			break;
    		case 9:
    			alert.alertError(title, "User Alerady Request to Join", "ERROR TYPES:\n-User already registred");
    			errorTf(tfEmail);
    			errorTf(tfCondominiumCode);
    			break;
    		default:   
    			alert.alertInfo("Condominium/Register/OK","Succesful Registration" ,
    					"Your request has succesfully sent to the administrator of the condominium.\nPlease check your in box messages.");
    			clearState();
				break;
	    	}
    	}    		
    }
        
    private void clearState() {
    	tfName.setText("");
    	tfSurname.setText("");  	
    	tfEmail.setText("");
    	tfPassword.setText("");
    	tfOkPwd.setText("");
    	lbRole.setText(noRole);
    	tfCondominiumCode.setText("");
    }
        
    private void errorTf(TextField tf){
    	tf.setText("");
    	tf.setStyle("-fx-border-color: red;");
    	tf.setOnMouseEntered(event -> {
    		tf.setStyle("-fx-border-color: transparent;");
        });
    }
       
    public UserBean registerBean(String name,String surname,String email, String password,String okPassword,String role,String condominiumCode){
		UserBean user = new UserBean();
		user.setName(name);
		user.setSurname(surname);
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(role);
		user.setOkPassword(okPassword);
		user.setcondominiumCode(condominiumCode);
		return user;
	}
    
    public void start(Stage primaryStage){
		view.loadPage("Register", primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}

}


    

	
    
