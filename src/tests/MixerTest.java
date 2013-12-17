package tests;

import gui.ConcreteFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;


import core.*;
import core.modules.*;
import junit.framework.TestCase;

public class MixerTest extends TestCase implements ITestModule {

	// les modules utilise dans les tests
	LFO vco1, vco2, vco3 ;
	Mixer mixer ;
	
	// pont vers le test Oscillo
	
	private ModuleOscillo mo ;
	public Module getTestedModule() { return mixer ; }
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
					"OscilloTest_Compute2Ports")) ;
			methods.add(this.getClass().getMethod(
					"OscilloTest_Compute3Ports")) ;
		} catch(Exception e) {}
		
		return methods ;
	}
	public void setModuleOscillo(ModuleOscillo mo) { this.mo = mo ; }

	protected void setUp() throws Exception {
		super.setUp();
		ConcreteFactory.setAFactory(AFactory.getInstance());
		
		// on peut avoir jusqu'a 4 entrees
		vco1 = new LFO();
		vco1.setFreqEch(44000) ;
		
		vco2 = new LFO();
		vco2.setFreqEch(44000) ;
		
		vco3 = new LFO();
		vco3.setFreqEch(44000) ;
		
		mixer = new Mixer();
		mixer.setFreqEch(44000) ;
		
		/* On branche l'oscillo à la sortie de l'ADHSR */
		mixer.getPortOut().addPortIn(mo.getPortIn(ModuleOscillo.PORT_IN)) ;
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'core.modules.VCF2.compute()'
	 */
	public void testCompute() {

	}
	public void OscilloTest_Compute2Ports() {
		/* On branche le 1er VCO au mixer */
		vco1.getPortOut().addPortIn(mixer.getPortIn(Mixer.PORT_1));
		vco1.getDiscreteParameter(LFO.PARAM_SHAPE)
			.setCurrentValue(new ParameterValue(LFO.PVALUE_SHAPE_SINE));
		
		/* On branche le 2e VCO au mixer */
		vco2.getPortOut().addPortIn(mixer.getPortIn(Mixer.PORT_2));
		vco2.getDiscreteParameter(LFO.PARAM_SHAPE)
			.setCurrentValue(new ParameterValue(LFO.PVALUE_SHAPE_SINE));
		
		vco1.getContinuousParameter(LFO.PARAM_PITCH).setCurrentValue(
				new ParameterValue(500));
		vco2.getContinuousParameter(LFO.PARAM_PITCH).setCurrentValue(
				new ParameterValue(1000));
		
		mixer.getContinuousParameter(Mixer.VOLUME_PORT_1).setCurrentValue(
				new ParameterValue(0.999));
		mixer.getContinuousParameter(Mixer.VOLUME_PORT_2).setCurrentValue(
				new ParameterValue(0.5));
	}
	public void OscilloTest_Compute3Ports() {
		/* On branche le 1er VCO au mixer */
		vco1.getPortOut().addPortIn(mixer.getPortIn(Mixer.PORT_1));
		vco1.getDiscreteParameter(LFO.PARAM_SHAPE)
			.setCurrentValue(new ParameterValue(LFO.PVALUE_SHAPE_SINE));
		
		/* On branche le 2e VCO au mixer */
		vco2.getPortOut().addPortIn(mixer.getPortIn(Mixer.PORT_2));
		vco2.getDiscreteParameter(LFO.PARAM_SHAPE)
			.setCurrentValue(new ParameterValue(LFO.PVALUE_SHAPE_SINE));
		
		/* On branche le 3e VCO au mixer */
		vco3.getPortOut().addPortIn(mixer.getPortIn(Mixer.PORT_3));
		vco3.getDiscreteParameter(LFO.PARAM_SHAPE)
			.setCurrentValue(new ParameterValue(LFO.PVALUE_SHAPE_SINE));
		
		vco1.getContinuousParameter(LFO.PARAM_PITCH).setCurrentValue(
				new ParameterValue(400));
		vco2.getContinuousParameter(LFO.PARAM_PITCH).setCurrentValue(
				new ParameterValue(800));
		vco3.getContinuousParameter(LFO.PARAM_PITCH).setCurrentValue(
				new ParameterValue(1600));
		
		mixer.getContinuousParameter(Mixer.VOLUME_PORT_1).setCurrentValue(
				new ParameterValue(1.));
		mixer.getContinuousParameter(Mixer.VOLUME_PORT_2).setCurrentValue(
				new ParameterValue(.5));
		mixer.getContinuousParameter(Mixer.VOLUME_PORT_3).setCurrentValue(
				new ParameterValue(.5));
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
			vco1.compute();
			vco2.compute();
			vco3.compute();
			mixer.compute();
			mo.compute();
		}
		catch(SyntheCoreException sce) {
			System.err.println(sce.getMessage());
		}
	}
}
