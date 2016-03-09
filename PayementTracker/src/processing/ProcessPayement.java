package processing;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import validation.Validator;

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

	private Map<String, Integer> payements = new HashMap<String, Integer>();

	private static final Logger LOG = Logger.getLogger(ProcessPayement.class.getName());

	/**
	 * Process one line of payment.
	 * 
	 * @param String[]
	 *            currency
	 * @return void
	 */
	public synchronized void processFromLine(String[] currency) {

		if (payements.containsKey(currency[0])) {
			Integer i = payements.get(currency[0]);
			i = i + Integer.valueOf(currency[1]);

			if (i.equals(0))
				payements.remove(currency[0]);
			else
				payements.put(currency[0], i);

		} else {
			payements.put(currency[0], Integer.valueOf(currency[1]));
		}
	}

	/**
	 * Process file of payements.
	 * 
	 * @param String[][]
	 *            currency
	 * @return void
	 */
	public synchronized void processFromFile(String[][] currencies) {

		for (int j = 0; j < currencies.length; j++) {

			if (currencies[j][0] == null)
				return;

			this.processFromLine(currencies[j]);
		}
	}

	/**
	 * Getter for payements.
	 * 
	 * @param void
	 * @return Map<String, Integer>
	 */
	public synchronized Map<String, Integer> getPayements() {
		return payements;
	}

	/**
	 * Setter for payements.
	 * 
	 * @param Map<String,
	 *            Integer>
	 * @return void
	 */
	public synchronized void setPayements(Map<String, Integer> payements) {
		this.payements = payements;
	}

}
