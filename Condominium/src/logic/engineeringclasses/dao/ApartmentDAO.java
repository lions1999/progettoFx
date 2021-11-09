//package logic.engineeringclasses.dao;
//
//import com.mysql.jdbc.ResultSet;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import logic.model.Apartment;
//
//import java.sql.SQLException;
//
//public class ApartmentDAO extends SqlDAO{
//    public String loadMailById(String userId) throws SQLException {
//        try {
//            connect();
//            ResultSet rs = SimpleQueries.selectEmail(stmt, userId);
//            if(rs.next()) {
//                this.userEmail = rs.getString("user_email");
//            }
//        } finally {
//            disconnect();
//        }
//        return this.userEmail;
//    }
//
//    public ObservableList<Apartment> checkApartmentsByCc(String condominiumCode) throws SQLException {
//        ObservableList<Apartment> apartments = FXCollections.observableArrayList();
//        Apartment apartment = null;
//        ResultSet rs = null;
//        try {
//            connect();
//            rs = SimpleQueries.selectAptInfoByCc(stmt,condominiumCode);
//            while(rs.next()) {
//                String aptID = rs.getString("apt_id");
//                String aptOwn = rs.getString("apt_owner");
//                String aptRes = rs.getString("apt_resident");
//                String aptWater = rs.getString("apt_water");
//                String aptHeat = rs.getString("apt_heating");
//                String aptMaint = rs.getString("apt_energy");
//                apartment = new Apartment(aptID,aptOwn,aptRes,aptHeat,aptWater,aptMaint, sg.getCode());
//                apartments.add(apartment);
//            }
//        } finally {
//            disconnect();
//        }
//        return apartments;
//    }
//}
