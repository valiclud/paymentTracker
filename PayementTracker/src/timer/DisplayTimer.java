package timer;

import java.awt.Toolkit;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import dto.Payement;
import processing.ProcessPayement;

/**
 * DisplayTimer class to display payements in relevant delay.
 * 
 * @author Ludvik Valicek - March 9 2016
 *
 */
public class DisplayTimer {
	Toolkit toolkit;

	Timer timer;

	public DisplayTimer(int seconds) {
		toolkit = Toolkit.getDefaultToolkit();
		timer = new Timer();
		timer.scheduleAtFixedRate(new RemindTask(), seconds * 1000, seconds * 1000);
	}

	class RemindTask extends TimerTask {
		public void run() {

			Map<String, Payement> payements = ProcessPayement.getInstance().getPayements();

			for (Payement payement : payements.values()) {
				System.out.println(payement.getCurrencyName() + " " + payement.getCurrencyAmount());
			}
		}
	}
}