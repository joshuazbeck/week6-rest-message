package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * This is the object used to hold a list of Order objects
 * @author Josh Beck
 *
 */
@ManagedBean
@ViewScoped
public class Orders {
	List<Order> orders;

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public Orders() {
		orders = new ArrayList<Order>();
	}
}
