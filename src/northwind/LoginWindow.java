package northwind;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.*;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class LoginWindow extends JFrame {
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	public LoginWindow() {
		setTitle("Login");
		setSize(300, 180);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
				
		formPanel.add(new JLabel("Username: "));
		usernameField = new JTextField();
		formPanel.add(usernameField);

		
		formPanel.add(new JLabel("Password: "));
		passwordField = new JPasswordField();
		formPanel.add(passwordField);
		
		JButton loginButton = new JButton("Login");
		formPanel.add(new JLabel());
		formPanel.add(loginButton);
		
		loginButton.addActionListener(e -> tryLogin());
		
		add(formPanel);
		setVisible(true);
	}
	
	private void tryLogin() {
		String username = usernameField.getText().trim();
		String password = new String(passwordField.getPassword()).trim();
		
		String connString = String.format(
			"jdbc:sqlserver://localhost:1433;databaseName=Northwind;user=%s;password=%s;encrypt=true;trustServerCertificate=true;",
			username, password
		);
		
		try (Connection conn = DriverManager.getConnection(connString)) {
			String role = getUserRole(conn, username);
			
			if (role == null) {
				JOptionPane.showMessageDialog(this,  "Login succeeded, but role not found.");
			} else {
				dispose();
				new MainAppWindow(role, connString);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Invalid login or database connection failed.");
			e.printStackTrace();
		}
	}
	
	private String getUserRole(Connection conn, String username) {
		String[] roles = { "SalesRole", "HRRole", "CEORole" };
		
		System.out.println("Check role of user: " + username);

		for (String role : roles) {
			try (PreparedStatement stmt = conn.prepareStatement("SELECT IS_MEMBER(?)")) {
				stmt.setString(1,  role);
				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next() && rs.getInt(1) == 1) {
						return role;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
