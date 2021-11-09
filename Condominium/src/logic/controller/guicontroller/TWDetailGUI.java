package logic.controller.guicontroller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import logic.controller.applicationcontroller.RegisterController;
import logic.engineeringclasses.bean.UserBean;
import logic.model.Role;
import logic.model.UserSingleton;

public class TWDetailGUI extends TWRegistrationGUI{

    private final AlertGUI alert = new AlertGUI();
    private final RegisterController controller = new RegisterController();
    UserSingleton sg = UserSingleton.getInstance();

    @FXML private TextField tfName;
    @FXML private TextField tfEmail;
    @FXML private TextField tfPwd;
    @FXML private TextField tfRole;
    @FXML private TextField tfAddr;


    @FXML private void btnX() {
        border.setRight(null);
    }

    @FXML private void btnAddClick() {
        if(alert.alertConfirm("Title","Are you sure?","")){

            //setUpRegistration(this.address);
            addUser();
            //controller.removeRegistered(int i);
        }
    }

    @FXML private void btnDeleteClick() {
        if(alert.alertConfirm("Title","Are you sure?","")){
//            switch(Role.valueOf(tfRole.getText())) {
//                case RESIDENT:
//                    //FXMLLoader loader = new FXMLLoader(getClass().getResource("ApartmentRegistrationGUI"));
//                    break;
//                case OWNER:
//                    break;
//            }
//            controller.removeRegistered(regId);
//            setUpRegistration(sg.getAddress());
            System.out.println(regId);
        }
    }

    private void addUser(){
        //UserBean bean = RegisteredBean(twName.getText(),twEmail.getText(),twPwd.getText(),twRole.getText(),twAddr.getText());
        //controller.addRegistered(bean);
        alert.alertInfo("","User successfully added","");
        //controller.removeRegistered(this.Id);
        //setUpRegistration(this.address);
    }

    protected void setUp(UserBean bean){
        tfName.setText(bean.getName());
        tfEmail.setText(bean.getEmail());
        tfPwd.setText(bean.getPassword());
        tfRole.setText(bean.getRole());
        tfAddr.setText(bean.getAddress());
    }
}
