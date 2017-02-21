/**
 * Project: A00884836Lab4
 * File: ApplicationException.java
 * Date: Oct 4, 2016
 * Time: 9:50:42 PM
 */
package a00884836.exception;

/**
 * @author Voicu Chirtes, A00884836
 *
 */
@SuppressWarnings("serial")
public class ApplicationException extends Exception {

	/**
	 * default constructor
	 */
	public ApplicationException() {
	}

	/**
	 * @param message
	 * @param cause
	 * @param arg2
	 * @param arg3
	 */
	public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ApplicationException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
	}
}
