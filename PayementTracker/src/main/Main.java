
package main;

import java.util.logging.Logger;

import simpleview.SimpleView;
import timer.DisplayTimer;

// TODO: Auto-generated Javadoc
/**
 * Main class to run application PayementTracker.
 * 
 * @author Ludvik Valicek - March 9 2016
 *
 */
public class Main {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(Main.class.getName());

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new DisplayTimer(6);
		new SimpleView().runView();
	}
}
