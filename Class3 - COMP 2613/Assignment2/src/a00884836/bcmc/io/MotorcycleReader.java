/**
 * Project: Bcmc
 * File: MotorcycleReader.java
 */

package a00884836.bcmc.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00884836.bcmc.ApplicationException;
import a00884836.bcmc.data.Motorcycle;

/**
 * @author Sam Cirka, A00123456
 *
 */
public class MotorcycleReader extends Reader {

	public static final String FILENAME = "motorcycles.dat";

	private static final Logger LOG = LogManager.getLogger();

	/**
	 * private constructor to prevent instantiation
	 */
	private MotorcycleReader() {
	}

	/**
	 * Read the game input data.
	 * 
	 * @return A collection of games.
	 * @throws ApplicationException
	 */
	public static Map<Integer, Motorcycle> read() throws ApplicationException {
		File file = new File(FILENAME);
		LOG.debug("Reading" + file.getAbsolutePath());
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			throw new ApplicationException(e);
		}

		Map<Integer, Motorcycle> motorcycles = new HashMap<>();
		try {
			// read past the first row
			if (scanner.hasNext()) {
				scanner.nextLine();
			}
			while (scanner.hasNext()) {
				String row = scanner.nextLine();
				int count = Motorcycle.getAttributeCount();
				String[] elements = getElements(row, count);

				int index = 0;
				int id = Integer.parseInt(elements[index++]);
				Motorcycle motorcycle = new Motorcycle.Builder(id). //
						setMake(elements[index++]). //
						setModel(elements[index++]). //
						setYear(Integer.parseInt(elements[index++])). //
						setSerialNumber(elements[index++]). //
						setMileage(Integer.parseInt(elements[index++])). //
						setCustomerId(Integer.parseInt(elements[index++])).build();

				motorcycles.put(motorcycle.getId(), motorcycle);
				LOG.debug("Added " + motorcycle.toString() + " as " + id);
			}
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

		return motorcycles;
	}
}
