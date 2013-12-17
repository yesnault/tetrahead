package tests;

import core.*;
import core.modules.*;
import gui.ConcreteFactory;

import java.lang.reflect.Method;
import java.util.*;
import junit.framework.*;

/**
* Voltage Controlled Oscillator
* 
 * @author Yvonnick Esnault, Gaetan Le Brun, Thibaut Lelièvre, Vincent Mahé
 * 
 *  Ce programme est un logiciel libre distribue sous licence GNU/GPL. 
 *  Pour plus de details voir le fichier COPYING.txt.
*
* le present programme teste le generateur des ondes de differentes formes :
* <ul>
*  <li> onde carree (Square)
*  <li> onde en dents de scie (SawTooth)
*  <li> onde en triangle (Triangle)
*  <li> onde sinusoidale (Sine)
* </ul>
*/
public class VCOPitchInTest extends TestCase implements ITestModule {

	// le VCO utilise dans les tests
	/**
	 * @uml.property  name="vco"
	 * @uml.associationEnd  
	 */
	VCO vco ;
	LFO lfo;
	
	// pont vers le test Oscillo
	/**
	 * @uml.property  name="mo"
	 * @uml.associationEnd  
	 */
	private ModuleOscillo mo ;
	public Module getTestedModule() { return vco ; }
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
			methods.add(this.getClass().getMethod("OscilloTest_PitchInComputeSquare")) ;
			methods.add(this.getClass().getMethod("OscilloTest_PitchInComputeSawtooth")) ;
			methods.add(this.getClass().getMethod("OscilloTest_PitchInComputeFactoryRoof")) ;
			methods.add(this.getClass().getMethod("OscilloTest_PitchInComputeTriangle")) ;
			methods.add(this.getClass().getMethod("OscilloTest_PitchInComputeSine")) ;
		} catch(Exception e) {}
		
		return methods ;
	}
	public void setModuleOscillo(ModuleOscillo mo) { this.mo = mo ; }
	
	protected void setUp() throws Exception {
		super.setUp();
		ConcreteFactory.setAFactory(AFactory.getInstance());
		vco = new VCO();
		vco.setFreqEch(44000);
		
		lfo = new LFO();
		lfo.setFreqEch(44000);
		
		lfo.getPortOut().addPortIn(vco.getPortIn(VCO.PORT_PITCH_IN));
		lfo.getContinuousParameter(LFO.PARAM_PITCH).setCurrentValue(
				new ParameterValue((5.)));
		lfo.getDiscreteParameter(LFO.PARAM_SHAPE).setCurrentValue(
				new ParameterValue(LFO.PVALUE_SHAPE_SINE));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
	}
	
	/*
	 * Test method for 'core.modules.VCO.compute()'
	 */
	public final void testCompute() {
		// Rien à faire ici, le test s'effectue à l'aide de TestCompute

	}
	public final void OscilloTest_PitchInComputeSquare() {
		// test du generateur d'ondes carrees
		vco.getContinuousParameter(VCO.PARAM_PITCH)
			.setCurrentValue(new ParameterValue(440)) ;
		vco.getDiscreteParameter(VCO.PARAM_SHAPE).setCurrentValue(
				new ParameterValue(VCO.PVALUE_SHAPE_SQUARE)
			);
		
	}
	public final void OscilloTest_PitchInComputeSawtooth() {
		// test du generateur d'ondes carrees
		vco.getContinuousParameter(VCO.PARAM_PITCH)
			.setCurrentValue(new ParameterValue(440)) ;
		vco.getDiscreteParameter(VCO.PARAM_SHAPE).setCurrentValue(
				new ParameterValue(VCO.PVALUE_SHAPE_SAWTOOTH)
			);
		
	}
	public final void OscilloTest_PitchInComputeFactoryRoof() {
		// test du generateur d'ondes carrees
		vco.getContinuousParameter(VCO.PARAM_PITCH)
			.setCurrentValue(new ParameterValue(440)) ;
		vco.getDiscreteParameter(VCO.PARAM_SHAPE).setCurrentValue(
				new ParameterValue(VCO.PVALUE_SHAPE_FACTORYROOF)
			);
		
	}
	public final void OscilloTest_PitchInComputeTriangle() {
		// test du generateur d'ondes carrees
		vco.getContinuousParameter(VCO.PARAM_PITCH)
			.setCurrentValue(new ParameterValue(440)) ;
		vco.getDiscreteParameter(VCO.PARAM_SHAPE).setCurrentValue(
				new ParameterValue(VCO.PVALUE_SHAPE_TRIANGLE)
			);
		
	}
	public final void OscilloTest_PitchInComputeSine() {
		// test du generateur d'ondes carrees
		vco.getContinuousParameter(VCO.PARAM_PITCH)
			.setCurrentValue(new ParameterValue(440)) ;
		vco.getDiscreteParameter(VCO.PARAM_SHAPE).setCurrentValue(
				new ParameterValue(VCO.PVALUE_SHAPE_SINE)
			);
		
	}

	/*
	 * Test method for 'core.modules.VCO.reset()'
	 */
	public final void testReset() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'core.modules.Module.setFreqEch(int)'
	 */
	public final void testSetFreqEch() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'core.modules.Module.addPortsIn(String, PortIn)'
	 */
	public final void testAddPortsIn() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'core.modules.Module.getPortIn(String)'
	 */
	public final void testGetPortIn() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'core.modules.Module.removePortsIn(PortIn)'
	 */
	public final void testRemovePortsIn() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'core.modules.Module.setPortOut(PortOut)'
	 */
	public final void testSetPortOut() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'core.modules.Module.getPortOut()'
	 */
	public final void testGetPortOut() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'core.modules.Module.addContinuousParameter(String, ContinuousParameter)'
	 */
	public final void testAddContinuousParameter() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'core.modules.Module.removeContinuousParameter(ContinuousParameter)'
	 */
	public final void testRemoveContinuousParameter() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'core.modules.Module.getContinuousParameter(String)'
	 */
	public final void testGetContinuousParameter() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'core.modules.Module.addDiscreteParameter(String, DiscreteParameter)'
	 */
	public final void testAddDiscreteParameter() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'core.modules.Module.removeDiscreteParameter(DiscreteParameter)'
	 */
	public final void testRemoveDiscreteParameter() {
		// TODO Auto-generated method stub

	}

	/*
	 * Test method for 'core.modules.Module.getDiscreteParameter(String)'
	 */
	public final void testGetDiscreteParameter() {
		// TODO Auto-generated method stub

	}
	
	public void execute() {
		try {
			lfo.compute();
			vco.compute();
			mo.compute();
		}
		catch(SyntheCoreException sce) {
			System.err.println(sce.getMessage());
		}
	}

}
