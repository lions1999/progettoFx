package logic.controller.guicontroller.admin.requests.meeting;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import logic.controller.applicationcontroller.MeetController;
import logic.engineeringclasses.bean.MeetRequestBean;
import logic.model.MeetRequest;

public class MeetingTableGUI  {

    protected final MeetController controller = new MeetController();

    @FXML private TableView<MeetRequest> tableMeeting;
    @FXML public TableColumn<MeetRequest,String> tcId;
    @FXML private TableColumn<MeetRequest,String> tcName;
    @FXML private TableColumn<MeetRequest,String> tcAddr;
    @FXML private TableColumn<MeetRequest,String> tcObj;
    @FXML private TableColumn<MeetRequest,String> tcTxt;

    @FXML private void getSelected() {
        int index;
        index = tableMeeting.getSelectionModel().getSelectedIndex();
        if(index<=-1)return;
        //TODO
        return;
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
