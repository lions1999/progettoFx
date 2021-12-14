package logic.engineeringclasses.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.engineeringclasses.query.MeetQuery;
import logic.model.MeetRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MeetDAO extends SqlDAO{

    private UserDAO userDao = new UserDAO();

    public ObservableList<MeetRequest> loadMeetList(String address) throws SQLException{
        ObservableList<MeetRequest> list = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            connect();
            rs = MeetQuery.selectMeetRequests(stmt,address);
            while(rs.next()) {
                list.add(new MeetRequest(rs.getString("meet_id"),userDao.checkNameByID(rs.getString("meet_from")),rs.getString("meet_addr"),rs.getString("meet_obj"), rs.getString("meet_txt")));
            }
        } finally {
            disconnect();
        }
        return list;
    }
}
