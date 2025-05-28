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

public class CustomerGUI extends JPanel {
	private JTextArea outputArea;
	private JButton fetchButton;
	private CustomerService customerService;
	
	public CustomerGUI(String connectionString) {
		// dbAccess
		DBConnector dbConnector = new DBConnector(connectionString);
		CustomerDAO customerDAO = new CustomerDAO(dbConnector);
		customerService = new CustomerService(customerDAO);
		
		// set up GUI
		setLayout(new BorderLayout());
		
		//Panel to hold the button
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		
		fetchButton = new JButton("Fetch Customer Last Names");
		int frameWidth = 400;
		int buttonWidth = (int) (frameWidth * 0.6);
		int buttonHeight = 30;
		fetchButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		buttonPanel.add(fetchButton);
		add(buttonPanel, BorderLayout.NORTH);
		
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		outputArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 0,0));
		
		add(new JScrollPane(outputArea), BorderLayout.CENTER);
		
		List<Customer> customers = customerService.getAllCustomers();
		outputArea.setText("Total customers: " + customers.size() + "\n\n");
		
		for (Customer customer : customers) {
			outputArea.append("Customer ID: " + customer.getCustomerId() + "\n");
			outputArea.append("Company Name: " + customer.getCompanyName() + "\n");
			outputArea.append("Contact Name: " + customer.getContactName() + "\n");
			outputArea.append("Contact Title: " + customer.getContactTitle() + "\n");
			outputArea.append("Country: " + customer.getCountry() + "\n");
			outputArea.append("------------------------------\n");
		}
		
		outputArea.setCaretPosition(0);
		
		// Button action
		fetchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				outputArea.setText(""); // clear
				CustomerSummary summary = customerService.getCustomerSummary();
				outputArea.append("Total customers: " + summary.getCount() + "\n\n");
				for (String lastName: summary.getNames()) {
					outputArea.append(" - " + lastName + "\n");
				}

				outputArea.setCaretPosition(0);
				
				int lineCount = outputArea.getLineCount();
				int lineHeight = outputArea.getFontMetrics(outputArea.getFont()).getHeight();
				
				int contentHeight = lineCount * lineHeight;
				int buttonPanelHeight = fetchButton.getPreferredSize().height + 60;
				int totalHeight = contentHeight + buttonPanelHeight;

				int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
				int finalHeight = Math.min(totalHeight,  screenHeight);
				
				setSize(400, finalHeight);
				validate();

			}
		});
		
		setVisible(true);
	}
}
