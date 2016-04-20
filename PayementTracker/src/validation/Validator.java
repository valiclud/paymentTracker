
package validation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Currency;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import dto.Payement;
import exception.ApplicationException;
import processing.ProcessPayement;

// TODO: Auto-generated Javadoc
/**
 * Validator class to validate input data.
 * 
 * @author Ludvik Valicek - March 9 2016
 *
 */
public class Validator {

	/** The instance. */
	private static Validator instance = null;

	/**
	 * Instantiates a new validator.
	 */
	private Validator() {
	}

	/**
	 * Gets the single instance of Validator.
	 *
	 * @return single instance of Validator
	 */
	public static Validator getInstance() {
		if (instance == null) {
			synchronized (Validator.class) {
				if (instance == null) {
					instance = new Validator();
				}
			}
		}
		return instance;
	}

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(Validator.class.getName());

	/** The file content. */
	private Boolean fileContent = null;

	/**
	 * Validate file.
	 *
	 * @param filename
	 *            the filename
	 * @return boolean
	 * @throws ApplicationException
	 *             the application exception
	 */
	public synchronized File validateFile(String filename) throws ApplicationException {

		File file = new File(filename);

		if (!file.exists()) {
			LOG.severe("File \"" + filename + "\" does not exist");
			return null;
		}

		return file;
	}

	/**
	 * Validate currency.
	 *
	 * @param currency
	 *            the currency
	 * @return boolean
	 */
	public boolean validateCurrency(String currency) {
		try {
			if (Currency.getAvailableCurrencies().contains(Currency.getInstance(currency)) == false)
				return false;
		} catch (IllegalArgumentException iax) {
			LOG.severe("Wrong currency symbol entered");
			return false;
		}

		return true;
	}

	/**
	 * Validate amount.
	 *
	 * @param amount
	 *            the amount
	 * @return true, if successful
	 */
	public boolean validateAmount(String amount) {

		if (amount.matches("-?\\d+") == false) {
			LOG.severe("Wrong amount format entered");
			return false;
		}

		return true;
	}

	/**
	 * Parse and Validate file.
	 *
	 * @param file
	 *            the file
	 * @return String[][]
	 * @throws ApplicationException
	 *             the application exception
	 */
	public Boolean validateParseFile(File file) throws ApplicationException {
		String[][] parts = new String[2][4];
		int row = 0;
		int col = 0;
		BufferedReader bufRdr = null;
		Payement payement = null;
		try {
			bufRdr = new BufferedReader(new FileReader(file));
			bufRdr.mark(1000);
			String line = null;
			StringTokenizer st = null;

			if (!this.validateEmptyFile(bufRdr, file))
				return false;

			while ((line = bufRdr.readLine()) != null) {
				st = new StringTokenizer(line, " ");
				col = 0;
				while (st.hasMoreTokens()) {
					parts[0][col] = st.nextToken().trim();
					col++;
					if (col > 2) {
						LOG.severe("There are more than 2 values on row: " + (row + 1) + " - non valid input file");
						return false;
					}
				}
				row++;
				ProcessPayement.getInstance().processFromLine((parts[0]));
			}
			bufRdr.close();
		} catch (NumberFormatException ex) {
			String err = " NumberFormatException:  " + file.getName() + "on row: " + row;
			LOG.severe("validateParseFile(File file) - " + err + ex);
			throw new ApplicationException(err);
		} catch (FileNotFoundException ex) {
			String err = " FileNotFoundException:  " + file.getName() + "on row: " + row;
			LOG.severe("validateParseFile(File file) - " + err + ex);
			throw new ApplicationException(err);
		} catch (Exception ee) {
			String err = " Exception:  " + file.getName() + " on row: " + row + " " + ee.getMessage();
			LOG.severe("validateParseFile(File file) - " + err + ee.getStackTrace());
			throw new ApplicationException(err);
		} finally {
			try {
				bufRdr.close();
			} catch (IOException e) {
				String err = " IOException:  " + file.getName();
				LOG.severe("validateParseFile(File file) - " + err + e);
				throw new ApplicationException(err);
			}
		}
		return true;
	}

	/**
	 * Validate the file for emptiness.
	 *
	 * @param bufRdr
	 *            the buf rdr
	 * @param file
	 *            the file
	 * @return String[][]
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private boolean validateEmptyFile(BufferedReader bufRdr, File file) throws IOException {
		if (bufRdr.readLine() == null) {
			String err = " Empty file:  " + file.getName();
			LOG.severe("validateParseFile(File file) - " + err);
			return false;
		}
		bufRdr.reset();
		return true;
	}

}
