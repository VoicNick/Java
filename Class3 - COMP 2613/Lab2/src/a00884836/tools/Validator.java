/**
* Project: A00884836Lab2
* File: Validator.java
* Date: Sep 27, 2016
* Time: 9:53:20 PM
*/
package a00884836.tools;

/**
* @author Voicu Chirtes, A00884836
*
*/
public class Validator {

	/**
	 * default constructor
	 */
	private Validator() {}

	/**
	 * validates if a string is a valid email address format *@*.*
	 * 
	 * @param email the email address to validate
	 * @return whether the email address is valid format
	 */
	public static boolean isValidEmail(String email) {
		return email.matches("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})");
	}
}