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
	
	public List<Customer> getAllCustomers() {
		String sql = "SELECT CustomerID, CompanyName, ContactName, ContactTitle, Country FROM Customers";
		List<Customer> customers = new ArrayList<>();
		
		try (Connection conn = dbConnector.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Customer customer = new Customer(
					rs.getString("CustomerID"),
					rs.getString("CompanyName"),
					rs.getString("ContactName"),
					rs.getString("ContactTitle"),
					rs.getString("Country")
				);
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customers;
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
