/**
 * Project: Bcmc
 * File: CustomersReport.java
 * Date: Aug 19, 2016
 * Time: 1:47:36 PM
 */

package a00884836.bcmc.io;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import a00884836.bcmc.BcmcOptions;
import a00884836.bcmc.data.AllData;
import a00884836.bcmc.data.Customer;
import a00884836.bcmc.data.util.Common;

/**
 * @author Sam Cirka, A00123456
 *
 */
public class CustomersReport {

	public static final String REPORT_FILENAME = "customers_report.txt";

	public static final String HORIZONTAL_LINE = "-------------------------------------------------------------------------------------------------------------------------------------------------------";
	public static final String HEADER_FORMAT = "%3s. %-6s %-12s %-12s %-25s %-12s %-12s %-15s %-25s %-12s %-8s";
	public static final String ROW_FORMAT = "%3d. %06d %-12s %-12s %-25s %-12s %-12s %-15s %-25s %s %7d";

	/**
	 * Print the report.
	 * 
	 * @param out
	 */
	public static void print(PrintStream out) {
		String text = null;
		println("Customers Report", out);
		println(HORIZONTAL_LINE, out);
		text = String.format(HEADER_FORMAT, "#", "ID", "First name", "Last name", "Street", "City", "Postal Code", "Phone", "Email", "Join Date", "Length");
		println(text, out);
		println(HORIZONTAL_LINE, out);

		Collection<Customer> customers = AllData.getCustomers().values();

		if (BcmcOptions.isByJoinDateOptionSet()) {
			List<Customer> list = new ArrayList<>(customers);
			if (BcmcOptions.isDescendingOptionSet()) {
				Collections.sort(list, new CompareByJoinedDateDescending());
			} else {
				Collections.sort(list, new CompareByJoinedDate());
			}

			customers = list;
		}

		int i = 0;
		for (Customer customer : customers) {
			LocalDate date = customer.getJoinedDate();
			text = String.format(ROW_FORMAT, ++i, customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getStreet(), customer.getCity(),
					customer.getPostalCode(), customer.getPhone(), customer.getEmailAddress(), Common.DATE_FORMAT.format(date), calculateJoinDuration(date));
			println(text, out);
		}
	}

	private static void println(String text, PrintStream out) {
		out.println(text);
	}

	/**
	 * Calculate how long this person has been a customer
	 * 
	 * @param date
	 *            the join date
	 * @return the age
	 */
	private static long calculateJoinDuration(LocalDate date) {
		return ChronoUnit.YEARS.between(date, LocalDate.now());
	}

	public static class CompareByJoinedDate implements Comparator<Customer> {
		@Override
		public int compare(Customer customer1, Customer customer2) {
			return customer1.getJoinedDate().compareTo(customer2.getJoinedDate());
		}
	}

	public static class CompareByJoinedDateDescending implements Comparator<Customer> {
		@Override
		public int compare(Customer customer1, Customer customer2) {
			return customer2.getJoinedDate().compareTo(customer1.getJoinedDate());
		}
	}
}
