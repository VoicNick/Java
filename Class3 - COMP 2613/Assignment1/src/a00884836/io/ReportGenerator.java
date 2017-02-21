/**
* Project: A00884836_Assignment1
* File: ReportGenerator.java
* Date: Oct 27, 2016
* Time: 4:32:39 PM
*/
package a00884836.io;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00884836.data.Customer;
import a00884836.data.Motorcycle;
import a00884836.data.Part;
import a00884836.data.ServicedMotorcycle;
import a00884836.data.ServicedMotorcycleWithParts;

/**
* @author Voicu Chirtes, A00884836
*
*/
public class ReportGenerator {

	public static final String HORIZONTAL_LINE = "-------------------------------------------------------------"
			+ "---------------------------------------------------------------------------------";
	public static final String HEADER_FORMAT_CUSTOMER_REPORT = "%3s. %-6s %-12s %-12s %-25s %-12s %-12s %-15s %-24s %-12s";
	public static final String CUSTOMER_REPORT_FORMAT = "%3d. %06d %-12s %-12s %-25s %-12s %-12s %-15s %-24s %-12s";
	
	public static final String HEADER_FORMAT_SERVICE_REPORT = "%-11s %-13s %-18s %-15s %-6s %-7s";
	public static final String SERVICE_REPORT_FORMAT = "%-11s %-13s %-18s %-15s %-6s %-7s";
	
	public static final String HEADER_FORMAT_INVENTORY_REPORT = "%-28s %-26s %-13s %7s %12s";
	public static final String INVENTORY_REPORT_FORMAT = "%-28s %-26s %-13s %7s %12s";
	
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * default constructor
	 */
	private ReportGenerator() {
	}

	/**
	 * Display a report of the customers
	 * 
	 * @param customers
	 *            the array of customers to display
	 */
	public static void showCustomerReport(List<Customer> customers) {
/*		 System.out.printf("%s%n", "Customer report");
		 System.out.println(HORIZONTAL_LINE);
		 System.out.format(HEADER_FORMAT, "#", "ID", "First name", "Last name", "Street", "City", "Postal Code", "Phone", "Email", "Join Date");
		 System.out.println(HORIZONTAL_LINE);
*/
		LOG.info(String.format("%s", "Customer report"));
		LOG.info(HORIZONTAL_LINE);
		LOG.info(String.format(HEADER_FORMAT_CUSTOMER_REPORT, "#", "ID", "First name", "Last name", "Street", "City", "Postal Code", "Phone", "Email", "Join Date"));
		LOG.info(HORIZONTAL_LINE);

		int i = 0;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd uuuu");
		for (Customer customer : customers) {
/*			 System.out.format(CUSTOMER_FORMAT, ++i, customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getStreet(), customer.getCity(),
			 customer.getPostalCode(), customer.getPhone(), customer.getEmailAddress(), formatter.format(customer.getJoinDate()));
*/			LOG.info(String.format(CUSTOMER_REPORT_FORMAT, ++i, customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getStreet(), customer.getCity(),
					customer.getPostalCode(), customer.getPhone(), customer.getEmailAddress(), formatter.format(customer.getJoinDate())));

		}

		LOG.info(HORIZONTAL_LINE);
	}

	/**
	 * Display the service report with the format:
	 * First Name | Last Name | Make | Model | Year | Mileage
	 * 
	 * @param customers
	 * @param motorcycles
	 */
	public static void showServiceReport(List<Customer> customers, List<Motorcycle> motorcycles) {
		
		List<ServicedMotorcycle> servicedMotorcycles = new ArrayList<ServicedMotorcycle>();
		
		for(Customer customer:customers) {
			for(Motorcycle motorcycle:motorcycles) {
				if(motorcycle.getCustomer_id() == customer.getId()) {
					ServicedMotorcycle servicedMotorcycle = new ServicedMotorcycle.Builder(customer.getId(), customer.getFirstName()
							, customer.getLastName(), customer.getPhone(), motorcycle.getMotorcycle_id(), motorcycle.getMake()
							, motorcycle.getModel()).street(customer.getStreet()).city(customer.getCity())
							.postalCode(customer.getPostalCode()).emailAddress(customer.getEmailAddress()).joinDate(customer.getJoinDate())
							.year(motorcycle.getYear()).serialNumber(motorcycle.getSerialNumber()).mileage(motorcycle.getMileage()).build();
					servicedMotorcycles.add(servicedMotorcycle);				}
			}		
		}
		
		LOG.info(String.format("%s", "Service report"));
		LOG.info(HORIZONTAL_LINE);
		LOG.info(String.format(HEADER_FORMAT_SERVICE_REPORT, "First name", "Last name", "Make", "Model", "Year", "Mileage"));
		LOG.info(HORIZONTAL_LINE);		
		for(ServicedMotorcycle m:servicedMotorcycles) {
			LOG.info(String.format(SERVICE_REPORT_FORMAT, m.getFirstName(), m.getLastName(), m.getMake(), m.getModel()
					, m.getYear(), m.getMileage()));
		}
		
		LOG.info(HORIZONTAL_LINE);
	}
	
	public static void showInventoryReport(List <ServicedMotorcycleWithParts> servicedMotorcycleWithParts) {
		
		LOG.info(String.format("%s", "Inventory report"));
		LOG.info(HORIZONTAL_LINE);
		LOG.info(String.format(HEADER_FORMAT_INVENTORY_REPORT, "Make+Model", "Description", "Part#", "Price", "Quantity"));
		LOG.info(HORIZONTAL_LINE);	
		
		for(ServicedMotorcycleWithParts m : servicedMotorcycleWithParts) {
			Motorcycle motorcycle = m.getMotorcycle();
			
			for (Map.Entry<Part, Integer> entry : m.getParts().entrySet()) {

				String makePlusModel = motorcycle.getMake() + '+' + motorcycle.getModel();
				LOG.info(String.format(INVENTORY_REPORT_FORMAT, makePlusModel
						, entry.getKey().getDescription(), entry.getKey().getPartNumber(), entry.getKey().getPriceInUSD()
						, entry.getValue()));
		    }
		}
		
		LOG.info(HORIZONTAL_LINE);
	}
}
