package northwind;

import java.util.List;

public class CustomerSummary {
	private int count;
	private List<String> names;
	
	// Constructor for DAO to use
	public CustomerSummary(int count, List<String> names) {
		this.count = count;
		this.names = names;
	}
	
	// Constructor that accepts connection string, uses DAO internally
	public CustomerSummary(String connectionString) {
		DBConnector dbConnector = new DBConnector(connectionString);
		CustomerDAO dao = new CustomerDAO(dbConnector);
		CustomerSummary summary = dao.getCustomerSummary();
		
		this.count = summary.getCount();
		this.names = summary.getNames();
	}
	
	public int getCount() {
		return count;
	}
	
	public List<String> getNames() {
		return names;
	}
	
	public void printSummary() {
		System.out.println("Total customers: " + count);
		System.out.println("Customer Names: ");
		for (String name : names) {
			System.out.println(" - " + name);
		}
	}
}
