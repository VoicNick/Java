/**
 * Project: Bcmc
 * File: InventoryReader.java
 * Date: Aug 18, 2016
 * Time: 3:43:16 PM
 */

package a00884836.bcmc.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00884836.bcmc.ApplicationException;
import a00884836.bcmc.data.Inventory;

/**
 * @author Sam Cirka, A00123456
 *
 */
public class InventoryReader extends Reader {

	public static final String FILENAME = "inventory.dat";

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * private constructor to prevent instantiation
	 */
	private InventoryReader() {
	}

	/**
	 * Read the inventory input data.
	 * 
	 * @return the inventory.
	 * @throws ApplicationException
	 */
	public static Map<String, Inventory> read() throws ApplicationException {
		File file = new File(FILENAME);
		LOG.debug("Reading" + file.getAbsolutePath());
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			throw new ApplicationException(e);
		}
		int items = 0;

		Map<String, Inventory> inventory = new HashMap<>();
		try {
			// read past the first row
			if (scanner.hasNext()) {
				scanner.nextLine();
			}

			while (scanner.hasNext()) {
				String row = scanner.nextLine();
				// Inventory item = Inventory.Builder();
				String[] elements = getElements(row, Inventory.getAttributeCount());
				LOG.debug(Arrays.toString(elements));

				int index = 0;
				// MOTORCYCLE_ID|DESCRIPTION|PART#|PRICE|QUANTITY
				String motorcycleId = elements[index++];
				String description = elements[index++];
				String partNumber = elements[index++];
				String price = elements[index++];
				String quantity = elements[index++];

				items++;

				Inventory item = new Inventory.Builder(partNumber).setMotorcycleId(motorcycleId).setDescription(description).setPrice(Integer.parseInt(price))
						.setQuantity(Integer.parseInt(quantity)).build();
				inventory.put(item.getPartNumber(), item);
				LOG.debug("Added " + item.toString() + " as " + item.getPartNumber());
			}
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

		LOG.debug("Read " + items + " records from file. Added " + inventory.size() + " valid entries to the memory");
		return inventory;
	}

}
