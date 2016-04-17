
package simpleview;

import java.util.Scanner;
import java.util.logging.Logger;

import dto.Payement;
import exception.ApplicationException;
import processing.ProcessPayement;
import validation.Validator;
	
// TODO: Auto-generated Javadoc
/**
 * The Class SimpleView.
 *
 * @author: Ludvik Valicek
 */
public class SimpleView {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(SimpleView.class.getName());

	/**
	 * Run view.
	 */
	public void runView() {

		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the payement in format \"Currrency Amount\" or filename to upload");
		System.out.println("For quit please enter quit");

		String input = in.nextLine();

		try {

			while (!input.equals("quit")) {

				String[] currency = input.split(" ");

				switch (currency.length) {

				case (0):
					System.err.println("Please enter currency in format of \"currency amount\" or file name.");
					break;

				case (1):
					if (Validator.getInstance().validateFile(currency[0]) == false) {
						System.err.println("There is an error - input file \"" + currency[0] + "\" ");
						break;
					}
					break;

				case (2):
					if (Validator.getInstance().validateCurrency(currency) == false) {
						System.err.println("You entered wrong format of currency/amount \"" + currency[0] + " "
								+ currency[1] + "\"");
						break;
					}
					ProcessPayement.getInstance()
							.processFromLine(new Payement(currency[0], Integer.valueOf(currency[1])));
					break;
				default:
					System.err.println("You entered wrong format - more than two words - of currency/amount \""
							+ currency[0] + " " + currency[1] + "\"");

				}

				input = in.nextLine();
			}
		} catch (ApplicationException aex) {
			String err = " ApplicationException:  " + aex.toString();
			LOG.severe("Main class - " + err);
		} finally {
			in.close();
			System.exit(0);
		}
	}

}
