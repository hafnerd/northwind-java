package northwind;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	private String connectionString;
	
	public DBConnector(String connectionString) {
		this.connectionString = connectionString;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(connectionString);
	}
}
