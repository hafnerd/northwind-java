package northwind;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnectionTest {

	public static void main(String[] args) {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Northwind;user=root;password=root;encrypt=true;trustServerCertificate=true;";
		
		DBConnector dbConnector = new DBConnector(url);
		
		try (Connection conn = dbConnector.getConnection()) {
			System.out.println("Connected to SQL Server!");
		} catch (SQLException e) {
			System.out.println("Connection failed.");
			e.printStackTrace();
		}
	}

}
