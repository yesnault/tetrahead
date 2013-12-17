package tests;

import gui.ConcreteFactory;

import javax.sound.sampled.LineUnavailableException;

import core.*;
import core.modules.*;
import junit.framework.TestCase;

public class OUTDirectTest extends TestCase {

	/**
	 * @uml.property name="vco"
	 * @uml.associationEnd
	 */
	public VCO vco;

	/**
	 * @uml.property name="out"
	 * @uml.associationEnd
	 */
	public OUTDirect out;

	/*
	 * Test method for 'core.modules.OUT.compute()'
	 */
	public void testCompute() {

	}

	/*
	 * Test method for 'core.modules.OUT.reset()'
	 */
	public void testReset() {

	}

	/**
	 * Test method for 'core.modules.OUT.OUT(AFactory)'
	 */
	public void testOUT() {
	}
	
	public static void main(String[] args) {
//		lanceTest(new ParameterValue(VCO.PVALUE_SHAPE_SINE));
//		lanceTest(new ParameterValue(VCO.PVALUE_SHAPE_SQUARE));
//		lanceTest(new ParameterValue(VCO.PVALUE_SHAPE_TRIANGLE));
//		lanceTest(new ParameterValue(VCO.PVALUE_SHAPE_SAWTOOTH));
//		lanceTest(new ParameterValue(VCO.PVALUE_SHAPE_FACTORYROOF));
		ConcreteFactory.setAFactory(AFactory.getInstance());

		OUTDirect out = new OUTDirect();
		VCO vco = new VCO();

		vco.getContinuousParameter(VCO.PARAM_PITCH).setCurrentValue(
				new ParameterValue(440));
		try {
			vco.setFreqEch(44100);
			out.setFreqEch(44100);
			vco.getDiscreteParameter(VCO.PARAM_SHAPE).setCurrentValue(
					new ParameterValue(VCO.PVALUE_SHAPE_SQUARE));

		} catch (SyntheCoreException e) {
			e.printStackTrace();
		}
		/* connexion des 2 modules */
		vco.getPortOut().addPortIn(out.getPortIn(OUTDirect.PORT_SIGNAL_IN));

		/* allumage du OUT */
		out.getDiscreteParameter(OUTDirect.PARAM_STATE).setCurrentValue(
				new ParameterValue(OUTDirect.PARAM_STATE_ON));

		for (int i = 0; i < 1000000; i++) {
			try {
				vco.compute();
				try {
					out.compute();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
			} catch (SyntheCoreException e) {
				e.printStackTrace();
			}

		}
		
		System.err.println("On tourne le bouton  OFF");
		out.getDiscreteParameter(OUTDirect.PARAM_STATE).setCurrentValue(
				new ParameterValue(OUTDirect.PARAM_STATE_OFF));
		try {
			System.err.println("Derniere execution du OUT");
			try {
				out.compute();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SyntheCoreException e) {
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println("termine");
		
	}
}
