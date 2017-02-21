/**
 * 
 */
package a00884836.bcmc;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Extract the program options from the commandline arguments and store them for safekeeping.
 * 
 * @author scirka
 *
 */
public class BcmcOptions {
	private static CommandLine commandLine;
	private static boolean service;
	private static boolean inventory;
	private static boolean customers;

	/**
	 * service Print the service report
	 * inventory Print the inventory report
	 * customers Print the customer report
	 * total Print the inventory report adding a Value column and calculated value for each part and the total value of the inventory is added to the
	 * end of the report.
	 * by_description Sorts the inventory report by part description name ascending. This is ignored if ‘inventory’ isn’t also specified
	 * by_count Sorts the inventory report by part count ascending
	 * by_join_date Sorts the customer report by join date
	 * make=<make> Filters the service or inventory report by make ascending
	 * desc Any sorted value is sorted in a descending order
	 */

	private BcmcOptions() {
	}

	/**
	 * Process the commandline arguments and set the program options.
	 * Although it may be overkill to use apache commons CLI to process the commandline, its used use to demonstrate its use.
	 * 
	 * @param args
	 *            Commandline arguments.
	 * @throws ParseException
	 */
	public static void process(String[] args) throws ParseException {
		commandLine = new DefaultParser().parse(createOptions(), args);

		if (args.length == 0) {
			service = true;
			inventory = true;
			customers = true;
		} else {
			service = commandLine.hasOption(Value.SERVICE.getOption());
			inventory = commandLine.hasOption(Value.INVENTORY.getOption());
			customers = commandLine.hasOption(Value.CUSTOMERS.getOption());
		}
	}

	/**
	 * @return the service
	 */
	public static boolean isServiceOptionSet() {
		return service;
	}

	/**
	 * @return the inventory
	 */
	public static boolean isInventoryOptionSet() {
		return inventory;
	}

	/**
	 * @return the customers
	 */
	public static boolean isCustomersOptionSet() {
		return customers;
	}

	/**
	 * @return the total
	 */
	public static boolean isTotalOptionSet() {
		return commandLine.hasOption(Value.TOTAL.getOption());
	}

	/**
	 * @return the byDescription
	 */
	public static boolean isByDescriptionOptionSet() {
		return commandLine.hasOption(Value.BY_DESCRIPTION.getOption());
	}

	/**
	 * @return the byCount
	 */
	public static boolean isByCountOptionSet() {
		return commandLine.hasOption(Value.BY_COUNT.getOption());
	}

	/**
	 * @return the byJoinDate
	 */
	public static boolean isByJoinDateOptionSet() {
		return commandLine.hasOption(Value.SERVICE.getOption());
	}

	/**
	 * @return the make
	 */
	public static String getMake() {
		return commandLine.getOptionValue(Value.MAKE.getOption());
	}

	/**
	 * @return the descending
	 */
	public static boolean isDescendingOptionSet() {
		return commandLine.hasOption(Value.DESCENDING.getOption());
	}

	/**
	 * @return the descending
	 */
	public static boolean isHelpOptionSet() {
		return commandLine.hasOption(Value.HELP.getOption());
	}

	private static Options createOptions() {
		// create Options object
		Options options = new Options();

		for (Value value : Value.values()) {
			Option option = null;

			if (value.hasArg) {
				option = Option.builder(value.option).longOpt(value.longOption).hasArg().desc(value.description).build();
			} else {
				option = Option.builder(value.option).longOpt(value.longOption).desc(value.description).build();
			}

			options.addOption(option);
		}

		return options;
	}

	public enum Value {
		HELP("?", "help", false, "Display help"), //
		SERVICE("s", "service", false, "Run the Service report"), //
		INVENTORY("i", "inventory", false, "Run the Inventory report"), //
		CUSTOMERS("c", "customers", false, "Run the Customers report"), //
		TOTAL("t", "total", false, "Calculate the inventory item total and grand total for the selected inventory"), //
		BY_DESCRIPTION("D", "by_description", false, "Sort the inventory by description"), //
		BY_COUNT("C", "by_count", false, "Sort the inventory by quantity"), //
		BY_JOIN_DATE("J", "by_join_date", false, "Sort the customers by join date"), //
		MAKE("m", "make", true, "Filter by make - case insensitive"), //
		DESCENDING("d", "descending", false, "Any sort is now displayed in descending order");

		private final String option;
		private final String longOption;
		private final boolean hasArg;
		private final String description;

		Value(String option, String longOption, boolean hasArg, String description) {
			this.option = option;
			this.longOption = longOption;
			this.hasArg = hasArg;
			this.description = description;
		}

		/**
		 * @return the option
		 */
		public String getOption() {
			return option;
		}

		/**
		 * @return the longOption
		 */
		public String getLongOption() {
			return longOption;
		}

		/**
		 * @return the hasArg
		 */
		public boolean isHasArg() {
			return hasArg;
		}

		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}

	}

}
