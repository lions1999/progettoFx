package logic.controller.guicontroller.admin.condominium;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.controller.applicationcontroller.UserController;
import logic.model.User;

public class InfoGUI {

    private final UserController controller = new UserController();

    @FXML private TableView<User> tableView;
    @FXML private TableColumn<User, String> tcID;
    @FXML private TableColumn<User, String> tcName;
    @FXML private TableColumn<User, String> tcEmail;
    @FXML private TableColumn<User, String> tcPwd;
    @FXML private TableColumn<User, String> tcRole;
    @FXML private TableColumn<User, String> tcAddress;

    public void getSelected() {

    }

    public void setUp(String address) {
        tcID.setCellValueFactory(new PropertyValueFactory<>("usrID"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("usrName"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("usrEmail"));
        tcPwd.setCellValueFactory(new PropertyValueFactory<>("usrPwd"));
        tcRole.setCellValueFactory(new PropertyValueFactory<>("usrRole"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<>("usrAddr"));
        try {
            ObservableList<User> list = controller.loadUserList(address);
            tableView.setItems(list);
        }catch(Exception e) {
            System.out.println("No");
        }
    }
}
