package logic.engineeringclasses.dao;

import logic.engineeringclasses.bean.FeeBean;
import logic.engineeringclasses.query.FeeQuery;
import logic.model.Fee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class FeeDAO extends SqlDAO{

    public Fee loadAvailableFees(String address) throws SQLException {
        Fee fee = null;
        try {
            connect();
            ResultSet rs = FeeQuery.loadAvailableFees(stmt,address);
            if(rs.next()) {
                boolean park = rs.getBoolean("con_park");
                boolean elevator = rs.getBoolean("con_elevator");
                boolean pet = rs.getBoolean("con_pet");
                boolean wifi = rs.getBoolean("con_wifi");
                fee = new Fee(park,elevator,pet,wifi);
            }
        } finally {
            disconnect();
        }
        return fee;
    }

    public Fee loadFees(int aptName,String typeFee) throws SQLException{
        Fee fee = null;
        try{
            connect();
            ResultSet rs = FeeQuery.loadFees(stmt,aptName,typeFee);
            while (rs.next()) {
                Double water = rs.getDouble("fee_water");
                Double gas = rs.getDouble("fee_gas");
                Double elect = rs.getDouble("fee_elect");
                Double admin = rs.getDouble("fee_admin");
                Double park = rs.getDouble("fee_park");
                Double elevator = rs.getDouble("fee_elevator");
                Double pet = rs.getDouble("fee_pet");
                Double wifi = rs.getDouble("fee_wifi");
                fee = new Fee(aptName, water, gas, elect, admin, park, elevator, pet, wifi);
            }
        } finally {disconnect();}
        return fee;
    }

    public void addFees(FeeBean fee) throws SQLException{
        try{
            connect();
            String sql = "INSERT INTO fee (fee_apt,fee_water,fee_gas,fee_elect,fee_admin,fee_park,fee_elevator,fee_pet,fee_wifi) VALUES (?,?,?,?,?,?,?,?,?)";
            preset = prepConnect(sql);
            System.out.println(fee);
            preset.setInt(1,fee.getApt());
            preset.setDouble(2,fee.getWater());
            preset.setDouble(3,fee.getGas());
            preset.setDouble(4,fee.getElect());
            preset.setDouble(5,fee.getAdmin());
            if(fee.getPark().equals(0.0)) preset.setNull(6, Types.NULL);
            else preset.setDouble(6,fee.getPark());
            if(fee.getElevator().equals(0.0)) preset.setNull(7, Types.NULL);
            else preset.setDouble(7,fee.getElevator());
            if(fee.getPet().equals(0.0)) preset.setNull(8, Types.NULL);
            else preset.setDouble(8,fee.getPet());
            if(fee.getWifi().equals(0.0)) preset.setNull(9, Types.NULL);
            else preset.setDouble(9,fee.getWifi());
            preset.execute();
        } finally {
            disconnect();
        }
    }
}
