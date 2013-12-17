package tests;

import gui.ConcreteFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;


import core.*;
import core.modules.*;
import junit.framework.TestCase;

public class VCATest extends TestCase implements ITestModule {

	// les modules utilise dans les tests
	LFO lfo ;
	VCO vco ;
	VCA vca ;
	
	// pont vers le test Oscillo
	
	private ModuleOscillo mo ;
	public Module getTestedModule() { return vca ; }
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
					"OscilloTest_Compute50p100")) ;
			methods.add(this.getClass().getMethod(
					"OscilloTest_Compute120p100")) ;
			methods.add(this.getClass().getMethod(
					"OscilloTest_ComputeGainIn_LFO_30Hz_Gain100")) ;
		} catch(Exception e) {}
		
		return methods ;
	}
	public void setModuleOscillo(ModuleOscillo mo) { this.mo = mo ; }

	protected void setUp() throws Exception {
		super.setUp();
		ConcreteFactory.setAFactory(AFactory.getInstance());
		
		// on peut avoir jusqu'a 4 entrees
		lfo = new LFO();
		lfo.setFreqEch(44000) ;
		
		vco = new VCO();
		vco.setFreqEch(44000) ;
		
		vca = new VCA();
		vca.setFreqEch(44000) ;
		
		/* On branche le VCO à l'entrée du VCA */
		vco.getPortOut().addPortIn(vca.getPortIn(VCA.PORT_SIGNAL_IN)) ;
		
		/* On teste sur le LA : 440 Hz */
		vco.getContinuousParameter(LFO.PARAM_PITCH).setCurrentValue(new ParameterValue(440));
		
		/* On branche l'oscillo à la sortie du VCA */
		vca.getPortOut().addPortIn(mo.getPortIn(ModuleOscillo.PORT_IN)) ;
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'core.modules.VCF2.compute()'
	 */
	public void testCompute() {

	}
	public void OscilloTest_Compute50p100() {
		vco.getDiscreteParameter(LFO.PARAM_SHAPE)
			.setCurrentValue(new ParameterValue(LFO.PVALUE_SHAPE_SINE));
		
		vca.getContinuousParameter(VCA.PARAM_GAIN).setCurrentValue(
				new ParameterValue(50));
	}
	public void OscilloTest_Compute120p100() {
		vco.getDiscreteParameter(LFO.PARAM_SHAPE)
			.setCurrentValue(new ParameterValue(LFO.PVALUE_SHAPE_SINE));
		
		vca.getContinuousParameter(VCA.PARAM_GAIN).setCurrentValue(
				new ParameterValue(120));
	}
	public void OscilloTest_ComputeGainIn_LFO_30Hz_Gain100() {
		/* On branche le LFO à l'entrée GainIn du VCA */
		lfo.getPortOut().addPortIn(vca.getPortIn(VCA.PORT_GAIN_IN));
		lfo.getContinuousParameter(LFO.PARAM_PITCH).setCurrentValue(
				new ParameterValue(10.));
		lfo.getDiscreteParameter(LFO.PARAM_SHAPE)
			.setCurrentValue(new ParameterValue(LFO.PVALUE_SHAPE_SINE));
		
		vco.getDiscreteParameter(LFO.PARAM_SHAPE)
			.setCurrentValue(new ParameterValue(LFO.PVALUE_SHAPE_SINE));
		
		vca.getContinuousParameter(VCA.PARAM_GAIN).setCurrentValue(
				new ParameterValue(100));
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
			lfo.compute();
			vca.compute();
			mo.compute();
		}
		catch(SyntheCoreException sce) {
			System.err.println(sce.getMessage());
		}
	}
}
