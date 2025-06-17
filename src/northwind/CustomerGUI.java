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
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 7, 0));
		
		fetchButton = new JButton("Last Names Only");
		int frameWidth = 400;
		int buttonWidth = (int) (frameWidth * 0.35);
		int buttonHeight = 22;
		fetchButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
		fetchButton.addActionListener(e -> refresh(true));
		buttonPanel.add(fetchButton);
		add(buttonPanel, BorderLayout.NORTH);
		
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		outputArea.setBorder(BorderFactory.createEmptyBorder(20, 20, 0,0));
		
		add(new JScrollPane(outputArea), BorderLayout.CENTER);
		refresh(false);
	}
		
		public void refresh(boolean lastNamesOnly) {
	        outputArea.setText("");

	    if (lastNamesOnly) {
	        CustomerSummary summary = customerService.getCustomerSummary();
	        outputArea.append("Total customers: " + summary.getCount() + "\n\n");
	        for (String name : summary.getNames()) {
	            outputArea.append(" - " + name + "\n");
	        }
	    } else {
	        List<Customer> customers = customerService.getAllCustomers();
	        outputArea.append("Total customers: " + customers.size() + "\n\n");
	        for (Customer c : customers) {
	            outputArea.append("Customer ID: " + c.getCustomerId() + "\n");
	            outputArea.append("Company Name: " + c.getCompanyName() + "\n");
	            outputArea.append("Contact Name: " + c.getContactName() + "\n");
	            outputArea.append("Contact Title: " + c.getContactTitle() + "\n");
	            outputArea.append("Country: " + c.getCountry() + "\n");
	            outputArea.append("------------------------------\n");
	        }
	    }
	    outputArea.setCaretPosition(0);
	}
}
