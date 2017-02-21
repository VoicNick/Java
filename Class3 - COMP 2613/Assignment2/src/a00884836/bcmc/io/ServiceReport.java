/**
 * Project: Bcmc
 * File: ServiceReport.java
 */

package a00884836.bcmc.io;

import java.io.PrintStream;
import java.util.Map;

import a00884836.bcmc.BcmcOptions;
import a00884836.bcmc.data.AllData;
import a00884836.bcmc.data.Customer;
import a00884836.bcmc.data.Motorcycle;

/**
 * @author Sam Cirka, A00123456
 *
 */
public class ServiceReport {

	public static final String REPORT_FILENAME = "service_report.txt";

	public static final String HORIZONTAL_LINE = "----------------------------------------------------------------------------------";

	public static final String HEADER_FORMAT = "%-12s %-12s %-20s %-20s %-4s %9s";
	public static final String ROW_FORMAT = "%-12s %-12s %-20s %-20s %-4s %,9d";

	/**
	 * Print the report.
	 * 
	 * @param out
	 */
	public static void print(PrintStream out) {
		String text = null;
		println("Service Report", out);
		println(HORIZONTAL_LINE, out);
		text = String.format(HEADER_FORMAT, "First name", "Last name", "Make", "Model", "Year", "Mileage");
		println(text, out);
		println(HORIZONTAL_LINE, out);

		String makeOption = BcmcOptions.getMake();
		Map<Integer, Customer> customers = AllData.getCustomers();
		for (Motorcycle motorcycle : AllData.getMotorcycles().values()) {
			String make = motorcycle.getMake();
			if (makeOption != null && !make.equalsIgnoreCase(makeOption)) {
				continue;
			}
			long customerId = motorcycle.getCustomerId();
			Customer customer = customers.get(customerId);
			text = String.format(ROW_FORMAT, customer.getFirstName(), customer.getLastName(), motorcycle.getMake(), motorcycle.getModel(), motorcycle.getYear(),
					motorcycle.getMileage());
			println(text, out);
		}
	}

	private static void println(String text, PrintStream out) {
		out.println(text);
	}

}
