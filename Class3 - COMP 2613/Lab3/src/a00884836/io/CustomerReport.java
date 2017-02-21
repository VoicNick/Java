/**
* Project: A00884836Lab3
* File: CustomerReport.java
* Date: Oct 3, 2016
* Time: 9:50:59 PM
*/
package a00884836.io;

import a00884836.data.Customer;
import java.time.format.DateTimeFormatter;


/**
 * Display a report of the customers
* @author Voicu Chirtes, A00884836
*
*/
public class CustomerReport {
	
	public static final String HORIZONTAL_LINE = "-------------------------------------------------------------" + 
	"---------------------------------------------------------------------------------";
	public static final String HEADER_FORMAT = "%3s. %-6s %-12s %-12s %-25s %-12s %-12s %-15s %-24s %-12s%n";
	public static final String CUSTOMER_FORMAT = "%3d. %06d %-12s %-12s %-25s %-12s %-12s %-15s %-24s %-12s%n";

	/**
	 * default constructor
	 */
	private CustomerReport() {}

	/**
	 * Display a report of the customers
	 * @param customers the array of customers to display
	 */
	public static void show(Customer[] customers) {
		System.out.printf("%s%n", "Customer report");
		System.out.println(HORIZONTAL_LINE);
		System.out.format(HEADER_FORMAT, "#", "ID", "First name", "Last name", "Street", "City", "Postal Code", 
				"Phone", "Email", "Join Date");
		System.out.println(HORIZONTAL_LINE);
		
		int i=0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd uuuu");
		for(Customer customer : customers) {
			System.out.format(CUSTOMER_FORMAT, ++i, 
					customer.getId(), customer.getFirstName(), customer.getLastName(), 
					customer.getStreet(), customer.getCity(), customer.getPostalCode(), 
					customer.getPhone(), customer.getEmailAddress(), formatter.format(customer.getJoinDate()));
		}
			
	}

}
