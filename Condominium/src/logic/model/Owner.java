package logic.model;

import logic.engineeringclasses.exception.InputException;

public class Owner extends User{

	public Owner(String userID, String name, String email, String password,String cc)throws InputException {
		super( userID,  name,  email,  password,cc);
		// TODO Auto-generated constructor stub
	}
}