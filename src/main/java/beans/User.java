package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A model that holds the User object
 * @author Josh Beck
 *
 */
@ManagedBean
@SessionScoped
public class User {
	
	@NotNull()
	@Size(min=5, max=15) 
	private String firstName;
	
	@NotNull()
	@Size(min=5, max=15) 
	private String lastName;
	
	public User() {
		firstName = "Josh";
		lastName = "Beck";
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}