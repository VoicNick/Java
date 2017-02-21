/**
 * Project: A00884836_Assignment1
 * File: DataReader.java
 * Date: Oct 27, 2016
 * Time: 2:41:45 PM
 */
package a00884836.io;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import a00884836.data.Customer;
import a00884836.data.Motorcycle;
import a00884836.data.Part;
import a00884836.data.ServicedMotorcycleWithParts;
import a00884836.exception.ApplicationException;
import a00884836.tools.Validator;

/**
 * @author Voicu Chirtes, A00884836
 *
 */
public class DataReader {

	public static final String FIELD_DELIMITER = "\\|";

	/**
	 * 
	 */
	public DataReader() {
	}

	/**
	 * Read the data from a file and parse it into
	 * an array of Customer objects.
	 * Data is assumed correct format,
	 * only the email address is validated in this version of the program.
	 * 
	 * @param inputFileName
	 *            the name of the file containing the input data
	 *            this must be in the same folder as the application
	 *            expected File Format:
	 *            Header line then data lines, one customer per line in the format:
	 *            ID|FIRST_NAME|LAST_NAME|STREET|CITY|POSTAL_CODE|PHONE|EMAIL|JOIN_DATE
	 * @return the array of Customer objects
	 * @throws ApplicationException
	 */
	public static List<Customer> readCustomers(String inputFileName) throws ApplicationException {

		Scanner scanner = null;
		// the list of customers read as Strings from the inputFile
		List<String> customerList = new ArrayList<>();

		// opening file and reading data into the local list of customers, as stings
		try {
			scanner = new Scanner(new File(inputFileName));
			while (scanner.hasNext()) {
				customerList.add(scanner.nextLine());
			}
		} catch (Exception e) {
			throw new ApplicationException(String.format("Could not open the input file. The error is: %s", e.getMessage()));
		} finally {
			if (scanner != null) {
				try {
					scanner.close();
				} catch (Exception e) {
				}
			}
		}

		if (customerList.size() <= 1) { // we have only header data
			throw new ApplicationException(String.format("Input file %s is empty", inputFileName));
		}

		// the number of customer from the input data, eliminating the header row
		int customerCount = customerList.size() - 1;
		// the List of customer objects. This can be changed to a LinkedList
		// or another implementation. For now ArrayList seems the best as
		// we only add data to the list.
		List<Customer> customers = new ArrayList<>(customerCount);

		// starting at 1 to skip the header row
		for (int i = 1; i <= customerCount; i++) {
			String[] customerData = customerList.get(i).split(FIELD_DELIMITER);

			// check if we have enough input fields to build the object
			if (customerData.length < Customer.FIELD_COUNT) {
				throw new ApplicationException(String.format("Expected %d but got %d: %s", Customer.FIELD_COUNT, customerData.length, Arrays.toString(customerData)));
			}

			String emailAddress = customerData[7];

			if (!Validator.isValidEmail(emailAddress)) {
				throw new ApplicationException(String.format("Invalid email: %s", emailAddress));
			}

			String validDate = Validator.isValidJoinDate(customerData[8]);

			if (validDate != null) {
				throw new ApplicationException(String.format("Invalid join date: %s - %s%n", customerData[8], validDate));
			}

			try {
				int id = Integer.parseInt(customerData[0]);
				int year = Integer.parseInt(customerData[8].substring(0, 4));
				int month = Integer.parseInt(customerData[8].substring(4, 6).trim());
				int day = Integer.parseInt(customerData[8].substring(6).trim());

				// For the month, we subtract 1 as the LocalDate stores the months as 0-11
				Customer customerObject = new Customer.Builder(id, customerData[1], customerData[2], customerData[6]).street(customerData[3]).city(customerData[4])
						.postalCode(customerData[5]).emailAddress(emailAddress).joinDate(LocalDate.of(year, month - 1, day)).build();

				customers.add(customerObject);
			} catch (Exception e) {
				throw new ApplicationException(e.getMessage());
			}
		}

		return customers;
	}

