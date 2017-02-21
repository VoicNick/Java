/**
* Project: A00884836Lab1
* File: Player.java
* Date: Sep 21, 2016
* Time: 9:24:34 PM
*/
package a00884836Lab1.data;

/**
* @author Voicu Chirtes, A00884836
*
*/
public class Player {
	
	private int  id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String gamerTag;
	
	/**
	 * default constructor
	 */
	public Player() {}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the alias
	 */
	public String getGamerTag() {
		return gamerTag;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setGamerTag(String gamerTag) {
		this.gamerTag = gamerTag;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailAddress=" + emailAddress + ", gamerTag=" + gamerTag + "]";
	}	
}