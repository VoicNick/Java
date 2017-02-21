/**
 * Project: A00884836Lab4
 * File: Lab4.java
 * Date: Oct 10, 2016
 * Time: 9:43:29 PM
 */
package a00884836;

import a00884836.data.Customer;
import a00884836.io.CustomerReader;
import a00884836.io.CustomerReport;
import a00884836.tools.CompareByJoinedDate;
import a00884836.exception.ApplicationException;
import java.time.Instant;
import java.util.List;
import java.time.Duration;
import java.util.Collections;

/**
 * @author Voicu Chirtes, A00884836
 *
 */
public class Lab4 {

	// will hold the raw input data
	private String inputData;

	// the list of Customers
	private List<Customer> customers;

	/**
	 * non default constructor
	 * 
	 * @param inputData
	 *            the string containing the input raw data
	 */
	public Lab4(String inputData) {
		this.inputData = inputData;
	}

	/**
	 * Run the program.
	 */
	private void run() {

		try {
			customers = CustomerReader.read(inputData);
			System.out.println("Customer list in the original order:");
			CustomerReport.show(customers);
			System.out.println("Customer list sorted by join date:");
			Collections.sort(customers, new CompareByJoinedDate());
			CustomerReport.show(customers);
		} catch (ApplicationException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * main entry point in the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Instant startTime = Instant.now();
		System.out.println(startTime);
		if (args.length <= 0) { // we must have one string argument to run the program
			System.out.println("Input data is missing. Expecting player data.");
			System.exit(-1);
		}
		new Lab4(args[0]).run();

		Instant endTime = Instant.now();
		System.out.println(endTime);
		System.out.println(String.format("Runtime duration: %d ms", Duration.between(startTime, endTime).toMillis()));
	}
}
