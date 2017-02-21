/**
 * Project: Bcmc
 * File: CustomersReport.java
 * Date: Aug 19, 2016
 * Time: 1:47:36 PM
 */

package a00884836.bcmc.io;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import a00884836.bcmc.BcmcOptions;
import a00884836.bcmc.data.AllData;
import a00884836.bcmc.data.Inventory;

/**
 * @author Sam Cirka, A00123456
 *
 */
public class InventoryReport {

	public static final String REPORT_FILENAME = "inventory_report.txt";

	public static final String HORIZONTAL_LINE = "-------------------------------------------------------------------------------------------";
	public static final String HORIZONTAL_LINE_VALUE = "-------------------------------------------------------------------------------------------------------";
	public static final String HEADER_FORMAT = "%-28s %-28s %-12s %8s %11s";
	public static final String HEADER_WITH_VALUE_FORMAT = "%-28s %-28s %-12s %8s %11s %11s";
	public static final String ROW_FORMAT = "%-28s %-28s %-12s %,8.2f %,11d";
	public static final String ROW_WITH_VALUE_FORMAT = "%-28s %-28s %-12s %,8.2f %11d %,11.2f";

	/**
	 * Print the report.
	 * 
	 * @param out
	 */
	public static void print(PrintStream out) {
		String text = null;
		boolean hasTotal = BcmcOptions.isTotalOptionSet();

		println("Inventory Report", out);

		if (hasTotal) {
			println(HORIZONTAL_LINE_VALUE, out);
			text = String.format(HEADER_WITH_VALUE_FORMAT, "Make+Model", "Description", "Part#", "Price", "Quantity", "Value");
			println(text, out);
			println(HORIZONTAL_LINE_VALUE, out);
		} else {
			println(HORIZONTAL_LINE, out);
			text = String.format(HEADER_FORMAT, "Make+Model", "Description", "Part#", "Price", "Quantity");
			println(text, out);
			println(HORIZONTAL_LINE, out);
		}

		Collection<Inventory> inventory = AllData.getInventory().values();

		if (BcmcOptions.isByDescriptionOptionSet()) {
			List<Inventory> list = new ArrayList<>(inventory);
			if (BcmcOptions.isDescendingOptionSet()) {
				Collections.sort(list, new CompareByDescriptionDescending());
			} else {
				Collections.sort(list, new CompareByDescription());
			}

			inventory = list;
		}

		if (BcmcOptions.isByCountOptionSet()) {
			List<Inventory> list = new ArrayList<>(inventory);
			if (BcmcOptions.isDescendingOptionSet()) {
				Collections.sort(list, new CompareByCountDescending());
			} else {
				Collections.sort(list, new CompareByCount());
			}

			inventory = list;
		}

		String make = BcmcOptions.getMake();
		if (make != null) {
			make = make.toLowerCase();
		}
		long total = 0;
		for (Inventory item : inventory) {
			String motorcycleId = item.getMotorcycleId();
			if (make != null && !motorcycleId.toLowerCase().contains(make)) {
				continue;
			}
			int price = item.getPrice();
			int quantity = item.getQuantity();

			if (hasTotal) {
				long value = price * quantity;
				total += value;
				text = String.format(ROW_WITH_VALUE_FORMAT, motorcycleId, item.getDescription(), item.getPartNumber(), price / 100.0f, quantity, value / 100.0f);
			} else {
				text = String.format(ROW_FORMAT, motorcycleId, item.getDescription(), item.getPartNumber(), price / 100.0f, quantity);
			}
			println(text, out);
		}

		if (hasTotal) {
			text = String.format("Value of current inventory: $%,.2f", total / 100.0f);
			println(text, out);
		}

	}

	// prints the inventory report to a PrintStream and reads the data from the database
	public static void printInventoryFromDB(PrintStream out, Boolean sortByDescription, Boolean sortByCount, Boolean descending, String filterMake) throws SQLException, Exception {
		String text = null;
		boolean hasTotal = BcmcOptions.isTotalOptionSet();

		println("Inventory Report", out);

		if (hasTotal) {
			println(HORIZONTAL_LINE_VALUE, out);
			text = String.format(HEADER_WITH_VALUE_FORMAT, "Make+Model", "Description", "Part#", "Price", "Quantity", "Value");
			println(text, out);
			println(HORIZONTAL_LINE_VALUE, out);
		} else {
			println(HORIZONTAL_LINE, out);
			text = String.format(HEADER_FORMAT, "Make+Model", "Description", "Part#", "Price", "Quantity");
			println(text, out);
			println(HORIZONTAL_LINE, out);
		}

		Collection<Inventory> inventory = AllData.getAllInventoryFromDB();

		if (sortByDescription) {
			List<Inventory> list = new ArrayList<>(inventory);
			if (descending) {
				Collections.sort(list, new CompareByDescriptionDescending());
			} else {
				Collections.sort(list, new CompareByDescription());
			}

			inventory = list;
		}

		if (sortByCount) {
			List<Inventory> list = new ArrayList<>(inventory);
			if (descending) {
				Collections.sort(list, new CompareByCountDescending());
			} else {
				Collections.sort(list, new CompareByCount());
			}

			inventory = list;
		}

		if (filterMake != null) {
			filterMake = filterMake.toLowerCase();
		}
		long total = 0;
		for (Inventory item : inventory) {
			String motorcycleId = item.getMotorcycleId();
			if (filterMake != null && !motorcycleId.toLowerCase().contains(filterMake)) {
				continue;
			}
			int price = item.getPrice();
			int quantity = item.getQuantity();

			if (hasTotal) {
				long value = price * quantity;
				total += value;
				text = String.format(ROW_WITH_VALUE_FORMAT, motorcycleId, item.getDescription(), item.getPartNumber(), price / 100.0f, quantity, value / 100.0f);
			} else {
				text = String.format(ROW_FORMAT, motorcycleId, item.getDescription(), item.getPartNumber(), price / 100.0f, quantity);
			}
			println(text, out);
		}

		if (hasTotal) {
			text = String.format("Value of current inventory: $%,.2f", total / 100.0f);
			println(text, out);
		}

	}

	private static void println(String text, PrintStream out) {
		out.println(text);
	}

	public static class CompareByDescription implements Comparator<Inventory> {
		@Override
		public int compare(Inventory item1, Inventory item2) {
			return item1.getDescription().compareTo(item2.getDescription());
		}
	}

	public static class CompareByDescriptionDescending implements Comparator<Inventory> {
		@Override
		public int compare(Inventory item1, Inventory item2) {
			return item2.getDescription().compareTo(item1.getDescription());
		}
	}

	public static class CompareByCount implements Comparator<Inventory> {
		@Override
		public int compare(Inventory item1, Inventory item2) {
			return item1.getQuantity() - item2.getQuantity();
		}
	}

	public static class CompareByCountDescending implements Comparator<Inventory> {
		@Override
		public int compare(Inventory item1, Inventory item2) {
			return item2.getQuantity() - item1.getQuantity();
		}
	}
}
