package logic.controller.guicontroller.first.owner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import logic.controller.applicationcontroller.MeetController;
import logic.controller.guicontroller.AlertGUI;
import logic.controller.guicontroller.first.general.Main1GUI;
import logic.model.UserSingleton;

import java.sql.SQLException;

public class RequestMeetingGUI extends Main1GUI{


    UserSingleton sg = UserSingleton.getInstance();
    private final MeetController meet = new MeetController();
    private final AlertGUI alert = new AlertGUI();


    @FXML private TextArea ReasonText;
    @FXML private TextArea ObjectText;
    @FXML private Button btnSend;

    public void SendMeeting() {
        String subject = ObjectText.getText();
        String message = ReasonText.getText();
        if (alert.alertConfirm("Confirmation","Confirm to send meeting request?","Are you sure to send a meeting request to Administrator with text '" + message + "'?")) {
            try {
                meet.AddMeeting(sg.getUserID(),sg.getAddress(),subject,message);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Request sent!");
            ClearReason();
        } else {
            System.out.println("Request not sent!");
        }

    }

    public void disableSend() {
        btnSend.setDisable(ReasonText.getText().matches("( *)"));
    }

    @FXML void ClearReason() {
        ObjectText.setText("");
        ReasonText.setText("");
        btnSend.setDisable(true);
    }
}