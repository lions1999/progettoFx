package logic.controller.guicontroller.admin.requests.registration;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import logic.controller.guicontroller.admin.requests.TabOrganizeGUI;
import logic.controller.guicontroller.general.AlertGUI;
import logic.controller.guicontroller.general.MenuGUI;
import logic.engineeringclasses.bean.RegistrationBean;
import logic.model.Role;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class RegistrationTableDetailGUI extends RegistrationTableGUI {

    private final AlertGUI alert = new AlertGUI();

    @FXML private TextField tfID;
    @FXML private TextField tfName;
    @FXML private TextField tfEmail;
    @FXML private TextField tfPwd;
    @FXML private TextField tfRole;
    @FXML private TextField tfAddr;
    @FXML private TextField tfApartment;

    @FXML private void btnX() {
        MenuGUI.border.setRight(null);
    }

    @FXML private void btnAddClick() throws IOException, SQLException {
        switch(Role.valueOf(tfRole.getText())){
            case RESIDENT:
                addResident();
                break;
            case OWNER:
                addOwner();
                break;
        }
    }

    @FXML private void btnDeleteClick() throws IOException {
        if(alert.alertConfirm("Title","Are you sure?","")){
            remove();
        }
    }

    private void addResident() throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/logic/view/FeeDialog.fxml"));
        DialogPane pane = loader.load();
        RegistrationFeeDialogGUI fee = loader.getController();
        fee.setUp(tfName.getText(),tfApartment.getText());
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(pane);
        Optional<ButtonType> btn = dialog.showAndWait();
        if(btn.isPresent() && fee.check()){
            controller.addRegistered(getRegistered(), fee.getFees());
            alert.alertInfo("Registration/Info","User Successfully Registered",null);
            remove();
        } else{
            alert.alertError("Registration/Error","Incorrect/Empty Credential","\n-Empty Field\n-More than 4 digits fee\n-Negative Value");
        }
    }

    private void addOwner() throws IOException {
        if(alert.alertConfirm("Registration/Confirm","Are you sure?",null)){
            controller.addRegistered(getRegistered(), null);
            alert.alertInfo("Registration/Info","User Successfully Registered",null);
            remove();
        }
    }

    private RegistrationBean getRegistered(){
        return regBean(tfName.getText(),tfEmail.getText(),tfPwd.getText(),tfRole.getText(),tfAddr.getText(),tfApartment.getText());
    }

    private RegistrationBean regBean(String name, String email, String pwd, String role, String addr, String apt){
        RegistrationBean bean = new RegistrationBean();
        bean.setName(name);
        bean.setEmail(email);
        bean.setPassword(pwd);
        bean.setRole(role);
        bean.setAddress(addr);
        bean.setApartment(apt);
        return bean;
    }

    private void remove() throws IOException {
        controller.removeRegistered(Integer.parseInt(tfID.getText()));
        btnX();
        FXMLLoader loader = view.loader("TabOrganize");
        Parent root = loader.load();
        TabOrganizeGUI org = loader.getController();
        org.selectRegistration();
        MenuGUI.border.setCenter(root);
    }

    protected void setUp(RegistrationBean bean){
        tfID.setText(bean.getID());
        tfName.setText(bean.getName());
        tfEmail.setText(bean.getEmail());
        tfPwd.setText(bean.getPassword());
        tfRole.setText(bean.getRole());
        tfAddr.setText(bean.getAddress());
        tfApartment.setText(bean.getApartment());
    }
}
