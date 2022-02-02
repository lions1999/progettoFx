package logic.engineeringclasses.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.engineeringclasses.query.RateQuery;
import logic.model.Rate;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RateDAO extends SqlDAO{

    public void rateResident(String userId, int Rate, String comment) throws SQLException {
        try {
            String sql = "INSERT INTO rating (rate_userId,rate,rate_comment) values (?,?,?)";
            System.out.println(sql);
            preset = prepConnect(sql);
            preset.setString(1, userId);
            preset.setInt(2, Rate);
            preset.setString(3, comment);
            preset.execute();
        } finally {
            disconnect();
        }
    }

    public ObservableList<Rate> getRatesRes(String userId) throws SQLException {
        ObservableList<Rate> rates = FXCollections.observableArrayList();
        try {
            connect();
            ResultSet rs = RateQuery.getRates(stmt,userId);
            while(rs.next()) {
                String rateId = rs.getString("rate_id");
                String rateRes = rs.getString("rate_res");
                String rateOwn = rs.getString("rate_own");
                String rateVal = rs.getString("rate_val");
                String rateTxt = rs.getString("rate_txt");
                Rate Rate = new Rate(rateId,rateRes,rateOwn,rateVal,rateTxt);
                rates.add(Rate);
            }
        }finally {
            disconnect();
        }
        return rates;
    }

}
