package logic.controller.guicontroller.admin.requests.registration;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.controller.applicationcontroller.ViewController;
import logic.engineeringclasses.bean.RegistrationBean;
import logic.controller.applicationcontroller.RegisterController;
import logic.model.Registration;
import java.io.IOException;

import static logic.controller.guicontroller.general.MainGUI.border;

public class RegistrationTableGUI {

	protected final RegisterController controller = new RegisterController();
    protected final ViewController view = new ViewController();

    @FXML private TableView<Registration> tableView;
    @FXML private TableColumn<Registration, String> tcID;
    @FXML private TableColumn<Registration, String> tcName;
    @FXML private TableColumn<Registration, String> tcEmail;
    @FXML private TableColumn<Registration, String> tcPwd;
    @FXML private TableColumn<Registration, String> tcRole;
    @FXML private TableColumn<Registration, String> tcAddress;
    @FXML private TableColumn<Registration, String> tcApartment;

    @FXML private void getSelected() throws IOException {
        int index;
        index = tableView.getSelectionModel().getSelectedIndex();
        if(index<=-1)return;
        RegistrationBean bean = registrationBean(tcID.getCellData(index),tcName.getCellData(index),tcEmail.getCellData(index),tcPwd.getCellData(index),tcRole.getCellData(index),tcAddress.getCellData(index),tcApartment.getCellData(index));
        FXMLLoader loader = view.loader("RegistrationDetailTable");
        Parent root = loader.load();
        RegistrationTableDetailGUI detail = loader.getController();
        detail.setUp(bean);
        border.setRight(root);
    }

    public void setUpRegistration(String address) {
        tcID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcPwd.setCellValueFactory(new PropertyValueFactory<>("password"));
        tcRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tcApartment.setCellValueFactory(new PropertyValueFactory<>("apartment"));
        try {
	    	ObservableList<Registration> list = controller.loadRegistration(address);
            tableView.setItems(list);
    	}catch(Exception e) {
    		System.out.println("No");
    	}
    }

    private RegistrationBean registrationBean(String id, String name, String email, String password, String role, String address, String apartment) {
        RegistrationBean registered = new RegistrationBean();
        registered.setID(id);
        registered.setName(name);
        registered.setEmail(email);
        registered.setPassword(password);
        registered.setRole(role);
        registered.setAddress(address);
        registered.setApartment(apartment);
        return registered;
    }
}
