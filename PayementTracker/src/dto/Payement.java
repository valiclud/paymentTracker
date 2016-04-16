package dto;

public class Payement {

	private String currencyName;
	private int currencyAmount;

	public Payement(String currencyName, int currencyAmount) {
		super();
		this.currencyName = currencyName;
		this.currencyAmount = currencyAmount;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public int getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(int currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currencyName == null) ? 0 : currencyName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Payement other = (Payement) obj;
		if (currencyName == null) {
			if (other.currencyName != null)
				return false;
		} else if (!currencyName.equals(other.currencyName))
			return false;
		return true;
	}

}
