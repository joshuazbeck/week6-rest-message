package beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is an object used to hold an order's information
 * @author schoolslimes
 *
 */

@XmlRootElement
public class Order implements Serializable {

	private static final long serialVersionUID = 3L;
	int id;
	String orderNumber;
	String productName;
	float price;
	int quantity;
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Order() {
		super();
		this.orderNumber = "";
		this.productName = "";
		this.price = 0.0f;
		this.quantity = 0;
	}
	public Order(int id, String orderNumber, String productName, float price, int quantity) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
