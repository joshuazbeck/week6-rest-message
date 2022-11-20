package business;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import beans.Order;

/**
 * Message-Driven Bean implementation class for: OrderMessageService
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/Order"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/queue/Order")
public class OrderMessageService implements MessageListener {

	@EJB
	OrdersDataService service;
    /**
     * Default constructor. 
     */
    public OrderMessageService() {
    	super();
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
        // Handle a passed message
        try {
        	
        	if (message instanceof TextMessage) { //Handle TextMessage
        		
        		//Display message
        		System.out.println(((TextMessage)message).getText());
        		
        	} else if (message instanceof ObjectMessage) { //Handle ObjectMessage
        		
        		// Convert the message to an Object
        		Object object = ((ObjectMessage)message).getObject();
        		
        		// Check the object is an Order
        		if (object instanceof Order) {
        			
	        		Order order = (Order)object;
	        		service.create(order);
	        		
        		} else {
        			
        			//Unable to convert the object to an order
        			System.out.println("Unable to convert the Object Message to type Order");
        			
        		}
        		
        	} else {
        		System.out.println("There was an error casting the message to a TextMessage");
        	}
		} catch (JMSException e) {
			System.out.println("There was an error while trying to print the message: " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("There was a generic exception " + e.getLocalizedMessage());
		}
    }

}
