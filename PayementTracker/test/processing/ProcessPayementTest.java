


package processing;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dto.Payement;
import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class ProcessPayementTest.
 *
 * @author: Ludvik Valicek
 */
public class ProcessPayementTest extends TestCase {

	/** The payement1. */
	private Payement payement1;

	/** The payement2. */
	private Payement payement2;

	/** The payement3. */
	private Payement payement3;

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

	/**
	 * Instantiates a new process payement test.
	 */
	public ProcessPayementTest() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	public void setUp() {
		payement1 = new Payement("USD", 125);
		payement2 = new Payement("CZK", 1250);
		payement3 = new Payement("HUF", 50);
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
	 * @param payement1
	 *            the payement1
	 */
	@Test
	public void testProcessFromLineZeroPayement(Payement payement1) {
		ProcessPayement.getInstance().processFromLine(payement1);
		assertEquals(2, payements.size());
	}

	/**
	 * Test process from line sum payement.
	 *
	 * @param payement2
	 *            the payement2
	 */
	@Test
	public void testProcessFromLineSumPayement(Payement payement2) {
		ProcessPayement.getInstance().processFromLine(payement2);
		Payement pay = this.findPayement(payement2);
		assertEquals(5000, pay.getCurrencyAmount());
	}

	/**
	 * Test process from line new payement.
	 *
	 * @param payement3
	 *            the payement3
	 */
	@Test
	public void testProcessFromLineNewPayement(Payement payement3) {
		ProcessPayement.getInstance().processFromLine(payement3);
		Payement pay = this.findPayement(payement3);
		assertEquals(50, pay.getCurrencyAmount());
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
	private Payement findPayement(Payement payement) {
		for (Payement pay : payements.values()) {
			if (pay.getCurrencyName().equals(payement.getCurrencyName())) {
				return pay;
			}
		}
		return null;
	}

}
