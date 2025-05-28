package northwind;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.*;
import java.awt.BorderLayout;
import java.util.List;

public class EmployeeGUI extends JPanel {
	private JTextArea outputArea;
	private EmployeeService employeeService;
	
	public EmployeeGUI(String connectionString) {
		setLayout(new BorderLayout());
		
		// dbAccess
		DBConnector dbConnector = new DBConnector(connectionString);
		EmployeeDAO employeeDAO = new EmployeeDAO(dbConnector);
		employeeService = new EmployeeService(employeeDAO);
		
		outputArea = new JTextArea();
		outputArea.setEditable(false);
		outputArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(new JScrollPane(outputArea), BorderLayout.CENTER);
		
		List<Employee> employees = employeeService.getAllEmployees();
		outputArea.setText("Total employees: " + employees.size() + "\n\n");
		
		for (Employee employee : employees) {
			outputArea.append("Employee ID: " + employee.getEmployeeId() + "\n");
			outputArea.append("Employee First Name: " + employee.getEmplFirstName() + "\n");
			outputArea.append("Employee Last Name: " + employee.getEmplLastName() + "\n");
			outputArea.append("Employee Title: " + employee.getEmplTitle() + "\n");
			outputArea.append("Salary: " + employee.getSalary() + "\n");
			outputArea.append("------------------------------\n");
		}
		
		outputArea.setCaretPosition(0);
		
	}
}
