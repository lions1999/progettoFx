package logic.engineeringclasses.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ApartmentQuery {
    public static ResultSet selectEmail(Statement stmt, String userID) throws SQLException {
        String sql= "SELECT user_email  from users where user_id ='"+userID+"'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectAptInfoByVia(Statement stmt, String  condAddr) throws SQLException {
        String sql = "SELECT * from apartment where apt_addr='"+condAddr+"'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectAptInfo(Statement stmt, String  userId,String condAddr,String type_usr) throws SQLException {
        String sql = "SELECT * from apartment where "+type_usr+"='"+userId+"' and apt_addr='"+condAddr+"'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectNameByID(Statement stmt, String id)  throws SQLException {
        String sql= "SELECT user_name FROM users WHERE user_id='"+id+"'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet checkApartmentFromNumber(Statement stmt, String condAddr, String aptNumber, String userRequired) throws SQLException {
        String sql= "SELECT "+userRequired+" FROM apartment WHERE apt_name='"+aptNumber+"' and apt_addr='"+condAddr+"'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
}
