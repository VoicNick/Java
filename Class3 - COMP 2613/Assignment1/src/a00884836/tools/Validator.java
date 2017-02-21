/**
* Project: A00884836_Assignment1
* File: Validator.java
* Date: Oct 27, 2016
* Time: 2:49:20 PM
*/
package a00884836.tools;

/**
* @author Voicu Chirtes, A00884836
*
*/
public class Validator {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	@SuppressWarnings("unused")
	@Deprecated
	private static final String SIMPLE_EMAIL_PATTERN = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
	private static final int DATE_LENGHT = 8;

	/**
	 * default constructor
	 */
	private Validator() {
	}

	/**
	 * validates if a string is a valid email address format *@*.*
	 * 
	 * @param email
	 *            the email address to validate
	 * @return whether the email address is in a valid format
	 */
	public static boolean isValidEmail(String email) {
		return email.matches(EMAIL_PATTERN);
	}

	/**
	 * validates if a string is a valid join date.
	 * The valid format is 8 characters: YYYYMMDD, numbers only
	 * As this will be stored in a LocalDate object, the LocalDate limits will apply
	 * 
	 * @param date
	 *            the date to validate
	 * @return a message showing the reason is not valid or, if value is valid it returns null
	 */
	public static String isValidJoinDate(String date) {
		String message = null;
		if (date.length() != DATE_LENGHT) {
			return "Date must be exactlly eight characters in the format YYYYMMDD, numbers only.";
		}
		// the rest of the validation (like valid days, months), are handled by the LocalTime class
		// the exceptions are thrown in LocalTime for bad dates and we catch them in our class
		return message;
	}
}
