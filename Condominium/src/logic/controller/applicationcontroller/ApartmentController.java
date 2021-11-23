package logic.controller.applicationcontroller;

import javafx.collections.ObservableList;
import logic.engineeringclasses.dao.ApartmentDAO;

import java.sql.SQLException;

public class ApartmentController {

    private final ApartmentDAO dao = new ApartmentDAO();

    public ObservableList<String> loadApartmentResident(String address) throws SQLException {
        return dao.loadApartmentResident(address);
    }

    public ObservableList<String> loadApartmentOwner(String address) throws SQLException {
        return dao.loadApartmentOwner(address);
    }

    public int loadApartmentId(String apartment,String address) throws SQLException{
        return dao.loadApartmentId(apartment,address);
    }

    public void addResident(String apartment,String address) {
        try{
            dao.addResident(apartment,address);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


}
