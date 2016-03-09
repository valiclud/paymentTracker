package validation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Currency;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import exception.ApplicationException;

/**
 * Validator class to validate input data.
 * @author Ludvik Valicek - March 9 2016
 *
 */
public class Validator {

	private static Validator instance = null;

	private Validator() {
	}

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

	private static final Logger LOG = Logger.getLogger(Validator.class.getName());
	
	private String[][] fileContent = null;
	String[][] parts = new String[16][16];

	/**
	 * Validate file.
	 * @param String filename
	 * @return boolean
	 */
	public synchronized boolean validateFile(String filename) throws ApplicationException {

		File file = new File(filename);

		if (!file.exists()) {
			LOG.severe("File \"" +filename + "\" does not exist");
			return false;
		}

		fileContent = this.validateParseFile(file);
		
		if (fileContent == null) {
			return false;
		}

		return true;
	}

	/**
	 * Validate currency.
	 * @param String[] currency
	 * @return boolean
	 */
	public synchronized boolean validateCurrency(String[] currency) {
		try {
			if (Currency.getAvailableCurrencies().contains(Currency.getInstance(currency[0].trim())) == false)
				return false;
		} catch (IllegalArgumentException iax) {
			LOG.severe("Wrong currency symbol entered");
			return false;
		}

		if (currency[1].trim().matches("-?\\d+") == false) {
			LOG.severe("Wrong amount format entered");
			return false;
		}

		return true;
	}

	/**
	 * Parse and Validate file.
	 * @param File file
	 * @return String[][]
	 * @throws ApplicationException 
	 */
	private String[][] validateParseFile(File file) throws ApplicationException {

		int row = 0;
		int col = 0;
		BufferedReader bufRdr = null;
		try {
			bufRdr = new BufferedReader(new FileReader(file));
			bufRdr.mark(1000);
			String line = null;
			StringTokenizer st = null;
			
			if (bufRdr.readLine() == null) {
				String err = " Empty file:  " + file.getName();
				LOG.severe("validateParseFile(File file) - " + err); 
				return null;
			}
				bufRdr.reset();
			
			while ((line = bufRdr.readLine()) != null) {
				st = new StringTokenizer(line, " ");
				col = 0;
				while (st.hasMoreTokens()) {
					parts[row][col] = st.nextToken().trim();
					col++;
					 if (col > 2) {
					 LOG.severe("There are more than 2 values on row: "+ (row+1) +" - non valid input file");
					 return null;
					 }
				}
				if(this.validateCurrency(parts[row]) == false) {
					 LOG.severe("Wrong values on row: "+ (row+1) +" - non valid input file");
					 return null;
				}
				row++;
				if (row >=parts.length) {
					parts = this.enlargeArray(parts, row);
				}
			}
			bufRdr.close();
		} catch (FileNotFoundException ex) {
			String err = " FileNotFoundException:  " + file.getName();
			LOG.severe("validateParseFile(File file) - " + err + ex); 
			throw new ApplicationException(err);
		} catch (Exception ee) {
			String err = " Exception:  " + file.getName();
			LOG.severe("validateParseFile(File file) - " + err + ee); 
			throw new ApplicationException(err);
		}finally {
			try {
				bufRdr.close();
			} catch (IOException e) {
				String err = " IOException:  " + file.getName();
				LOG.severe("validateParseFile(File file) - " + err + e); 
				throw new ApplicationException(err);
			}
	    }
		
		return parts;

	}
	
	/**
	 * Enlarge and copy array.
	 * @param String[][] array, int size
	 * @return String[][]
	 */
	private String[][] enlargeArray(String[][] array, int size){
		String[][] tempArray = new String [size*2] [2];
		
		for (int i = 0; i<array.length; i++){
		     for (int j = 0; j<array[i].length; j++){
		    	 tempArray [i][j] = array[i][j];
		     } 
		}
		return tempArray;
	}

	/**
	 * Getter for file content.
	 * @param void
	 * @return String[][]
	 */
	public String[][] getFileContent() {
		return fileContent;
	}

	/**
	 * Setter for file content.
	 * @param String[][] fileContent
	 * @return void
	 */
	public void setFileContent(String[][] fileContent) {
		this.fileContent = fileContent;
	}

}
