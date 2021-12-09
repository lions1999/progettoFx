package logic.controller.guicontroller.admin.requests;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
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

    @FXML private Tab tabRegistration;
    @FXML private Tab tabMeeting;
    @FXML private Tab tabEmail;
    protected ViewController view = new ViewController();
   // protected final String address = MenuGUI.sg.getAddress();
    UserSingleton sg = UserSingleton.getInstance();
//    public void regTabClick() {
//        try {
//            FXMLLoader loader = view.loader("RegistrationTable");
//            Parent root = loader.load();
//            RegistrationTableGUI reg = loader.getController();
//            reg.setUpRegistration(this.address);
//            tabRegistration.setContent(root);
//        }catch (IOException e){
//            System.out.println("ERROR REGISTRATION");
//            e.printStackTrace();
//        }
//    }
//
//    public void meetTabClick() {
//        try {
//            FXMLLoader loader = view.loader("RegistrationTable");
//            Parent root = loader.load();
//            RegistrationTableGUI reg = loader.getController();
//            reg.setUpRegistration(this.address);
//            tabRegistration.setContent(root);
//        }catch (IOException e){
//            System.out.println("ERROR REGISTRATION");
//            e.printStackTrace();
//        }
////        try {
////            FXMLLoader loader = view.loader("MeetingTable");
////            Parent root = loader.load();
////            MeetingTableGUI meet = new MeetingTableGUI();
////            meet.setUpMeeting(this.address);
////            tabMeeting.setContent(root);
////        }catch (IOException e){
////            System.out.println("ERROR MEETING");
////            e.printStackTrace();
////        }
//    }
//
//    public void emailTabClick() {
////        try {
////            FXMLLoader loader = view.loader("AddPost");
////            Parent root = loader.load();
////            tabEmail.setContent(root);
////        }catch (IOException e){
////            e.printStackTrace();
////        }
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
