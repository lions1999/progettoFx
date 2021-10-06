package logic.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import logic.model.Administrator;
import logic.model.Owner;
import logic.model.Post;
import logic.model.Request;
import logic.model.Resident;
import logic.controller.exception.PatternException;
import logic.dao.queries.SimpleQueries;
import logic.model.Role;
import logic.model.User;
import logic.model.UserSingleton;

public class SqlDAO {
				
	UserSingleton sg = UserSingleton.getInstance();
	private static final String URL = "jdbc:mysql://localhost:3306/condominium_db";
	private static final String USER = "condominium";
	private static final String PASSWORD = "ispw2021";
	
	private PreparedStatement pstmt;
	private Statement stmt;
	private Connection conn;
	
	private String lastId;
	private Role role;
	private String roleId;
	private String usrName;
	private String getID;
	private String pwd;
	private String condominium;
	
	public SqlDAO() {
		this.stmt = null;
		this.conn = null;
		this.pstmt = null;
	}
	
	private void connect() throws SQLException 	{
		 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		 conn = DriverManager.getConnection(URL,USER,PASSWORD);
		 stmt = conn.createStatement();
	}
	
	private PreparedStatement prepConnect(String sql) throws SQLException{
		 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		 conn = DriverManager.getConnection(URL,USER,PASSWORD);
		 return conn.prepareStatement(sql);
	}
	
	private void disconnect() throws SQLException{
        if (stmt != null)
            stmt.close();
        if (conn != null)
            conn.close();
	}
	
	public Administrator loadAdminbyID(String id) throws SQLException,PatternException{
		Administrator admin = null;
		try {
			connect();
			ResultSet rs = SimpleQueries.loadUserByID(stmt,id);
			if(rs.next()) {
				String userId = id;
				String userName = rs.getString("user_name");
				String userEmail = rs.getString("user_email");
				String userPwd = rs.getString("user_pwd");
				String userCc = rs.getString("user_cc");
				admin = new Administrator(userId,userName,userEmail,userPwd,userCc,null);
				//checkListRequest(userCc);
            }									
		} finally {
			disconnect();
		}
		return admin;
	}
	
	public Owner loadOwnerByID(String id) throws SQLException,PatternException {
		Owner owner = null;
		try {
			connect();
			ResultSet rs = SimpleQueries.loadUserByID(stmt,id);
			if(rs.next()) {
				String userId = id;
				String userName = rs.getString("user_name");
				String userEmail = rs.getString("user_email");
				String userPwd = rs.getString("user_pwd");
				String userCc = rs.getString("user_cc");
				owner = new Owner(userId,userName,userEmail,userPwd,userCc);
            }									
		} finally {
			disconnect();
		}
		return owner;
	}

	public Resident loadResidentByID(String id) throws SQLException,PatternException{
		Resident resident = null;
		try {
			connect();
			ResultSet rs = SimpleQueries.loadUserByID(stmt,id);
			if(rs.next()) {
				String userId = id;
				String userName = rs.getString("user_name");
				String userEmail = rs.getString("user_email");
				String userPwd = rs.getString("user_pwd");
				String userCc = rs.getString("user_cc");
				resident = new Resident(userId,userName,userEmail,userPwd,userCc);
            }									
		} finally {
			disconnect();
		}
		return resident;
	}
	
		
	public String checkUserID(String email,String condominiumCode) throws SQLException{
		 try {        	
	        	connect();           
	            ResultSet rs = SimpleQueries.selectUserID(stmt, email,condominiumCode);           
	            if(rs.next()) {
	            	this.getID = rs.getString("user_id");
	            }
	        } finally {       	
	        	disconnect();
	        }
	        return this.getID;
	}
		
	public  String checkLogin(String email,String condominiumCode) throws SQLException {		                
        try {        	
        	connect();           
            ResultSet rs = SimpleQueries.selectLogin(stmt, email, condominiumCode);           
            if(rs.next()) {
            	this.pwd = rs.getString("user_pwd");
            	//return true;
            }
        } finally {       	
        	disconnect();
        }
        return this.pwd;
	}
	
	public boolean checkRegistration(String email,String condominiumCode) throws SQLException {		                
        try {        	
        	connect();           
            ResultSet rs = SimpleQueries.selectRegistration(stmt, email, condominiumCode);           
            if(rs.next()) {
            	return false;
            }
        } finally {       	
        	disconnect();
        }
        return true;
	}
		
	public  Role checkRole(String id) throws SQLException {				
        try {
        	
        	connect();            
            ResultSet rs = SimpleQueries.selectRole(stmt,id);                                    
            if(rs.next()) {
            	this.role = Role.valueOf(rs.getString("role_name"));
            }            
        } finally {
        		disconnect();
        }
        return this.role;
	}
	
	public String checkRoleId(String id) throws SQLException{
		try {
        	connect();            
            ResultSet rs = SimpleQueries.selectRoleId(stmt,id);                                    
            if(rs.next()) {
            	this.roleId = rs.getString("user_role");
            }            
        } finally {
        		disconnect();
        }
        return this.roleId;
	}
		
