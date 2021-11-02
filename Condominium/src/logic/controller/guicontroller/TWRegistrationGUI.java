package logic.controller.guicontroller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import logic.engineeringclasses.bean.UserBean;
import logic.controller.applicationcontroller.RegisterController;
import logic.model.Registered;
import logic.model.Role;

public class TWRegistrationGUI {

	private final RegisterController controller = new RegisterController();
    private final AlertGUI alert = new AlertGUI();
    private String address;
    private int Id;

    @FXML
    private TableView<Registered> tableView;
    @FXML
    private VBox invBox;
    @FXML
    private TableColumn<Registered, Integer> twID;
    @FXML
    private TableColumn<Registered, String> twName;
    @FXML
    private TableColumn<Registered, String> twEmail;
    @FXML
    private TableColumn<Registered, String> twPwd;
    @FXML
    private TableColumn<Registered, String> twRole;
    @FXML
    private TableColumn<Registered, String> twAddress;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;
    @FXML
    private TextField tfRole;
    @FXML
    private TextField tfAddress;
    @FXML
    void btnAddClick() {
        if(!tfName.getText().isEmpty()){
           switch(Role.valueOf(tfRole.getText())){
               case RESIDENT:
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("ApartmentRegistrationGUI"));
                   break;
               case OWNER:
                   break;
           }
           addUser();
        }
    }
    @FXML
    void btnRemoveClick() {
        if(!tfName.getText().isEmpty()&& alert.alertConfirm("Title","Are you sure?","")){
            controller.removeRegistered(this.Id);
            setUpRegistration(this.address);
        }
    }   
    @FXML
    public void getSelected() {
    	int index;
    	index = tableView.getSelectionModel().getSelectedIndex();   	
    	if(index<=-1)return ;
        this.Id = twID.getCellData(index);
        System.out.println(Id);
    	tfName.setText(twName.getCellData(index));
    	tfEmail.setText(twEmail.getCellData(index));
    	tfPassword.setText(twPwd.getCellData(index));
    	tfRole.setText(twRole.getCellData(index));
    	tfAddress.setText(twAddress.getCellData(index));
        invBox.setVisible(true);
    }
    
    public void setUpRegistration(String address) {
        this.address = address;
        twID.setCellValueFactory(new PropertyValueFactory<>("Id"));
    	twName.setCellValueFactory(new PropertyValueFactory<>("Name"));
    	twEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
    	twPwd.setCellValueFactory(new PropertyValueFactory<>("Password"));
    	twRole.setCellValueFactory(new PropertyValueFactory<>("Role"));
    	twAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
    	try {
	    	ObservableList<Registered> list = controller.loadRegistration(address);
            tableView.setItems(list);
    	}catch(Exception e) {
    		System.out.println("NOOOOOOOOO");
    	}
    }

    public UserBean RegisteredBean(String name,String email, String password,String role, String address) {
        UserBean user = new UserBean();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setAddress(address);
        return user;
    }
    private void addUser(){
        UserBean bean = RegisteredBean(tfName.getText(),tfEmail.getText(),tfPassword.getText(),tfRole.getText(),tfAddress.getText());
        controller.addRegistered(bean);
        alert.alertInfo("","User successfully added","");
        controller.removeRegistered(this.Id);
        setUpRegistration(this.address);
    }
}
