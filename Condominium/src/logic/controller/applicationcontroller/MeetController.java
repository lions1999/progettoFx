package logic.controller.applicationcontroller;

import javafx.collections.ObservableList;
import logic.engineeringclasses.dao.MeetDAO;
import logic.model.MeetRequest;

import java.sql.SQLException;

public class MeetController {

    private final MeetDAO meet = new MeetDAO();
    private UserController usrCtrl = new UserController();

    public ObservableList<MeetRequest> loadMeetRequest(String address) throws SQLException {
        return meet.loadMeetList(address);
    }

    public void removeMeetRequest(int meetId) {
        try{
            meet.deleteMeetRequest(meetId);
        }catch(SQLException e){
            System.out.println("SQLException");
        }
    }

    public ObservableList<String> loadMailList(String addr) throws SQLException {
        return usrCtrl.loadMailList(addr);
    }
}
