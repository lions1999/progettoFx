package logic.controller.applicationcontroller;

import javafx.collections.ObservableList;
import logic.engineeringclasses.dao.MeetDAO;
import logic.model.MeetRequest;

import java.sql.SQLException;

public class MeetController {

    private final MeetDAO meet = new MeetDAO();

    public ObservableList<MeetRequest> loadMeetRequest(String address) throws SQLException {
        return meet.loadMeetList(address);
    }
}
