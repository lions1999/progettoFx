package logic.engineeringclasses.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserQuery {

    public static ResultSet selectUserID(Statement stmt, String email, String address) throws SQLException {
        String sql = "SELECT user_id FROM users where user_email='" + email + "'and user_addr='" + address + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectRole(Statement stmt, String id) throws SQLException {
        String sql = "SELECT user_role FROM users WHERE user_id='" + id + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectLogin(Statement stmt, String email, String address) throws SQLException {
        String sql = "SELECT user_pwd from users where user_email='" + email + "'and user_addr='" + address + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet loadUserByID(Statement stmt, String id) throws SQLException {
        String sql = "SELECT user_name,user_email,user_pwd,user_role,user_addr FROM users WHERE user_id='" + id + "'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectUserList(Statement stmt, String address) throws SQLException{
        String sql = "SELECT * FROM users WHERE user_addr='" + address + "' AND user_role !='ADMINISTRATOR'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
}
