/**
* Project: A00884836Lab2
* File: CustomerReport.java
* Date: Sep 27, 2016
* Time: 9:50:59 PM
*/
package a00884836.io;

import a00884836.data.Customer;

/**
 * Display a report of the customers
* @author Voicu Chirtes, A00884836
*
*/
public class CustomerReport {

	/**
	 * default constructor
	 */
	private CustomerReport() {}

	/**
	 * Display a report of the customers
	 * @param customers the array of customers to display
	 */
	public static void show(Customer[] customers) {
		System.out.printf("%n%s%n", "Customer report");
		System.out.println("---------------------------------------------------------------------"
				+ "-------------------------------------------------------------");
		System.out.println("  #. ID     First name   Last name    Street                    "
				+ "City         Postal Code  Phone           Email");
		System.out.println("---------------------------------------------------------------------"
				+ "-------------------------------------------------------------");
		
		int i=0;
		for(Customer customer : customers) {
			System.out.format("%3d. %06d %-12s %-12s %-25s %-12s %-12s %-14s  %s%n", ++i, 
					customer.getId(), customer.getFirstName(), customer.getLastName(), 
					customer.getStreet(), customer.getCity(), customer.getPostalCode(), 
					customer.getPhone(), customer.getEmailAddress());
		}
			
	}

}
