package a00884836.bcmc;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

import a00884836.bcmc.data.AllData;
import a00884836.bcmc.io.CustomersReport;
import a00884836.bcmc.io.InventoryReport;
import a00884836.bcmc.io.ServiceReport;
import a00884836.bcmc.ui.MainFrame;

/**
 * Project: Bcmc
 * File: Bcmc.java
 * Date: Aug 18, 2016
 * Time: 1:22:25 PM
 */

/**
 * @author Sam Cirka, A00123456
 *
 */
public class Bcmc {

	private static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";
	static {
		configureLogging();
	}
	private static final Logger LOG = LogManager.getLogger();

	/**
	 * Bcmc Constructor. Processes the commandline arguments
	 * ex. -inventory -make=honda -by_count -desc -total -service
	 * 
	 * @throws ApplicationException
	 * @throws ParseException
	 */
	public Bcmc(String[] args) throws ApplicationException, ParseException {
		LOG.info("Created Bcmc");

		BcmcOptions.process(args);
	}

	/**
	 * Entry point to GIS
	 * 
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) {
		Instant startTime = Instant.now();
		LOG.info(startTime);

		// start the BC Motorcycle System
		try {
			Bcmc bcmc = new Bcmc(args);
			if (BcmcOptions.isHelpOptionSet()) {
				BcmcOptions.Value[] values = BcmcOptions.Value.values();
				System.out.format("%-5s %-15s %-10s %s%n", "Option", "Long Option", "Has Value", "Description");
				for (BcmcOptions.Value value : values) {
					System.out.format("-%-5s %-15s %-10s %s%n", value.getOption(), ("-" + value.getLongOption()), value.isHasArg(), value.getDescription());
				}

				System.out.println("\nex. -inventory -make=honda -by_count -desc -total -service");

				return;
			}

			bcmc.run();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.debug(e.getMessage());
		}

		Instant endTime = Instant.now();
		LOG.info(endTime);
		LOG.info(String.format("Duration: %d ms", Duration.between(startTime, endTime).toMillis()));
	}

	/**
	 * Configures log4j2 from the external configuration file specified in LOG4J_CONFIG_FILENAME.
	 * If the configuration file isn't found then log4j2's DefaultConfiguration is used.
	 */
	private static void configureLogging() {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);
		} catch (IOException e) {
			System.out.println(String.format("WARNING! Can't find the log4j logging configuration file %s; using DefaultConfiguration for logging.", LOG4J_CONFIG_FILENAME));
			Configurator.initialize(new DefaultConfiguration());
		}
	}

	/**
	 * @throws ApplicationException
	 * @throws SQLException
	 * @throws IOException
	 * 
	 */
	private void run() throws ApplicationException, SQLException, IOException {
		LOG.debug("run()");
		// AllData.dropTables();
		AllData.loadData();
		// generateReports();

		// create the GUI
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
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
					MainFrame frame = new MainFrame();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Generate the reports from the input data
	 * 
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("unused")
	private void generateReports() throws FileNotFoundException {
		LOG.debug("generating the reports");

		PrintStream out = null;

		if (BcmcOptions.isCustomersOptionSet()) {
			LOG.debug("generating the customer report");
			CustomersReport.print(System.out);
			out = getOutputStream(CustomersReport.REPORT_FILENAME);
			CustomersReport.print(out);
			out.close();
		}

		if (BcmcOptions.isServiceOptionSet()) {
			LOG.debug("generating the service report");
			ServiceReport.print(System.out);
			out = new PrintStream(new File(ServiceReport.REPORT_FILENAME));
			ServiceReport.print(out);
			out.close();
		}

		if (BcmcOptions.isInventoryOptionSet()) {
			LOG.debug("generating the inventory report");
			InventoryReport.print(System.out);
			out = new PrintStream(new File(InventoryReport.REPORT_FILENAME));
			InventoryReport.print(out);
			out.close();
		}
	}

	/**
	 * @param reportFilename
	 * @return
	 * @throws ApplicationException
	 * @throws FileNotFoundException
	 */
	private PrintStream getOutputStream(String reportFilename) throws FileNotFoundException {
		PrintStream out = null;
		try {
			out = new PrintStream(new File(CustomersReport.REPORT_FILENAME));
		} catch (FileNotFoundException e) {
			LOG.equals(e);
			throw e;
		}

		return out;
	}

}
