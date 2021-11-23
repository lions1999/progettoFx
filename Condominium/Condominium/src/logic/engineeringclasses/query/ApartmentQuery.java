package logic.engineeringclasses.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ApartmentQuery {
    public static ResultSet selectApartmentResident(Statement stmt, String address) throws SQLException {
        String sql = "SELECT * FROM apartment where apt_addr='"+address+"' and apt_res is NULL and apt_own is not NULL";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectApartmentOwner(Statement stmt, String address) throws SQLException{
        String sql = "SELECT * FROM apartment where apt_addr='"+address+"' and  apt_own is NULL";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static ResultSet selectApartmentId(Statement stmt, String apartment, String address) throws SQLException{
        String sql = "SELECT apt_id FROM apartment where apt_name='"+apartment+"' and apt_addr='"+address+"'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
}
