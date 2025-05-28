package northwind;

public class Customer {
	private String customerId;
	private String companyName;
	private String contactName;
	private String contactTitle;
	private String country;
	
	public Customer(String customerId, String companyName, String contactName, String contactTitle, String country) {
		this.customerId = customerId;
		this.companyName = companyName;
		this.contactName = contactName;
		this.contactTitle = contactTitle;
		this.country = country;
	}
	
	public void printDetails() {
		System.out.println("Customer ID: " + customerId);
		System.out.println("Company Name: " + companyName);
		System.out.println("Contact Name: " + contactName);
		System.out.println("Contact Title: " + contactTitle);
		System.out.println("Country: " + country);
	}
	
	public String getCustomerId() { return customerId; }
	public String getCompanyName() { return companyName; }
	public String getContactName() { return contactName; }
	public String getContactTitle() { return contactTitle; }
	public String getCountry() { return country; }
}
