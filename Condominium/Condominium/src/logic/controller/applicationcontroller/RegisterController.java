package logic.controller.applicationcontroller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import logic.controller.guicontroller.ApartmentDialogGUI;
import logic.engineeringclasses.bean.FeeBean;
import logic.engineeringclasses.bean.RegisteredBean;
import logic.engineeringclasses.bean.UserBean;
import logic.engineeringclasses.dao.LoginDAO;
import logic.engineeringclasses.dao.RegisterDAO;
import logic.engineeringclasses.exception.PatternException;
import logic.model.Registered;
import logic.model.Role;
import logic.model.User;

public class RegisterController{

	private final RegisterDAO register = new RegisterDAO();
	private final LoginDAO login = new LoginDAO();
	private final ApartmentController aptController = new ApartmentController();
	private final PatternController pattern = new PatternController();
	private int typError;

	public int registration(UserBean bean){
		this.typError = 0;
		try {
			if(pattern.isName(bean.getName())) {
				this.typError = 1;
				throw new PatternException("Incorrect Name : "+bean.getName());
			}
			if(pattern.isName(bean.getSurname())) {
				this.typError = 2;
				throw new PatternException("Incorrect Surname : "+bean.getSurname());
			}
			if(!pattern.isEmail(bean.getEmail())) {
				this.typError = 3;
				throw new PatternException("Incorrect Email : "+bean.getEmail());
			}
			if(!pattern.isPassword(bean.getPassword())){
				this.typError = 4;
				throw new PatternException("Incorrect Password");
			}
			if(!bean.getPassword().equals(bean.getOkPassword())) {
				this.typError = 5;
				throw new PatternException("Password Mismatch");
			}
			if(bean.getRole() == null) {
				this.typError = 6;
				throw new PatternException("No Role Selected");
			}
			if(bean.getAddress() == null) {
				this.typError = 7;
				throw new PatternException("No Address Selected");
			}
			if(checkRegistration(bean.getEmail(),bean.getAddress())){
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/logic/view/ApartmentDialog.fxml"));
				DialogPane pane = loader.load();
				ApartmentDialogGUI apt = loader.getController();
				switch(Role.valueOf(bean.getRole().toUpperCase())){
					case OWNER:
						apt.setUp(aptController.loadApartmentOwner(bean.getAddress()),bean.getRole(),bean.getAddress());
					case RESIDENT:
						apt.setUp(aptController.loadApartmentResident(bean.getAddress()),bean.getRole(),bean.getAddress());
					default:
						Dialog<ButtonType> dialog = new Dialog<>();
						dialog.setDialogPane(pane);
						Optional<ButtonType> btn = dialog.showAndWait();
						if(btn.isPresent() && !apt.getApt().equals("Available Apartments")){
							String name = bean.getName() +" "+ bean.getSurname();
							User user = new User(null,name,bean.getEmail(),bean.getPassword(),bean.getAddress());
							register.addRegistrationUser(user,bean.getRole().toUpperCase(),apt.getApt());
						}else{
							this.typError = 10;
							throw new PatternException("No Apartment Selected");
						}
						break;
				}
			}
			return this.typError;
		}catch(PatternException | SQLException | IOException e){
			return this.typError;
		}
	}

	private boolean checkRegistration(String email, String address) throws SQLException{
		try{
			if(register.checkRegistration(email, address)) {
				return true;
			}
			this.typError = 9;
			return false;
		}catch(SQLException e) {
			this.typError = 8;
			return false;
		}
	}

	public ObservableList<String> loadAddresses() throws SQLException{
		return login.checkAddressesList();
	}

	public ObservableList<Registered> loadRegistration(String address)throws SQLException{
		return register.loadRegistrationList(address);
	}

	public void addRegistered(RegisteredBean reg, FeeBean fee){
		try{
			FeeController feeCtrl = new FeeController();
			ApartmentController aptCtrl = new ApartmentController();
			register.addRegistered(reg);
			fee.setApt(aptCtrl.loadApartmentId(reg.getApartment(),reg.getAddress()));
			feeCtrl.addFees(fee);
			aptCtrl.addResident(reg.getApartment(),reg.getAddress());
		}catch(Exception e) {
			System.out.println("SQLException");
		}
	}

	public void removeRegistered(int id) {
		try{
			register.deleteRegistered(id);
		}catch(SQLException e){
			System.out.println("SQLException");
		}
	}
}
