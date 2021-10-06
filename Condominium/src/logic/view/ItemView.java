package logic.view;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import logic.controller.ViewController;
import logic.dao.SqlDAO;
import logic.model.UserSingleton;

public class ItemView implements Initializable{

	private SqlDAO ourDb = new SqlDAO(); 
	UserSingleton sg = UserSingleton.getInstance();
	private int pos;
	
    @FXML
    private HBox itemBox;

    @FXML
    private Label lblName;

    @FXML
    private TextField textField;

    @FXML
    private Button btnAccept;

    @FXML
    private Button btnDecline;

    @FXML
    void onAcceptClick(ActionEvent event) {

    }

    @FXML
    void onDeclineClick(ActionEvent event) {
    	try {  
    		if(confirmationDisplay()) {
    		ourDb.removeRequest(btnDecline.getAccessibleText());
    		sg.getAdministrator().getListRequest().remove(pos);
    		btnDecline.setVisible(false);
    		btnAccept.setVisible(false);
    		}
    	}catch(SQLException e){
    		
    	}
    }
    
    public void initialize(URL location, ResourceBundle resources){
    	itemBox.setMinWidth(1000);
    	textField.setEditable(false);
    	textField.setText(sg.getReq().getReason());
    	lblName.setText(sg.getReq().getUser());
    	btnDecline.setAccessibleText(sg.getReq().getId());
    	pos = sg.getReq().getPos();
    	
    }
    
    private boolean confirmationDisplay() {
    	ConfirmBox box = new ConfirmBox();
    	boolean i = box.display("Condominium/HomePage/Confirm","Are you sure about it?");
    	return i;
    }
    
}