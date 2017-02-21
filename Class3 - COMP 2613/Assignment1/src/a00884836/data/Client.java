/**
* Project: A00884836_Assignment1
* File: Client.java
* Date: Oct 26, 2016
* Time: 9:13:28 PM
*/
package a00884836.data;

import java.util.List;

/**
* @author Voicu Chirtes, A00884836
* this class models a client object
*/
public class Client {

	private Customer customer;
	private List<ServicedMotorcycleWithParts> servicedMotorcycleWithParts;
	
	/**
	 * 
	 * @param customer
	 * @param motorcycles
	 */
	public Client(Customer customer, List<ServicedMotorcycleWithParts> servicedMotorcycleWithParts) {
		this.customer = customer;
		this.servicedMotorcycleWithParts = servicedMotorcycleWithParts;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @return the motorcycles
	 */
	public List<ServicedMotorcycleWithParts> getServicedMotorcycles() {
		return servicedMotorcycleWithParts;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @param motorcycles the motorcycles to set
	 */
	public void setMotorcycles(List<ServicedMotorcycleWithParts> servicedMotorcycleWithParts) {
		this.servicedMotorcycleWithParts = servicedMotorcycleWithParts;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Client [customer=" + customer + ", servicedMotorcycleWithParts=" + servicedMotorcycleWithParts + "]";
	}

}
