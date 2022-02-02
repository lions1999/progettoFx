package logic.controller.guicontroller.second.general.registration;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import logic.controller.applicationcontroller.RegisterController;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.AlertGUI;
import logic.controller.guicontroller.first.general.registration.SelectApartmentDialogGUI;
import logic.controller.guicontroller.second.general.AddressesDialogGUI;
import logic.engineeringclasses.bean.UserBean;
import logic.model.Role;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static logic.controller.guicontroller.second.general.Main2GUI.secondBorder;

public class RegisterGUI implements Initializable {

    private final RegisterController controller = new RegisterController();
    private final ViewController view = new ViewController();
    private final AlertGUI alert = new AlertGUI();

    @FXML private TextField tfName;
    @FXML private TextField tfSurname;
    @FXML private TextField tfEmail;
    @FXML private PasswordField tfPassword;
    @FXML private PasswordField tfOkPwd;
    @FXML private ToggleGroup radio;
    @FXML private Text txtAddress;


    public void addressBtnClick() {
        AddressesDialogGUI address = new AddressesDialogGUI();
        txtAddress.setText(address.loadAddresses());
    }

    public void onSignClick() {
        Pane pane = view.getPage("Login",2);
        secondBorder.setCenter(pane);
    }

    public void onSignupClick() throws IOException {
        logic.controller.guicontroller.first.general.registration.RegisterGUI registerGUI = new logic.controller.guicontroller.first.general.registration.RegisterGUI();
        RadioButton selectedRadioButton = (RadioButton) radio.getSelectedToggle();
        String radioTxt = selectedRadioButton.getText();
        UserBean bean = registerGUI.getUsrBean(tfName.getText(),tfSurname.getText(),tfEmail.getText(),tfPassword.getText(),tfOkPwd.getText(),radioTxt,txtAddress.getText());
        if(controller.registration(bean)){
            System.out.println("OK");
            //todo change gui to get apartment
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/logic/view/first/SelectApartmentDialog.fxml"));
            DialogPane pane = loader.load();
            ObservableList<String> list = controller.loadAddress(bean);
            ListView<String> listView = new ListView<>();
            switch (Role.valueOf(bean.getUsrRole().toUpperCase())){
                case RESIDENT:
                    viewResident(listView,list);
                    break;
                case OWNER:
                    viewOwner();
                    break;
            }
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(pane);
            Optional<ButtonType> btn = dialog.showAndWait();
        }else{
            alert.alertError("Condominium/Register/Error","Incorrect Data","ERROR TYPE:\nDATABASE NOT CONNECTED\nEmpty Fields\nPASSWORD:least one letter and one number maximum 15 charters\nPassword mismatch");
        }
    }

    private void viewResident(ListView<String> listView, ObservableList<String> list) {
        for()
    }

    private void viewOwner() {
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
        test();
    }
}
