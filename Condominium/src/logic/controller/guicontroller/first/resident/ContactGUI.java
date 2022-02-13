package logic.controller.guicontroller.first.resident;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import logic.controller.applicationcontroller.ApartmentController;
import logic.controller.applicationcontroller.EmailController;
import logic.controller.applicationcontroller.PatternController;
import logic.controller.guicontroller.AlertGUI;
import logic.model.UserSingleton;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContactGUI implements Initializable{

	UserSingleton sg = UserSingleton.getInstance();
	private final ApartmentController aptCtrl = new ApartmentController();
	private final PatternController pattern = new PatternController();
	private final AlertGUI alert = new AlertGUI();


	@FXML private TextArea ReasonText;
	@FXML private Button btnSend;


	@Override
	public void initialize(URL location, ResourceBundle resources){
		btnSend.setDisable(true);
		ReasonText.setPromptText("What do you want to communicate to your apartment Owner?");
	}


	@FXML void ClearReason() {
		ReasonText.setText("");
		btnSend.setDisable(true);
	}

	@FXML void SendMail()  throws SQLException {
		String mail;
		mail = aptCtrl.checkMailById(aptCtrl.checkUserAptFromNumber(sg.getAddress(),aptCtrl.checkApartments(sg.getUserID(),sg.getAddress(),"apt_res").getNumber(),"apt_own"));
		System.out.println(mail);
		String subject = "Message Request from "+sg.getResident();
		String body = ReasonText.getText();
		if(pattern.isText(body)) {
			alert.alertError("Error","Incorrect Text!","You cannot insert more that one consecutive space, please control text before send");
		}
		else{
			String[] recipients = new String[]{mail};

			if (alert.alertConfirm("Confirmation","Confirm to send email?","Are you sure to send mail to your Owner with text '" + ReasonText.getText() + "'?")) {
				new EmailController().send(recipients, recipients, subject, body);
				ClearReason();
			} else {
				System.out.println("not sent!");
			}
		}
	}

	public void disableSend() {
		btnSend.setDisable(ReasonText.getText().matches("( *)"));
	}

}