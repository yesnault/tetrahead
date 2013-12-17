package tests;

import core.*;
import core.modules.*;

import gui.ConcreteFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import javax.sound.sampled.LineUnavailableException;

import junit.framework.TestCase;

public class ADSRTriggerTest extends TestCase implements ITestModule {

	// les modules utilise dans les tests
	/**
	 * @uml.property  name="vco"
	 * @uml.associationEnd  
	 */
	VCO vco ;
	LFO lfoGate ;
	LFO lfoTrigger ;
	
	/**
	 * @uml.property  name="adhsr"
	 * @uml.associationEnd  
	 */
	ADSRTrigger adsrt ;
	
	// pont vers le test Oscillo
	
	/**
	 * @uml.property  name="mo"
	 * @uml.associationEnd  
	 */
	private ModuleOscillo mo ;
	private OUTDirect out;
	public Module getTestedModule() { return adsrt ; }
	public void init() { 
		try  {
			setUp() ;
		} catch(Exception e) {
			e.printStackTrace() ;
		}
	}
	public Collection<Method> getTestedMethods() {
		Collection<Method> methods = new ArrayList<Method>() ;
		try {
			// Ajouter les methodes a tester
			methods.add(this.getClass().getMethod(
					"OscilloTest_Compute")) ;
		} catch(Exception e) {}
		
		return methods ;
	}
	public void setModuleOscillo(ModuleOscillo mo) { this.mo = mo ; }

