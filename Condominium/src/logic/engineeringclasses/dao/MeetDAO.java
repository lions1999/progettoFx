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

    public void deleteMeetRequest(int meetId) throws SQLException {
        try{
            connect();
            String sql= "DELETE FROM meeting WHERE meet_id='"+meetId+"'";
            stmt.executeUpdate(sql);
        } finally {
            disconnect();
        }
    }

    public void AddMeeting(String meet_from, String meet_addr, String meet_obj, String meet_txt) throws SQLException{
        try {
            String sql = "INSERT INTO meeting (meet_from,meet_addr,meet_obj,meet_txt) values (?,?,?,?)";
            System.out.println(sql);
            preset = prepConnect(sql);
            preset.setString(1,meet_from);
            preset.setString(2,meet_addr);
            preset.setString(3,meet_obj);
            preset.setString(4,meet_txt);
            preset.execute();
        } finally {
            disconnect();
        }
    }
}