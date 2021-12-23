package logic.controller.applicationcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import logic.controller.guicontroller.general.registration.SelectApartmentDialogGUI;
import logic.engineeringclasses.bean.FeeBean;
import logic.engineeringclasses.bean.RegistrationBean;
import logic.engineeringclasses.bean.UserBean;
import logic.engineeringclasses.dao.CondominiumDAO;
import logic.engineeringclasses.dao.RegisterDAO;
import logic.engineeringclasses.exception.InputException;
import logic.model.Registration;
import logic.model.Role;
import logic.model.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class RegisterController{

	private final CondominiumDAO cond = new CondominiumDAO();
	private final RegisterDAO register = new RegisterDAO();
	private final ApartmentController aptController = new ApartmentController();
	private final PatternController pattern = new PatternController();
	private int typError;

	public int registration(UserBean bean){
		this.typError = 0;
		try {
			if(pattern.isName(bean.getUsrName())) {
				this.typError = 1;
				throw new InputException("Incorrect Name : "+bean.getUsrName());
			}
			if(pattern.isName(bean.getUsrSurname())) {
				this.typError = 2;
				throw new InputException("Incorrect Surname : "+bean.getUsrSurname());
			}
			if(!pattern.isEmail(bean.getUsrEmail())) {
				this.typError = 3;
				throw new InputException("Incorrect Email : "+bean.getUsrEmail());
			}
			if(!pattern.isPassword(bean.getUsrPwd())){
				this.typError = 4;
				throw new InputException("Incorrect Password");
			}
			if(!bean.getUsrPwd().equals(bean.getOkPassword())) {
				this.typError = 5;
				throw new InputException("Password Mismatch");
			}
			if(bean.getUsrRole() == null) {
				this.typError = 6;
				throw new InputException("No Role Selected");
			}
			if(bean.getUsrAddr() == null) {
				this.typError = 7;
				throw new InputException("No Address Selected");
			}
			if(checkRegistration(bean.getUsrEmail(),bean.getUsrAddr())){
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/logic/view/SelectApartmentDialog.fxml"));
				DialogPane pane = loader.load();
				SelectApartmentDialogGUI apt = loader.getController();
				ObservableList<String> list = FXCollections.observableArrayList();
				switch(Role.valueOf(bean.getUsrRole().toUpperCase())) {
					case OWNER:
						list = aptController.loadApartmentOwner(bean.getUsrAddr());
						break;
					case RESIDENT:
						list = aptController.loadApartmentResident(bean.getUsrAddr());
						break;
				}
				if(list.isEmpty()){
					this.typError = 10;
					throw new InputException("No Apartment Available");
				}else{
					apt.setUp(list,bean);
				}
				Dialog<ButtonType> dialog = new Dialog<>();
				dialog.setDialogPane(pane);
				Optional<ButtonType> btn = dialog.showAndWait();
				if(btn.isPresent() && btn.get() == ButtonType.OK){
					list = apt.getApt();
					if(list.isEmpty()){
						this.typError = 11;
						throw new InputException("No Apartment Selected");
					}else{
						for(String aptName : list){
							String name = bean.getUsrName() +" "+ bean.getUsrSurname();
							User user = new User(null,name,bean.getUsrEmail(),bean.getUsrPwd(),bean.getUsrAddr());
							register.addRegistrationUser(user,bean.getUsrRole().toUpperCase(),aptName);
						}
					}
				}else{
					this.typError = 11;
					throw new InputException("No Apartment Selected");
				}
			}
			return this.typError;
		}catch(InputException | SQLException | IOException e){
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
		return cond.checkAddressesList();
	}

	public ObservableList<Registration> loadRegistration(String address)throws SQLException{
		return register.loadRegistrationList(address);
	}

	public void addRegistered(RegistrationBean reg, FeeBean fee){
		ApartmentController aptCtrl = new ApartmentController();
		switch (Role.valueOf(reg.getRole())){
			case OWNER:
				try {
					register.addRegistered(reg);
					aptCtrl.addOwner(reg.getApartment(), reg.getAddress());
				}catch (SQLException e){
					e.printStackTrace();
				}
				break;
			case RESIDENT:
				try{
					FeeController feeCtrl = new FeeController();
					register.addRegistered(reg);
					fee.setApt(aptCtrl.loadApartmentId(reg.getApartment(),reg.getAddress()));
					feeCtrl.addFees(fee);
					aptCtrl.addResident(reg.getApartment(),reg.getAddress());
				}catch(SQLException e) {
					System.out.println("SQLException");
				}
				break;
		}

	}

	public void removeRegistered(int id) {
		try{
			register.deleteRegistered(id);
		}catch(SQLException e){
			System.out.println("SQLException");
		}
	}

	public void removeAllRegistered(String apartment) {
		try{
			register.deleteAllRegistered(apartment);
		}catch(SQLException e){
			System.out.println("SQLException");
		}
	}
}
