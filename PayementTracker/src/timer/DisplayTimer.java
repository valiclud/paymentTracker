
package timer;

import java.awt.Toolkit;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import dto.Payement;
import processing.ProcessPayement;

// TODO: Auto-generated Javadoc
/**
 * DisplayTimer class to display payements in relevant delay.
 * 
 * @author Ludvik Valicek - March 9 2016
 *
 */
public class DisplayTimer {

	/** The toolkit. */
	Toolkit toolkit;

	/** The timer. */
	Timer timer;

	/**
	 * Instantiates a new display timer.
	 *
	 * @param seconds
	 *            the seconds
	 */
	public DisplayTimer(int seconds) {
		toolkit = Toolkit.getDefaultToolkit();
		timer = new Timer();
		timer.scheduleAtFixedRate(new RemindTask(), seconds * 1000, seconds * 1000);
	}

	/**
	 * The Class RemindTask.
	 *
	 * @author: Ludvik Valicek
	 */
	class RemindTask extends TimerTask {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.TimerTask#run()
		 */
		public void run() {

			Map<String, Payement> payements = ProcessPayement.getInstance().getPayements();

			synchronized (RemindTask.class) {
				for (Payement payement : payements.values()) {
					System.out.println(payement.getCurrencyName() + " " + payement.getCurrencyAmount());
				}
			}
		}
	}
}