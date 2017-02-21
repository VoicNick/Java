/**
 * Project: A00884836Lab7
 * File: MotorcycleDao.java
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

import a00884836.bcmc.data.Motorcycle;
import a00884836.bcmc.ApplicationException;
import a00884836.bcmc.io.db.sql.Database;

/**
 * @author Voicu Chirtes, A00884836
 *
 */
public class MotorcycleDao extends Dao {

	public static final String TABLE_NAME = "A00884836_Assign2_Motorcycles";
	public static final String ID = "ID";
	public static final String MAKE = "MAKE";
	public static final String MODEL = "MODEL";
	public static final String YEAR = "YEAR";
	public static final String SERIALNUMBER = "SERIALNUMBER";
	public static final String MILEAGE = "MILEAGE";
	public static final String CUSTOMERID = "CUSTOMERID";

	public static final String DATE_DELIMITER = "-";

	private static final Logger LOG = LogManager.getLogger();

	public MotorcycleDao(Database database) {
		super(database, TABLE_NAME);
	}

	@Override
	public boolean create() throws SQLException {
		if (Database.tableExists(_tableName)) {
			LOG.info(String.format("Table %s already exists.", _tableName));
			return false;
		}

		LOG.debug(String.format("Creating database table %s", _tableName));
		String createStatement = String.format("create table %s(%s INTEGER, %s VARCHAR(20), %s VARCHAR(20), %s INTEGER, %s VARCHAR(20), %s INTEGER, %s INTEGER, primary key (%s) )",
				_tableName, //
				ID, MAKE, MODEL, YEAR, SERIALNUMBER, MILEAGE, CUSTOMERID, ID);
		super.create(createStatement);
		return true;
	}

	public void add(Motorcycle motorcycle) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = _database.getConnection();
			statement = connection.createStatement();
			String insertString = String.format("insert into %s values('%s', '%s', '%s', '%s', '%s', '%s', '%s')", _tableName, //
					Integer.toString(motorcycle.getId()), //
					motorcycle.getMake(), motorcycle.getModel(), //
					motorcycle.getYear(), //
					motorcycle.getSerialNumber(), //
					motorcycle.getMileage(), //
					motorcycle.getCustomerId());
			LOG.debug(insertString);
			int result = statement.executeUpdate(insertString);
			// System.out.println(insertString);
			LOG.debug(String.format("Adding motorcycle %s was %ssuccessful", motorcycle, ((result == 1) ? "" : "un")));
		} finally {
			close(statement);
		}
	}

	public Motorcycle getMotorcycle(String id) throws SQLException, ApplicationException {
		Connection connection;
		Statement statement = null;
		Motorcycle motorcycle = null;
		try {
			connection = _database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("SELECT * FROM %s WHERE %s = '%s'", _tableName, ID, id);
			LOG.debug(sqlString);
			ResultSet resultSet = statement.executeQuery(sqlString);

			// get the motorcycle
			// throw an exception if we get more than one result
			int count = 0;
			while (resultSet.next()) {
				count++;
				if (count > 1) {
					throw new ApplicationException(String.format("Expected one result, got %d", count));
				}

				// String[] date = resultSet.getString(JOINDATE).split(DATE_DELIMITER);

				int id_int = Integer.parseInt(id);
				// int year = Integer.parseInt(date[0]);
				// int month = Integer.parseInt(date[1]);
				// int day = Integer.parseInt(date[2]);

				motorcycle = new Motorcycle.Builder(id_int).setMake(resultSet.getString(MAKE)).setModel(resultSet.getString(MODEL)).setYear(resultSet.getInt(YEAR))
						.setSerialNumber(resultSet.getString(SERIALNUMBER)).setMileage(resultSet.getInt(MILEAGE)).setCustomerId(Integer.parseInt(resultSet.getString(CUSTOMERID)))
						.build();
			}
		} finally {
			close(statement);
		}

		return motorcycle;
	}

	public List<Motorcycle> readAllMotorcycles() throws SQLException, Exception {
		List<Motorcycle> motorcycles = new ArrayList<>();
		Connection connection;
		Statement statement = null;
		Motorcycle motorcycle = null;
		try {
			connection = _database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("SELECT * FROM %s", _tableName);
			ResultSet resultSet = statement.executeQuery(sqlString);

			// get the motorcycle and add it to the arrayList
			while (resultSet.next()) {

				motorcycle = new Motorcycle.Builder(resultSet.getInt(ID)).setMake(resultSet.getString(MAKE)).setModel(resultSet.getString(MODEL)).setYear(resultSet.getInt(YEAR))
						.setSerialNumber(resultSet.getString(SERIALNUMBER)).setMileage(resultSet.getInt(MILEAGE)).build();
				motorcycles.add(motorcycle);
			}
		} finally {
			close(statement);
		}

		return motorcycles;
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

			// get the motorcycle ID and add it to the arrayList
			while (resultSet.next()) {
				ids.add(resultSet.getString(ID));
			}
		} finally {
			close(statement);
		}
		LOG.debug(String.format("Loaded %s motorcycle IDs from the database", ids.size()));
		return ids;
	}

	public void update(Motorcycle motorcycle) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = _database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("UPDATE %s set %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s' WHERE %s='%s'", _tableName, //
					ID, motorcycle.getId(), //
					MAKE, motorcycle.getMake(), //
					MODEL, motorcycle.getModel(), //
					YEAR, motorcycle.getYear(), //
					SERIALNUMBER, motorcycle.getSerialNumber(), //
					MILEAGE, motorcycle.getMileage(), //
					CUSTOMERID, motorcycle.getCustomerId(), //
					ID, motorcycle.getId());

			System.out.println(sqlString);
			int rowcount = statement.executeUpdate(sqlString);
			System.out.println(String.format("Updated %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public void delete(Motorcycle motorcycle) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = _database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sqlString = String.format("DELETE from %s WHERE %s='%s'", _tableName, ID, motorcycle.getId());
			System.out.println(sqlString);
			int rowcount = statement.executeUpdate(sqlString);
			System.out.println(String.format("Deleted %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public int getMotorcycleCount() throws SQLException {
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
