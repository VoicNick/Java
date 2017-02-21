/**
* Project: A00884836Lab2
* File: CustomerReader.java
* Date: Sep 27, 2016
* Time: 9:50:42 PM
*/
package a00884836.io;

import a00884836.data.Customer;
import a00884836.tools.Validator;


/**
 * Read and parse the data form the input string
* @author Voicu Chirtes, A00884836
*
*/
public class CustomerReader {

	/**
	 * Default constructor
	 */
	private CustomerReader() {}

	/**
	 * Read the data from raw string and parse it into 
	 * an array of Customer objects. 
	 * Data is assumed correct format, 
	 * only the email address is validated in this version of the program.
	 * 
	 * @param inputData the raw input data
	 * @return the array of Customer objects
	 */
	public static Customer[] read(String inputData) {
		//each element is one customer with data separated by |
		String[] arrayOfCustomers = inputData.split(":");
		//the number of customer from the input data
		int customerCount = arrayOfCustomers.length;
		//the array of customer objects
		Customer[] customers = new Customer[customerCount];
		
		int i = 0;
		
		for(String currentCustomer : arrayOfCustomers) {
			String[] customerData  = currentCustomer.split("\\|");
			
			int id = 0;
			try {
				id = Integer.parseInt(customerData[0]);
			}
			catch (Exception e) {
				System.out.println("Invalid ID, it must be an integer value");
				System.exit(-1);
			}

			String emailAddress = customerData[7];
			
			if(!Validator.isValidEmail(emailAddress)) {
				emailAddress = "N/A";
			}
			Customer customerObject = new Customer.Builder(id,customerData[6]).firstName(customerData[1]).lastName(customerData[2]).street(customerData[3]).city(customerData[4]).postalCode(customerData[5]).emailAddress(emailAddress).build();
			
			customers[i++] = customerObject;
		}
		
		return customers;
	}

}
