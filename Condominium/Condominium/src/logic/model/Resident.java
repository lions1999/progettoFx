package logic.model;

import logic.engineeringclasses.exception.PatternException;

public class Resident extends User{

	public Resident(String userID, String name, String email, String password,String cc)throws PatternException{
		super( userID,  name,  email,  password, cc);
		// TODO Auto-generated constructor stub
	}
}
