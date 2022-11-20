package business;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.Order;

/**
 * A REST API for returning all the orders provided
 * @author Josh Beck
 *
 */
@RequestScoped
@Path("/orders")
public class OrdersRestService {
	
	@Inject
	OrdersBusinessInterface service;
	
	/**
	 * Get the orders as JSON
	 * @return
	 */
	@GET
	@Path("/getjson")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getOrdersAsJson(){
		return service.getOrders();
	}
	
	/**
	 * Get the orders as XML
	 * @return
	 */
	@GET
	@Path("/getxml")
	@Produces(MediaType.APPLICATION_XML)
	public Object[] getOrdersAsXml() {
		return service.getOrders().toArray(new Order[0]);
	}

}
