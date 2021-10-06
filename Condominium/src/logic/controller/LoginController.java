package logic.controller;

import java.sql.SQLException;
import logic.bean.UserBean;
import logic.controller.exception.PatternException;
import logic.dao.SqlDAO;
import logic.model.UserSingleton;
import logic.model.Administrator;
import logic.model.Owner;
import logic.model.Resident;

public class LoginController {
	 	
	private SqlDAO ourDb = new SqlDAO();
	private static UserSingleton sg = UserSingleton.getInstance();
	private int typError = 0;
	
	public int login(UserBean bean) throws SQLException,PatternException{
		try {
			if (checkBean(bean)) {			
				sg.setUserID(ourDb.checkUserID(bean.getEmail(), bean.getCondominiumCode()));
				sg.setCode(bean.getCondominiumCode());
				sg.setRole(ourDb.checkRole(ourDb.checkRoleId(sg.getUserID())));
					switch (sg.getRole()) {
						case ADMINISTRATOR:						
							Administrator admin = ourDb.loadAdminbyID(sg.getUserID());						
							sg.setAdministrator(admin);
							break;
						case OWNER:
							Owner owner = ourDb.loadOwnerByID(sg.getUserID());
							sg.setOwner(owner);
							break;
						case RESIDENT:
							Resident resident = ourDb.loadResidentByID(sg.getUserID());
							sg.setResident(resident);
							break;				
						default:
							break;
					}
			}
			return this.typError;
		}catch(SQLException|PatternException e){
			e.printStackTrace();
			return this.typError;
		}
	}
	
	public boolean checkBean(UserBean bean) {
		try {
			if(bean.getEmail().isEmpty()) {
				this.typError = 1;
				return false;
			}
			if(bean.getPassword().isEmpty()) {
				this.typError = 1;
				return false;
			}
			if(bean.getCondominiumCode().isEmpty()) {
				this.typError = 1;
				return false;
			}
			if(ourDb.checkLogin( bean.getEmail(),bean.getCondominiumCode()).equals(bean.getPassword())) {
				return true;
			}
			this.typError = 1;
			return false;
		}catch(SQLException e) {
			this.typError = 2;
			return false;
		}
	}
	
}




