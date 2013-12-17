package tests;

import core.ContinuousParameter;
import core.ParameterValue;
import junit.framework.TestCase;

public class ContinuousParameterTest extends TestCase {
	ContinuousParameter cp;
	
	public ContinuousParameterTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		cp = new ContinuousParameter("resonnance", "hz", new ParameterValue(50), new ParameterValue(100));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'core.ContinuousParameter.setRate(double)'
	 */
	public void testSetRate() {
		cp.setRate(0.25);
		assertTrue(""+cp.getCurrentValue().toDouble(),cp.getCurrentValue().toDouble() == 62.5);
		cp.setRate(-1);
		assertTrue(""+cp.getCurrentValue().toDouble(),cp.getCurrentValue().toDouble() == 50.0);
		cp.setRate(2);
		assertTrue(""+cp.getCurrentValue().toDouble(),cp.getCurrentValue().toDouble() == 100.0);
	}

	/*
	 * Test method for 'core.ContinuousParameter.reset()'
	 */
	public void testReset() {
		cp.setRate(0.25);
		assertTrue(""+cp.getCurrentValue().toDouble(),cp.getCurrentValue().toDouble() == 62.5);
		cp.reset();
		assertTrue(""+cp.getCurrentValue().toDouble(),cp.getCurrentValue().toDouble() ==50.0);
		assertTrue(""+cp.getRate(),cp.getRate() == 0.0);
	}

	/*
	 * Test method for 'core.ContinuousParameter.setDoubleValue(double)'
	 */
	public void testSetDoubleValue() {
		cp.setDoubleValue(10.0);
		assertTrue(""+cp.getCurrentValue().toDouble(),cp.getCurrentValue().toDouble()==50.0);
		cp.setDoubleValue(150.0);
		assertTrue(""+cp.getCurrentValue().toDouble(),cp.getCurrentValue().toDouble()==100.0);
		cp.setDoubleValue(70.0);
		assertTrue(""+cp.getCurrentValue().toDouble(),cp.getCurrentValue().toDouble()==70.0);
	}

}