	/**
	 * Read the data from a file and parse it into
	 * an array of Motorcycle objects.
	 * Data is assumed correct format,
	 * only the email address is validated in this version of the program.
	 * 
	 * @param inputFileName
	 *            the name of the file containing the input data
	 *            this must be in the same folder as the application
	 *            expected File Format:
	 *            Header line then data lines, one customer per line in the format:
	 *            ID|MAKE|MODEL|YEAR|SERIAL_NUMBER|MILEAGE|CUSTOMER_ID
	 * @return the array of Motorcycle objects
	 * @throws ApplicationException
	 */
	public static List<Motorcycle> readMotorcycles(String inputFileName) throws ApplicationException {

		Scanner scanner = null;
		// the list of Motorcycles read as Strings from the inputFile
		List<String> motorcycleList = new ArrayList<>();

		// opening file and reading data into the local list of motorcycles, as stings
		try {
			scanner = new Scanner(new File(inputFileName));
			while (scanner.hasNext()) {
				motorcycleList.add(scanner.nextLine());
			}
		} catch (Exception e) {
			throw new ApplicationException(String.format("Could not open the input file. The error is: %s", e.getMessage()));
		} finally {
			if (scanner != null) {
				try {
					scanner.close();
				} catch (Exception e) {
				}
			}
		}

		if (motorcycleList.size() <= 1) { // we have only header data
			throw new ApplicationException(String.format("Input file %s is empty", inputFileName));
		}

		// the number of motorcycle from the input data, eliminating the header row
		int motorcycleCount = motorcycleList.size() - 1;
		// the List of motorcycle objects. This can be changed to a LinkedList
		// or another implementation. For now ArrayList seems the best as
		// we only add data to the list.
		List<Motorcycle> motorcycles = new ArrayList<>(motorcycleCount);

		// starting at 1 to skip the header row
		for (int i = 1; i <= motorcycleCount; i++) {
			String[] motorcycleData = motorcycleList.get(i).split(FIELD_DELIMITER);

			// check if we have enough input fields to build the object
			if (motorcycleData.length < Motorcycle.FIELD_COUNT) {
				throw new ApplicationException(String.format("Expected %d but got %d: %s", Motorcycle.FIELD_COUNT, motorcycleData.length, Arrays.toString(motorcycleData)));
			}

			try {
				int motorcycle_id = Integer.parseInt(motorcycleData[0]);
				int customer_id = Integer.parseInt(motorcycleData[6]);
				int year = Integer.parseInt(motorcycleData[3]);
				int mileage = Integer.parseInt(motorcycleData[5]);

				Motorcycle motorcycleObject = new Motorcycle.Builder(motorcycle_id, motorcycleData[1], motorcycleData[2], customer_id).year(year).serialNumber(motorcycleData[4])
						.mileage(mileage).build();
				motorcycles.add(motorcycleObject);
			} catch (Exception e) {
				throw new ApplicationException(e.getMessage());
			}
		}

		return motorcycles;
	}

	/**
	 * Read the data from a file and parse it into
	 * an array of Part objects.
	 * Data is assumed correct format,
	 * only the email address is validated in this version of the program.
	 * 
	 * @param inputFileName
	 *            the name of the file containing the input data
	 *            this must be in the same folder as the application
	 *            expected File Format:
	 *            Header line then data lines, one customer per line in the format:
	 *            MOTORCYCLE_ID|DESCRIPTION|PART#|PRICE|QUANTITY
	 * @return the Map of <partNumber, Part> objects
	 * @throws ApplicationException
	 */
	public static Map<String, Part> readParts(String inputFileName) throws ApplicationException {

		Scanner scanner = null;
		// the list of Parts read as Strings from the inputFile
		List<String> partList = new ArrayList<>();

		// opening file and reading data into the local list of parts, as stings
		try {
			scanner = new Scanner(new File(inputFileName));
			while (scanner.hasNext()) {
				partList.add(scanner.nextLine());
			}
		} catch (Exception e) {
			throw new ApplicationException(String.format("Could not open the input file. The error is: %s", e.getMessage()));
		} finally {
			if (scanner != null) {
				try {
					scanner.close();
				} catch (Exception e) {
				}
			}
		}

		if (partList.size() <= 1) { // we have only header data
			throw new ApplicationException(String.format("Input file %s is empty", inputFileName));
		}

		// the number of part from the input data, eliminating the header row
		int partCount = partList.size() - 1;
		// the List of part objects. This can be changed to a LinkedList
		// or another implementation. For now ArrayList seems the best as
		// we only add data to the list.
		Map<String, Part> parts = new LinkedHashMap<>();

		// starting at 1 to skip the header row
		for (int i = 1; i <= partCount; i++) {
			String[] partData = partList.get(i).split(FIELD_DELIMITER);

			// check if we have enough input fields to build the object
			if (partData.length < Part.FIELD_COUNT) {
				throw new ApplicationException(String.format("Expected %d but got %d: %s", Part.FIELD_COUNT, partData.length, Arrays.toString(partData)));
			}

			try {
				long priceInUSD = Long.parseLong(partData[3]);

				Part partObject = new Part.Builder(partData[2], priceInUSD).description(partData[1]).build();

				parts.put(partData[2], partObject);
			} catch (Exception e) {
				throw new ApplicationException(e.getMessage());
			}
		}

		return parts;
	}

