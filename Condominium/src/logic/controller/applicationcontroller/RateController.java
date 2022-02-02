package logic.controller.applicationcontroller;

import javafx.collections.ObservableList;
import logic.engineeringclasses.dao.RateDAO;
import logic.model.Rate;

import java.sql.SQLException;

public class RateController {

    private final RateDAO rateDAO = new RateDAO();

    public void rateUser(String userId, int Rate, String comment) throws SQLException {
        rateDAO.rateResident(userId,Rate,comment);
    }

    public ObservableList<Rate> getRatesRes(String userId) throws SQLException {
        return rateDAO.getRatesRes(userId);
    }

}
