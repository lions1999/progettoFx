package logic.engineeringclasses.dao;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.engineeringclasses.query.ApartmentQuery;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApartmentDAO extends SqlDAO{

    public int loadApartmentId(String apartment,String address) throws SQLException {
        int id = 0;
        ResultSet rs;
        try{
            connect();
            rs = ApartmentQuery.selectApartmentId(stmt,apartment,address);
            if(rs.next()) {
                id = rs.getInt("apt_id");
            }
        }finally{
            disconnect();
        }
        return id;
    }

    public ObservableList<String> loadApartmentResident(String address) throws SQLException{
        ObservableList<String> list = FXCollections.observableArrayList();
        ResultSet rs;
        try{
            connect();
            rs = ApartmentQuery.selectApartmentResident(stmt,address);
            while(rs.next()) {
                list.add(rs.getString("apt_name"));
            }
        }finally{
            disconnect();
        }
        return list;
    }


    public ObservableList<String> loadApartmentOwner(String address) throws SQLException{
        ObservableList<String> list = FXCollections.observableArrayList();
		ResultSet rs;
        try{
            connect();
            rs = ApartmentQuery.selectApartmentOwner(stmt,address);
            while(rs.next()) {
                list.add(rs.getString("apt_name"));
            }
        }finally{
            disconnect();
        }
        return list;
    }
		

    public ObservableList<Apartment> checkApartments(String userId,String condAddress, String type_usr) throws SQLException{
        ObservableList<Apartment> apartments = FXCollections.observableArrayList();
        ResultSet rs;
        try{
            connect();
            rs = ApartmentQuery.selectAptInfo(stmt,userId,condAddress,type_usr);
            while(rs.next()) {
                String aptID = rs.getString("apt_name");
                String aptAdd = rs.getString("apt_addr");
                String aptOwn = rs.getString("apt_own");
                String aptRes = rs.getString("apt_res");
                String aptGas = rs.getString("apt_gas");
                String aptWtr = rs.getString("apt_water");
                String aptEne = rs.getString("apt_energy");
                String aptPrk = rs.getString("apt_parking");
                apartment = new Apartment(aptID,aptAdd,checkNameByID(aptOwn),checkNameByID(aptRes),aptGas,aptWtr,aptEne,aptPrk);
                apartments.add(apartment);
            }
        }finally{
            disconnect();
        }
        return list;
    }

    public void addResident(String apartment,String address) throws SQLException{
        try{
            connect();
            String sql = "UPDATE apartment SET apt_res=? where apt_name=? and apt_addr=?";
            preset = prepConnect(sql);
            preset.setString(1, loadLatestId("users","user_id"));
            preset.setString(2,apartment);
            preset.setString(3,address);
            preset.executeUpdate();
        }finally{
            ResultSet rs = ApartmentQuery.selectNameByID(stmt, id);
            if(rs.next()) {
                this.usrName = rs.getString("user_name");
            }
        } finally {
            disconnect();
        }
        return this.usrName;
    }

    public String checkUserAptFromNumber(String aptNumber,String condAddr, String userRequired) throws SQLException{
        try {
            connect();
            ResultSet rs = ApartmentQuery.checkApartmentFromNumber(stmt,aptNumber,condAddr,userRequired);
            if(rs.next()) {
                this.usrId = rs.getString(userRequired);
            }
        } finally {
            disconnect();
        }
    }
}