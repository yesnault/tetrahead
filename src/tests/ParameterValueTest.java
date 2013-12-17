package tests;

import core.ParameterValue;
import junit.framework.TestCase;

public class ParameterValueTest extends TestCase {

	/*
	 * Test method for 'core.ParameterValue.toString()'
	 */
	public void testToString() {
		ParameterValue pv = new ParameterValue("texte");
		assertTrue(pv.toString().equals("texte"));
		// un acces a toInt() doit renvoyer une NumberFormatException
		try {
			pv.toInt();
			fail();
		} catch (NumberFormatException nfe) {
		}
	}

	/*
	 * Test method for 'core.ParameterValue.toInt()'
	 */
	public void testToInt() {
		ParameterValue pv = new ParameterValue(10);
		assertTrue(pv.toInt() == 10);
		assertTrue(Integer.parseInt(pv.toString()) == 10);
	}

	/*
	 * Test method for 'core.ParameterValue.toDouble()'
	 */
	public void testToDouble() {
		ParameterValue pv = new ParameterValue(10.0);
		assertTrue(pv.toDouble() == 10.0);
		assertTrue(Double.parseDouble(pv.toString()) == 10.0);
		try {
			pv.toInt();
			fail("Il y aurait du avoir une exception");
		} catch (Exception e) {
		}
	}

	/*
	 * Test method for 'core.ParameterValue.setDoubleValue(double)'
	 */
	public void testSetDoubleValue() {
		ParameterValue pv = new ParameterValue(0.0);
		pv.setDoubleValue(10.0);
		assertTrue(pv.toDouble() == 10.0);
		try {
			assertTrue(Double.parseDouble(pv.toString()) == 10.0);
		} catch (Exception e) {
			fail("Pas d'exception normalement");
		}
	}

	/*
	 * Test method for 'core.ParameterValue.setIntValue(int)'
	 */
	public void testSetIntValue() {
		ParameterValue pv = new ParameterValue(0);
		pv.setIntValue(10);
		assertTrue(pv.toInt() == 10);
		try {
			assertTrue(Integer.parseInt(pv.toString()) == 10);
		} catch (Exception e) {
			fail("Pas d'exception normalement");
		}
	}

}
