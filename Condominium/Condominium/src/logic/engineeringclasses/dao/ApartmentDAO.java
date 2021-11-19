package logic.engineeringclasses.dao;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.engineeringclasses.query.ApartmentQuery;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApartmentDAO extends SqlDAO{

//    public ObservableList<Apartment> loadApartmentResident(String address) throws SQLException {
//        ObservableList<Apartment> list = FXCollections.observableArrayList();
//        ResultSet rs;
//        try {
//            connect();
//            rs = ApartmentQuery.selectApartmentResident(stmt,address);
//            while(rs.next()) {
//                list.add(new Apartment(rs.getString("apt_name"), rs.getString("apt_addr"),rs.getString("apt_own"),rs.getString("apt_res"),rs.getString("apt_fee")));
//            }
//        } finally {
//            disconnect();
//        }
//        return list;
//    }

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

    public void addResident(String apartment) throws SQLException{
        //TODO IMPLEMENTS UPDATE
        try{
            connect();
//            String sql = "UPDATE apartment SET apt_res VALUES (?) where apt_addr";
//            preset = prepConnect(sql);
//            preset.setString(1, loadLatestId("users","user_id"));
//            preset.execute();
        }finally{
            disconnect();
        }
    }
}
