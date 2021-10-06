package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import javafx.stage.Stage;
import logic.controller.PatternController;
//
//import logic.model.*;
//import logic.controller.HomeController;
import logic.dao.*;
import logic.view.LoginView;
import logic.view.PostView;
import logic.view.RegisterView;

public class AllTests {

//	@Test
//	public void TestCondominiumDAO() throws Exception{
//		CondominiumDAO dao = new CondominiumDAO();	
//		System.out.println(dao.retreiveByCondominumList(12345));
//	}
//	
	@Test
	public void TestLoginDAO() throws Exception{
		SqlDAO log = new SqlDAO();		
		System.out.println(log.checkLogin("2","67890"));
	}
//	
//	@Test
//	public void TestLoginRole() throws Exception{
//		SqlDAO log = new SqlDAO();	
//		System.out.println(log.checkRole(1));
//	}
//	
//
//	@Test
//	public void TestImagePostDAO()throws  Exception{
//		SqlDAO log = new SqlDAO();		
//		List<String> list = new ArrayList<>();
//		list = log.checkListPost("67890");
//		//(list.get(0));
//		//System.out.println(list);
//		for (int i=0;i<=list.size();i++) {
//			System.out.println(log.checkPost(list.get(i)).getUser());
//		}
//	}
//	
//	@Test
//	public void TestImagePostController()throws  Exception{
//		PostDAO post = new PostDAO();	
//		post.checkImagePost(1, 12345);
//	}
//
//	@Test
//	public void TestloadUserId()throws  Exception{
//		SqlDAO log = new SqlDAO();		
//		log.loadAdminbyID("1");
//	}
//	@Test
//	public void TestLoadListPost() throws  Exception{
//		SqlDAO log = new SqlDAO();		
//		System.out.println( log.checkListPost("67890"));
//	}
//	@Test
//	public void TestLoadLastid() throws  Exception{
//		SqlDAO log = new SqlDAO();		
//		System.out.println( log.loadLatestId("registration","reg_id"));
//		System.out.println( log.loadLatestId("users","user_id"));
//	}	
//	@Test
//	public void TestRoleByName() throws  Exception{
//		SqlDAO log = new SqlDAO();		
//		System.out.println( log.checkRoleByName("Resident"));
//	
//	}
//	@Test
//	public void TestPattern() throws  Exception{
//		PatternController controller = new PatternController();
//		System.out.println(controller.isPassword("sasca sasa"));
//	}
//	@Test
//	public void TestPattern() throws  Exception{
//		SqlDAO controller = new SqlDAO();
//		System.out.println(controller.checkRegistration(" ","6789"));
//	}
}
