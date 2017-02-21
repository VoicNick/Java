package a00884836.bcmc.data;

/**
 * Created by scirka on 2016-08-31.
 */
public class Inventory {
	public static final int ATTRIBUTE_COUNT = 5;

	private String description;
	private String partNumber;
	private int price;
	private int quantity;
	private String motorcycleId;

	public static class Builder {
		// Required parameters
		private final String partNumber;

		// Optional parameters
		private String description;
		private int price;
		private int quantity;
		private String motorcycleId;

		public Builder(String partNumber) {
			this.partNumber = partNumber;
		}

		/**
		 * @param description
		 *            the description to set
		 */
		public Builder setDescription(String description) {
			this.description = description;
			return this;
		}

		/**
		 * @param price
		 *            the price to set
		 */
		public Builder setPrice(int price) {
			this.price = price;
			return this;
		}

		/**
		 * @param quantity
		 *            the quantity to set
		 */
		public Builder setQuantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		/**
		 * @param motorcycleId
		 *            the motorcycleId to set
		 */
		public Builder setMotorcycleId(String motorcycleId) {
			this.motorcycleId = motorcycleId;
			return this;
		}

		public Inventory build() {
			return new Inventory(this);
		}
	}

	/**
	 * @param builder
	 */
	public Inventory(Builder builder) {
		this.description = builder.description;
		this.partNumber = builder.partNumber;
		this.price = builder.price;
		this.quantity = builder.quantity;
		this.motorcycleId = builder.motorcycleId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the partNumber
	 */
	public String getPartNumber() {
		return partNumber;
	}

	/**
	 * @param partNumber
	 *            the partNumber to set
	 */
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the motorcycleId
	 */
	public String getMotorcycleId() {
		return motorcycleId;
	}

	/**
	 * @param motorcycleId
	 *            the motorcycleId to set
	 */
	public void setMotorcycleId(String motorcycleId) {
		this.motorcycleId = motorcycleId;
	}

	/**
	 * Get the attribute count used for input validation.
	 *
	 * @return the attribute count
	 */
	public static int getAttributeCount() {
		return ATTRIBUTE_COUNT;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Inventory [description=" + description + ", partNumber=" + partNumber + ", price=" + price + ", quantity=" + quantity + ", motorcycleId=" + motorcycleId + "]";
	}

}
