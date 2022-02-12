package logic.controller.guicontroller.second.owner;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class RateDialogGUI{
    @FXML private ToggleGroup group1;
    @FXML public Text TitleTxt;


    public String submitValue(String string){
        TitleTxt.setText("What would you do with resident "+string+"?");
        RadioButton selected = (RadioButton) group1.getSelectedToggle();
        return selected.getText();
    }
}
