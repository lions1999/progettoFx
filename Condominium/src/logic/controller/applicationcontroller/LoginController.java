package logic.controller.applicationcontroller;

import javafx.collections.ObservableList;
import logic.engineeringclasses.bean.UserBean;
import logic.engineeringclasses.dao.LoginDAO;
import logic.engineeringclasses.exception.PatternException;
import logic.model.Administrator;
import logic.model.Owner;
import logic.model.Resident;
import logic.model.UserSingleton;

import java.sql.SQLException;

public class LoginController {

	private final LoginDAO login = new LoginDAO();
	private static final UserSingleton sg = UserSingleton.getInstance();
	private int typError;
	
	public int login(UserBean bean){
		try {
			if (checkBean(bean)) {			
				sg.setUserID(login.checkUserID(bean.getEmail(), bean.getAddress()));
				sg.setAddress(bean.getAddress());
				sg.setRole(login.checkRole((sg.getUserID())));
					switch (sg.getRole()) {
						case ADMINISTRATOR:						
							Administrator admin = login.loadAdminID(sg.getUserID());
							sg.setAdministrator(admin);
							break;
						case OWNER:
							Owner owner = login.loadOwnerID(sg.getUserID());
							sg.setOwner(owner);
							break;
						case RESIDENT:
							Resident resident = login.loadResidentID(sg.getUserID());
							sg.setResident(resident);
							break;				
						default:
							break;
					}
			}
			return this.typError;
		}catch(SQLException|PatternException e){
			return this.typError;
		}
	}
	
	public boolean checkBean(UserBean bean) {
		this.typError = 0;
		try {
			if(bean.getEmail().isEmpty() || bean.getPassword().isEmpty()||bean.getAddress().isEmpty()) {
				this.typError = 1;
				return false;
			}
			if(login.checkLogin( bean.getEmail(),bean.getAddress()).equals(bean.getPassword())) {
				return true;
			}
			this.typError = 1;
			return false;
		}catch(NullPointerException n) {
			this.typError = 1;
			return false;
		}catch(SQLException s) {
			this.typError = 2;
			return false;
		}
	}
	
	public ObservableList<String> loadAddresses() throws SQLException{
		return login.checkAddressesList();
	}
	
}