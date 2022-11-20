package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jms.ObjectMessage;

import beans.Order;
import beans.Orders;
import beans.User;
import business.MyTimerService;
import business.OrdersBusinessInterface;

/**
 * The controller in charge of manipulating the FormController
 * @author Josh Beck
 *
 */
@ManagedBean
@ViewScoped
public class FormController {
	
	@Inject
	OrdersBusinessInterface service;
	
	public OrdersBusinessInterface getService() {
		return service;
	}
	
	public String onSendOrder() {
		
		Order testOrder = new Order(1, "0000001", "Blanket", 20.99f, 33);
		service.sendOrder(testOrder);
		
		
		return "OrderResponse.xhtml";
	}

	//Code that is executed on form submit
	public String onSubmit() {
		
		System.out.println("Testing");
		service.test();
		
		// Get the users from the input form
		FacesContext context = FacesContext.getCurrentInstance();
		User user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		
		// Inject the user into the POST request
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		
		Orders orders = new Orders();
		// Inject the orders into the POST request
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("orders", service.getOrders());

		
		return "TestResponse.xhtml";
	}
	
	//Code submitted to cause a redirect on press
	public String onFlash(User user) {
		// Inject the user into the POST request
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);

		return "TestResponse2.xhtml?faces-redirect=true";
	}
	
}
