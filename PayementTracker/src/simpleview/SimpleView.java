
package simpleview;

import java.util.Scanner;
import java.util.logging.Logger;

import exception.ApplicationException;
import processing.ProcessPayement;

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
					ProcessPayement.getInstance().processFromFile(currency[0].trim());
					break;

				case (2):
					ProcessPayement.getInstance().processFromLine(currency);
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
