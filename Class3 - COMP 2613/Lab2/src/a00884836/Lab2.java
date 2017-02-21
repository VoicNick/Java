/**
* Project: A00884836Lab2
* File: Lab2.java
* Date: Sep 27, 2016
* Time: 9:43:29 PM
*/
package a00884836;

import a00884836.data.Customer;
import a00884836.io.CustomerReader;
import a00884836.io.CustomerReport;

/**
* @author Voicu Chirtes, A00884836
*
*/
public class Lab2 {
	
	//will hold the raw input data
	private String inputData;
	// the array of Customers
	private Customer[] customers;

	/**
	 * non default constructor
	 * @param inputData the string containing the input raw data
	 */
	public Lab2(String inputData) {
		this.inputData = inputData;
	}

	/**
	 * Run the program. 
	 */
	private void run() {
		customers = CustomerReader.read(inputData);
		CustomerReport.show(customers);
	}
	
	/**
	 * main entry point in the program
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length <= 0) { // we must have one string argument to run the program
			System.out.println("Input data is missing. Expecting player data.");
			System.exit(-1);
		}
		
		new Lab2(args[0]).run();	
	}
}
