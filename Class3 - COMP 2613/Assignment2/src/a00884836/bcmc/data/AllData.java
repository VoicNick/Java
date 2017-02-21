/**
 * 
 */
package a00884836.bcmc.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
// import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00884836.bcmc.io.db.sql.CustomerDaoTester;
import a00884836.bcmc.ApplicationException;
import a00884836.bcmc.io.CustomerReader;
import a00884836.bcmc.io.InventoryReader;
import a00884836.bcmc.io.InventoryReport;
import a00884836.bcmc.io.MotorcycleReader;
import a00884836.bcmc.io.db.sql.CustomerDao;
import a00884836.bcmc.io.db.sql.MotorcycleDao;
import a00884836.bcmc.io.db.sql.MotorcycleDaoTester;
import a00884836.bcmc.io.db.sql.Database;
import a00884836.bcmc.io.db.sql.InventoryDao;
import a00884836.bcmc.io.db.sql.InventoryDaoTester;
import a00884836.bcmc.data.Customer;

/**
 * @author scirka
 *
 */
public class AllData {

	public static final String DB_PROPERTIES_FILENAME = "db.properties";

	private static final Logger LOG = LogManager.getLogger();

	private static Map<Integer, Motorcycle> motorcycles;
	private static Map<Integer, Customer> customers;
	private static Map<String, Inventory> inventory;

	private static Database database;
	private static CustomerDao customerDao;
	private static MotorcycleDao motorcycleDao;
	private static InventoryDao inventoryDao;
	private static Properties dbProperties;

	private AllData() throws IOException {

	}

	/**
	 * @throws ApplicationException
	 * @throws SQLException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * 
	 */
	public static void loadData() throws ApplicationException, SQLException, FileNotFoundException, IOException {

		dbProperties = new Properties();
		dbProperties.load(new FileReader(DB_PROPERTIES_FILENAME));

		database = new Database(dbProperties);

		database.getConnection();
		customerDao = new CustomerDao(database);
		motorcycleDao = new MotorcycleDao(database);
		inventoryDao = new InventoryDao(database);

		if (!customerDao.create()) {
			LOG.debug("Customer table already in SQL, use dropTables() function to clear all tables if needed");
		} else {
			try {

				LOG.debug("loading customer data into memory");
				customers = CustomerReader.read();
				LOG.debug("successfully loaded customer data into memory");

				LOG.debug("Inserting the customers into SQL");

				for (Customer customer : customers.values()) {
					customerDao.add(customer);
				}

				CustomerDaoTester.getIds(customerDao, database);

			} catch (ApplicationException | SQLException e) {
				LOG.error(e.getMessage());
			}

			LOG.debug("successfully loaded the customers into SQL");
		}

		if (!motorcycleDao.create()) {
			LOG.debug("Motorcycle table already in SQL, use dropTables() function to clear all tables if needed");
		} else {
			try {

				LOG.debug("loading motorcycle data into memory");
				motorcycles = MotorcycleReader.read();
				LOG.debug("successfully loaded motorcycle data into memory");

				LOG.debug("Inserting the motorcycles into SQL");

				for (Motorcycle motorcycle : motorcycles.values()) {
					motorcycleDao.add(motorcycle);
				}

				MotorcycleDaoTester.getIds(motorcycleDao, database);
				LOG.debug("successfully loaded the customers into SQL");
			} catch (ApplicationException | SQLException e) {
				LOG.error(e.getMessage());
			}
		}

		if (!inventoryDao.create()) {
			LOG.debug("Inventory table already in SQL, use dropTables() function to clear all tables if needed");
		} else {
			try {

				LOG.debug("loading invenotry data into memory");
				inventory = InventoryReader.read();
				LOG.debug("successfully loaded inventory data into memory");

				LOG.debug("Inserting the inventory data into SQL");

				for (Inventory oneInventory : inventory.values()) {
					inventoryDao.add(oneInventory);
				}

				InventoryDaoTester.getPartNumbers(inventoryDao, database);
				LOG.debug("successfully loaded the inventory data into SQL");

			} catch (ApplicationException | SQLException e) {
				LOG.error(e.getMessage());
			}
		}
	}

	// test method in case we want to drop the tables in the db
	public static void dropTables() throws SQLException, FileNotFoundException, IOException {
		LOG.debug("dropTables() - dropping all the tables from SQL");

		dbProperties = new Properties();
		dbProperties.load(new FileReader(DB_PROPERTIES_FILENAME));
		database = new Database(dbProperties);

		database.getConnection();
		customerDao = new CustomerDao(database);
		customerDao.drop();

		motorcycleDao = new MotorcycleDao(database);
		motorcycleDao.drop();

		inventoryDao = new InventoryDao(database);
		inventoryDao.drop();
	}

	/**
	 * @return the customers
	 */
	public static Map<Integer, Customer> getCustomers() {
		return customers;
	}

	/**
	 * @return the motorcycles
	 */
	public static Map<Integer, Motorcycle> getMotorcycles() {
		return motorcycles;
	}

	/**
	 * @return the inventory from the memory
	 */
	public static Map<String, Inventory> getInventory() {
		return inventory;
	}

	/**
	 * @return the inventory from the database
	 * @throws SQLException
	 */
	public static List<String> getInventoryPartNumbersFromDB() throws SQLException {
		return (inventoryDao.getPartNumbers());
	}

	public static List<String> getAllMakes() throws SQLException, Exception {
		List<String> allMakes = new ArrayList<>();
		for (Motorcycle m : motorcycleDao.readAllMotorcycles()) {
			allMakes.add(m.getMake());
		}
		return allMakes;

	}

	public static boolean isValidMake(String make) throws SQLException, Exception {
		Boolean validMake = false;
		for (String s : getAllMakes()) {
			if (make.equalsIgnoreCase(s)) {
				validMake = true;
			}
		}
		return validMake;
	}

	public static Inventory getOneInventory(String partNumber) throws SQLException, ApplicationException {

		return (inventoryDao.getInventory(partNumber));
	}

	public static int updateInventory(Inventory inventory) throws SQLException {
		int rowcount = inventoryDao.update(inventory);
		return rowcount;
	}

	public static List<Inventory> getAllInventoryFromDB() throws SQLException, Exception {

		return (inventoryDao.readAllInventorys());
	}

	public static File generateInventoryReportFile(Boolean sortByDescription, Boolean sortByCount, Boolean descending, String filterMake)
			throws FileNotFoundException, SQLException, Exception {

		File reportFile = new File(InventoryReport.REPORT_FILENAME);
		PrintStream out = null;

		LOG.debug("generating the inventory report");
		// InventoryReport.print(System.out);
		out = new PrintStream(reportFile);
		InventoryReport.printInventoryFromDB(out, sortByDescription, sortByCount, descending, filterMake);
		out.close();
		return reportFile;
	}

}
