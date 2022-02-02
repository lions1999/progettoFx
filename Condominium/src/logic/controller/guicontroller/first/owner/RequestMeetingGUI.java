package logic.controller.guicontroller.first.owner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import logic.controller.applicationcontroller.PatternController;
import logic.controller.applicationcontroller.EmailController;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.AlertGUI;
import logic.controller.guicontroller.first.general.Main1GUI;
import logic.engineeringclasses.dao.ApartmentDAO;
import logic.model.UserSingleton;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RequestMeetingGUI extends Main1GUI implements Initializable {

    private ViewController view = new ViewController();
    UserSingleton sg = UserSingleton.getInstance();
    private ApartmentDAO ourDb = new ApartmentDAO();
    private final PatternController pattern = new PatternController();
    private final AlertGUI alert = new AlertGUI();


    @FXML private TextArea ReasonText;
    @FXML private Button btnSend;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void SendMeeting(ActionEvent actionEvent) throws SQLException {
        String mail =  "condominium.ispw@gmail.com";
        System.out.println(mail);

        String subject = "Meeting Request from "+sg.getResident();
        String body = ReasonText.getText();
        String message = String.format(body);
        if(pattern.isText(body)) {
            alert.alertError("Error","Incorrect Text!","You cannot insert more that one consecutive space, please control text before send");
        }
        else{
            String[] recipients = new String[]{mail};

            if (alert.alertConfirm("Confirmation","Confirm to send email?","Are you sure to send mail to the Administrator with text '" + ReasonText.getText() + "' to request a meeting?")) {
                new EmailController().send(recipients, recipients, subject, message);
                alert.alertInfo("Information", "Mail sent to Administrator!","");
                ClearReason();
            } else {
                alert.alertInfo("Information", "Mail not sent!","");
            }
        }
    }

    public void disableSend(KeyEvent keyEvent) {
        if(ReasonText.getText().matches("( *)")){
            btnSend.setDisable(true);
        }else{
            btnSend.setDisable(false);
        }
    }

    @FXML void ClearReason() {
        ReasonText.setText("");
        btnSend.setDisable(true);
    }
}
