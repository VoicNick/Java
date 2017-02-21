/**
 * Project: A00884836Lab7
 * File: CustomerDao.java
 * Date: Oct 31, 2016
 * Time: 9:45:11 PM
 */
package a00884836.bcmc.io.db.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00884836.bcmc.data.Customer;
import a00884836.bcmc.ApplicationException;
import a00884836.bcmc.io.db.sql.Database;

/**
 * @author Voicu Chirtes, A00884836
 *
 */
public class CustomerDao extends Dao {

	public static final String TABLE_NAME = "A00884836_Assign2_Customers";
	public static final String ID = "ID";
	public static final String FIRSTNAME = "FIRSTNAME";
	public static final String LASTNAME = "LASTNAME";
	public static final String STREET = "STREET";
	public static final String CITY = "CITY";
	public static final String POSTALCODE = "POSTALCODE";
	public static final String PHONE = "PHONE";
	public static final String EMAIL = "EMAIL";
	public static final String JOINDATE = "JOINDATE";

	public static final String DATE_DELIMITER = "-";

	private static final Logger LOG = LogManager.getLogger();

	public CustomerDao(Database database) {
		super(database, TABLE_NAME);
	}

	@Override
	public boolean create() throws SQLException {
		if (Database.tableExists(_tableName)) {
			LOG.info(String.format("Table %s already exists.", _tableName));
			return false;
		}

		LOG.debug(String.format("Creating database table %s", _tableName));
		String createStatement = String.format(
				"create table %s(%s INTEGER, %s VARCHAR(20), %s VARCHAR(20), %s VARCHAR(40), %s VARCHAR(20), %s VARCHAR(10), %s VARCHAR(15), %s VARCHAR(40), %s VARCHAR(10), primary key (%s) )",
				_tableName, //
				ID, FIRSTNAME, LASTNAME, STREET, CITY, POSTALCODE, PHONE, EMAIL, JOINDATE, ID);
		super.create(createStatement);
		return true;
	}

	public void add(Customer customer) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = _database.getConnection();
			statement = connection.createStatement();
			String insertString = String.format("insert into %s values('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", _tableName, //
					Integer.toString(customer.getId()), //
					customer.getFirstName(), //
					customer.getLastName(), //
					customer.getStreet(), //
					customer.getCity(), //
					customer.getPostalCode(), //
					customer.getPhone(), //
					customer.getEmailAddress(), //
					customer.getJoinedDate().toString());
			LOG.debug(insertString);
			int result = statement.executeUpdate(insertString);
			// System.out.println(insertString);
			LOG.debug(String.format("Adding Customer %s was %ssuccessful", customer, ((result == 1) ? "" : "un")));
		} finally {
			close(statement);
		}
	}

	public Customer getCustomer(String id) throws SQLException, ApplicationException {
		Connection connection;
		Statement statement = null;
		Customer customer = null;
		try {
			connection = _database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("SELECT * FROM %s WHERE %s = '%s'", _tableName, ID, id);
			LOG.debug(sqlString);
			ResultSet resultSet = statement.executeQuery(sqlString);

			// get the Customer
			// throw an exception if we get more than one result
			int count = 0;
			while (resultSet.next()) {
				count++;
				if (count > 1) {
					throw new ApplicationException(String.format("Expected one result, got %d", count));
				}

				String[] date = resultSet.getString(JOINDATE).split(DATE_DELIMITER);

				int id_int = Integer.parseInt(id);
				int year = Integer.parseInt(date[0]);
				int month = Integer.parseInt(date[1]);
				int day = Integer.parseInt(date[2]);

				customer = new Customer.Builder(id_int, resultSet.getString(PHONE)).setFirstName(resultSet.getString(FIRSTNAME)).setLastName(resultSet.getString(LASTNAME))
						.setStreet(resultSet.getString(STREET)).setCity(resultSet.getString(CITY)).setPostalCode(resultSet.getString(POSTALCODE))
						.setEmailAddress(resultSet.getString(EMAIL)).setJoinedDate(LocalDate.of(year, month - 1, day)).build();
			}
		} finally {
			close(statement);
		}

		return customer;
	}

	public List<Customer> readAllCustomers() throws SQLException, Exception {
		List<Customer> customers = new ArrayList<>();
		Connection connection;
		Statement statement = null;
		Customer customer = null;
		try {
			connection = _database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("SELECT * FROM %s", _tableName);
			ResultSet resultSet = statement.executeQuery(sqlString);

			// get the Customer and add it to the arrayList
			while (resultSet.next()) {
				String[] date = resultSet.getString(JOINDATE).split(DATE_DELIMITER);

				int year = Integer.parseInt(date[0]);
				int month = Integer.parseInt(date[1]);
				int day = Integer.parseInt(date[2]);

				customer = new Customer.Builder(resultSet.getInt(ID), resultSet.getString(PHONE)).setFirstName(resultSet.getString(FIRSTNAME))
						.setLastName(resultSet.getString(LASTNAME)).setStreet(resultSet.getString(STREET)).setCity(resultSet.getString(CITY))
						.setPostalCode(resultSet.getString(POSTALCODE)).setEmailAddress(resultSet.getString(EMAIL)).setJoinedDate(LocalDate.of(year, month - 1, day)).build();
				customers.add(customer);
			}
		} finally {
			close(statement);
		}

		return customers;
	}

	public List<String> geIds() throws SQLException {
		List<String> ids = new ArrayList<>();
		Connection connection;
		Statement statement = null;
		try {
			connection = _database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("SELECT %s FROM %s", ID, _tableName);
			ResultSet resultSet = statement.executeQuery(sqlString);

			// get the Customer ID and add it to the arrayList
			while (resultSet.next()) {
				ids.add(resultSet.getString(ID));
			}
		} finally {
			close(statement);
		}
		LOG.debug(String.format("Loaded %s customer IDs from the database", ids.size()));
		return ids;
	}

	public void update(Customer customer) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = _database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("UPDATE %s set %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s' WHERE %s='%s'", _tableName, //
					ID, customer.getId(), //
					FIRSTNAME, customer.getFirstName(), //
					LASTNAME, customer.getLastName(), //
					STREET, customer.getStreet(), //
					CITY, customer.getCity(), //
					POSTALCODE, customer.getPostalCode(), //
					PHONE, customer.getPhone(), //
					EMAIL, customer.getEmailAddress(), //
					JOINDATE, customer.getJoinedDate(), //
					ID, customer.getId());

			System.out.println(sqlString);
			int rowcount = statement.executeUpdate(sqlString);
			System.out.println(String.format("Updated %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public void delete(Customer customer) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = _database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("DELETE from %s WHERE %s='%s'", _tableName, ID, customer.getId());
			System.out.println(sqlString);
			int rowcount = statement.executeUpdate(sqlString);
			System.out.println(String.format("Deleted %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public int getCustomerCount() throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = _database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("SELECT COUNT(*) FROM %s", _tableName);
			System.out.println(sqlString);

			ResultSet rs = statement.executeQuery(sqlString);
			rs.next();
			int count = rs.getInt(1);
			rs.close();
			return count;
		} finally {
			close(statement);
		}
	}
}
