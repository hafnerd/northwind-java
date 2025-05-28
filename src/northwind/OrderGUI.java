package northwind;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.*;
import java.awt.BorderLayout;
import java.util.List;

public class OrderGUI extends JPanel {
	private JTextArea outputArea;
	private OrderService orderService;
	
	public OrderGUI(String connectionString) {
		setLayout(new BorderLayout());
		
		// dbAccess
		DBConnector dbConnector = new DBConnector(connectionString);
		OrderDAO orderDAO = new OrderDAO(dbConnector);
		orderService = new OrderService(orderDAO);
		
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		outputArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(new JScrollPane(outputArea), BorderLayout.CENTER);
		
		List<Order> orders = orderService.getAllOrders();
		outputArea.setText("Total orders: " + orders.size() + "\n\n");
		
		for (Order order : orders) {
			outputArea.append("Order ID: " + order.getOrderId() + "\n");
			outputArea.append("Order Date: " + order.getOrderDate() + "\n");
			outputArea.append("Shipping Name: " + order.getShipName() + "\n");
			outputArea.append("Shipping Address: " + order.getShipAddress() + "\n");
			outputArea.append("Shipping City: " + order.getShipCity() + "\n");
			outputArea.append("------------------------------\n");
		}
		
		outputArea.setCaretPosition(0);
		
	}
}
