/**
* Project: A00884836Lab3
* File: CustomerReader.java
* Date: Oct 3, 2016
* Time: 9:50:42 PM
*/
package a00884836.io;

import java.time.LocalDate;
import java.util.Arrays;

import a00884836.data.Customer;
import a00884836.tools.Validator;
import a00884836.exception.ApplicationException;


/**
 * Read and parse the data form the input string
* @author Voicu Chirtes, A00884836
*
*/
public class CustomerReader {

	public static final String RECORD_DELIMITER = ":";
	public static final String FIELD_DELIMITER = "\\|";

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
	 * @throws ApplicationException 
	 */
	public static Customer[] read(String inputData) throws ApplicationException {
		//each element is one customer with data separated by |
		String[] arrayOfCustomers = inputData.split(RECORD_DELIMITER);
		//the number of customer from the input data
		int customerCount = arrayOfCustomers.length;
		//the array of customer objects
		Customer[] customers = new Customer[customerCount];
		
		int i = 0;
		
		for(String currentCustomer : arrayOfCustomers) {
			String[] customerData  = currentCustomer.split(FIELD_DELIMITER);
			
			//check if we have enough input fields to build the object
			if(customerData.length<Customer.FIELD_COUNT) {
				throw new ApplicationException(String.format("Expected %d but got %d: %s", Customer.FIELD_COUNT, customerData.length, Arrays.toString(customerData)));
			}

			String emailAddress = customerData[7];
			
			if(!Validator.isValidEmail(emailAddress)) {
				throw new ApplicationException(String.format("Invalid email: %s", emailAddress));
			}
			
			String validDate = Validator.isValidJoinDate(customerData[8]);
			
			if(validDate != null) {
				throw new ApplicationException(String.format("Invalid join date: %s - %s%n", customerData[8], validDate));
			}
			
			try {
				int id = Integer.parseInt(customerData[0]);
				int year = Integer.parseInt(customerData[8].substring(0, 4));
				int month = Integer.parseInt(customerData[8].substring(4, 6).trim());
				int day = Integer.parseInt(customerData[8].substring(6).trim());
			
			//For the month, we substract 1 as the LocalDate stores the months as 0-11
			Customer customerObject = new Customer.Builder(id,customerData[6]).firstName(customerData[1]).
					lastName(customerData[2]).street(customerData[3]).city(customerData[4]).postalCode(customerData[5]).
					emailAddress(emailAddress).joinDate(LocalDate.of(year, month-1, day)).build();
			
			customers[i++] = customerObject;
			}
			catch (Exception e) {
				throw new ApplicationException(e.getMessage());
			}
		}	
		return customers;
	}

}
