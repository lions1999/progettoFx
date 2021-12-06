package logic.controller.guicontroller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import logic.controller.applicationcontroller.ViewController;
import logic.model.UserSingleton;

import java.io.IOException;

public class TabOrganizeGUI{

    private ViewController view = new ViewController();
    @FXML private Tab tabRegistration;
    @FXML private Tab tabMeeting;
    @FXML private Tab tabEmail;
    UserSingleton sg = UserSingleton.getInstance();

    public void regTabClick() {
        try {
            FXMLLoader loader = view.loader("RegistrationTable");
            Parent root = loader.load();
            RegistrationGUI reg = loader.getController();
            reg.setUpRegistration(sg.getAddress());
            tabRegistration.setContent(root);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void meetTabClick() {
        try {
            FXMLLoader loader = view.loader("AddPost");
            Parent root = loader.load();
            tabMeeting.setContent(root);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void emailTabClick() {
        try {
            FXMLLoader loader = view.loader("AddPost");
            Parent root = loader.load();
            tabEmail.setContent(root);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
