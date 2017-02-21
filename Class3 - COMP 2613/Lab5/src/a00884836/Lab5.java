/**
 * Project: A00884836Lab5
 * File: Lab5.java
 * Date: Oct 16, 2016
 * Time: 9:43:29 PM
 */
package a00884836;

import a00884836.data.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import a00884836.io.CustomerReader;
import a00884836.io.CustomerReport;
import a00884836.tools.CompareByJoinedDate;
import a00884836.exception.ApplicationException;
import java.time.Instant;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;

/**
 * @author Voicu Chirtes, A00884836
 *
 */
public class Lab5 {

	// default command line argument if none is specified
	private static final String DEFAULT_FILENAME = "customers.txt";

	// the name of the file containing the input data
	// this must be in the same folder as the application
	private String inputFileName;

	// the list of Customers
	private List<Customer> customers;

	// setting up logging
	private static final String LOG4J_CONFIG_FILENAME = "log_config.xml";

	static {
		configureLogging();
	}

	private static final Logger LOG = LogManager.getLogger(Lab5.class);

	/**
	 * non default constructor
	 * 
	 * @param inputFileName
	 *            the name of the file containing the input data
	 *            this must be in the same folder as the application
	 */
	public Lab5(String inputFileName) {
		this.inputFileName = inputFileName;
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
	 * Run the program.
	 */
	private void run() {

		try {
			customers = CustomerReader.read(inputFileName);
			// System.out.println("Customer list in the original order:");
			LOG.info("Customer list in the original order:");
			CustomerReport.show(customers);
			// System.out.println("Customer list sorted by join date:");
			LOG.info("Customer list sorted by join date:");
			Collections.sort(customers, new CompareByJoinedDate());
			CustomerReport.show(customers);
		} catch (ApplicationException e) {
			// System.out.println(e.getMessage());
			LOG.error(e.getMessage());
		}
	}

	/**
	 * main entry point in the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		String fileName = DEFAULT_FILENAME; // if no command line args, we will use the default filename
		Instant startTime = Instant.now();
		// System.out.println(startTime);
		LOG.info(startTime);
		if (args.length > 0) { // if command line args is specified, we will use it as the filename
			fileName = args[0];
		}
		new Lab5(fileName).run();

		Instant endTime = Instant.now();
		// System.out.println(endTime);
		LOG.info(endTime);
		// System.out.println(String.format("Runtime duration: %d ms", Duration.between(startTime, endTime).toMillis()));
		LOG.info(String.format("Runtime duration: %d ms", Duration.between(startTime, endTime).toMillis()));
	}
}
