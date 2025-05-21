package northwind;

public class CustomerService {
	private CustomerDAO customerDAO;
	
	public CustomerService(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
	
	public CustomerSummary getCustomerSummary() {
		return customerDAO.getCustomerSummary();
	}
}
