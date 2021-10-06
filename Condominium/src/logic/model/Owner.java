package logic.model;

import logic.controller.exception.PatternException;

public class Owner extends User{

	public Owner(String userID, String name, String email, String password,String cc)throws PatternException{
		super( userID,  name,  email,  password,cc);
		// TODO Auto-generated constructor stub
	}
}