package processing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import dto.Payement;

/**
 * ProcessPayement class to process payment of application PayementTracker.
 * 
 * @author Ludvik Valicek - March 9 2016
 *
 */
public class ProcessPayement {

	private static ProcessPayement instance = null;

	private ProcessPayement() {
	}

	public static ProcessPayement getInstance() {
		if (instance == null) {
			synchronized (ProcessPayement.class) {
				if (instance == null) {
					instance = new ProcessPayement();
				}
			}
		}
		return instance;
	}

	private Map<String, Payement> payements = new HashMap<String, Payement>();

	private static final Logger LOG = Logger.getLogger(ProcessPayement.class.getName());

	/**
	 * Process one line of payment.
	 * 
	 * @param String[]
	 *            currency
	 * @return void
	 */
	public void processFromLine(Payement payement) {
		int sum = 0;

		if (payements.containsKey(payement.getCurrencyName())) {
			Payement alreadyPaid = payements.get(payement.getCurrencyName());
			sum = alreadyPaid.getCurrencyAmount() + payement.getCurrencyAmount();
			alreadyPaid.setCurrencyAmount(sum);

			if (sum == 0)
				payements.remove(payement.getCurrencyName());
			else
				payements.put(alreadyPaid.getCurrencyName(), alreadyPaid);

		} else {
			payements.put(payement.getCurrencyName(), payement);
		}
	}

	/**
	 * Getter for payements.
	 * 
	 * @param void
	 * @return Map<String,Payement>
	 */
	public synchronized Map<String, Payement> getPayements() {
		return payements;
	}

	/**
	 * Setter for payements.
	 * 
	 * @param Map<String,Payement>
	 * @return void
	 */
	public synchronized void setPayements(Map<String, Payement> payements) {
		this.payements = payements;
	}

}
