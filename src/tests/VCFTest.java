package tests;

import gui.ConcreteFactory;

import java.lang.reflect.Method;
import java.util.*;
import core.*;
import core.modules.*;
import junit.framework.*;

public class VCFTest extends TestCase implements ITestModule {

	// les modules utilise dans les tests
	VCO vco ;
	LFO lfo;
	VCF vcf ;
	
	// pont vers le test Oscillo
	
	private ModuleOscillo mo ;
	public Module getTestedModule() { return vcf ; }
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
					"OscilloTest_ComputeGainLFO_LowPass")) ;
			methods.add(this.getClass().getMethod(
					"OscilloTest_ComputeGainLFO_HighPass")) ;
		} catch(Exception e) {}
		
		return methods ;
	}
	public void setModuleOscillo(ModuleOscillo mo) { this.mo = mo ; }
	
	protected void setUp() throws Exception {
		super.setUp();
		ConcreteFactory.setAFactory(AFactory.getInstance());
		vco = new VCO();
		vco.setFreqEch(44000) ;
		lfo = new LFO();
		lfo.setFreqEch(44000) ;
		vcf = new VCF();
		vcf.setFreqEch(44000) ;
		
		/* On branche le VCO au VCF */
		vco.getPortOut().addPortIn(vcf.getPortIn(VCF.PORT_SIGNAL_IN));
		
		/* On branche le LFO au gain du VCF */
		lfo.getPortOut().addPortIn(vcf.getPortIn(VCFPar.PORT_CUT_OFF_IN));
		
		/* la fréquence de coupure varie avec le signal du LFO */
		lfo.getContinuousParameter(LFO.PARAM_PITCH).setCurrentValue(
				new ParameterValue(20));
		/* On module avec un signal doux */
		lfo.getDiscreteParameter(LFO.PARAM_SHAPE).setCurrentValue(
				new ParameterValue(LFO.PVALUE_SHAPE_SINE)
			);
		
		/* On branche l'oscillo à la sortie du VCF */
		vcf.getPortOut().addPortIn(mo.getPortIn(ModuleOscillo.PORT_IN)) ;
		
		/* On passe un signal carré */
		vco.getDiscreteParameter(VCO.PARAM_SHAPE).setCurrentValue(
				new ParameterValue(VCO.PVALUE_SHAPE_SQUARE)
			);
		
		vco.getContinuousParameter(VCO.PARAM_PITCH)
			.setCurrentValue(new ParameterValue(440)) ;

		vcf.getContinuousParameter(VCF.PARAM_RESONANCE).setCurrentValue(
				new ParameterValue(0.)
			);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'core.modules.VCF.compute()'
	 */
	public void testCompute() {
		
	}
	
	public void OscilloTest_ComputeGainLFO_LowPass() {
		vcf.getDiscreteParameter(VCF.PARAM_BY_PASS).setCurrentValue(
				new ParameterValue(VCF.PVALUE_BYPASS_LOWPASS)	
			);
	}
	
	public void OscilloTest_ComputeGainLFO_HighPass() {
		vcf.getDiscreteParameter(VCF.PARAM_BY_PASS).setCurrentValue(
			new ParameterValue(VCF.PVALUE_BYPASS_HIGHPASS)	
		);
	}

	/*
	 * Test method for 'core.modules.VCF.reset()'
	 */
	public void testReset() {

	}

	/*
	 * Test method for 'core.modules.VCF.VCF(IFactory)'
	 */
	public void testVCF() {
	
	}

	public void execute() {
		try {
			vco.compute();
			lfo.compute();
			vcf.compute();
			mo.compute();
		}
		catch(SyntheCoreException sce) {
			System.err.println(sce.getMessage());
		}
	}

}