package logic.controller.applicationcontroller;

import javafx.collections.ObservableList;
import logic.engineeringclasses.dao.UserDAO;
import logic.model.User;

import java.sql.SQLException;

public class UserController {

    private final UserDAO dao = new UserDAO();

    public ObservableList<User> loadUserList(String address) throws SQLException {
        return dao.loadUserList(address);
    }
}
