package main;

import java.util.logging.Logger;

import simpleview.SimpleView;
import timer.DisplayTimer;

/**
 * Main class to run application PayementTracker.
 * 
 * @author Ludvik Valicek - March 9 2016
 *
 */
public class Main {

	private static final Logger LOG = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		new DisplayTimer(6);
		new SimpleView().runView();
	}
}
