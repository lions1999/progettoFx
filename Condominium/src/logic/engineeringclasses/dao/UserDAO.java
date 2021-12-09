package logic.engineeringclasses.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.engineeringclasses.exception.PatternException;
import logic.engineeringclasses.query.CondominiumQuery;
import logic.engineeringclasses.query.UserQuery;
import logic.model.Administrator;
import logic.model.Owner;
import logic.model.Resident;
import logic.model.Role;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends SqlDAO{

    public String checkUserID(String email,String address) throws SQLException{
        String getID = "";
        try {
            connect();
            ResultSet rs = UserQuery.selectUserID(stmt, email,address);
            if(rs.next()) {
                getID = rs.getString("user_id");
            }
        } finally {
            disconnect();
        }
        return getID;
    }

    public Role checkRole(String id) throws SQLException {
        Role role = null;
        try {
            connect();
            ResultSet rs = UserQuery.selectRole(stmt,id);
            if(rs.next()) {
                role = Role.valueOf(rs.getString("user_role"));
            }
        } finally {
            disconnect();
        }
        return role;
    }

    public  String checkLogin(String email,String condominiumCode) throws SQLException {
        String pwd = "";
        try {
            connect();
            ResultSet rs = UserQuery.selectLogin(stmt, email, condominiumCode);
            if(rs.next()) {
                pwd = rs.getString("user_pwd");
            }
        } finally {
            disconnect();
        }
        return pwd;
    }

    //USER INITIALIZATION
    public Administrator loadAdminID(String id) throws SQLException, PatternException {
        Administrator admin = null;
        try {
            connect();
            ResultSet rs = UserQuery.loadUserByID(stmt,id);
            if(rs.next()) {
                String userName = rs.getString("user_name");
                String userEmail = rs.getString("user_email");
                String userPwd = rs.getString("user_pwd");
                String userAddr = rs.getString("user_addr");
                admin = new Administrator(id,userName,userEmail,userPwd,userAddr,null);
            }
        } finally {
            disconnect();
        }
        return admin;
    }

    public Owner loadOwnerID(String id) throws SQLException,PatternException {
        Owner owner = null;
        try {
            connect();
            ResultSet rs = UserQuery.loadUserByID(stmt,id);
            if(rs.next()) {
                String userName = rs.getString("user_name");
                String userEmail = rs.getString("user_email");
                String userPwd = rs.getString("user_pwd");
                String userAddr = rs.getString("user_addr");
                owner = new Owner(id,userName,userEmail,userPwd,userAddr);
            }
        } finally {
            disconnect();
        }
        return owner;
    }

    public Resident loadResidentID(String id) throws SQLException,PatternException{
        Resident resident = null;
        try {
            connect();
            ResultSet rs = UserQuery.loadUserByID(stmt,id);
            if(rs.next()) {
                String userName = rs.getString("user_name");
                String userEmail = rs.getString("user_email");
                String userPwd = rs.getString("user_pwd");
                String userAddr = rs.getString("user_addr");
                resident = new Resident(id,userName,userEmail,userPwd,userAddr);
            }
        } finally {
            disconnect();
        }
        return resident;
    }
}
