package br.recife.edu.ifpe.paokentin.model.repositorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private static final String URL = "jdbc:mysql://localhost:3306/paokentin";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";

	private static Connection conn = null;
	
	public static Connection getCurrentConnection() throws SQLException {
		
		if(conn == null)
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		
		return conn;
		
	}
	
	static Connection getNewConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
}
