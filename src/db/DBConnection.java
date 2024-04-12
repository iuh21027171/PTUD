package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static DBConnection instance;
	private Connection con;

	private DBConnection() {
<<<<<<< HEAD
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QUANLYHIEUSACH;trustServerCertificate=true";

		String user = "sa";
		String pass = "Password.1";
		try {
			con = DriverManager.getConnection(url, user, pass);
//			System.out.println("Connected");
=======
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QUANLYHIEUSACH;trustServerCertificate=true", "sa", "Password123");
>>>>>>> 18a2abe (Update DAO & Service)
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

<<<<<<< HEAD
	public synchronized static DBConnection getInstance() {
=======
	public static DBConnection getInstance() {
>>>>>>> 18a2abe (Update DAO & Service)
		if (instance == null)
			instance = new DBConnection();
		return instance;
	}

	public Connection getConnection() {
		return con;
	}
<<<<<<< HEAD

=======
>>>>>>> 18a2abe (Update DAO & Service)
}