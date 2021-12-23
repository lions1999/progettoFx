package logic.controller.applicationcontroller;

import javafx.collections.ObservableList;
import logic.engineeringclasses.bean.UserBean;
import logic.engineeringclasses.dao.CondominiumDAO;
import logic.engineeringclasses.dao.UserDAO;
import logic.engineeringclasses.exception.InputException;
import logic.model.Administrator;
import logic.model.Owner;
import logic.model.Resident;
import logic.model.UserSingleton;

import java.sql.SQLException;

public class LoginController {

	private final CondominiumDAO cond = new CondominiumDAO();
	private final UserDAO login = new UserDAO();
	private static final UserSingleton sg = UserSingleton.getInstance();
	private int typError;
	
	public int login(UserBean bean){
		try {
			if (checkBean(bean)) {			
				sg.setUserID(login.checkUserID(bean.getUsrEmail(), bean.getUsrAddr()));
				sg.setAddress(bean.getUsrAddr());
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
		}catch(SQLException| InputException e){
			return this.typError;
		}
	}
	
	public boolean checkBean(UserBean bean) {
		this.typError = 0;
		try {
			if(bean.getUsrEmail().isEmpty() || bean.getUsrPwd().isEmpty()||bean.getUsrAddr().isEmpty()) {
				this.typError = 1;
				return false;
			}
			if(login.checkLogin( bean.getUsrEmail(),bean.getUsrAddr()).equals(bean.getUsrPwd())) {
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
		return cond.checkAddressesList();
	}
	
}