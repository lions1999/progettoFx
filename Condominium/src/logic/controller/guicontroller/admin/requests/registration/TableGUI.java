package logic.controller.guicontroller.admin.requests.registration;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.controller.applicationcontroller.RegisterController;
import logic.controller.guicontroller.general.MainGUI;
import logic.engineeringclasses.bean.RegisteredBean;
import logic.model.Registered;

import java.io.IOException;

public class TableGUI extends MainGUI {

	private final RegisterController controller = new RegisterController();

    @FXML private TableView<Registered> tableView;
    @FXML private TableColumn<Registered, String> tcID;
    @FXML private TableColumn<Registered, String> tcName;
    @FXML private TableColumn<Registered, String> tcEmail;
    @FXML private TableColumn<Registered, String> tcPwd;
    @FXML private TableColumn<Registered, String> tcRole;
    @FXML private TableColumn<Registered, String> tcAddress;
    @FXML private TableColumn<Registered, String> tcApartment;

    @FXML private void getSelected() throws IOException {
        int index;
        index = tableView.getSelectionModel().getSelectedIndex();
        if(index<=-1)return ;
        RegisteredBean bean = registeredBean(tcID.getCellData(index),tcName.getCellData(index),tcEmail.getCellData(index),tcPwd.getCellData(index),tcRole.getCellData(index),tcAddress.getCellData(index),tcApartment.getCellData(index));
        FXMLLoader loader = view.loader("RegistrationDetailTable");
        Parent root = loader.load();
        TableDetailGUI detail = loader.getController();
        detail.setUp(bean);
        border.setRight(root);
    }

    public void setUpRegistration(String address) {
        tcID.setCellValueFactory(new PropertyValueFactory<>("Id"));
    	tcName.setCellValueFactory(new PropertyValueFactory<>("Name"));
    	tcEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
    	tcPwd.setCellValueFactory(new PropertyValueFactory<>("Password"));
    	tcRole.setCellValueFactory(new PropertyValueFactory<>("Role"));
    	tcAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        tcApartment.setCellValueFactory(new PropertyValueFactory<>("Apartment"));
    	try {
	    	ObservableList<Registered> list = controller.loadRegistration(address);
            tableView.setItems(list);
    	}catch(Exception e) {
    		System.out.println("No");
    	}
    }

    private RegisteredBean registeredBean(String id,String name, String email, String password, String role, String address,String apartment) {
        RegisteredBean registered = new RegisteredBean();
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
