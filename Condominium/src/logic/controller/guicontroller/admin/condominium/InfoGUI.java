package logic.controller.guicontroller.admin.condominium;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import logic.controller.applicationcontroller.UserController;
import logic.controller.applicationcontroller.ViewController;
import logic.controller.guicontroller.general.FeeInfoGUI;
import logic.engineeringclasses.bean.UserBean;
import logic.model.Role;
import logic.model.User;

import java.io.IOException;
import java.sql.SQLException;

import static logic.controller.guicontroller.general.MainGUI.border;

public class InfoGUI {

    private final UserController controller = new UserController();
    private final ViewController view = new ViewController();
    private final VBox scrollBox = new VBox();

    @FXML private TableView<User> InfoTableView;
    @FXML private TableColumn<User, String> tcID;
    @FXML private TableColumn<User, String> tcName;
    @FXML private TableColumn<User, String> tcEmail;
    @FXML private TableColumn<User, String> tcPwd;
    @FXML private TableColumn<User, String> tcRole;
    @FXML private TableColumn<User, String> tcAddress;

    @FXML private void getSelected() throws IOException, SQLException {
        scrollBox.setAlignment(Pos.TOP_CENTER);
        scrollBox.getChildren().clear();
        int index;
        index = InfoTableView.getSelectionModel().getSelectedIndex();
        if(index<=-1)return;
        UserBean bean = usrBean(tcID.getCellData(index),tcName.getCellData(index),tcEmail.getCellData(index),tcPwd.getCellData(index),tcRole.getCellData(index),tcAddress.getCellData(index));
        scrollBox.getChildren().add(loadInfo(bean));
        switch (Role.valueOf(bean.getUsrRole())){
            case RESIDENT:
                scrollBox.getChildren().add(loadFee(bean));
                break;
            case OWNER:
                break;
        }
        Button btn = new Button("DELETE");
        scrollBox.getChildren().add(btn);
        border.setRight(scrollBox);
    }

    private Parent loadFee(UserBean bean) throws IOException, SQLException {
        FXMLLoader loader = view.loader("FeeInfo");
        Parent feeInfo = loader.load();
        FeeInfoGUI ctrlFee = loader.getController();
        ctrlFee.setUp(bean.getUsrAddr());
        ctrlFee.loadFeeInfo(bean.getUsrID());
        return feeInfo;
    }

    private Parent loadInfo(UserBean bean) throws IOException {
        FXMLLoader loader = view.loader("InfoDetail");
        Parent usrInfo = loader.load();
        InfoDetailGUI ctrlInfo = loader.getController();
        ctrlInfo.setUp(bean);
        return usrInfo;
    }

    private UserBean usrBean(String id, String name, String email, String password, String role, String address) {
        UserBean bean = new UserBean();
        bean.setUsrID(id);
        bean.setUsrName(name);
        bean.setUsrEmail(email);
        bean.setUsrPwd(password);
        bean.setUsrRole(role);
        bean.setUsrAddr(address);
        return bean;
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
            InfoTableView.setItems(list);
        } catch (Exception e) {
            System.out.println("No");
        }
    }
}
