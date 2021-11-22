package logic.engineeringclasses.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/condominium_db";
	private static final String USER = "condominium";
	private static final String PASSWORD = "ispw2021";

	private Connection conn;

	PreparedStatement preset;
	Statement stmt;
	
	public SqlDAO() {
		this.stmt = null;
		this.conn = null;
		this.preset = null;
	}
	
	void connect() throws SQLException 	{
		 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		 conn = DriverManager.getConnection(URL,USER,PASSWORD);
		 stmt = conn.createStatement();
	}
	
	PreparedStatement prepConnect(String sql) throws SQLException{
		 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		 conn = DriverManager.getConnection(URL,USER,PASSWORD);
		 return conn.prepareStatement(sql);
	}
	
	void disconnect() throws SQLException{
        if (stmt != null)
            stmt.close();
        if (conn != null)
            conn.close();
	}
}
	
