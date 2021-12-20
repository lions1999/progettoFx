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

    public ObservableList<Rate> getRates(String userId) throws SQLException {
        ObservableList<Rate> rates = FXCollections.observableArrayList();
        try {
            connect();
            ResultSet rs = RateQuery.getRates(stmt,userId);
            while(rs.next()) {
                String ratedUser = rs.getString("rate_userId");
                String rate = rs.getString("rate");
                String rateComment = rs.getString("rate_comment");
                Rate Rate = new Rate(ratedUser,rate,rateComment);
                rates.add(Rate);
            }
        }finally {
            disconnect();
        }
        return rates;
    }

}
