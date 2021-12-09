package logic.model;

import logic.engineeringclasses.exception.PatternException;

import java.util.List;

public class Administrator extends User {

	private List<Request> list;
	//organize meeting
	
	public Administrator(String userID, String name, String email, String password,String cc,List<Request> list)throws PatternException{
		super( userID,  name,  email,  password, cc);
		this.setListRequest(list);
	}
	
	public List<Request> getListRequest(){
		return this.list;
	}
	
	public void setListRequest(List<Request> list) {
		this.list = list;
	}

}
