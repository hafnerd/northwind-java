package northwind;

import java.util.List;

public class CustomerService {
	private CustomerDAO customerDAO;
	
	public CustomerService(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
	
	public CustomerSummary getCustomerSummary() {
		return customerDAO.getCustomerSummary();
	}
	
	public List<Customer> getAllCustomers() {
		return customerDAO.getAllCustomers();
	}
}
