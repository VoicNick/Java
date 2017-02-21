/**
* Project: A00884836Lab3
* File: Lab3.java
* Date: Oct 3, 2016
* Time: 9:43:29 PM
*/
package a00884836;

import a00884836.data.Customer;
import a00884836.io.CustomerReader;
import a00884836.io.CustomerReport;
import a00884836.exception.ApplicationException;
import java.time.Instant;
import java.time.Duration;

/**
* @author Voicu Chirtes, A00884836
*
*/
public class Lab3 {
	
	//will hold the raw input data
	private String inputData;
	// the array of Customers
	private Customer[] customers;

	/**
	 * non default constructor
	 * @param inputData the string containing the input raw data
	 */
	public Lab3(String inputData) {
		this.inputData = inputData;
	}

	/**
	 * Run the program. 
	 */
	private void run() {
		
		try {
			customers = CustomerReader.read(inputData);
			CustomerReport.show(customers);
		}
		catch (ApplicationException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * main entry point in the program
	 * @param args
	 */
	public static void main(String[] args) {
		
		Instant startTime = Instant.now();
		System.out.println(startTime);
		if(args.length <= 0) { // we must have one string argument to run the program
			System.out.println("Input data is missing. Expecting player data.");
			System.exit(-1);
		}
		new Lab3(args[0]).run();		
		
		Instant endTime = Instant.now();
		System.out.println(endTime);
		System.out.println(String.format("Runtime duration: %d ms", Duration.between(startTime, endTime).toMillis()));
	}
}
