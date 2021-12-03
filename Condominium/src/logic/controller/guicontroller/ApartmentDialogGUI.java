package logic.controller.guicontroller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class ApartmentDialogGUI {

    @FXML private Text txtRole;
    @FXML private Text txtAddress;
    @FXML private ComboBox apartmentBox;

    public void setUp(ObservableList<String> list,String role,String address){
        apartmentBox.setValue("Available Apartments");
        if(list.isEmpty()) {
            apartmentBox.setValue("NO Available Apartments left");
        }else {
            apartmentBox.setItems(list);
        }
        txtAddress.setText(address);
        txtRole.setText(role);
    }

    public String getApt(){
        return apartmentBox.getValue().toString();
    }
}
