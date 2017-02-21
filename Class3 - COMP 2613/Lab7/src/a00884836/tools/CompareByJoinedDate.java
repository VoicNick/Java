/**
 * Project: A00884836Lab5
 * File: CompareByJoinedDate.java
 * Date: Oct 16, 2016
 * Time: 9:29:11 PM
 */
package a00884836.tools;

import java.util.Comparator;

import a00884836.data.Customer;

/**
 * used to implement a comparator that would allow sorting by joined date
 * 
 * @author Voicu Chirtes, A00884836
 *
 */
public class CompareByJoinedDate implements Comparator<Customer> {

	/**
	 * default constructor
	 */
	public CompareByJoinedDate() {
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Customer customer0, Customer customer1) {
		return customer0.getJoinDate().compareTo(customer1.getJoinDate());
	}

}
