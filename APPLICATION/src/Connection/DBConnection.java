package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection con;
	private static DBConnection instance;

	private DBConnection() {
		
		try {
				con = DriverManager.getConnection("jdbc:mysql://localhost/GestionCabinetMedicale", "root", "");
		} catch (SQLException e) {
				e.printStackTrace();
		}
	}

	public static DBConnection getCon() {
		if (instance == null) {
				instance = new DBConnection();
		}
		return instance;
	}

	public Connection getConnection() {
		return con;
	}
}
