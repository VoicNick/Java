/**
 * Project: A00884836Lab7
 * File: InventoryDao.java
 * Date: Oct 31, 2016
 * Time: 9:45:11 PM
 */
package a00884836.bcmc.io.db.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00884836.bcmc.data.Inventory;
import a00884836.bcmc.ApplicationException;
import a00884836.bcmc.io.db.sql.Database;

/**
 * @author Voicu Chirtes, A00884836
 *
 */
public class InventoryDao extends Dao {

	public static final String TABLE_NAME = "A00884836_Assign2_Inventory";
	public static final String PARTNUMBER = "PARTNUMBER";
	public static final String DESCRIPTION = "DESCRIPTION";
	public static final String PRICE = "PRICE";
	public static final String QUANTITY = "QUANTITY";
	public static final String MOTORCYCLEID = "MOTORCYCLEID";

	public static final String DATE_DELIMITER = "-";

	private static final Logger LOG = LogManager.getLogger();

	public InventoryDao(Database database) {
		super(database, TABLE_NAME);
	}

	@Override
	public boolean create() throws SQLException {
		if (Database.tableExists(_tableName)) {
			LOG.info(String.format("Table %s already exists.", _tableName));
			return false;
		}

		LOG.debug(String.format("Creating database table %s", _tableName));
		String createStatement = String.format("create table %s(%s VARCHAR(40), %s VARCHAR(30), %s VARCHAR(20), %s INTEGER, %s INTEGER)", _tableName, MOTORCYCLEID, DESCRIPTION,
				PARTNUMBER, PRICE, QUANTITY);
		super.create(createStatement);
		return true;
	}

	public void add(Inventory inventory) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = _database.getConnection();
			statement = connection.createStatement();
			String insertString = String.format("insert into %s values('%s', '%s', '%s', '%s', '%s')", _tableName, //
					inventory.getMotorcycleId(), //
					inventory.getDescription(), //
					inventory.getPartNumber(), //
					Integer.toString(inventory.getPrice()), //
					Integer.toString(inventory.getQuantity()));
			LOG.debug(insertString);
			int result = statement.executeUpdate(insertString);
			// System.out.println(insertString);
			LOG.debug(String.format("Adding Inventory %s was %ssuccessful", inventory, ((result == 1) ? "" : "un")));
		} finally {
			close(statement);
		}
	}

	public Inventory getInventory(String partNumber) throws SQLException, ApplicationException {
		Connection connection;
		Statement statement = null;
		Inventory inventory = null;
		try {
			connection = _database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("SELECT * FROM %s WHERE %s = '%s'", _tableName, PARTNUMBER, partNumber);
			LOG.debug(sqlString);
			ResultSet resultSet = statement.executeQuery(sqlString);

			// get the inventory
			// int count = 0;
			while (resultSet.next()) {
				// count++;
				// if (count > 1) {
				// throw new ApplicationException(String.format("Expected one result, got %d", count));
				// }

				inventory = new Inventory.Builder(resultSet.getString(PARTNUMBER)).setDescription(resultSet.getString(DESCRIPTION)).setPrice(resultSet.getInt(PRICE))
						.setQuantity(resultSet.getInt(QUANTITY)).setMotorcycleId(resultSet.getString(MOTORCYCLEID)).build();
			}
		} finally {
			close(statement);
		}

		return inventory;
	}

	public List<Inventory> readAllInventorys() throws SQLException, Exception {
		List<Inventory> inventorys = new ArrayList<>();
		Connection connection;
		Statement statement = null;
		Inventory inventory = null;
		try {
			connection = _database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("SELECT * FROM %s", _tableName);
			ResultSet resultSet = statement.executeQuery(sqlString);
			LOG.debug(String.format("Running the SQL query: \n %s \n", sqlString));

			// get the Inventory and add it to the arrayList
			while (resultSet.next()) {
				inventory = new Inventory.Builder(resultSet.getString(PARTNUMBER)).setDescription(resultSet.getString(DESCRIPTION)).setPrice(resultSet.getInt(PRICE))
						.setQuantity(resultSet.getInt(QUANTITY)).setMotorcycleId(resultSet.getString(MOTORCYCLEID)).build();
				inventorys.add(inventory);
			}
		} finally {
			close(statement);
		}

		return inventorys;
	}

	public List<String> getPartNumbers() throws SQLException {
		List<String> ids = new ArrayList<>();
		Connection connection;
		Statement statement = null;
		try {
			connection = _database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("SELECT %s FROM %s", PARTNUMBER, _tableName);
			LOG.debug(String.format("Running the SQL query: \n %s \n", sqlString));
			ResultSet resultSet = statement.executeQuery(sqlString);

			// get the Motorcycle ID and add it to the arrayList
			while (resultSet.next()) {
				ids.add(resultSet.getString(PARTNUMBER));
			}
		} finally {
			close(statement);
		}
		LOG.debug(String.format("Loaded %s Motorcycle IDs from the database", ids.size()));
		return ids;
	}

	// This method should be revisited once we design a proper PK for the Inventory table
	public int update(Inventory inventory) throws SQLException {
		Connection connection;
		Statement statement = null;
		int rowcount = -1;
		try {
			connection = _database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String updateString = String.format("update %s set %s='%s', %s='%s', %s='%s', %s='%s' where %s='%s'", _tableName, //
					MOTORCYCLEID, inventory.getMotorcycleId(), //
					DESCRIPTION, inventory.getDescription(), //
					PRICE, Integer.toString(inventory.getPrice()), //
					QUANTITY, Integer.toString(inventory.getQuantity()), PARTNUMBER, inventory.getPartNumber());

			// System.out.println(updateString);
			LOG.debug(String.format("Running the SQL query: \n %s \n", updateString));
			rowcount = statement.executeUpdate(updateString);
			// System.out.println(String.format("Updated %d rows", rowcount));
			LOG.debug(String.format("Updated %d rows", rowcount));
		} finally {
			close(statement);
		}
		return rowcount;
	}

	// deletes all the entries for a specific Motorcycle ID
	public void delete(Inventory inventory) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = _database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("DELETE from %s WHERE %s='%s'", _tableName, MOTORCYCLEID, inventory.getMotorcycleId());
			System.out.println(sqlString);
			int rowcount = statement.executeUpdate(sqlString);
			System.out.println(String.format("Deleted %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public int getInventoryCount() throws SQLException {
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
