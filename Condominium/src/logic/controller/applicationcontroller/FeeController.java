package logic.controller.applicationcontroller;

import logic.engineeringclasses.bean.FeeBean;
import logic.engineeringclasses.dao.FeeDAO;
import logic.model.Fee;

import java.sql.SQLException;

public class FeeController {

    private FeeDAO fee = new FeeDAO();

    public Fee setUpFees(String address) throws SQLException {
        return fee.loadAvailableFees(address);
    }

    public void addFees(FeeBean bean) {
        try{
            fee.addFees(bean);
        }catch(SQLException e){
            System.out.println("SQLException");
        }
    }

    public Fee loadFees(String loadApartmentId,String typeFee) throws SQLException {
        return fee.loadFees(loadApartmentId,typeFee);
    }

}