	public  String checkCondominium(String condCode) throws SQLException {		       
		try {    	
			connect();         
			ResultSet rs = SimpleQueries.selectCondominiumList(stmt, condCode);                        
			if(rs.next()) {
				this.condominium = rs.getString("Via");
			}        
	    } finally {    	
	            disconnect();
	    }
	    	return this.condominium;
		}

	public String checkNameByID(String id)throws SQLException {
		try {    	
			connect();         
			ResultSet rs = SimpleQueries.selectNameByID(stmt, id);                        
			if(rs.next()) {
				this.usrName = rs.getString("user_name");
			}        
	    } finally {    	
	            disconnect();
	    }
		return this.usrName;
	}
	
	public List<Post> checkListPost(String codiceCondominio)throws SQLException{
		List<Post> list = new ArrayList<>();
		Post post = null;
		ResultSet rs = null;
		try {
			connect();
			rs = SimpleQueries.selectListPost(stmt,codiceCondominio);
			while(rs.next()) {	
				String postId = rs.getString("post_id");
				String postUsr = rs.getString("post_usr");
				String postText = rs.getString("post_txt");
				InputStream postImg = rs.getBinaryStream("post_img");			
				post = new Post(postId,checkNameByID(postUsr),postText,postImg);
				list.add(post);				
			}
		} finally {
			disconnect();
		}   
		return list;
	}
	
	public List<Request> checkListRequest(String codiceCondominio)throws SQLException{
		List<Request> list = new ArrayList<>();
		Request req = null;
		ResultSet rs = null;
		try {
			connect();
			rs = SimpleQueries.selectListRequest(stmt,codiceCondominio);
			while(rs.next()) {	
				String reqId = rs.getString("req_id");
				String reqUsr = rs.getString("req_usr");
				String reqText = rs.getString("req_reason");		
				req = new Request(reqId,checkNameByID(reqUsr),reqText,0);
				list.add(req);				
			}
		} finally {
			disconnect();
		}   
		return list;
	}
	
	public void addPost(String usrId,String txt,File file,String cc) throws SQLException,IOException{
		try (InputStream input = 
				new FileInputStream(file)){
				String sql = "INSERT INTO posts (post_id,post_usr, post_cc, post_txt, post_img) VALUES (?, ?, ?, ?, ?)";				
				pstmt = prepConnect(sql);
				pstmt.setString(1, loadLatestId("posts","post_id"));
				pstmt.setString(2, usrId);
				pstmt.setString(3, cc);
				pstmt.setString(4, txt);
				pstmt.setBinaryStream(5, input, (int) file.length());		
				pstmt.executeUpdate();
			
		} finally {
			disconnect();
		}
	}

	public void deletePost(String postId)throws SQLException {
		try{
			connect();
			String sql= "DELETE FROM posts WHERE post_id='"+postId+"'";                     
			stmt.executeUpdate(sql);
	    } finally {    	
	            disconnect();
	    }		
	}

	public void removeRequest(String reqId) throws SQLException{
		try{
			connect();
			String sql= "DELETE FROM request WHERE req_id='"+reqId+"'";    
			System.out.println(sql);
			stmt.executeUpdate(sql);
	    } finally {    	
	            disconnect();
	    }		
	}

	public void addRegistrationUser(User user,String roleId) throws SQLException{
		try{
			String sql= "INSERT INTO registration (reg_id,reg_name, reg_email, reg_pwd, reg_role, reg_cc) VALUES (?,?,?,?,?,?)";    
			System.out.println(sql);
			pstmt = prepConnect(sql);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, roleId);
			pstmt.setString(6, user.getCode());				
			pstmt.executeUpdate();
	    } finally {    	
	            disconnect();
	    }		
	}

	public String loadLatestId(String table,String column) throws SQLException{
		ResultSet rs = null;
		try{
			connect();
			rs = SimpleQueries.selectLastId(stmt,table,column);
			if(rs.next()) {
				this.lastId = rs.getString(column);
				int id = Integer.parseInt(lastId);
				this.lastId = Integer.toString(++id);
			}        		
	    } finally {    	
	            disconnect();
	    }	
		return this.lastId;
	}
	
	public String checkRoleByName(String role) throws SQLException{
		ResultSet rs = null;
		try {
			connect();
			rs = SimpleQueries.selectRoleByName(stmt,role);
			if(rs.next()) {
				this.roleId = rs.getString("role_id");
			}			
		} finally {
			disconnect();
		}
		return this.roleId;
	}

	public boolean checkCondominiumCode(String condominiumCode) throws SQLException{
		ResultSet rs = null;
		try {
			connect();
			rs = SimpleQueries.selectCondominiumCode(stmt,condominiumCode);
			if(rs.next()) {
				return true;
			}			
		} finally {
			disconnect();
		}	
		return false;
	}
	
	public boolean checkEmail(String condominiumCode) throws SQLException{
		ResultSet rs = null;
		try {
			connect();
			rs = SimpleQueries.selectEmail(stmt,condominiumCode);
			if(rs.next()) {
				return true;
			}			
		} finally {
			disconnect();
		}	
		return false;
	}

}
	
