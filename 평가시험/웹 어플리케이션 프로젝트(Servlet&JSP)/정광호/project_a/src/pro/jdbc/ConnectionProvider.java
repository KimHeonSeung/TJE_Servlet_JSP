package pro.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:website");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
