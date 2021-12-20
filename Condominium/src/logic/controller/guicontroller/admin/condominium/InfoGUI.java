package logic.controller.guicontroller.admin.condominium;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class InfoGUI {

    @FXML private TableView tableView;
    @FXML private TableColumn tcId;
    @FXML private TableColumn tcName;
    @FXML private TableColumn tcEmail;
    @FXML private TableColumn tcPassword;
    @FXML private TableColumn tcRole;
    @FXML private TableColumn tcAddress;

    public void getSelected() {
    }
}
