/**
* Project: A00884836_Assignment1
* File: Part.java
* Date: Oct 25, 2016
* Time: 12:37:57 PM
*/
package a00884836.data;

/**
* @author Voicu Chirtes, A00884836
*
* This class models a real world replacement part
*/
public class Part {

	/**
	 * the number of fields in the Part object
	 */
	public static final int FIELD_COUNT = 4;
	
	private String partNumber;
	private String description;
	//this could be represented as BigDecimal, but for the purpose of this 
	//assignment the long should be enough.
	private long priceInUSD;
	
	//inner builder class
	public static class Builder {
		
		//required parameters
		private String partNumber;
		private long priceInUSD;
		
		//optional parameters - initialized to default values
		private String description;
		
		/**
		 * Builder constructor with the required parameters
		 * 
		 * @param partNumber
		 * @param priceInUSD
		 */
		public Builder (String partNumber, long priceInUSD){
			super();
			this.partNumber = partNumber;
			this.priceInUSD = priceInUSD;
		}
		
		/**
		 * @param description the description to set
		 * @return the Builder object
		 */
		public Builder description (String description) {
			this.description = description;
			return this;
		}
		
		public Part build() {
			return new Part(this);
		}
	}
	
	/**
	 * 
	 * @param builder
	 */
	public Part(Builder builder) {
		partNumber = builder.partNumber;
		priceInUSD = builder.priceInUSD;
		description = builder.description;
	}

	/**
	 * @return the partNumber
	 */
	public String getPartNumber() {
		return partNumber;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the priceInUSD
	 */
	public long getPriceInUSD() {
		return priceInUSD;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Part [partNumber=" + partNumber + ", description=" + description + ", priceInUSD=" + priceInUSD + "]";
	}
}
