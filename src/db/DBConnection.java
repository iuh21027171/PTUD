package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static DBConnection instance;
	private Connection con;

	private DBConnection() {
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QUANLYHIEUSACH;trustServerCertificate=true", "sa", "Password123");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static DBConnection getInstance() {
		if (instance == null)
			instance = new DBConnection();
		return instance;
	}

	public Connection getConnection() {
		return con;
	}
}