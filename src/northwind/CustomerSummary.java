package northwind;

import java.util.List;

public class CustomerSummary {
	private int count;
	private List<String> names;
	private String customerId;
	private String companyName;
	private String contactName;
	private String contactTitle;
	private String country;
	
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
	
	public int getCount() { return count; }	
	public List<String> getNames() { return names; }
	public String getCustomerId() { return customerId; }
	public String getCompanyName() { return companyName; }
	public String getContactName() { return contactName; }
	public String getContactTitle() { return contactTitle; }
	public String getCountry() { return country; }
	
	public void printSummary() {
		System.out.println("Total customers: " + count);
		System.out.println("Customer Names: ");
		for (String name : names) {
			System.out.println(" - " + name);
		}
		System.out.println("Customer ID: " + customerId);
		System.out.println("Company Name: " + companyName);
		System.out.println("Contact Name: " + contactName);
		System.out.println("Contact Title: " + contactTitle);
		System.out.println("Country: " + country);
	}
}
