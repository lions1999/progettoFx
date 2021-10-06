package logic.dao.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SimpleQueries {
	
	public static ResultSet selectListPost(Statement stmt,String condominiumCode) throws SQLException{
		String sql="SELECT post_id,post_usr,post_txt,post_img FROM posts where post_id IN (SELECT  post_id FROM `posts` WHERE post_cc='"+condominiumCode+"')";
		System.out.println(sql);
		return stmt.executeQuery(sql);
	}
	
	public static ResultSet selectNameByID(Statement stmt, String id)  throws SQLException {
		String sql= "SELECT user_name FROM users WHERE user_id='"+id+"'";
		System.out.println(sql);
		return stmt.executeQuery(sql);
	}
	
	public static ResultSet loadUserByID(Statement stmt, String id)  throws SQLException {
		String sql= "SELECT user_name,user_email,user_pwd,user_role,user_cc FROM users WHERE user_id='"+id+"'";
		System.out.println(sql);
		return stmt.executeQuery(sql);
	}
	
    public static ResultSet selectUserID(Statement stmt, String email,String condominiumCode)  throws SQLException {
		String sql= "SELECT user_id from users where user_email='"+email+"'and user_cc='"+condominiumCode+"'";
		System.out.println(sql);
		return stmt.executeQuery(sql);	
    }
		
    public static ResultSet selectCondominiumList(Statement stmt, String condominiumCode) throws SQLException  {
        String sql = "SELECT * FROM condominiums where CodiceCondominio = '" + condominiumCode + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
    
    public static ResultSet selectLogin(Statement stmt, String email,String condominiumCode)  throws SQLException {
		String sql= "SELECT user_pwd from users where user_email='"+email+"'and user_cc='"+condominiumCode+"'";
		System.out.println(sql);
		return stmt.executeQuery(sql);	
    }
	
    public static ResultSet selectRole(Statement stmt,String id) throws SQLException  {
        String sql = "SELECT role_name FROM role WHERE role_id='"+id+"'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
    
    public static ResultSet selectRoleId(Statement stmt,String id) throws SQLException  {
        String sql = "SELECT user_role FROM users WHERE user_id='"+id+"'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

	public static ResultSet selectListRequest(Statement stmt,String condominiumCode) throws SQLException{
		String sql="SELECT req_id,req_usr,req_reason FROM request where req_id IN (SELECT  req_id FROM `posts` WHERE req_cc='"+condominiumCode+"')";
		System.out.println(sql);
		return stmt.executeQuery(sql);
	}

	public static ResultSet selectLastId(Statement stmt,String table,String column) throws SQLException{
		String sql="SELECT "+column+" FROM "+table+" ORDER BY "+column+" DESC LIMIT 1";
		System.out.println(sql);
		return stmt.executeQuery(sql);
	}

	public static ResultSet selectRoleByName(Statement stmt,String role) throws SQLException{
		String sql="SELECT role_id FROM role WHERE role_name='"+role+"'";
		System.out.println(sql);
		return stmt.executeQuery(sql);
	}

	public static ResultSet selectRegistration(Statement stmt, String email,String condominiumCode)  throws SQLException {
		String sql= "SELECT reg_id from registration where reg_email='"+email+"'and reg_cc='"+condominiumCode+"'";
		System.out.println(sql);
		return stmt.executeQuery(sql);	
    }

	public static ResultSet selectCondominiumCode(Statement stmt, String condominiumCode) throws SQLException{
		String sql= "SELECT condominium_id  from condominiums where condominium_id ='"+condominiumCode+"'";
		System.out.println(sql);
		return stmt.executeQuery(sql);	
	}

	public static ResultSet selectEmail(Statement stmt, String email) throws SQLException{
		String sql= "SELECT user_email  from users where user_email ='"+email+"'";
		System.out.println(sql);
		return stmt.executeQuery(sql);	
	}
    
}