	protected void setUp() throws Exception {
		super.setUp();
		ConcreteFactory.setAFactory(AFactory.getInstance());
		
		vco = new VCO();
		vco.setFreqEch(44000) ;
		
		lfoGate = new LFO();
		lfoGate.setFreqEch(44000) ;
		
		lfoTrigger = new LFO();
		lfoTrigger.setFreqEch(44000) ;
		
		adsrt = new ADSRTrigger();
		adsrt.setFreqEch(44000) ;
		
		/* On branche le VCO a l'ADHSR */
		vco.getPortOut().addPortIn(adsrt.getPortIn(ADSRTrigger.PORT_SIGNAL_IN));
		vco.getDiscreteParameter(VCO.PARAM_SHAPE)
			.setCurrentValue(new ParameterValue(VCO.PVALUE_SHAPE_SINE));
		
		vco.getContinuousParameter(VCO.PARAM_PITCH).setCurrentValue(
				new ParameterValue(1000));
		
		/* On branche le 1er LFO au Gate l'ADHSR */
		lfoGate.getPortOut().addPortIn(adsrt.getPortIn(ADSRTrigger.PORT_GATE_IN));
		lfoGate.getDiscreteParameter(LFO.PARAM_SHAPE)
			.setCurrentValue(new ParameterValue(VCO.PVALUE_SHAPE_SQUARE));
		lfoGate.getContinuousParameter(LFO.PARAM_PITCH).setCurrentValue(
				new ParameterValue(100.0));
		
		/* On branche le 2nd LFO au Trigger l'ADHSR */
		lfoTrigger.getPortOut().addPortIn(adsrt.getPortIn(ADSRTrigger.PORT_TRIGGER_IN));
		lfoTrigger.getDiscreteParameter(LFO.PARAM_SHAPE)
			.setCurrentValue(new ParameterValue(VCO.PVALUE_SHAPE_SQUARE));
		lfoTrigger.getContinuousParameter(LFO.PARAM_PITCH).setCurrentValue(
				new ParameterValue(70.0));
		
		adsrt.getContinuousParameter(ADSRTrigger.PARAM_ATTAK).setCurrentValue(
				new ParameterValue(1));
		adsrt.getContinuousParameter(ADSRTrigger.PARAM_DECAY).setCurrentValue(
				new ParameterValue(2));
		adsrt.getContinuousParameter(ADSRTrigger.PARAM_SUSTAIN).setCurrentValue(
				new ParameterValue(0.5));
		adsrt.getContinuousParameter(ADSRTrigger.PARAM_RELEASE).setCurrentValue(
				new ParameterValue(4));
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'core.modules.VCF2.compute()'
	 */
	public void testCompute() {
		lanceTest(new ParameterValue(LFO.PVALUE_SHAPE_SINE));
		lanceTest(new ParameterValue(LFO.PVALUE_SHAPE_SQUARE));
		lanceTest(new ParameterValue(LFO.PVALUE_SHAPE_TRIANGLE));
		lanceTest(new ParameterValue(LFO.PVALUE_SHAPE_SAWTOOTH));
		lanceTest(new ParameterValue(LFO.PVALUE_SHAPE_FACTORYROOF));
	}
	
	private void lanceTest(ParameterValue forme) {

		out = new OUTDirect();

		vco.getContinuousParameter(VCO.PARAM_PITCH).setCurrentValue(
				new ParameterValue(280));
		try {
			vco.setFreqEch(44000);
			out.setFreqEch(44000);
			vco.getDiscreteParameter(VCO.PARAM_SHAPE).setCurrentValue(
					forme);

		} catch (SyntheCoreException e) {
			e.printStackTrace();
		}
		/* connexion des 2 modules */
		adsrt.getPortOut().addPortIn(out.getPortIn(OUTDirect.PORT_SIGNAL_IN));

		assertTrue(" Pitch INIT : ", vco
				.getContinuousParameter(LFO.PARAM_PITCH).getCurrentValue()
				.toInt() == 280);
		
		/* allumage du OUT */
		out.getDiscreteParameter(OUTDirect.PARAM_STATE).setCurrentValue(
				new ParameterValue(OUTDirect.PARAM_STATE_ON));

		for (int i = 0; i < 160000; i++) {
			try {
				vco.compute();
				adsrt.compute();
				try {
					out.compute();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
			} catch (SyntheCoreException e) {
				e.printStackTrace();
			}

		}
	}
	
	public void OscilloTest_Compute() {
		/* On branche l'oscillo à la sortie de l'ADHSR */
		adsrt.getPortOut().addPortIn(mo.getPortIn(ModuleOscillo.PORT_IN)) ;
	

	}

	/*
	 * Test method for 'core.modules.VCF.reset()'
	 */
	public void testReset() {

	}

	/*
	 * Test method for 'core.modules.VCF.VCF()'
	 */
	public void testVCF() {

	}

	/*
	 * Test method for 'core.modules.Module.setFreqEch(int)'
	 */
	public void testSetFreqEch() {

	}

	/*
	 * Test method for 'core.modules.Module.getFreqEch()'
	 */
	public void testGetFreqEch() {

	}

	/*
	 * Test method for 'core.modules.Module.addPortsIn(String, IPortIn)'
	 */
	public void testAddPortsIn() {

	}

	/*
	 * Test method for 'core.modules.Module.getPortIn(String)'
	 */
	public void testGetPortIn() {

	}

	/*
	 * Test method for 'core.modules.Module.removePortsIn(IPortIn)'
	 */
	public void testRemovePortsIn() {

	}

	/*
	 * Test method for 'core.modules.Module.setPortOut(IPortOut)'
	 */
	public void testSetPortOut() {

	}

	/*
	 * Test method for 'core.modules.Module.getPortOut()'
	 */
	public void testGetPortOut() {

	}

	/*
	 * Test method for 'core.modules.Module.addContinuousParameter(String, IContinuousParameter)'
	 */
	public void testAddContinuousParameter() {

	}

	/*
	 * Test method for 'core.modules.Module.removeContinuousParameter(IContinuousParameter)'
	 */
	public void testRemoveContinuousParameter() {

	}

	/*
	 * Test method for 'core.modules.Module.getContinuousParameter(String)'
	 */
	public void testGetContinuousParameter() {

	}

	/*
	 * Test method for 'core.modules.Module.addDiscreteParameter(String, IDiscreteParameter)'
	 */
	public void testAddDiscreteParameter() {

	}

	/*
	 * Test method for 'core.modules.Module.removeDiscreteParameter(IDiscreteParameter)'
	 */
	public void testRemoveDiscreteParameter() {

	}

	/*
	 * Test method for 'core.modules.Module.getDiscreteParameter(String)'
	 */
	public void testGetDiscreteParameter() {

	}

	/*
	 * Test method for 'core.modules.Module.getPortsInTable()'
	 */
	public void testGetPortsInTable() {

	}

	/*
	 * Test method for 'core.modules.Module.getDiscreteParameterTable()'
	 */
	public void testGetDiscreteParameterTable() {

	}

	/*
	 * Test method for 'core.modules.Module.getContinuousParameterTable()'
	 */
	public void testGetContinuousParameterTable() {

	}

	public void execute() {
		try {
			vco.compute();
			lfoGate.compute();
			lfoTrigger.compute();
			adsrt.compute();
			mo.compute();
		}
		catch(SyntheCoreException sce) {
			System.err.println(sce.getMessage());
		}
	}
	
}
