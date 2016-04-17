
package dto;

// TODO: Auto-generated Javadoc
/**
 * The Class Payement.
 *
 * @author: Ludvik Valicek
 */
public class Payement {

	/** The currency name. */
	private String currencyName;

	/** The currency amount. */
	private int currencyAmount;

	/**
	 * Instantiates a new payement.
	 *
	 * @param currencyName
	 *            the currency name
	 * @param currencyAmount
	 *            the currency amount
	 */
	public Payement(String currencyName, int currencyAmount) {
		super();
		this.currencyName = currencyName;
		this.currencyAmount = currencyAmount;
	}

	/**
	 * Gets the currency name.
	 *
	 * @return the currency name
	 */
	public String getCurrencyName() {
		return currencyName;
	}

	/**
	 * Sets the currency name.
	 *
	 * @param currencyName
	 *            the new currency name
	 */
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	/**
	 * Gets the currency amount.
	 *
	 * @return the currency amount
	 */
	public int getCurrencyAmount() {
		return currencyAmount;
	}

	/**
	 * Sets the currency amount.
	 *
	 * @param currencyAmount
	 *            the new currency amount
	 */
	public void setCurrencyAmount(int currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currencyAmount;
		result = prime * result + ((currencyName == null) ? 0 : currencyName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payement other = (Payement) obj;
		if (currencyAmount != other.currencyAmount)
			return false;
		if (currencyName == null) {
			if (other.currencyName != null)
				return false;
		} else if (!currencyName.equals(other.currencyName))
			return false;
		return true;
	}

}
