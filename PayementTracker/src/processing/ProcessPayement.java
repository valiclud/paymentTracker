

package processing;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import dto.Payement;
import exception.ApplicationException;
import validation.Validator;

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
	 * @param payement            the payement
	 * @return void
	 * @throws ApplicationException the application exception
	 */
	public void processFromLine(String[] payement) throws ApplicationException {
		boolean result = false;

		result = Validator.getInstance().validateCurrency(payement[0].trim());
		if (!result) {
			String err = "processFromLine(String[] currency) " + payement[0] + " " + payement[1] + " - non valid input";
			LOG.severe(err);
			throw new ApplicationException(err);
		}
		result = Validator.getInstance().validateAmount(payement[1].trim());
		if (!result) {
			String err = "processFromLine(String[] currency) " + payement[0] + " " + payement[1] + " - non valid input";
			LOG.severe(err);
			throw new ApplicationException(err);
		}
		this.processOnePayement(payement);

	}

	/**
	 * Process from file.
	 *
	 * @param filename the filename
	 * @throws ApplicationException the application exception
	 */
	public void processFromFile(String filename) throws ApplicationException {
		Boolean result = null;
		File file = null;

		file = Validator.getInstance().validateFile(filename);
		if (file == null) {
			LOG.severe("processFromFile(String filename) - Validator.getInstance().validateFile(filename) " + filename + " - non valid input file");
		}

		result = Validator.getInstance().validateParseFile(file);
		if (!result) {
			LOG.severe("processFromFile(String filename) - Validator.getInstance().validateParseFile(file) " + filename + " - non valid input file");
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
	 * @param payements
	 *            the payements
	 * @return void
	 */
	public synchronized void setPayements(Map<String, Payement> payements) {
		this.payements = payements;
	}

	/**
	 * Process one payement.
	 *
	 * @param payement the payement
	 */
	private void processOnePayement(String[] payement) {
		int sum = 0;
		Payement alreadyPaid = null;
		if (payements.containsKey(payement[0])) {
			alreadyPaid = payements.get(payement[0]);
			sum = alreadyPaid.getCurrencyAmount() + Integer.valueOf(payement[1]);
			alreadyPaid.setCurrencyAmount(sum);

			if (sum == 0)
				payements.remove(alreadyPaid.getCurrencyName());
			else
				payements.put(alreadyPaid.getCurrencyName(), alreadyPaid);

		} else {
			payements.put(payement[0], new Payement(payement[0], Integer.valueOf(payement[1])));
		}
	}

}
