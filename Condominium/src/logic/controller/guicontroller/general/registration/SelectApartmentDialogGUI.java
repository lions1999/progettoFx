package logic.controller.guicontroller.general.registration;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.text.Text;
import logic.model.Role;

public class SelectApartmentDialogGUI {

    @FXML private Text txtRole;
    @FXML private Text txtAddress;
    @FXML private ListView<String> listView;

    public void setUp(ObservableList<String> list, String role, String address){
        listView.setItems(list);
        switch (Role.valueOf(role.toUpperCase())){
            case RESIDENT:
                listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                break;
            case OWNER:
                listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                break;
        }
        txtAddress.setText(address);
        txtRole.setText(role);
    }

    public ObservableList<String> getApt() {return listView.getSelectionModel().getSelectedItems();}
}
