/**
 * Project: A00884836Lab7
 * File: Dao.java
 * Date: Oct 31, 2016
 * Time: 9:27:27 PM
 */
package a00884836.io.db.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00884836.io.db.sql.Database;
import a00884836.io.db.sql.DbUtil;

/**
 * @author Voicu Chirtes, A00884836
 *
 */
public abstract class Dao {

	protected final Database _database;
	protected final String _tableName;
	private static final Logger LOG = LogManager.getLogger();

	protected Dao(Database database, String tableName) {
		_database = database;
		_tableName = tableName;
	}

	public abstract boolean create() throws SQLException;

	protected boolean create(String createStatement) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = _database.getConnection();
			statement = connection.createStatement();
			LOG.debug(createStatement);
			statement.executeUpdate(createStatement);
			return true;
		} finally {
			close(statement);
		}
	}

	protected void add(String updateStatement) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = _database.getConnection();
			statement = connection.createStatement();
			statement.executeUpdate(updateStatement);
		} finally {
			close(statement);
		}
	}

	public void drop() throws SQLException {
		Statement statement = null;
		try {
			Connection connection = _database.getConnection();
			statement = connection.createStatement();
			if (DbUtil.tableExists(connection, _tableName)) {
				statement.executeUpdate("drop table " + _tableName);
				LOG.debug(String.format("drop table %s", _tableName));
			}
		} finally {
			close(statement);
		}
	}

	protected void close(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
