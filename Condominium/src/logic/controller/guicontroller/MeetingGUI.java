package logic.controller.guicontroller;

import java.net.URL;
import java.sql.SQLException;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import logic.controller.applicationcontroller.PatternController;
import logic.controller.applicationcontroller.SendEmail;
import logic.controller.applicationcontroller.ViewController;
import logic.engineeringclasses.dao.ApartmentDAO;
import logic.model.UserSingleton;

public class MeetingGUI implements Initializable{

	private ViewController view = new ViewController();
	UserSingleton sg = UserSingleton.getInstance();
	private ApartmentDAO ourDb = new ApartmentDAO();
	private final PatternController pattern = new PatternController();
	private final AlertGUI alert = new AlertGUI();
	private int typError;


	@FXML private TextArea ReasonText;
	@FXML private ChoiceBox<String> comboBox;
	@FXML private Button btnClear;
	@FXML private Button btnSend;

	@FXML void ClearReason(ActionEvent event) {
		ReasonText.setText("");
		btnSend.setDisable(true);
	}

	@FXML void SendMeeting(ActionEvent event)  throws SQLException {
		String mail;
		if (comboBox.getValue().equals("Administrator")){
			mail = "condominium.ispw@gmail.com";
		}
		else {
			mail = ourDb.checkMailById(ourDb.checkUserAptFromNumber(ourDb.checkApartments(sg.getUserID()).get(0).getNumber(),"apt_own"));
		}
		System.out.println(mail);

		String subject = "Meeting Request from "+sg.getResident();
		String body = ReasonText.getText();
		String message = String.format(body);
		if(pattern.isText(body)) {
			alert.alertError("Error","Incorrect Text!","You cannot insert more that one consecutive space, please control text before send");
		}
		else{
			String[] recipients = new String[]{mail};

			if (alert.alertConfirm("Confirmation","Confirm to send email?","Are you sure to send mail to the " + comboBox.getValue() + " with text '" + ReasonText.getText() + "' to request a meeting?")) {
				new SendEmail().send(recipients, recipients, subject, message);
				alert.alertInfo("Information", "Mail sent to "+comboBox.getValue()+"!","");
			} else {
				alert.alertInfo("Information", "Mail not sent!","");
			}
		}
	}

    @Override
	public void initialize(URL location, ResourceBundle resources){
		btnSend.setDisable(true);
		comboBox.getItems().addAll("Administrator","Owner");
		comboBox.setValue("Administrator");
		ReasonText.setPromptText("What you want to communicate to "+comboBox.getValue()+"?");
	}

	public void disableSend(KeyEvent keyEvent) {
		if(ReasonText.getText().matches("( *)")){
			btnSend.setDisable(true);
		}else{
			btnSend.setDisable(false);
		}
	}

	public void changeReason(ActionEvent actionEvent) {
		ReasonText.setPromptText("What you want to communicate to "+comboBox.getValue()+"?");
	}
}
