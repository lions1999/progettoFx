package logic.controller.guicontroller.admin.requests.meeting;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import logic.controller.applicationcontroller.MeetController;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.admin.requests.registration.RegistrationTableDetailGUI;
import logic.controller.guicontroller.general.FeeInfoGUI;
import logic.engineeringclasses.bean.MeetRequestBean;
import logic.engineeringclasses.bean.RegistrationBean;
import logic.model.MeetRequest;

import java.io.IOException;
import java.util.Optional;

public class MeetingTableGUI  {

    protected final MeetController controller = new MeetController();
    protected final ViewController view = new ViewController();

    @FXML private TableView<MeetRequest> tableMeeting;
    @FXML private TableColumn<MeetRequest,String> tcId;
    @FXML private TableColumn<MeetRequest,String> tcName;
    @FXML private TableColumn<MeetRequest,String> tcAddr;
    @FXML private TableColumn<MeetRequest,String> tcObj;
    @FXML private TableColumn<MeetRequest,String> tcTxt;

    @FXML private void getSelected() throws IOException {
        int index;
        index = tableMeeting.getSelectionModel().getSelectedIndex();
        if(index<=-1)return;
        OrganizeMeet(index);
//        FXMLLoader loader = view.loader("RegistrationDetailTable");

//        Parent root = loader.load();
//        RegistrationTableDetailGUI detail = loader.getController();
//        detail.setUp(bean);
//        style();
       // border.setRight();
    }

    private void OrganizeMeet(int index) throws IOException {
        MeetRequestBean bean = meetRequestBean(tcId.getCellData(index),tcName.getCellData(index),tcAddr.getCellData(index),tcObj.getCellData(index),tcTxt.getCellData(index));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/logic/view/Dialog.fxml"));
        DialogPane pane = loader.load();
        FXMLLoader meet = view.loader("OrganizeMeet");
        Parent meetInfo = meet.load();
        OrganizeMeetGUI ctrlMeet = meet.getController();
        ctrlMeet.setUp(bean);
        pane.setContent(meetInfo);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(pane);
        Optional<ButtonType> btn = dialog.showAndWait();
        if(btn.isPresent() && btn.get() == ButtonType.OK){
            System.out.println("OK");
            //TODO
        }
    }

    public void setUpMeeting(String address) {
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcAddr.setCellValueFactory(new PropertyValueFactory<>("address"));
        tcObj.setCellValueFactory(new PropertyValueFactory<>("object"));
        tcTxt.setCellValueFactory(new PropertyValueFactory<>("text"));
        try {
            ObservableList<MeetRequest> list = controller.loadMeetRequest(address);
            tableMeeting.setItems(list);
        }catch(Exception e) {
            System.out.println("No");
        }
    }
    private MeetRequestBean meetRequestBean(String id, String name, String address, String object, String text) {
        MeetRequestBean meet = new MeetRequestBean();
        meet.setId(id);
        meet.setName(name);
        meet.setAddress(address);
        meet.setObject(object);
        meet.setText(text);
        return meet;
    }
}
