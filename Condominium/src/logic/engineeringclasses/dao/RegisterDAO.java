package logic.engineeringclasses.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.engineeringclasses.bean.UserBean;
import logic.engineeringclasses.query.RegisterQuery;
import logic.model.Registered;
import logic.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterDAO extends SqlDAO{

    public boolean checkRegistration(String email,String condominiumCode) throws SQLException {
        try {
            connect();
            ResultSet rs = RegisterQuery.selectRegistration(stmt, email, condominiumCode);
            if(rs.next()) {
                return false;
            }
        } finally {
            disconnect();
        }
        return true;
    }

    public void addRegistrationUser(User user, String role) throws SQLException{
        try{
            String sql= "INSERT INTO registration (reg_name, reg_email, reg_pwd, reg_role, reg_addr) VALUES (?,?,?,?,?)";
            System.out.println(sql);
            preset = prepConnect(sql);
            preset.setString(1, user.getName());
            preset.setString(2, user.getEmail());
            preset.setString(3, user.getPassword());
            preset.setString(4, role);
            preset.setString(5, user.getAddress());
            preset.execute();
        } finally {
            disconnect();
        }
    }

//    public ObservableList<Registered> loadRegisteredUserList(String address) throws SQLException{
//        ObservableList<Registered> list = FXCollections.observableArrayList();
//        ResultSet rs;
//        try {
//            connect();
//            rs = RegisterQuery.selectRegistratedUserList(stmt,address);
//            while(rs.next()) {
//                list.add(new Registered(rs.getString("reg_name"),rs.getString("reg_email"),rs.getString("reg_pwd"),rs.getString("reg_role"),rs.getString("reg_addr")));
//            }
//        } finally {
//            disconnect();
//        }
//        return list;
//    }

    public ObservableList<Registered> loadRegisteredUserList(String address) throws SQLException{
        ObservableList<Registered> list = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            connect();
            rs = RegisterQuery.selectRegistratedUserList(stmt,address);
            while(rs.next()) {
                list.add(new Registered(rs.getInt("reg_id"),rs.getString("reg_name"),rs.getString("reg_email"),rs.getString("reg_pwd"),rs.getString("reg_role"),rs.getString("reg_addr")));
            }
        } finally {
            disconnect();
        }
        return list;
    }

    public void addRegistered(UserBean bean) throws SQLException{
        try{
            connect();
            String sql = "INSERT INTO users (user_name,user_email,user_pwd,user_role,user_addr) VALUES (?,?,?,?,?)";
            preset = prepConnect(sql);
            System.out.println(bean.getName());
            preset.setString(1, bean.getName());
            System.out.println(bean.getEmail());
            preset.setString(2, bean.getEmail());
            System.out.println(bean.getPassword());
            preset.setString(3, bean.getPassword());
            System.out.println(bean.getRole());
            preset.setString(4, bean.getRole());
            System.out.println(bean.getAddress());
            preset.setString(5, bean.getAddress());
            preset.execute();
            System.out.println("AO");
        } finally {
            disconnect();
        }
    }

    public void deleteRegistered(int registerId)throws SQLException {
        try{
            connect();
            String sql= "DELETE FROM registration WHERE reg_id='"+registerId+"'";
            stmt.executeUpdate(sql);
        } finally {
            disconnect();
        }
    }
}
