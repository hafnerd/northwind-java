package northwind;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	private DBConnector dbConnector;
	
	public EmployeeDAO(DBConnector dbConnector) {
		this.dbConnector = dbConnector;
	}
	
	public List<Employee> getAllEmployees() {
		String sql = "SELECT EmployeeID, FirstName, LastName, Title, Salary FROM Employees";
		List<Employee> employees = new ArrayList<>();
		
		try (Connection conn = dbConnector.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Employee employee = new Employee(
					rs.getString("EmployeeID"),
					rs.getString("FirstName"),
					rs.getString("LastName"),
					rs.getString("Title"),
					rs.getString("Salary")
				);
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return employees;
	}
}
