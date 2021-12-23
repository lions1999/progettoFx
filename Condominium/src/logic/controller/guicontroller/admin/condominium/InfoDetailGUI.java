package logic.controller.guicontroller.admin.condominium;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import logic.controller.guicontroller.general.MenuGUI;
import logic.engineeringclasses.bean.UserBean;

public class InfoDetailGUI {

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
    }

    public void btnUpdateClick() {
    }
}
