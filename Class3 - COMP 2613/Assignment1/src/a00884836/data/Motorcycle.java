/**
* Project: A00884836_Assignment1
* File: Motorcycle.java
* Date: Oct 24, 2016
* Time: 9:24:49 PM
*/
package a00884836.data;

/**
* @author Voicu Chirtes, A00884836
*
* This class models a real world motorcycle object.
*/
public class Motorcycle {
	
	/**
	 * the number of fields in the motorcycle object
	 */
	public static final int FIELD_COUNT = 7;
	
	private int motorcycle_id;
	private String make;
	private String model;
	//setting this as int for now. It could be changed to 
	// a date/time type, but this does to seem necessary as of now.
	private int year;
	private String serialNumber;
	private int mileage;
	private int customer_id;
	
	// inner builder class
	public static class Builder {
		// Required parameters
		// these are needed in order to create a motorcycle.
		// as they have dependencies with the other data. 
		// e.g. you can't have a motorcycle with no owner (customer_id)
		private final int motorcycle_id;
		private final String make;
		private final String model;
		private final int customer_id;
	
		//optional parameters - initialized to default values
		private int year;
		private String serialNumber;
		private int mileage;
		
		/**
		 * Builder constructor with the required parameters
		 * 
		 * @param motorcycle_id
		 * @param make
		 * @param model
		 * @param customer_id
		 */
		public Builder (int motorcycle_id, String make, String model, int customer_id) {
			super();
			this.motorcycle_id = motorcycle_id;
			this.make = make;
			this.model = model;
			this.customer_id = customer_id;
		}
		
		/**
		 * @param year the year to set
		 * @return the Builder object
		 */
		public Builder year (int year) {
			this.year = year;
			return this;
		}
		
		/**
		 * @param serialNumber the serialNumber to set
		 * @return the Builder object
		 */
		public Builder serialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
			return this;
		}
		
		/**
		 * @param milleage the mileage to set
		 * @return the Builder object
		 */
		public Builder mileage(int mileage) {
			this.mileage = mileage;
			return this;
		}
		
		public Motorcycle build() {
			return new Motorcycle(this);
		}
	}

	/**
	 * @param builder
	 */
	public Motorcycle(Builder builder) {
		motorcycle_id = builder.motorcycle_id;
		make = builder.make;
		model = builder.model;
		year = builder.year;
		serialNumber = builder.serialNumber;
		mileage = builder.mileage;
		customer_id = builder.customer_id;
	}

	/**
	 * @return the motorcycle_id
	 */
	public int getMotorcycle_id() {
		return motorcycle_id;
	}

	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @return the serialNumber
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * @return the mileage
	 */
	public int getMileage() {
		return mileage;
	}

	/**
	 * @return the customer_id
	 */
	public int getCustomer_id() {
		return customer_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Motorcycle [motorcycle_id=" + motorcycle_id + ", make=" + make + ", model=" + model + ", year=" + year + ", serialNumber=" + serialNumber + ", mileage=" + mileage
				+ ", customer_id=" + customer_id + "]";
	}
	
}
