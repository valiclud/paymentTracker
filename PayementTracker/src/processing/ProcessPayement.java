
package processing;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import dto.Payement;

// TODO: Auto-generated Javadoc
/**
 * ProcessPayement class to process payment of application PayementTracker.
 * 
 * @author Ludvik Valicek - March 9 2016
 *
 */
public class ProcessPayement {

	/** The instance. */
	private static ProcessPayement instance = null;

	/**
	 * Instantiates a new process payement.
	 */
	private ProcessPayement() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Gets the single instance of ProcessPayement.
	 *
	 * @return single instance of ProcessPayement
	 */
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

	/** The payements. */
	private Map<String, Payement> payements = new HashMap<String, Payement>();

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(ProcessPayement.class.getName());

	/**
	 * Process one line of payment.
	 *
	 * @param payement the payement
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
	 * @return Map<String,Payement>
	 */
	public synchronized Map<String, Payement> getPayements() {
		return payements;
	}

	/**
	 * Setter for payements.
	 *
	 * @param payements the payements
	 * @return void
	 */
	public synchronized void setPayements(Map<String, Payement> payements) {
		this.payements = payements;
	}

}
