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
* @author Yvonnick ESNAULT, Gaetan LE BRUN, Thibaut LELIEVRE, Vincent MAHE
*
* le present programme teste le generateur des ondes de differentes formes :
* <ul>
*  <li> onde carree (Square)
*  <li> onde en dents de scie (SawTooth)
*  <li> onde en triangle (Triangle)
*  <li> onde sinusoidale (Sine)
* </ul>
*/
public class LFOTest extends TestCase implements ITestModule {

	// le LFO utilise dans les tests
	/**
	 * @uml.property  name="lfo"
	 * @uml.associationEnd  
	 */
	LFO lfo ;
	
	// pont vers le test Oscillo
	/**
	 * @uml.property  name="mo"
	 * @uml.associationEnd  
	 */
	private ModuleOscillo mo ;
	public Module getTestedModule() { return lfo ; }
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
			methods.add(this.getClass().getMethod("OscilloTest_ComputeSquare")) ;
			methods.add(this.getClass().getMethod("OscilloTest_ComputeSawtooth")) ;
			methods.add(this.getClass().getMethod("OscilloTest_ComputeFactoryRoof")) ;
			methods.add(this.getClass().getMethod("OscilloTest_ComputeTriangle")) ;
			methods.add(this.getClass().getMethod("OscilloTest_ComputeSine")) ;
		} catch(Exception e) {}
		
		return methods ;
	}
	public void setModuleOscillo(ModuleOscillo mo) { this.mo = mo ; }
	
	protected void setUp() throws Exception {
		super.setUp();
		ConcreteFactory.setAFactory(AFactory.getInstance());
		lfo = new LFO();

		lfo.setFreqEch(44000) ;
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
	}
	
	/*
	 * Test method for 'core.modules.LFO.compute()'
	 */
	public final void testCompute() {
		// Rien à faire ici, le test s'effectue à l'aide de TestCompute
	}
	
	public final void OscilloTest_ComputeSquare() {
		// test du generateur d'ondes carrees
		lfo.getContinuousParameter(LFO.PARAM_PITCH)
			.setCurrentValue(new ParameterValue(4.40)) ;
		lfo.getDiscreteParameter(LFO.PARAM_SHAPE).setCurrentValue(
				new ParameterValue(LFO.PVALUE_SHAPE_SQUARE)
			);
		
	}
	public final void OscilloTest_ComputeSawtooth() {
		// test du generateur d'ondes carrees
		lfo.getContinuousParameter(LFO.PARAM_PITCH)
			.setCurrentValue(new ParameterValue(4.40)) ;
		lfo.getDiscreteParameter(LFO.PARAM_SHAPE).setCurrentValue(
				new ParameterValue(LFO.PVALUE_SHAPE_SAWTOOTH)
			);
		
	}
	public final void OscilloTest_ComputeFactoryRoof() {
		// test du generateur d'ondes carrees
		lfo.getContinuousParameter(LFO.PARAM_PITCH)
			.setCurrentValue(new ParameterValue(4.40)) ;
		lfo.getDiscreteParameter(LFO.PARAM_SHAPE).setCurrentValue(
				new ParameterValue(LFO.PVALUE_SHAPE_FACTORYROOF)
			);
		
	}
	public final void OscilloTest_ComputeTriangle() {
		// test du generateur d'ondes carrees
		lfo.getContinuousParameter(LFO.PARAM_PITCH)
			.setCurrentValue(new ParameterValue(4.40)) ;
		lfo.getDiscreteParameter(LFO.PARAM_SHAPE).setCurrentValue(
				new ParameterValue(LFO.PVALUE_SHAPE_TRIANGLE)
			);
		
	}
	public final void OscilloTest_ComputeSine() {
		// test du generateur d'ondes carrees
		lfo.getContinuousParameter(LFO.PARAM_PITCH)
			.setCurrentValue(new ParameterValue(4.40)) ;
		lfo.getDiscreteParameter(LFO.PARAM_SHAPE).setCurrentValue(
				new ParameterValue(LFO.PVALUE_SHAPE_SINE)
			);
		
	}

	/*
	 * Test method for 'core.modules.LFO.reset()'
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
			mo.compute();
		}
		catch(SyntheCoreException sce) {
			System.err.println(sce.getMessage());
		}
	}

}
