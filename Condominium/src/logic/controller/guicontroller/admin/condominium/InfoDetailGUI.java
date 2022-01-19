package logic.controller.guicontroller.admin.condominium;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import logic.controller.applicationcontroller.FeeController;
import logic.controller.applicationcontroller.UserController;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.general.AlertGUI;
import logic.controller.guicontroller.general.FeeInfoGUI;
import logic.controller.guicontroller.general.MenuGUI;
import logic.engineeringclasses.bean.FeeBean;
import logic.engineeringclasses.bean.UserBean;
import logic.model.Role;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class InfoDetailGUI {

    private final UserController usrController = new UserController();
    private final FeeController feeController = new FeeController();
    private final ViewController view = new ViewController();
    private final AlertGUI alert = new AlertGUI();

    @FXML private Button btnRole;
    @FXML private TextField tfID;
    @FXML private TextField tfName;
    @FXML private TextField tfEmail;
    @FXML private TextField tfPwd;
    @FXML private TextField tfRole;
    @FXML private TextField tfAddr;

    @FXML private void btnX() {
        MenuGUI.border.setRight(null);
    }

    protected void setUp(UserBean bean){
        tfID.setText(bean.getUsrID());
        tfName.setText(bean.getUsrName());
        tfEmail.setText(bean.getUsrEmail());
        tfPwd.setText(bean.getUsrPwd());
        tfRole.setText(bean.getUsrRole());
        tfAddr.setText(bean.getUsrAddr());
        switch (Role.valueOf(bean.getUsrRole())){
            case RESIDENT:
                btnRole.setText("Update Fee");
                break;
            case OWNER:
                btnRole.setText("Update Apartments");
                break;
        }
    }

    public void btnUpdateClick() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/logic/view/Dialog.fxml"));
        DialogPane pane = loader.load();
        FXMLLoader loadInfo = view.loader("UpdateInfo");
        Parent info = loadInfo.load();
        UpdateInfoGUI ctrlInfo = loadInfo.getController();
        ctrlInfo.setUp(getUserBean());
        pane.setContent(info);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(pane);
        Optional<ButtonType> btn = dialog.showAndWait();
        if(btn.isPresent() && btn.get() == ButtonType.OK && ctrlInfo.checkInfo()){
            usrController.updateInfo(ctrlInfo.getBean());
            alert.alertInfo("Condominium/Info","User Successfully Updated",null);
            btnX();
            reloadPage();
        } else {
            alert.alertError("Condominium/Error","Incorrect Data",null);
        }
    }

    public void btnRemoveClick() throws IOException {
        if(alert.alertConfirm("Condominium/Info","Are you sure you want to permanently delete user "+tfName.getText()+"?",null)){
            switch (Role.valueOf(tfRole.getText())){
                case RESIDENT:
                    usrController.removeResident(tfID.getText());
                    break;
                case OWNER:
                    usrController.removeOwner(tfID.getText());
                    break;
            }
            btnX();
            reloadPage();
        }
    }

    public void btnRoleClick() throws SQLException, IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/logic/view/Dialog.fxml"));
        DialogPane pane = loader.load();
        switch (Role.valueOf(tfRole.getText())){
            case RESIDENT:
                updateFee(pane);
                break;
            case OWNER:
                btnRole.setText("Update Apartments");
                break;
        }
        btnX();
    }

    private void updateFee(DialogPane pane) throws SQLException, IOException {
        FXMLLoader feeInfo = view.loader("FeeInfo");
        Parent feeParent = feeInfo.load();
        FeeInfoGUI ctrlFee = feeInfo.getController();
        ctrlFee.setUp(tfAddr.getText());
        ctrlFee.loadFeeInfo(tfID.getText());
        FeeBean past = ctrlFee.getFees();
        pane.setContent(feeParent);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(pane);
        Optional<ButtonType> btn = dialog.showAndWait();
        if(btn.isPresent() && btn.get() == ButtonType.OK && ctrlFee.check()){
            feeController.updateFees(past,ctrlFee.getFees());
            alert.alertInfo("Condominium/Info","Fee Successfully Updated",null);
        } else{
            alert.alertError("Condominium/Error","Incorrect/Empty Credential","\n-Empty Field\n-More than 4 digits fee\n-Negative Value");
        }
    }

    private UserBean getUserBean(){
        return setUserBean(tfID.getText(),tfName.getText(),tfEmail.getText(),tfPwd.getText());
    }

    private UserBean setUserBean(String id,String name, String email, String pwd) {
        UserBean bean = new UserBean();
        bean.setUsrID(id);
        bean.setUsrName(name);
        bean.setUsrEmail(email);
        bean.setUsrPwd(pwd);
        return bean;
    }

    private void reloadPage() throws IOException {
        FXMLLoader loader = view.loader("Info");
        Parent root = loader.load();
        InfoGUI infoCtrl = loader.getController();
        infoCtrl.setUp(tfAddr.getText());
        MenuGUI.border.setCenter(root);
    }
}
