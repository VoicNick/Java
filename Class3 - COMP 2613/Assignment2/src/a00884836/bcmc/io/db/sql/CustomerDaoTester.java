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
import a00884836.bcmc.io.db.sql.CustomerDao;
import a00884836.bcmc.io.db.sql.Database;

/**
 * @author Voicu Chirtes, A00884836
 *
 */
public class CustomerDaoTester {

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * 
	 */
	public CustomerDaoTester() {
		// TODO Auto-generated constructor stub
	}

	public static void getIds(CustomerDao customerDao, Database database) throws SQLException, ApplicationException {
		List<String> list = customerDao.geIds();
		for (String s : list) {
			LOG.info(s);
			LOG.info(customerDao.getCustomer(s));
		}
	}

}
