package northwind;

import java.util.List;

public class EmployeeService {
	private EmployeeDAO employeeDAO;
	
	public EmployeeService(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}
	
	public List<Employee> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}
}
