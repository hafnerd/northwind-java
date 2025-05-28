package northwind;

public class Employee {
	private String employeeId;
	private String emplFirstName;
	private String emplLastName;
	private String emplTitle;
	private String salary;
	
	public Employee(String employeeId, String emplFirstName, String emplLastName, String emplTitle, String salary) {
		this.employeeId = employeeId;
		this.emplFirstName = emplFirstName;
		this.emplLastName = emplLastName;
		this.emplTitle = emplTitle;
		this.salary = salary;
	}
	
	public void printDetails() {
		System.out.println("Employee ID: " + employeeId);
		System.out.println("Employee Fist Name: " + emplFirstName);
		System.out.println("Employee Last Name: " + emplLastName);
		System.out.println("Employee Title: " + emplTitle);
		System.out.println("Salary: " + salary);
	}
	
	public String getEmployeeId() { return employeeId; }
	public String getEmplFirstName() { return emplFirstName; }
	public String getEmplLastName() { return emplLastName; }
	public String getEmplTitle() { return emplTitle; }
	public String getSalary() { return salary; }

}