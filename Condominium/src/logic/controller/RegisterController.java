package logic.controller;

import java.sql.SQLException;
import logic.bean.UserBean;
import logic.controller.exception.PatternException;
import logic.dao.SqlDAO;
import logic.model.User;

public class RegisterController{
	
	private SqlDAO ourDb = new SqlDAO();
	private PatternController pattern = new PatternController();
	private int typError = 0;
	
	public int registration(UserBean bean) throws SQLException,PatternException{		
		try {	
			if(!pattern.isFullname(bean.getName())) {
				this.typError = 1;
				throw new PatternException("Incorrect Name : "+bean.getName());				
			}
			if(!pattern.isFullname(bean.getSurname())) {
				this.typError = 2;
				throw new PatternException("Incorrect Surname : "+bean.getSurname());				
			}
			if(!pattern.isEmail(bean.getEmail())) {
				this.typError = 3;
				throw new PatternException("Incorrect Email : "+bean.getEmail());
			}
			if(pattern.isPassword(bean.getPassword()) || bean.getPassword().length()>20 || bean.getPassword().length()<4){
				this.typError = 4;
				throw new PatternException("Incorrect Password");
			}
			if(!bean.getPassword().equals(bean.getOkPassword())) {
				this.typError = 5;
				throw new PatternException("Password Mismatch");
			}
			if(bean.getRole().equals("No Role Selected")) {
				this.typError = 6;
				throw new PatternException("No Role Selected");
			}
			if(checkCode(bean.getCondominiumCode()) && checkRegistration(bean.getEmail(),bean.getCondominiumCode())){
				String name = bean.getName() +" "+ bean.getSurname();
				User user = new User(ourDb.loadLatestId("registration","reg_id"),name,bean.getEmail(),bean.getPassword(),bean.getCondominiumCode());				
				ourDb.addRegistrationUser(user,ourDb.checkRoleByName(bean.getRole()));
			}
			return this.typError;
		}catch(PatternException|SQLException e){		
			e.printStackTrace();
			return this.typError;			
		}
	}  
	
	private boolean checkCode(String cc) {
		try {
			if( ourDb.checkCondominiumCode(cc)) {
				return true;
			}
			this.typError = 7;
			return false;
		}catch(SQLException e) {
			this.typError = 8;
			return false;
		}
	}
	
	private boolean checkRegistration(String email, String cc) throws SQLException{
		if(ourDb.checkRegistration(email, cc)) {
			return true;
		}
		this.typError = 9;
		return false;
	}
	
	
}
