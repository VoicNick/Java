/**
 * Project: A00884836Lab9
 * File: Lab9.java
 * Date: Nov 21, 2016
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
import a00884836.ui.MainFrame;
import a00884836.io.db.sql.*;
import a00884836.exception.ApplicationException;
import java.time.Instant;
import java.util.List;
import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Collections;

/**
 * @author Voicu Chirtes, A00884836
 *
 */
@SuppressWarnings("unused")
public class Lab9 {

	public static final String DB_PROPERTIES_FILENAME = "db.properties";

	// default command line argument if none is specified
	private static final String DEFAULT_FILENAME = "customers.txt";

	// the name of the file containing the input data
	// this must be in the same folder as the application
	private String inputFileName;

	// the list of Customers
	private List<Customer> customers;

	private static Database database;
	private static CustomerDao customerDao;
	private final Properties dbProperties;
	private static Connection connection;

	// setting up logging
	private static final String LOG4J_CONFIG_FILENAME = "log_config.xml";

	static {
		configureLogging();
	}

	private static final Logger LOG = LogManager.getLogger(Lab9.class);

	/**
	 * non default constructor
	 * 
	 * @param inputFileName
	 *            the name of the file containing the input data
	 *            this must be in the same folder as the application
	 */
	private Lab9(String inputFileName, File dbPropertiesFile) throws IOException {
		this.inputFileName = inputFileName;
		dbProperties = new Properties();
		dbProperties.load(new FileReader(dbPropertiesFile));

		database = new Database(dbProperties);
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
	 * 
	 * @throws ClassNotFoundException,
	 *             SQLException
	 */
	private void run() throws ClassNotFoundException, SQLException {

		
		connection = database.getConnection();
		customerDao = new CustomerDao(database);

		try {
			customers = CustomerReader.read(inputFileName);
			
			if (!customerDao.create()) {
				this.usage();
				return;
			}
			LOG.debug("Inserting the customers");

			for (Customer customer : customers) {
				customerDao.add(customer);
			}

			CustomerDaoTester.getIds(customerDao, database);

			/*
			 * // System.out.println("Customer list in the original order:");
			 * LOG.info("Customer list in the original order:");
			 * CustomerReport.show(customers);
			 * // System.out.println("Customer list sorted by join date:");
			 * LOG.info("Customer list sorted by join date:");
			 * Collections.sort(customers, new CompareByJoinedDate());
			 * CustomerReport.show(customers);
			 */
		} catch (ApplicationException | SQLException e) {
			// System.out.println(e.getMessage());
			LOG.error(e.getMessage());
		}
	}

	private void dropTables() throws SQLException {
		
		connection = database.getConnection();
		customerDao = new CustomerDao(database);
		customerDao.drop();
		usage();
	}

	/**
	 * main entry point in the program
	 * 
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {

		String fileName = DEFAULT_FILENAME; // if no command line args, we will use the default filename
		Instant startTime = Instant.now();
		// System.out.println(startTime);
		LOG.info(startTime);

		
		File dbPropertiesFile = new File(DB_PROPERTIES_FILENAME);

		if (args.length > 0 && args[0].equalsIgnoreCase("drop")) { // if command line args is specified

			try {
				new Lab9(fileName, dbPropertiesFile).dropTables();

			} catch (SQLException | IOException e) {
				LOG.error(e.getMessage());
			} finally {
				connection.close();
			}
		} else {
			if (args.length > 0) {
				fileName = args[0];
			}
			try {
				new Lab9(fileName, dbPropertiesFile).run();
				//create the GUI
				try {
					  for (LookAndFeelInfo info:UIManager.getInstalledLookAndFeels()) {
					    if ("Nimbus".equals(info.getName())) {
					      UIManager.setLookAndFeel(info.getClassName());
					      break;
					    }
					  }
					} catch (Exception e) {
					    // If Nimbus is not available, use the default.
					}

				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							MainFrame frame = new MainFrame(customerDao);
							
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				

			} catch (Exception e) {
				LOG.error(e.getMessage());
			} finally {
				database.shutdown();
			}
		}

		Instant endTime = Instant.now();
		// System.out.println(endTime);
		LOG.info(endTime);
		// System.out.println(String.format("Runtime duration: %d ms", Duration.between(startTime, endTime).toMillis()));
		LOG.info(String.format("Runtime duration: %d ms", Duration.between(startTime, endTime).toMillis()));
	}

	private void usage() {
		LOG.info("Usage:");
		LOG.info("Lab9 - no argument. Reads the filename customers.txt. It then creates and populates the data into the database " + "table named A00884836_Customers."
				+ "This only happens if the database table does not exist.");
		LOG.info("Lab9 <filename> - reads the filename provided an the argument and it creates and populates the data into the database " + "table named A00884836_Customers."
				+ "This only happens if the database table does not exist.");
		LOG.info("Usage: Lab9 drop - will drop the database table named A00884836_Customers");
	}
	
	/*
	 
	public static void createUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

*/
}
