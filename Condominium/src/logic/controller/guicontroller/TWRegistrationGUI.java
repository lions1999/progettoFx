package logic.controller.guicontroller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.engineeringclasses.bean.UserBean;
import logic.controller.applicationcontroller.RegisterController;
import logic.model.Registered;
import java.io.IOException;

public class TWRegistrationGUI extends MainGUI{

	private final RegisterController controller = new RegisterController();
    protected int regId;

    @FXML protected TableView<Registered> tableView;
    @FXML private TableColumn<Registered, Integer> tcID;
    @FXML private TableColumn<Registered, String> tcName;
    @FXML private TableColumn<Registered, String> tcEmail;
    @FXML private TableColumn<Registered, String> tcPwd;
    @FXML private TableColumn<Registered, String> tcRole;
    @FXML private TableColumn<Registered, String> tcAddress;

    @FXML private void getSelected() throws IOException {
        int index;
        index = tableView.getSelectionModel().getSelectedIndex();
        if(index<=-1)return ;
        regId = tcID.getCellData(index);
        UserBean bean = RegisteredBean(tcName.getCellData(index),tcEmail.getCellData(index),tcPwd.getCellData(index),tcRole.getCellData(index),tcAddress.getCellData(index));
        FXMLLoader loader = view.loader("TWDetail");
        Parent root = loader.load();
        TWDetailGUI detail = loader.getController();
        detail.setUp(bean);
        border.setRight(root);
    }

    protected void setUpRegistration(String address) {
        tcID.setCellValueFactory(new PropertyValueFactory<>("Id"));
    	tcName.setCellValueFactory(new PropertyValueFactory<>("Name"));
    	tcEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
    	tcPwd.setCellValueFactory(new PropertyValueFactory<>("Password"));
    	tcRole.setCellValueFactory(new PropertyValueFactory<>("Role"));
    	tcAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
    	try {
	    	ObservableList<Registered> list = controller.loadRegistration(address);
            tableView.setItems(list);
    	}catch(Exception e) {
    		System.out.println("No");
    	}
    }

    private UserBean RegisteredBean(String name,String email, String password,String role, String address) {
        UserBean user = new UserBean();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setAddress(address);
        return user;
    }
}
