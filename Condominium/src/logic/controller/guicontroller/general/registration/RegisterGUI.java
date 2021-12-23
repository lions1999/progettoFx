package logic.controller.guicontroller.general.registration;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import logic.controller.applicationcontroller.RegisterController;
import logic.controller.guicontroller.general.MainGUI;
import logic.engineeringclasses.bean.UserBean;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterGUI extends MainGUI implements Initializable{
	 
	private final RegisterController controller = new RegisterController();
	private int typError;

	@FXML private TextField tfName;
	@FXML private TextField tfSurname;
	@FXML private TextField tfEmail;
	@FXML private PasswordField tfPassword;
	@FXML private PasswordField tfOkPwd;
	@FXML private ComboBox<String> roleBox;
    @FXML private ComboBox<String> addressBox;

	@FXML private void onSignClick() {
		Pane pane = view.getPage("Login");
		border.setCenter(pane);
    }

    @FXML
    void onSignupClick() {
    	UserBean bean = registerBean(tfName.getText(),tfSurname.getText(),tfEmail.getText(),tfPassword.getText(),tfOkPwd.getText(),roleBox.getValue(),addressBox.getValue());
    	try {
    		this.typError = controller.registration(bean);  		
    	}finally{
			String noRole = "No Role Selected";
			String title = "Condominium/Register/Error";
			String pls = "\nPlease Retry";
			switch(this.typError) {
    		case 1:
    			alert.alertError(title, "Incorrect Name : "+bean.getUsrName()+ pls,"ERROR TYPES:\n-Empty Field\n-Contains Numbers\n-Over 15 characters");
    			errorTf(tfName);
    			break;
    		case 2:		
    			alert.alertError(title,"Incorrect Surname : "+bean.getUsrSurname()+ pls, "ERROR TYPES:\n-Empty Field\n-Contains Numbers\n-Over 15 characters");
    			errorTf(tfSurname);					
    			break;
    		case 3:		
    			alert.alertError(title,"Incorrect Email : "+bean.getUsrEmail()+ pls,"ERROR TYPES:\n-Empty Field\n-Not Email Pattern");
    			errorTf(tfEmail);					
    			break;		
    		case 4:
    			alert.alertError(title,"Incorrect Password"+ pls,"ERROR TYPES:\n-Empty Field\n-Minimum 4 charters\n-At least one letter and one number\n-Maximum 15 charters\n-With spaces");
    			errorTf(tfPassword);
				break;				
    		case 5:
    			alert.alertError(title,"Password Mismatch"+ pls,"ERROR TYPES:\n-Empty Field\n-Different from Password field");
				errorTf(tfOkPwd);
				break;	
    		case 6:
    			alert.alertError(title, noRole + pls,"ERROR TYPES:\n-Empty Field");
    			errorBox(roleBox);
    			break;
    		case 7:
    			alert.alertError(title,"Incorrect Address "+ pls,"ERROR TYPES:\n-Empty Field");
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
			case 10:
				alert.alertError(title, "No Apartment Available", "Please Retry");
				break;
			case 11:
				alert.alertError(title, "Apartment not Selected", "Please Retry");
				break;
    		default:
    			alert.alertInfo("Condominium/Register/Info","Successful Registration" ,
    					"Your request has successfully sent to the administrator of the condominium.\nPlease check your in box messages.");
    			clearState();
				break;
	    	}
    	}    		
    }
    
    public UserBean registerBean(String name,String surname,String email, String password,String okPassword,String role,String condominiumCode){
		UserBean user = new UserBean();
		user.setUsrName(name);
		user.setUsrSurname(surname);
		user.setUsrEmail(email);
		user.setUsrPwd(password);
		user.setUsrRole(role);
		user.setOkPassword(okPassword);
		user.setUsrAddr(condominiumCode);
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
		//TODO ADD TO CSS
    }
    
    private void errorBox(ComboBox<String> box) {
    	box.setValue(null);
    	box.setStyle("-fx-border-color: red;");
    	box.setOnMouseEntered(event -> box.setStyle("-fx-border-color: transparent;"));
		//TODO ADD TO CSS
    }
    
    private void setUp() {
    	roleBox.getItems().addAll("Resident","Owner");
    }
      
    private void test() {
    	tfName.setText("try");
    	tfSurname.setText("try");
    	tfEmail.setText("try2try@try.try");
    	tfPassword.setText("try4");
    	tfOkPwd.setText("try4");
		//TODO DELETE FINAL RELEASE
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
}


    

	
    
