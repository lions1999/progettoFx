package logic.controller.guicontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import logic.controller.applicationcontroller.RegisterController;
import logic.engineeringclasses.bean.FeeBean;
import logic.engineeringclasses.bean.RegisteredBean;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class RegistrationDetailGUI {

    private static final MenuGUI menu = new MenuGUI();
    private final AlertGUI alert = new AlertGUI();
    private final RegisterController controller = new RegisterController();

    @FXML private TextField tfID;
    @FXML private TextField tfName;
    @FXML private TextField tfEmail;
    @FXML private TextField tfPwd;
    @FXML private TextField tfRole;
    @FXML private TextField tfAddr;
    @FXML private TextField tfApartment;

    @FXML private void btnX() {
        MainGUI.border.setRight(null);
    }

    @FXML private void btnAddClick() throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/logic/view/FeeDialog.fxml"));
        DialogPane pane = loader.load();
        FeeDialogGUI fee = loader.getController();
        fee.setUp();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane( pane);
        Optional<ButtonType> btn = dialog.showAndWait();
        if(btn.isPresent() && fee.check()){
            System.out.println("OK");
            System.out.println(fee.getFees().getElect());
            controller.addRegistered(getRegistered(), fee.getFees());
            remove();
        } else{
            alert.alertError("Registration/Error","Incorrect/Empty Credential","Please Retry");
        }
    }

    @FXML private void btnDeleteClick() {
        if(alert.alertConfirm("Title","Are you sure?","")){
            remove();
        }
    }

    private RegisteredBean getRegistered(){
        return regBean(tfName.getText(),tfEmail.getText(),tfPwd.getText(),tfRole.getText(),tfAddr.getText(),tfApartment.getText());
    }

    private RegisteredBean regBean(String name,String email,String pwd,String role,String addr,String apt){
        RegisteredBean bean = new RegisteredBean();
        bean.setName(name);
        bean.setEmail(email);
        bean.setPassword(pwd);
        bean.setRole(role);
        bean.setAddress(addr);
        bean.setApartment(apt);
        return bean;
    }

    private void remove(){
        controller.removeRegistered(Integer.parseInt(tfID.getText()));
        btnX();
        menu.btnMeetingClick();
    }

    protected void setUp(RegisteredBean bean){
        tfID.setText(bean.getID());
        tfName.setText(bean.getName());
        tfEmail.setText(bean.getEmail());
        tfPwd.setText(bean.getPassword());
        tfRole.setText(bean.getRole());
        tfAddr.setText(bean.getAddress());
        tfApartment.setText(bean.getApartment());
    }
}
