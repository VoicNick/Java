/**
* Project: A00884836_Assignment1
* File: ServicedMotorcycle.java
* Date: Oct 27, 2016
* Time: 9:16:40 PM
*/
package a00884836.data;

import java.time.LocalDate;

/**
* @author Voicu Chirtes, A00884836
* this class models a serviced motorcycle object excluding the 
* service details e.g the parts used
* this class combines the Customer and Motorcycle classes
* and it is used to generate the required in the ServiceReport
*/
public class ServicedMotorcycle {

	public static final int FIELD_COUNT = 7;
	
	private int customer_id;
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String postalCode;
	private String phone;
	private String emailAddress;
	private LocalDate joinDate;
	
	private int motorcycle_id;
	private String make;
	private String model;
	//setting this as int for now. It could be changed to 
	// a date/time type, but this does to seem necessary as of now.
	private int year;
	private String serialNumber;
	private int mileage;

	// inner builder class
		public static class Builder {

			// Required parameters
			// the assignment notes did not specify this aspect
			// so I added just the id, name and phone. Others can be added if required.
			private final int customer_id;
			private final String firstName;
			private final String lastName;
			private final String phone;
			private final int motorcycle_id;
			private final String make;
			private final String model;


			// Optional parameters - initialized to default values
			private String street;
			private String city;
			private String postalCode;
			private String emailAddress;
			private LocalDate joinDate;
			private int year;
			private String serialNumber;
			private int mileage;

			/**
			 * @param customer_id
			 * @param firstName
			 * @param lastName
			 * @param phone
			 * @param motorcycle_id
			 * @param make
			 * @param model
			 */
			public Builder(int customer_id, String firstName, String lastName, String phone
					, int motorcycle_id, String make, String model) {
				super();
				this.customer_id = customer_id;
				this.firstName = firstName;
				this.lastName = lastName;
				this.phone = phone;
				this.motorcycle_id = motorcycle_id;
				this.make = make;
				this.model = model;
			}

			/**
			 * @param street
			 *            the street to set
			 */
			public Builder street(String street) {
				this.street = street;
				return this;
			}

			/**
			 * @param city
			 *            the city to set
			 */
			public Builder city(String city) {
				this.city = city;
				return this;
			}

			/**
			 * @param postalCode
			 *            the postalCode to set
			 */
			public Builder postalCode(String postalCode) {
				this.postalCode = postalCode;
				return this;
			}

			/**
			 * @param emailAddress
			 *            the emailAddress to set
			 */
			public Builder emailAddress(String emailAddress) {
				this.emailAddress = emailAddress;
				return this;
			}

			/**
			 * @param joinDate
			 *            the joinDate to set
			 */
			public Builder joinDate(LocalDate joinDate) {
				this.joinDate = joinDate;
				return this;
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

			public ServicedMotorcycle build() {
				return new ServicedMotorcycle(this);
			}
		
		}


		/**
		 * @param builder
		 */
		private ServicedMotorcycle(Builder builder) {
			customer_id = builder.customer_id;
			firstName = builder.firstName;
			lastName = builder.lastName;
			street = builder.street;
			city = builder.city;
			postalCode = builder.postalCode;
			phone = builder.phone;
			emailAddress = builder.emailAddress;
			joinDate = builder.joinDate;
			motorcycle_id = builder.motorcycle_id;
			make = builder.make;
			model = builder.model;
			year = builder.year;
			serialNumber = builder.serialNumber;
			mileage = builder.mileage;
		}


		/**
		 * @return the customer_id
		 */
		public int getCustomer_id() {
			return customer_id;
		}


		/**
		 * @return the firstName
		 */
		public String getFirstName() {
			return firstName;
		}


		/**
		 * @return the lastName
		 */
		public String getLastName() {
			return lastName;
		}


		/**
		 * @return the street
		 */
		public String getStreet() {
			return street;
		}


		/**
		 * @return the postalCode
		 */
		public String getPostalCode() {
			return postalCode;
		}


		/**
		 * @return the phone
		 */
		public String getPhone() {
			return phone;
		}


		/**
		 * @return the emailAddress
		 */
		public String getEmailAddress() {
			return emailAddress;
		}


		/**
		 * @return the joinDate
		 */
		public LocalDate getJoinDate() {
			return joinDate;
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
		 * @param city the city to set
		 */
		public void setCity(String city) {
			this.city = city;
		}


		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "ServicedMotorcycle [customer_id=" + customer_id + ", firstName=" + firstName + ", lastName=" + lastName + ", street=" + street + ", city=" + city
					+ ", postalCode=" + postalCode + ", phone=" + phone + ", emailAddress=" + emailAddress + ", joinDate=" + joinDate + ", motorcycle_id=" + motorcycle_id
					+ ", make=" + make + ", model=" + model + ", year=" + year + ", serialNumber=" + serialNumber + ", mileage=" + mileage + "]";
		}
		
		
}
