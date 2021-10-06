package logic.controller;

import java.util.List;
import logic.dao.SqlDAO;
import logic.model.Post;
import logic.model.UserSingleton;

public class HomePageController{
	
	private static UserSingleton sg = UserSingleton.getInstance();
	private SqlDAO ourDb = new SqlDAO();
	private List<Post> posts = null;
	
	public List<Post> loadPost() {
		try{
	    	this.posts = ourDb.checkListPost(sg.getCode());
	    	
		}catch(Exception e){
			
		}
		return this.posts;
	}
	
}
	

