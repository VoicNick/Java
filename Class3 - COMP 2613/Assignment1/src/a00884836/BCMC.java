/**
* Project: A00884836_Assignment1
* File: BCMC.java
* Date: Oct 24, 2016
* Time: 8:54:33 PM
*/
package a00884836;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import a00884836.data.Customer;
import a00884836.data.Motorcycle;
import a00884836.data.Part;
import a00884836.data.ServicedMotorcycleWithParts;
import a00884836.exception.ApplicationException;
import a00884836.io.DataReader;
import a00884836.io.ReportGenerator;

/**
* @author Voicu Chirtes, A00884836
*
* This is the main driver class of this project
*/
public class BCMC {

	private static final String CUSTOMERS_FILENAME = "customers.dat";
	private static final String MOTORCYCLES_FILENAME = "motorcycles.dat";
	private static final String INVENTORY_FILNAME = "inventory.dat";	
	
	//the command line arguments
	private String[] args;

	private List<Customer> customers;
	private List<Motorcycle> motorcycles;
	
	// the serviced motorcycle object including the service details e.g the parts used
	private List <ServicedMotorcycleWithParts> servicedMotorcycleWithParts;
	
	//the key will be partNumber
	@SuppressWarnings("unused")
	private Map<String,Part> parts;
	
	
	// setting up logging
	private static final String LOG4J_CONFIG_FILENAME = "log_config.xml";

	static {
		configureLogging();
	}

	private static final Logger LOG = LogManager.getLogger(BCMC.class);
	/**
	 * 
	 */
	public BCMC(String[] args) {
		this.args = args;
	}

	/**
	 * Configuring the logging
	 */
	private static void configureLogging() {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);

		} catch (IOException e) {
			System.out.println(String.format("Can't find the log4j logging configuration file %s.", LOG4J_CONFIG_FILENAME));
		}
	}
	
	/**
	 * @param args
	 * The entry point of the program
	 */
	public static void main(String[] args) {
		
		Instant startTime = Instant.now();
		// System.out.println(startTime);
		LOG.info(startTime);
		new BCMC(args).run();
		
		Instant endTime = Instant.now();
		// System.out.println(endTime);
		LOG.info(endTime);
		// System.out.println(String.format("Runtime duration: %d ms", Duration.between(startTime, endTime).toMillis()));
		LOG.info(String.format("Runtime duration: %d ms", Duration.between(startTime, endTime).toMillis()));

		


		

	}

	private void run() {
		
		try {
			customers = DataReader.readCustomers(CUSTOMERS_FILENAME);
			motorcycles = DataReader.readMotorcycles(MOTORCYCLES_FILENAME);
			parts = DataReader.readParts(INVENTORY_FILNAME);
			servicedMotorcycleWithParts = DataReader.readServicedMotorcycleWithPartss(motorcycles, INVENTORY_FILNAME);
/*			
  			ReportGenerator.showCustomerReport(customers);
			ReportGenerator.showServiceReport(customers, motorcycles);
			ReportGenerator.showInventoryReport(servicedMotorcycleWithParts);
*/		
			} catch (ApplicationException e) {
			// System.out.println(e.getMessage());
			LOG.error(e.getMessage());
		}
			
		
		
		if(args.length<=0) {
			//print all reports
  			ReportGenerator.showCustomerReport(customers);
			ReportGenerator.showServiceReport(customers, motorcycles);
			ReportGenerator.showInventoryReport(servicedMotorcycleWithParts);

			return;
		} 
		
		
		//if we get here we have some arguments
		for(int i=0;i<args.length;i++)
		{
			switch (args[i].toUpperCase()) {
			case "SERVICE":
				ReportGenerator.showServiceReport(customers, motorcycles);
/*				String filter = "";
				//we assume ascending order
				String sortOrder = "ASC";
				if(hasNextArg(i) && (args[i+1].toUpperCase().startsWith("MAKE=") ))  {
					if(args[i++].length()>5) {
						filter = args[i].substring(5);						
					}
				}
				if(hasNextArg(i) && 
						(args[i+1].equalsIgnoreCase("ASC") || args[i+1].equalsIgnoreCase("DESC")))  {
					sortOrder = args[i++].toUpperCase();						
					}
				//call a method to print service report with arguments sortOrder and filter
				//e.g. CustomerReport.show(sortOrder, filter);
*/				break;
				
			case "INVENTORY":
				ReportGenerator.showInventoryReport(servicedMotorcycleWithParts);
				break;
			case "CUSTOMERS":
	  			ReportGenerator.showCustomerReport(customers);
				break;
				
			default:
				//not a valid argument
				// do something here throw exception or print usage?
				LOG.warn("Invalid parameter, use: <service, customers or inventory>");
				break;
			}
				
		}
		
	}
	
	@SuppressWarnings("unused")
	private boolean hasNextArg(int i) {
		return ((args.length>0) && (i<args.length-1));
	}
	
	public enum SortOrder {
		ASC,
		DESC
	}
	
	public enum ReportType {
		SERVICE,
		INVENTORY,
		CUSTOMERS
	}
}
