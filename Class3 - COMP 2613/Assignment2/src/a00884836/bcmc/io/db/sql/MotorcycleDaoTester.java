/**
 * Project: A00884836Lab7
 * File: CustomerDaoTester.java
 * Date: Nov 2, 2016
 * Time: 3:07:40 PM
 */
package a00884836.bcmc.io.db.sql;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00884836.bcmc.ApplicationException;
import a00884836.bcmc.io.db.sql.Database;

/**
 * @author Voicu Chirtes, A00884836
 *
 */
public class MotorcycleDaoTester {

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * 
	 */
	public MotorcycleDaoTester() {
		// TODO Auto-generated constructor stub
	}

	public static void getIds(MotorcycleDao motorcycleDao, Database database) throws SQLException, ApplicationException {
		List<String> list = motorcycleDao.geIds();
		for (String s : list) {
			LOG.info(s);
			LOG.info(motorcycleDao.getMotorcycle(s));
		}
	}

}
