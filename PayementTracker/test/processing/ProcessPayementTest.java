
package processing;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dto.Payement;
import exception.ApplicationException;
import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class ProcessPayementTest.
 *
 * @author: Ludvik Valicek
 */
public class ProcessPayementTest extends TestCase {

	/** The payement1. */
	private String[] payement1;

	/** The payement2. */
	private String[] payement2;

	/** The payement3. */
	private String[] payement3;
	
	/** The payement4. */
	private String[] payement4;
	
	/** The payement5. */
	private String[] payement5;

	/** The payements. */
	private Map<String, Payement> payements = new HashMap<String, Payement>();

	/**
	 * Constructor for this class.
	 *
	 * @param name
	 *            the name
	 * @return void
	 */
	public ProcessPayementTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	public void setUp() {
		payement1 = new String[]{"USD", "125"};
		payement2 = new String[]{"CZK", "1250"};
		payement3 = new String[]{"HUF", "50"};
		payement4 = new String[]{"HUF", "hjd<,o"};
		payement5 = new String[]{"XXX", "1290"};
		payements.put("USD", new Payement("USD", -125));
		payements.put("CZK", new Payement("CZK", 1250));
		payements.put("CHF", new Payement("CHF", 560));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	public void tearDown() {
		payement1 = null;
		payement2 = null;
		payements = null;
	}

	/**
	 * Test process from line zero payement.
	 *
	 * @param payement1            the payement`
	 * @throws ApplicationException the application exception
	 */
	@Test
	public void testProcessFromLineZeroPayement(String[] payement1) throws ApplicationException {
		ProcessPayement.getInstance().processFromLine(payement1);
		assertEquals(2, payements.size());
	}

	/**
	 * Test process from line sum payement.
	 *
	 * @param payement2            the payement2
	 * @throws ApplicationException the application exception
	 */
	@Test
	public void testProcessFromLineSumPayement(String[] payement2) throws ApplicationException {
		ProcessPayement.getInstance().processFromLine(payement2);
		Payement pay = this.findPayement(payement2);
		assertEquals(5000, pay.getCurrencyAmount());
	}

	/**
	 * Test process from line new payement.
	 *
	 * @param payement3            the payement3
	 * @throws ApplicationException the application exception
	 */
	@Test
	public void testProcessFromLineNewPayement(String[] payement3) throws ApplicationException {
		ProcessPayement.getInstance().processFromLine(payement3);
		Payement pay = this.findPayement(payement3);
		assertEquals(50, pay.getCurrencyAmount());
	}
	
	/**
	 * Test process from line new payement exc1.
	 *
	 * @param payement4 the payement4
	 * @throws ApplicationException the application exception
	 */
	@Test(expected = ApplicationException.class)
	public void testProcessFromLineNewPayementExc1(String[] payement4) throws ApplicationException {
		ProcessPayement.getInstance().processFromLine(payement4);
	}

	/**
	 * Test process from line new payement exc2.
	 *
	 * @param payement5 the payement5
	 * @throws ApplicationException the application exception
	 */
	@Test(expected = ApplicationException.class)
	public void testProcessFromLineNewPayementExc2(String[] payement5) throws ApplicationException {
		ProcessPayement.getInstance().processFromLine(payement5);
	}
	
	/**
	 * Test get payements.
	 */
	@Test
	public void testGetPayements() {
		assertEquals(3, payements.size());
	}

	/**
	 * Find payement.
	 *
	 * @param payement
	 *            the payement
	 * @return the payement
	 */
	private Payement findPayement(String[] payement) {
		for (Payement pay : payements.values()) {
			if (pay.getCurrencyName().equals(payement[0])) {
				return pay;
			}
		}
		return null;
	}

}
