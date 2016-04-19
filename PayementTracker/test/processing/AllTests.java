package processing;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({processing.ProcessPayementTest.class })
public class AllTests {

	/**
	 * Main method to run all tests
	 * 
	 * @return void
	 */
	public static void main(String[] args) {
		System.out.println("RUNNING FAILURES ");
		Result result = JUnitCore.runClasses(processing.ProcessPayementTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

	}
	
}
