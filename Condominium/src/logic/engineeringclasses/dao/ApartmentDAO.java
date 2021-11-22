package logic.engineeringclasses.dao;

import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.engineeringclasses.query.ApartmentQuery;
import logic.model.Apartment;

import java.sql.SQLException;

public class ApartmentDAO extends SqlDAO{
    private String userEmail;
    private String usrName;
    private String usrId;

    public String checkMailById(String userId) throws SQLException {
        try {
            connect();
            ResultSet rs = ApartmentQuery.selectEmail(stmt, userId);
            if(rs.next()) {
                this.userEmail = rs.getString("user_email");
            }
        } finally {
            disconnect();
        }
        return this.userEmail;
    }

    public ObservableList<Apartment> checkApartmentsByVia(String condAddr) throws SQLException {
        ObservableList<Apartment> apartments = FXCollections.observableArrayList();
        Apartment apartment;
        ResultSet rs;
        try {
            connect();
            rs = ApartmentQuery.selectAptInfoByVia(stmt,condAddr);
            while(rs.next()) {
                String aptID = rs.getString("apt_name");
                String aptOwn = rs.getString("apt_own");
                String aptRes = rs.getString("apt_res");
                String aptGas = rs.getString("apt_gas");
                String aptWtr = rs.getString("apt_water");
                String aptEne = rs.getString("apt_energy");
                String aptPrk = rs.getString("apt_parking");
                apartment = new Apartment(aptID,condAddr,checkNameByID(aptOwn),checkNameByID(aptRes),aptGas,aptWtr,aptEne,aptPrk);
                apartments.add(apartment);
            }
        } finally {
            disconnect();
        }
        return apartments;
    }

    public ObservableList<Apartment> checkApartments(String userId) throws SQLException{
        ObservableList<Apartment> apartments = FXCollections.observableArrayList();
        Apartment apartment;
        ResultSet rs;
        try {
            connect();
            rs = ApartmentQuery.selectAptInfo(stmt,userId);
            while(rs.next()) {
                String aptID = rs.getString("apt_name");
                String aptAdd = rs.getString("apt_addr");
                String aptOwn = rs.getString("apt_own");
                String aptGas = rs.getString("apt_gas");
                String aptWtr = rs.getString("apt_water");
                String aptEne = rs.getString("apt_energy");
                String aptPrk = rs.getString("apt_parking");
                apartment = new Apartment(aptID,aptAdd,checkNameByID(aptOwn),checkNameByID(userId),aptGas,aptWtr,aptEne,aptPrk);
                apartments.add(apartment);
            }
        } finally {
            disconnect();
        }
        return apartments;
    }

    public String checkNameByID(String id)throws SQLException {
        try {
            connect();
            ResultSet rs = ApartmentQuery.selectNameByID(stmt, id);
            if(rs.next()) {
                this.usrName = rs.getString("user_name");
            }
        } finally {
            disconnect();
        }
        return this.usrName;
    }

    public String checkUserAptFromNumber(String aptNumber, String userRequired) throws SQLException{
        try {
            connect();
            ResultSet rs = ApartmentQuery.checkApartmentFromNumber(stmt,aptNumber,userRequired);
            if(rs.next()) {
                this.usrId = rs.getString(userRequired);
            }
        } finally {
            disconnect();
        }
        return this.usrId;
    }
}
