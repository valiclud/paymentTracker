package main;

import java.util.Scanner;
import java.util.logging.Logger;

import exception.ApplicationException;
import processing.ProcessPayement;
import timer.DisplayTimer;
import validation.Validator;

/**
 * Main class to run application PayementTracker.
 * 
 * @author Ludvik Valicek - March 9 2016
 *
 */
public class Main {

	private static final Logger LOG = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		new DisplayTimer(60);
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the payement in format \"Currrency Amount\" or filename to upload");
		System.out.println("For quit please enter \"quit\"");

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
					ProcessPayement.getInstance().processFromFile(Validator.getInstance().getFileContent());
					break;

				case (2):
					if (Validator.getInstance().validateCurrency(currency) == false) {
						System.err.println("You entered wrong format of currency/amount \"" + currency[0] + " "
								+ currency[1] + "\"");
						break;
					}
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
