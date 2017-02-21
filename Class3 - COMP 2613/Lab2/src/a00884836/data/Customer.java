/**
* Project: A00884836Lab2
* File: Customer.java
* Date: Sep 27, 2016
* Time: 9:44:31 PM
*/
package a00884836.data;

/**
* @author Voicu Chirtes, A00884836
*
*/
public class Customer {

	private int id;
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String postalCode;
	private String phone;
	private String emailAddress;

	//inner builder class
	public static class Builder {
		//Required parameters
		private final int id;
		private final String phone;
		
		//Optional parameters - initialized to default values
		private String firstName;
		private String lastName;
		private String street;
		private String city;
		private String postalCode;
		private String emailAddress;
		
		/**
		 * @param id
		 * @param phone
		 */
		public Builder(int id, String phone) {
			super();
			this.id = id;
			this.phone = phone;
		}

		/**
		 * @param firstName the firstName to set
		 */
		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		/**
		 * @param lastName the lastName to set
		 */
		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		/**
		 * @param street the street to set
		 */
		public Builder street(String street) {
			this.street = street;
			return this;
		}

		/**
		 * @param city the city to set
		 */
		public Builder city(String city) {
			this.city = city;
			return this;
		}

		/**
		 * @param postalCode the postalCode to set
		 */
		public Builder postalCode(String postalCode) {
			this.postalCode = postalCode;
			return this;
		}

		/**
		 * @param emailAddress the emailAddress to set
		 */
		public Builder emailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
			return this;
		}
		
		public Customer build() {
			return new Customer(this);
		}
	}

	/**
	 * @param builder
	 */
	private Customer(Builder builder) {
		id = builder.id;
		firstName = builder.firstName;
		lastName = builder.lastName;
		street = builder.street;
		city = builder.city;
		postalCode = builder.postalCode;
		phone = builder.phone;
		emailAddress = builder.emailAddress;
	}

	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
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
	 * @return the city
	 */
	public String getCity() {
		return city;
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



	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", street=" + street + ", city=" + city + ", postalCode=" + postalCode
				+ ", phone=" + phone + ", emailAddress=" + emailAddress + "]";
	}

}

