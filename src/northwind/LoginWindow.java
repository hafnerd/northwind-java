package northwind;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.*;
import java.awt.BorderLayout;
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
		setLayout(new GridLayout(3, 2, 10, 10));
		
		add(new JLabel("Username: "));
		usernameField = new JTextField();
		add(usernameField);
		
		add(new JLabel("Password: "));
		passwordField = new JPasswordField();
		add(passwordField);
		
		JButton loginButton = new JButton("Login");
		add(new JLabel());
		add(loginButton);
		
		loginButton.addActionListener(e -> tryLogin());
		
		setVisible(true);
	}
	
	private void tryLogin() {
		String username = usernameField.getText().trim();
		String password = new String(passwordField.getPassword()).trim();
		
		Map<String, String> credentials = Map.of(
			 "User_Sales", "sales123",
			 "User_HR", "hr123",
			 "User_CEO", "ceo123"
		);
		Map<String, String> roles = Map.of(
			 "User_Sales", "SalesRole",
			 "User_HR", "HRRole",
			 "User_CEO", "CEORole"
		);
		
		if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
			String role = roles.get(username);
			dispose();
			new MainAppWindow(role);
		} else {
			JOptionPane.showMessageDialog(this, "Invalid username or password.");
		}
	}
}
