package logic.engineeringclasses.query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RateQuery {


    public static ResultSet getRates(Statement stmt, String userId) throws SQLException {
        String sql = "SELECT * FROM rating WHERE rate_userId = '"+userId+"' ";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
}
