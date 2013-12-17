package tests;

import core.AFactory;
import core.IDiscreteParameter;
import core.IFactory;
import core.ParameterValue;
import core.modules.LFO;
import gui.ConcreteFactory;

import java.util.ArrayList;
import java.util.Collection;
import junit.framework.TestCase;

public class DiscreteParameterTest extends TestCase {
	
	/**
	 * @uml.property  name="dp"
	 * @uml.associationEnd  
	 */
	IDiscreteParameter dp;
	Collection<ParameterValue> pv = new ArrayList<ParameterValue>();
	
	public DiscreteParameterTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		ConcreteFactory.setAFactory(AFactory.getInstance());
		IFactory usine = ConcreteFactory.getFactory();
		pv.add(new ParameterValue(LFO.PVALUE_SHAPE_SQUARE));
		pv.add(new ParameterValue(LFO.PVALUE_SHAPE_SAWTOOTH));
		pv.add(new ParameterValue(LFO.PVALUE_SHAPE_TRIANGLE));
		pv.add(new ParameterValue(LFO.PVALUE_SHAPE_SINE));
		dp = usine.newDiscreteParameter(LFO.PARAM_SHAPE,"", pv);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'core.DiscreteParameter.getNumberOfValue()'
	 */
	public void testGetNumberOfValue() {
		assertTrue("Nb values = "+dp.getNumberOfValue(),
				dp.getNumberOfValue()==pv.size());
	}

	/*
	 * Test method for 'core.DiscreteParameter.next()'
	 */
	public void testNext() {
		assertTrue(dp.getCurrentValue().toString()==LFO.PVALUE_SHAPE_SQUARE);
		dp.next();
		assertTrue(dp.getCurrentValue().toString()==LFO.PVALUE_SHAPE_SAWTOOTH);
		dp.next();
		assertTrue(dp.getCurrentValue().toString()==LFO.PVALUE_SHAPE_TRIANGLE);
		dp.next();
		assertTrue(dp.getCurrentValue().toString()==LFO.PVALUE_SHAPE_SINE);
		dp.next();
		assertTrue(dp.getCurrentValue().toString()==LFO.PVALUE_SHAPE_SINE);
	}

	/*
	 * Test method for 'core.DiscreteParameter.previous()'
	 */
	public void testPrevious() {
		dp.previous();
		assertTrue(dp.getCurrentValue().toString()==LFO.PVALUE_SHAPE_SQUARE);
		dp.next();
		assertTrue(dp.getCurrentValue().toString()==LFO.PVALUE_SHAPE_SAWTOOTH);
		dp.next();
		dp.previous();
		assertTrue(dp.getCurrentValue().toString()==LFO.PVALUE_SHAPE_SAWTOOTH);
	}

	/*
	 * Test method for 'core.DiscreteParameter.getStringValues()'
	 */
	public void testGetStringValues() {

	}

	/*
	 * Test method for 'core.DiscreteParameter.DiscreteParameter(String, Collection<ParameterValue>)'
	 */
	public void testDiscreteParameter() {
		
	}

	public void testReset() {
		dp.next();
		dp.next();
		dp.reset();
		assertTrue(dp.getCurrentValue().toString()==LFO.PVALUE_SHAPE_SQUARE);
		dp.next();
		assertTrue(dp.getCurrentValue().toString()==LFO.PVALUE_SHAPE_SAWTOOTH);
	}
}
