/**
 * Project: A00884836Lab5
 * File: CustomerReader.java
 * Date: Oct 16, 2016
 * Time: 9:50:42 PM
 */
package a00884836.io;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import a00884836.data.Customer;
import a00884836.tools.Validator;
import a00884836.exception.ApplicationException;

/**
 * Read and parse the data
 * 
 * @author Voicu Chirtes, A00884836
 *
 */
public class CustomerReader {

	@Deprecated
	// used only for raw data input via readFromString method
	public static final String RECORD_DELIMITER = ":";

	public static final String FIELD_DELIMITER = "\\|";

	/**
	 * Default constructor
	 */
	private CustomerReader() {
	}

	/**
	 * Read the data from a file and parse it into
	 * an array of Customer objects.
	 * Data is assumed correct format,
	 * only the email address is validated in this version of the program.
	 * 
	 * @param inputFileName
	 *            the name of the file containing the input data
	 *            this must be in the same folder as the application
	 * @return the array of Customer objects
	 * @throws ApplicationException
	 */
	public static List<Customer> read(String inputFileName) throws ApplicationException {

		Scanner scanner = null;
		// the list of customers read as Strings from the inputFile
		List<String> customerList = new ArrayList<>();

		// opening file and reading data into the local list of customers, as stings
		try {
			scanner = new Scanner(new File(inputFileName));
			while (scanner.hasNext()) {
				customerList.add(scanner.nextLine());
			}
		} catch (Exception e) {
			throw new ApplicationException(String.format("Could not open the input file. The error is: %s", e.getMessage()));
		} finally {
			if (scanner != null) {
				try {
					scanner.close();
				} catch (Exception e) {
				}
			}
		}

		if (customerList.size() <= 1) { // we have only header data
			throw new ApplicationException(String.format("Input file %s is empty", inputFileName));
		}

		// the number of customer from the input data, eliminating the header row
		int customerCount = customerList.size() - 1;
		// the List of customer objects. This can be changed to a LinkedList
		// or another implementation. For now ArrayList seems the best as
		// we only add data to the list.
		List<Customer> customers = new ArrayList<>(customerCount);

		// starting at 1 to skip the header row
		for (int i = 1; i <= customerCount; i++) {
			String[] customerData = customerList.get(i).split(FIELD_DELIMITER);

			// check if we have enough input fields to build the object
			if (customerData.length < Customer.FIELD_COUNT) {
				throw new ApplicationException(String.format("Expected %d but got %d: %s", Customer.FIELD_COUNT, customerData.length, Arrays.toString(customerData)));
			}

			String emailAddress = customerData[7];

			if (!Validator.isValidEmail(emailAddress)) {
				throw new ApplicationException(String.format("Invalid email: %s", emailAddress));
			}

			String validDate = Validator.isValidJoinDate(customerData[8]);

			if (validDate != null) {
				throw new ApplicationException(String.format("Invalid join date: %s - %s%n", customerData[8], validDate));
			}

			try {
				int id = Integer.parseInt(customerData[0]);
				int year = Integer.parseInt(customerData[8].substring(0, 4));
				int month = Integer.parseInt(customerData[8].substring(4, 6).trim());
				int day = Integer.parseInt(customerData[8].substring(6).trim());

				// For the month, we subtract 1 as the LocalDate stores the months as 0-11
				Customer customerObject = new Customer.Builder(id, customerData[6]).firstName(customerData[1]).lastName(customerData[2]).street(customerData[3])
						.city(customerData[4]).postalCode(customerData[5]).emailAddress(emailAddress).joinDate(LocalDate.of(year, month - 1, day)).build();

				customers.add(customerObject);
			} catch (Exception e) {
				throw new ApplicationException(e.getMessage());
			}
		}

		return customers;
	}

	@Deprecated
	/**
	 * Read the data from raw string and parse it into
	 * an array of Customer objects.
	 * Data is assumed correct format,
	 * only the email address is validated in this version of the program.
	 * 
	 * @param inputData
	 *            the raw input data
	 * @return the array of Customer objects
	 * @throws ApplicationException
	 */
	public static List<Customer> readFromString(String inputData) throws ApplicationException {
		// each element is one customer with data separated by |
		String[] arrayOfCustomers = inputData.split(RECORD_DELIMITER);
		// the number of customer from the input data
		int customerCount = arrayOfCustomers.length;
		// the List of customer objects. This can be changed to a LinkedList
		// or another implementation. For now ArrayList seems the best as
		// we only add data to the list.
		List<Customer> customers = new ArrayList<>(customerCount);

		for (String currentCustomer : arrayOfCustomers) {
			String[] customerData = currentCustomer.split(FIELD_DELIMITER);

			// check if we have enough input fields to build the object
			if (customerData.length < Customer.FIELD_COUNT) {
				throw new ApplicationException(String.format("Expected %d but got %d: %s", Customer.FIELD_COUNT, customerData.length, Arrays.toString(customerData)));
			}

			String emailAddress = customerData[7];

			if (!Validator.isValidEmail(emailAddress)) {
				throw new ApplicationException(String.format("Invalid email: %s", emailAddress));
			}

			String validDate = Validator.isValidJoinDate(customerData[8]);

			if (validDate != null) {
				throw new ApplicationException(String.format("Invalid join date: %s - %s%n", customerData[8], validDate));
			}

			try {
				int id = Integer.parseInt(customerData[0]);
				int year = Integer.parseInt(customerData[8].substring(0, 4));
				int month = Integer.parseInt(customerData[8].substring(4, 6).trim());
				int day = Integer.parseInt(customerData[8].substring(6).trim());

				// For the month, we subtract 1 as the LocalDate stores the months as 0-11
				Customer customerObject = new Customer.Builder(id, customerData[6]).firstName(customerData[1]).lastName(customerData[2]).street(customerData[3])
						.city(customerData[4]).postalCode(customerData[5]).emailAddress(emailAddress).joinDate(LocalDate.of(year, month - 1, day)).build();

				customers.add(customerObject);
			} catch (Exception e) {
				throw new ApplicationException(e.getMessage());
			}
		}
		return customers;
	}

}
