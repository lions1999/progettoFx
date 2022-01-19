package logic.controller.guicontroller.admin.requests.meeting;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import logic.controller.applicationcontroller.MeetController;
import logic.controller.applicationcontroller.SendEmail;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.admin.requests.TabOrganizeGUI;
import logic.controller.guicontroller.general.AlertGUI;
import logic.controller.guicontroller.general.MenuGUI;
import logic.engineeringclasses.bean.MeetRequestBean;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class OrganizeMeetDetailGUI {

    private final AlertGUI alert = new AlertGUI();
    protected final MeetController controller = new MeetController();
    protected final ViewController view = new ViewController();
    private final SendEmail ctrlEmail = new SendEmail();

    public TextField tfID;
    public TextField tfAddr;
    public TextField tfName;
    public TextField tfObject;
    public TextArea txtArea;

    public void btnX() {
        MenuGUI.border.setRight(null);
    }

    public void btnOrganizeClick() throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/logic/view/Dialog.fxml"));
        DialogPane pane = loader.load();
        FXMLLoader meet = view.loader("OrganizeMeet");
        Parent orgMeet = meet.load();
        OrganizeMeetGUI ctrlMeet = meet.getController();
        ctrlMeet.setUp(tfObject.getText());
        pane.setContent(orgMeet);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(pane);
        Optional<ButtonType> btn = dialog.showAndWait();
        if(btn.isPresent() && btn.get() == ButtonType.OK){//todo meet check
            ObservableList<String> list = controller.loadMailList(tfAddr.getText());
            MeetRequestBean bean = ctrlMeet.getMeetBean();
            System.out.println(list);
            for(String email : list){
                String[] recipient = new String[]{email};
                ctrlEmail.send(recipient,recipient, bean.getObject(), bean.getTextArea());
            }
            alert.alertConfirm("MeetRequest","Mail successfully sent to all OWNERS in your condominium",null);
            remove();
        }
    }

    public void btnDeleteClick() throws IOException {
        if(alert.alertConfirm("Title","Are you sure?","")){
            remove();
        }
    }

    private void remove() throws IOException {
        controller.removeMeetRequest(Integer.parseInt(tfID.getText()));
        btnX();
        reloadPage();
    }

    private void reloadPage() throws IOException {
        FXMLLoader loader = view.loader("TabOrganize");
        Parent root = loader.load();
        MenuGUI.border.setCenter(root);
        TabOrganizeGUI tab = loader.getController();
        tab.selectTab(1);
    }

    public void setUp(MeetRequestBean bean){
        tfID.setText(bean.getId());
        tfAddr.setText(bean.getAddress());
        tfName.setText(bean.getName());
        tfObject.setText(bean.getObject());
        txtArea.setText(bean.getTextArea());
    }
}
