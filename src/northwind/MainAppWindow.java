package northwind;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainAppWindow extends JFrame {
	private CardLayout cardLayout;
	private JPanel contentPanel;
	
	public MainAppWindow(String userRole) {
		// set up GUI
		setTitle("Northwind Java App - " + userRole);
		setSize(400, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		cardLayout = new CardLayout();
		contentPanel = new JPanel(cardLayout);

		String connStr = "jdbc:sqlserver://localhost:1433;databaseName=Northwind;user=root;password=root;encrypt=true;trustServerCertificate=true;";
		
		JPanel navPanel = new JPanel();
		add(navPanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		
		if (userRole.equals("SalesRole") || userRole.equals("CEORole")) {
			JButton customerBtn = new JButton("Customers");
			JButton orderBtn = new JButton("Orders");
			navPanel.add(customerBtn);
			navPanel.add(orderBtn);
			contentPanel.add(new CustomerGUI(connStr), "CUSTOMERS");
			contentPanel.add(new OrderGUI(connStr), "ORDERS");
			customerBtn.addActionListener(e -> cardLayout.show(contentPanel, "CUSTOMERS"));
			orderBtn.addActionListener(e -> cardLayout.show(contentPanel, "ORDERS"));
		}
		if (userRole.equals("HRRole") || userRole.equals("CEORole")) {
			JButton employeeBtn = new JButton("Employees");
			navPanel.add(employeeBtn);
			contentPanel.add(new EmployeeGUI(connStr), "EMPLOYEES");
			employeeBtn.addActionListener(e -> cardLayout.show(contentPanel, "EMPLOYEES"));
		}
		
		
	
		cardLayout = (CardLayout) contentPanel.getLayout();
		setVisible(true);
	}
	
	private void show(String name) {
		cardLayout.show(contentPanel, name);
	}
}
