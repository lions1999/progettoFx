package logic.engineeringclasses.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FeeQuery {
    public static ResultSet loadAvailableFees(Statement stmt, String address) throws SQLException {
        String sql = "SELECT * FROM condominiums where con_addr='" + address +"'";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
}
