package northwind;

public class Order {
	private String orderId;
	private String orderDate;
	private String shipName;
	private String shipAddress;
	private String shipCity;
	
	public Order(String orderId, String orderDate, String shipName, String shipAddress, String shipCity) {
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.shipName = shipName;
		this.shipAddress = shipAddress;
		this.shipCity = shipCity;
	}
	
	public void printDetails() {
		System.out.println("Order ID: " + orderId);
		System.out.println("Order Date: " + orderDate);
		System.out.println("Shipping Name: " + shipName);
		System.out.println("Shipping Address: " + shipAddress);
		System.out.println("Ship City: " + shipCity);
	}
	
	public String getOrderId() { return orderId; }
	public String getOrderDate() { return orderDate; }
	public String getShipName() { return shipName; }
	public String getShipAddress() { return shipAddress; }
	public String getShipCity() { return shipCity; }

}