	/**
	 * Read the data from a file and parse it into
	 * an array of ServicedMotorcycleWithParts objects.
	 * Data is assumed correct format,
	 * only the email address is validated in this version of the program.
	 * 
	 * @param inputFileName
	 *            the name of the file containing the input data
	 *            this must be in the same folder as the application
	 *            expected File Format:
	 *            Header line then data lines, one customer per line in the format:
	 *            ID|MAKE|MODEL|YEAR|SERIAL_NUMBER|MILEAGE|CUSTOMER_ID
	 * @return the array of ServicedMotorcycleWithParts objects
	 * @throws ApplicationException
	 */
	public static List<ServicedMotorcycleWithParts> readServicedMotorcycleWithPartss(List<Motorcycle> motorcycles, String inputFileName) throws ApplicationException {

		Scanner scanner = null;
		// the list of ServicedMotorcycleWithPartss read as Strings from the inputFile
		List<String> servicedMotorcycleWithPartsList = new ArrayList<>();

		// opening file and reading data into the local list of servicedMotorcycleWithPartss, as stings
		try {
			scanner = new Scanner(new File(inputFileName));
			while (scanner.hasNext()) {
				servicedMotorcycleWithPartsList.add(scanner.nextLine());
			}
		} catch (Exception e) {
			throw new ApplicationException(String.format("Could not open the input file. The error is: %s", e.getMessage()));
		} finally {
			if (scanner != null) {
				try {
					scanner.close();
				} catch (Exception e) {
				}
			}
		}

		if (servicedMotorcycleWithPartsList.size() <= 1) { // we have only header data
			throw new ApplicationException(String.format("Input file %s is empty", inputFileName));
		}

		// the number of servicedMotorcycleWithParts from the input data, eliminating the header row
		int servicedMotorcycleWithPartsCount = servicedMotorcycleWithPartsList.size() - 1;
		// the List of servicedMotorcycleWithParts objects. This can be changed to a LinkedList
		// or another implementation. For now ArrayList seems the best as
		// we only add data to the list.
		List<ServicedMotorcycleWithParts> servicedMotorcycleWithParts = new ArrayList<>();

		for (Motorcycle motorcycle : motorcycles) {
			LinkedHashMap<Part, Integer> parts = new LinkedHashMap<Part, Integer>();

			// starting at 1 to skip the header row
			for (int i = 1; i <= servicedMotorcycleWithPartsCount; i++) {
				String[] servicedMotorcycleWithPartsData = servicedMotorcycleWithPartsList.get(i).split(FIELD_DELIMITER);

				// if matching current motorcycle, create an object and add it to the Map
				if (servicedMotorcycleWithPartsData[0].equalsIgnoreCase(motorcycle.getMake() + '+' + motorcycle.getModel())) {

					try {
						long priceInUSD = Long.parseLong(servicedMotorcycleWithPartsData[3]);
						Part partObject = new Part.Builder(servicedMotorcycleWithPartsData[2], priceInUSD).description(servicedMotorcycleWithPartsData[1]).build();
						int quantity = Integer.parseInt(servicedMotorcycleWithPartsData[4]);
						parts.put(partObject, quantity);
						
					} catch (Exception e) {
						throw new ApplicationException(e.getMessage());
					}
					
				}
			}
			servicedMotorcycleWithParts.add(new ServicedMotorcycleWithParts(motorcycle, parts));
		}
		return servicedMotorcycleWithParts;
	}

}
