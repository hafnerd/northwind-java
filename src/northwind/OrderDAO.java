package northwind;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
	private DBConnector dbConnector;
	
	public OrderDAO(DBConnector dbConnector) {
		this.dbConnector = dbConnector;
	}
	
	public List<Order> getAllOrders() {
		String sql = "SELECT OrderID, OrderDate, ShipName, ShipAddress, ShipCity FROM Orders";
		List<Order> orders = new ArrayList<>();
		
		try (Connection conn = dbConnector.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				Order order = new Order(
					rs.getString("OrderID"),
					rs.getString("OrderDate"),
					rs.getString("ShipName"),
					rs.getString("ShipAddress"),
					rs.getString("ShipCity")
				);
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return orders;
	}
}
