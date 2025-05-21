package northwind;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
	private DBConnector dbConnector;
	
	public CustomerDAO(DBConnector dbConnector) {
		this.dbConnector = dbConnector;
	}
	
	public CustomerSummary getCustomerSummary() {
		String sql = "SELECT ContactName FROM Customers";
		List<String> lastNames = new ArrayList<>();
		int count = 0;
		
		try (Connection conn = dbConnector.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			
			while (rs.next()) {
				String fullName = rs.getString("ContactName").trim();
				String[] parts = fullName.split(" ");
				String lastName = parts[parts.length - 1]; // get last word
				lastNames.add(lastName);
				count ++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new CustomerSummary(count, lastNames);
	}
}
