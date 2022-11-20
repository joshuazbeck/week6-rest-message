package business;

import beans.Order;
import beans.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class OrdersDataService
 */
@Stateless
@Local(DataAccessInterface.class)
@LocalBean
public class OrdersDataService implements DataAccessInterface {

	List<Order> orders = new ArrayList<Order>();
	
    /**
     * Default constructor. 
     */
    public OrdersDataService() {
    }
    
    /**
     * Handle deleting the order
     * @param order
     */
    public void delete(Order order) {
    	Connection conn = null;
		try {
			//open connections
			String url = "jdbc:mysql://localhost:3306/testapp";
			conn = DriverManager.getConnection(url, "root", "password");
			
			//Delete orders
			String sql = "DELETE FROM testapp.ORDERS WHERE ID = ?";
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, order.getId());
			
			//Execute the query
			statement.executeUpdate(sql);
			conn.close();
			
			
			System.out.println("Successfully deleted order " + order.getId() + "!!");
		} catch (Exception e) {
			//Catch exceptions
			System.out.println("Failure!!" + e.getLocalizedMessage());
		} finally {
			
			//If the connection is not null, close
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					//Catch any further exceptions on connection close
					e.printStackTrace();
				}
			}
			
		}
    }
    /**
     * Update the orders
     * @param order
     */
    public void update(Order order) {
		Connection conn = null;
		try {
			//Open the connection
			String url = "jdbc:mysql://localhost:3306/testapp";
			conn = DriverManager.getConnection(url, "root", "password");
			
			//Update the order
			String sql = "UPDATE testapp.ORDERS SET ORDER_NO=?, PRODUCT_NAME=?, PRICE=?, QUANTITY=? WHERE ID = ?";
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, order.getOrderNumber());
			statement.setString(2, order.getProductName());
			statement.setFloat(3, order.getPrice());
			statement.setInt(4, order.getQuantity());
			statement.setInt(5, order.getId());
			
			//Execute the query
			statement.executeUpdate(sql);
			conn.close();
			System.out.println("Successfully updated order " + order.getId() + "!!");
		} catch (Exception e) {
			//Catch exceptions
			System.out.println("Failure!!" + e.getLocalizedMessage());
		} finally {
			
			//If the connection is not null, close
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					//Catch any further exceptions on connection close
					e.printStackTrace();
				}
			}
			
		}
	}
    /**
     * Create the order
     * @param order
     */
    public void create(Order order) {
		Connection conn = null;
		try {
			//Open the connection
			String url = "jdbc:mysql://localhost:3306/testapp";
			conn = DriverManager.getConnection(url, "root", "password");
			
			//Insert into the SQL
			String sql = "INSERT INTO testapp.ORDERS (ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY)"
					+ " VALUES (?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			//Set the integers in the prepared statement
			statement.setString(1, order.getOrderNumber());
			statement.setString(2, order.getProductName());
			statement.setFloat(3, order.getPrice());
			statement.setInt(4, order.getQuantity());
			
			//Execute the query
			statement.executeUpdate();
			conn.close();
			System.out.println("Successfully added order " + order.getId() + "!!");
		} catch (Exception e) {
			//Catch exceptions
			System.out.println("Failure!!" + e.getLocalizedMessage());
		} finally {
			
			//If the connection is not null, close
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					//Catch any further exceptions on connection close
					e.printStackTrace();
				}
			}
			
		}
	}
    /**
     * Find the order
     * @param id - the ID to check for
     * @return - null object if no results
     */
    public Order findById(int id) {
		Connection conn = null;
		try {
			//Connect to table
			String url = "jdbc:mysql://localhost:3306/testapp";
			conn = DriverManager.getConnection(url, "root", "password");
			
			//Create SQL statement
			String sql = "SELECT * FROM testapp.ORDERS WHERE ID = ?";
			PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = statement.executeQuery();
			
			Order o = null;
			while (rs.next()) {
				
				//Build order
				o = new Order();
				o.setId(id);
				o.setOrderNumber(rs.getString("ORDER_NO"));
				o.setProductName(rs.getString("PRODUCT_NUM"));
				o.setPrice(rs.getFloat("PRICE"));
				o.setQuantity(rs.getInt("QUANTITY"));
			}
			
			conn.close();
			
			//Return order
			System.out.println("Successfully added order " + id + "!!");
			return o;
			
		} catch (Exception e) {
			//Catch exceptions
			System.out.println("Failure!!" + e.getLocalizedMessage());
		} finally {
			
			//If the connection is not null, close
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					//Catch any further exceptions on connection close
					e.printStackTrace();
				}
			}
			
		}
		return null;
	}
    /**
     * Return all the objects
     */
	public List<Order> findAll() {
		//Open a connection
		Connection conn = null;
		this.orders = new ArrayList<Order>();
		try {
			
			//Connect using the connection link
			String url = "jdbc:mysql://localhost:3306/testapp";
			conn = DriverManager.getConnection(url, "root", "password");
			Statement statement = conn.createStatement();
			
			//Create the SQL
			String sql = "SELECT * FROM testapp.ORDERS";
			ResultSet rs = statement.executeQuery(sql);
			
			//Iterate through the results
			while (rs.next()) {
				
				//Build an order
				int id = rs.getInt("ID");
				String order_no = rs.getString("ORDER_NO");
				String productName = rs.getString("PRODUCT_NAME");
				float price = rs.getFloat("PRICE");
				int quantity = rs.getInt("QUANTITY");
				
				Order order = new Order(id, order_no, productName, price, quantity);
				
				//Add order to the array
				this.orders.add(order);
			}
			//Close
			conn.close();
		
		
		} catch (Exception e) {
			//Catch exceptions
			System.out.println("Failure!!" + e.getLocalizedMessage());
		} finally {
			
			//If the connection is not null, close
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					//Catch any further exceptions on connection close
					e.printStackTrace();
				}
			}
			
		}
		return this.orders;
	}
}
