package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static Connection conn;

	public static Connection getConnection() { // is the connection.
		if( conn != null )
			return conn;

		try {
			String driver ="com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/assignment4";
			String user ="root";
			String password ="password";
			Class.forName(driver);
			conn = DriverManager.getConnection( url, user, password );
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static void closeConnection( Connection stop ) {
		if( stop == null )
			return;
		try {
			stop.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}