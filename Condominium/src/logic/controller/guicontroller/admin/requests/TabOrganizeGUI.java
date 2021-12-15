package logic.controller.guicontroller.admin.requests;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.admin.requests.meeting.MeetingTableGUI;
import logic.controller.guicontroller.admin.requests.registration.RegistrationTableGUI;
import logic.controller.guicontroller.general.MainGUI;
import logic.controller.guicontroller.general.MenuGUI;
import logic.model.UserSingleton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TabOrganizeGUI implements Initializable {

    @FXML private TabPane tabOrganize;
    @FXML private Tab tabRegistration;
    @FXML private Tab tabMeeting;
    protected ViewController view = new ViewController();
    UserSingleton sg = UserSingleton.getInstance();


    public void selectRegistration(){
        tabOrganize.getSelectionModel().select(tabRegistration);
    }

    public void selectMeeting(){
        tabOrganize.getSelectionModel().select(tabMeeting);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            FXMLLoader loader = view.loader("RegistrationTable");
            Parent root = loader.load();
            RegistrationTableGUI reg = loader.getController();
            reg.setUpRegistration(sg.getAddress());
            tabRegistration.setContent(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = view.loader("MeetTable");
            Parent root = loader.load();
            MeetingTableGUI meet = loader.getController();
            meet.setUpMeeting(sg.getAddress());
            tabMeeting.setContent(root);
        } catch (Exception e) {
            System.out.println("ERRORE");
            e.printStackTrace();
        }

    }
}
