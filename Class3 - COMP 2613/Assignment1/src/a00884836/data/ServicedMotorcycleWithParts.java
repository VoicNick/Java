/**
* Project: A00884836_Assignment1
* File: ServicedMotorcycleWithParts.java
* Date: Oct 26, 2016
* Time: 9:32:48 PM
*/
package a00884836.data;

import java.util.LinkedHashMap;

/**
* @author Voicu Chirtes, A00884836
* this class models a serviced motorcycle object including the 
* service details e.g the parts used
*/
public class ServicedMotorcycleWithParts {

	private Motorcycle motorcycle;
	//the parts used and quantity of each
	private LinkedHashMap<Part, Integer> parts;
	
	/**
	 * 
	 */
	public ServicedMotorcycleWithParts(Motorcycle motorcycle, LinkedHashMap<Part, Integer> parts) {
		this.motorcycle = motorcycle;
		this.parts = parts;
	}

	/**
	 * @return the motorcycle
	 */
	public Motorcycle getMotorcycle() {
		return motorcycle;
	}

	/**
	 * @return the parts
	 */
	public LinkedHashMap<Part, Integer> getParts() {
		return parts;
	}

	/**
	 * @param motorcycle the motorcycle to set
	 */
	public void setMotorcycle(Motorcycle motorcycle) {
		this.motorcycle = motorcycle;
	}

	/**
	 * @param parts the parts to set
	 */
	public void setParts(LinkedHashMap<Part, Integer> parts) {
		this.parts = parts;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ServicedMotorcycleWithParts [motorcycle=" + motorcycle + ", parts=" + parts + "]";
	}

	
}
