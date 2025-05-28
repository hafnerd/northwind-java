package northwind;

import java.util.List;

public class OrderService {
	private OrderDAO orderDAO;
	
	public OrderService(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
	public List<Order> getAllOrders() {
		return orderDAO.getAllOrders();
	}
}
