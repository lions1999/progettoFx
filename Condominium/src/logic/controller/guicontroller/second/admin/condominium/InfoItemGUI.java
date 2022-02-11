package logic.controller.guicontroller.second.admin.condominium;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.controller.applicationcontroller.UserController;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.AlertGUI;
import logic.controller.guicontroller.first.admin.condominium.InfoDetailGUI;
import logic.controller.guicontroller.first.admin.condominium.UpdateInfoGUI;
import logic.controller.guicontroller.second.general.Menu2GUI;
import logic.engineeringclasses.bean.UserBean;
import logic.model.Role;

import java.io.IOException;

import static logic.controller.guicontroller.second.general.Main2GUI.secondBorder;

public class InfoItemGUI {

    private final ViewController view = new ViewController();
    private final InfoDetailGUI infoDetailGUI = new InfoDetailGUI();
    private final UserController usrController = new UserController();
    private final AlertGUI alert = new AlertGUI();
    private final Menu2GUI menu = new Menu2GUI();

    @FXML private ImageView btnDelete;
    @FXML private Label lbID;
    @FXML private Label lbName;
    @FXML private Label lbEmail;
    @FXML private Label lbPwd;
    @FXML private Label lbRole;
    @FXML private Label lbAddr;

    public void onInfoPress() throws IOException {
        Button btnOk = new Button("OK");
        FXMLLoader loader = view.loader("UpdateInfo",1);
        Parent info = loader.load();
        UpdateInfoGUI ctrlInfo = loader.getController();
        UserBean bean = infoDetailGUI.setUserBean(lbID.getText(),lbName.getText(),lbEmail.getText(),lbPwd.getText());
        ctrlInfo.setUp(bean);
        btnOk.setOnAction(e->{
            if(ctrlInfo.checkInfo()){
                    usrController.updateInfo(ctrlInfo.getBean());
                    alert.alertInfo("Condominium/Info","User Successfully Updated",null);
                try {
                    reloadPage();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                    alert.alertError("Condominium/Error","Incorrect Data",null);
                }
            });
        view.secondRight(info,btnOk);
    }

    public void onDeletePress() throws IOException {
        if (Role.valueOf(lbRole.getText()) == Role.RESIDENT) {//TODO
            if(alert.alertConfirm("Condominium/Info","Are you sure you want to permanently delete user "+lbName.getText()+"?",null)){
                usrController.removeResident(lbID.getText());
            }
            reloadPage();
        }
    }


    public void onUpdatePress() {
        switch (Role.valueOf(lbRole.getText())){
            case OWNER:
                //TODO
                break;
            case RESIDENT:
                //TODO
                break;
        }
    }

    private void reloadPage() throws IOException {
       menu.btnInfoClick();
    }

    public void setUp(UserBean bean) {
        lbID.setText(bean.getUsrID());
        lbName.setText(bean.getUsrName());
        lbEmail.setText(bean.getUsrEmail());
        lbPwd.setText(bean.getUsrPwd());
        lbRole.setText(bean.getUsrRole());
        lbAddr.setText(bean.getUsrAddr());
        if (Role.valueOf(bean.getUsrRole()) == Role.OWNER) {
            btnDelete.setVisible(false);
        }
    }
}
