package logic.controller.guicontroller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.engineeringclasses.bean.UserBean;
import logic.controller.applicationcontroller.RegisterController;
import logic.controller.applicationcontroller.ViewController;
import logic.engineeringclasses.exception.PatternException;

public class RegisterGUI extends Application implements Initializable{
	 
	private RegisterController controller = new RegisterController();
	private ViewController view = new ViewController();	
	private AlertGUI alert = new AlertGUI();
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
    private Button btnSign;
    @FXML
    private ComboBox<String> roleBox;
    @FXML
    private ComboBox<String> addressBox;
    @FXML
    void onSignClick() {
    	LoginGUI reg = new LoginGUI();
    	reg.start((Stage) btnSign.getScene().getWindow());
    }
    @FXML
    void onSignupClick()throws SQLException,PatternException{
    	UserBean bean = registerBean(tfName.getText(),tfSurname.getText(),tfEmail.getText(),tfPassword.getText(),tfOkPwd.getText(),roleBox.getValue(),addressBox.getValue());
    	System.out.println("ROLE = "+roleBox.getValue());
    	System.out.println("ADDRESS = "+addressBox.getValue());
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
    			alert.alertError( title,"Incorrect Password"+pls,"ERROR TYPES:\n-Empty Field\n-Minimum 4 charters\n-At least one letter and one number\n-Maximum 15 charters\n-With spaces");
    			errorTf(tfPassword);
				break;				
    		case 5:
    			alert.alertError(title,"Password Mismatch"+pls,"ERROR TYPES:\n-Empty Field\n-Different from Password field");
				errorTf(tfOkPwd);
				break;	
    		case 6:
    			alert.alertError(title,noRole+pls,"ERROR TYPES:\n-Empty Field");
    			errorBox(roleBox);
    			break;
    		case 7:
    			alert.alertError(title,"Incorrect Address "+pls,"ERROR TYPES:\n-Empty Field");
    			errorBox(addressBox);
    			break;
    		case 8:
    			alert.alertError(title,"DATA BASE not connected","Please Retry");
    			errorBox(addressBox);
    			break;
    		case 9:
    			alert.alertError(title, "User Already Request to Join", "ERROR TYPES:\n-User already registered");
    			errorTf(tfEmail);
    			errorBox(addressBox);
    			break;
    		default:   
    			alert.alertInfo("Condominium/Register/OK","Successful Registration" ,
    					"Your request has successfully sent to the administrator of the condominium.\nPlease check your in box messages.");
    			clearState();
				break;
	    	}
    	}    		
    }
    
    public UserBean registerBean(String name,String surname,String email, String password,String okPassword,String role,String condominiumCode){
		UserBean user = new UserBean();
		user.setName(name);
		user.setSurname(surname);
		user.setEmail(email);
		user.setPassword(password);
		user.setRole(role);
		user.setOkPassword(okPassword);
		user.setAddress(condominiumCode);
		return user;
	}
        
    private void clearState() {
    	tfName.setText("");
    	tfSurname.setText("");  	
    	tfEmail.setText("");
    	tfPassword.setText("");
    	tfOkPwd.setText("");
    	roleBox.setValue(null);
    	addressBox.setValue(null);
    }
        
    private void errorTf(TextField tf){
    	tf.setText("");
    	tf.setStyle("-fx-border-color: red;");
    	tf.setOnMouseEntered(event -> tf.setStyle("-fx-border-color: transparent;"));
    }
    
    private void errorBox(ComboBox<String> box) {
    	box.setValue(null);
    	box.setStyle("-fx-border-color: red;");
    	box.setOnMouseEntered(event -> box.setStyle("-fx-border-color: transparent;"));
    }
       
/*    private void comboText(ComboBox<String> box) {
    	box.setStyle(" -fx-font: 16px ");
    }*/
    
    private void setUp() {
    	/*comboText(roleBox);
    	comboText(addressBox);*/
    	roleBox.getItems().addAll("Resident","Owner");
    }
      
    private void test() {
    	tfName.setText("prova");
    	tfSurname.setText("prova");  	
    	tfEmail.setText("prova@prova.prova");
    	tfPassword.setText("prova");
    	tfOkPwd.setText("prova");
    }
    
    @Override
   	public void initialize(URL location, ResourceBundle resources){
       	setUp();
       	test();
       	try {
			 addressBox.setItems(controller.loadAddresses());
		 }catch(Exception e) {
			 alert.alertError("DATA BASE ERROR","DATA BASE not connected ","Please Restart the Application");
			 Platform.exit();
		 }
    }	
    
    public void start(Stage primaryStage){
		view.loadPage("Register", primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}


    

	
    
