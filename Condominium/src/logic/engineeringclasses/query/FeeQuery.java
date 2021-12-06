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

    public static ResultSet loadFees(Statement stmt, int aptName) throws SQLException {
        String sql = "SELECT fee_water,fee_gas,fee_elect,fee_elevator,fee_pet,fee_wifi,fee_park,fee_admin FROM fee AS T1 LEFT JOIN apartment AS T2 ON T1.fee_apt = T2.apt_id ";
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }
}
