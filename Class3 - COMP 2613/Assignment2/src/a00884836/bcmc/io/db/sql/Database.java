/**
 * Project: A00884836Lab7
 * File: Database.java
 * Date: Oct 31, 2016
 * Time: 8:35:31 PM
 */
package a00884836.bcmc.io.db.sql;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Voicu Chirtes, A00884836
 *
 */
public class Database {

	public static final String DB_DRIVER_KEY = "db.driver";
	public static final String DB_URL_KEY = "db.url";
	public static final String DB_USER_KEY = "db.user";
	public static final String DB_PASSWORD_KEY = "db.password";

	private static final Logger LOG = LogManager.getLogger();

	private static Connection connection;
	private final Properties _properties;

	public Database(Properties properties) throws FileNotFoundException, IOException {
		LOG.debug("Loading database properties from db.properties");
		_properties = properties;
	}

	public Connection getConnection() throws SQLException {
		if (connection != null) {
			return connection;
		}

		try {
			connect();
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}

		return connection;
	}

	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName(_properties.getProperty(DB_DRIVER_KEY));
		System.out.println("Driver loaded");
		connection = DriverManager.getConnection(_properties.getProperty(DB_URL_KEY), _properties.getProperty(DB_USER_KEY), _properties.getProperty(DB_PASSWORD_KEY));
		System.out.println("Database connected");
	}

	public void shutdown() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean tableExists(String tableName) throws SQLException {
		DatabaseMetaData databaseMetaData = connection.getMetaData();
		ResultSet resultSet = null;
		String rsTableName = null;

		try {
			resultSet = databaseMetaData.getTables(connection.getCatalog(), "%", "%", null);
			while (resultSet.next()) {
				rsTableName = resultSet.getString("TABLE_NAME");
				if (rsTableName.equalsIgnoreCase(tableName)) {
					LOG.debug(String.format("Found the target table named: %s", tableName));
					return true;
				}
			}
		} finally {
			resultSet.close();
		}

		return false;
	}

}
